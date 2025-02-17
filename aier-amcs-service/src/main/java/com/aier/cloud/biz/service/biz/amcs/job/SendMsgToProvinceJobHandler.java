package com.aier.cloud.biz.service.biz.amcs.job;

import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import com.aier.basic.weixin.api.AwxService;
import com.aier.basic.weixin.api.CpMesg;
import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.AuthorizeCondition;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.biz.service.biz.sys.feign.AuthorizeService;
import com.aier.cloud.biz.service.biz.sys.feign.InstitutionService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeBasicInfoService;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;

/**
 * 
 * 省区定时消息
 *
 */
@JobHandler(value="com.aier.cloud.amcs.SendMsgToProvinceJobHandler")
@Component
public class SendMsgToProvinceJobHandler extends IJobHandler {

	@Value("${amcs.adverse.provinceRoles}")
    private String provinceRoleIds;
	
	@Autowired
    private AuthorizeService authorizeService;
	
	@Autowired
    private InstitutionService institutionService;
	
	@Autowired
	private AeBasicInfoService aeBasicInfoService;
	
	private static final Logger logger = LoggerFactory.getLogger(SendMsgToProvinceJobHandler.class);
	
	@Override
    public ReturnT<String> execute(String param) throws Exception {
		
		try {
			logger.info("===========进入省区消息提醒============");
			AeInfoCondition aeCond = new AeInfoCondition();
			//TODO 等待下期发版
			List<Long> idList = Arrays.stream(provinceRoleIds.split(",")).map(Long::parseLong).collect(Collectors.toList());
			//List<Long> idList = Arrays.asList(1608361653054500866L);
			AuthorizeCondition authorizeCondition = new AuthorizeCondition();
			List<Map<String, Object>> users = Lists.newArrayList();
				
			//获取所有省区
			List<Institution> instList = institutionService.getListByInstType(InstEnum.PROVINCE);
			Map<String, List<Long>> provinceInst = Maps.newHashMap();
			//获取省区下所有医院
			for(Institution curInst: instList) {
				List<Long> instIds = allHospFromParent(curInst.getId());
				provinceInst.put(curInst.getName(), instIds);
			}
					
			for(Long curId: idList) {
				authorizeCondition.setRoleId(curId);
				authorizeCondition.setRows(1000);
				users.addAll(authorizeService.getListByRoleAuthorize(authorizeCondition).getRows());
			}
			logger.info("============开始按人员推送==============");
			for(Map<String, Object> curUser: users) {
				CpMesg cpMesg = new CpMesg();
		        cpMesg.setUser(curUser.get("code").toString());
		        //获取当前用户省区下的机构ID
		        List<Long> instIds = provinceInst.get(curUser.get("instname").toString());
		        if(ObjectUtils.isEmpty(instIds)) continue;
		        //获取当前医院ID下的所有医院审核通过的不良事件
		        aeCond.setHospList(instIds);
		        aeCond.setNode(NodeEnum.HOSPITAL_REVIEWS.getSeq());
				aeCond.setIsProvinceReview(true);
		        Integer eventCount = aeBasicInfoService.getCountByCond(aeCond);
		        logger.info("============正在进行"+ curUser.get("instname").toString() + "省区消息提醒，其医院编号为："+ aeCond.getHospList() + ", 不良事件数量为：" + eventCount);
		        if(eventCount == 0) continue;
		        String msg = Constants.ProvinceMsg;
		        
		        msg = String.format(msg, curUser.get("instname").toString(), eventCount);
		        cpMesg.setContent(msg);
		        AwxService.sendCpMesg(cpMesg);
		        //记录省区推送日志
		        logger.info("【"+ curUser.get("instname").toString() +"】推送省区审核提醒, 推送人工号【"+ curUser.get("code").toString() +"】");
			}
			
			return new ReturnT<String>(ReturnT.SUCCESS_CODE, "推送省区医院上报数量成功");
		}catch(Exception ex) {
			return new ReturnT<String>(ReturnT.FAIL_CODE, ex.getMessage());
		}
		
	}
	
	/**
	 * 获取指定机构下所有医院
	 */
	private List<Long> allHospFromParent(Long instId){
        List<Long> instIds = Lists.newArrayList();
        JSONArray o=(JSONArray) institutionService.getInstByParent(instId);
        if (o.size()==0){
            return new ArrayList<>();
        }
        o.forEach(jo->{
        	JSONObject curInst = (JSONObject)jo;
            if (curInst.get("instTypeName").equals("医院")){
            	instIds.add(Long.parseLong(curInst.getString("ahisHosp")));
            }else{
            	instIds.addAll(allHospFromParent(curInst.getLong("id")));
            }
        });
        return instIds;
    }
}
