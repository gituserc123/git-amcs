package com.aier.cloud.biz.ui.biz.adverse.service;

import com.aier.cloud.api.amcs.adverse.domain.AeBasicInfo;
import com.aier.cloud.api.amcs.adverse.domain.AeOaPayVO;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.common.util.DateUtils;
import com.aier.cloud.basic.log.annotion.AierLog;
import com.aier.cloud.basic.log.bean.LogMessage;
import com.aier.cloud.basic.log.utils.LogUtils;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeCommonService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.biz.ui.biz.sys.feign.StaffService;
import com.aier.cloud.center.common.context.UserContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.google.common.collect.Maps;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-01-31 09:32
 **/
@Service
public class OaPayService {
    @Autowired
    private AeCommonService aeCommonService;
    @Autowired
    private OaFactory oaFactory;
    @Autowired
    private InstitutionService institutionService;
    @Autowired
    private StaffService staffService;
    private static final Logger logger = LoggerFactory.getLogger(OaPayService.class);

    @AierLog(message="{0}发起赔付， oa流程号：{1}", module="OA赔付流程")
    public Map<String, Object> payCreate(Long id){

    	Map<String, Object> rs = Maps.newHashMap();
        AeBasicInfo aeBasicInfo =aeCommonService.getBasicById(id);
        if (ObjectUtils.isEmpty(aeBasicInfo.getCompensationAmount())||aeBasicInfo.getCompensationAmount().equals(BigDecimal.ZERO)){
            throw new BizException("没有赔付金额！！！");
        }
        if(!ObjectUtils.isEmpty(aeBasicInfo.getOaRequestId())){
            throw new BizException("流程已经在OA建立，OA交易号："+aeBasicInfo.getOaRequestId().toString());
        }
        InstCondition cond=new InstCondition();
        cond.setInstId(aeBasicInfo.getHospId());
        cond.setInstType(InstEnum.HOSP.getEnumCode());
        List<Map> l=institutionService.getInstByConditionForSelect(cond);
        Map map=l.get(0);

        String eventCode=aeBasicInfo.getEventCode();
        EventEnum eEnum=EventEnum.findEnumByCode(eventCode);
        Map<String,Object> mapInfo=aeCommonService.findById(id,eEnum);

        OaHandlerService os= oaFactory.getByName(map.get("INVEST_NATURE").equals("10")?"market":"partner");

        String oaCmpyId=os.getOaCMPY(map.get("ID").toString());
        AeOaPayVO aeOaPayVO=new AeOaPayVO();
        Object payDiagnose=mapInfo.get("payDiagnose");
        if(!ObjectUtils.isEmpty(payDiagnose)){
            aeOaPayVO.setZd(payDiagnose.toString());
        }
        if(ObjectUtils.isEmpty(aeBasicInfo.getPatientName())){
            throw new BizException("无患者的事件OA不允许发起流程，请更换成有患者的事件赔付！");
        }
        //有preid则设置主事件id为preid
        if(ObjectUtils.isEmpty(aeBasicInfo.getPrevId())){
            aeOaPayVO.setZsjid(id.toString());
        }else{
            aeOaPayVO.setZsjid(aeBasicInfo.getPrevId().toString());
        }

        aeOaPayVO.setYymc(oaCmpyId);
        aeOaPayVO.setXb(aeBasicInfo.getPatientSex()==1?"男":"女");
        aeOaPayVO.setHzxm(aeBasicInfo.getPatientName());
        aeOaPayVO.setNl(aeBasicInfo.getPatientAge().toString());


        aeOaPayVO.setPfje(aeBasicInfo.getCompensationAmount().toString());

        aeOaPayVO.setScomment("YLJF("+aeBasicInfo.getEventName()+"--"+ DateUtils.formatDate(new Date(),"yyyy-MM-dd HH:mm) "+aeBasicInfo.getReportTimes().toString()));
        aeOaPayVO.setZyh(aeBasicInfo.getPatientNo());
        //设置第一次上报时间
        Date frd = aeBasicInfo.getCreateDate();
        if(!ObjectUtils.isEmpty(aeBasicInfo.getPrevId())) {
        	AeBasicInfo prevBasicInfo =aeCommonService.getBasicById(aeBasicInfo.getPrevId());
        	frd = prevBasicInfo.getCreateDate();
        }
        aeOaPayVO.setFrd(DateUtils.format(frd, DateUtils.DATE_FORMAT_YYYY_MM_DD));
        	
        /*
        Long auditor=aeCommonService.findOperator(aeBasicInfo.getId(),2,1);
        if (auditor==null){
            logger.error("审核人问题：{},basicInfoId:{}",aeOaPayVO,aeBasicInfo.getId());
            throw new BizException("没有获取到当前项目审核人，请重试或联系管理员！！！");
        }
        String userCode=staffService.getById(auditor).getCode();
        */
        String userCode=ShiroUtils.getLoginCode();
        String userId=os.getOaUserId(userCode);
        Integer i =os.createNew(aeOaPayVO,"YLJF("+aeBasicInfo.getEventName()+"--"+aeBasicInfo.getId()+") "+aeBasicInfo.getReportTimes().toString(),userId);
        LogUtils.putArgs(LogMessage.newWrite().setParams(userCode,i.toString()));
        Integer updateI=aeCommonService.updateOaRequestById(aeBasicInfo.getId(),i);
        if(updateI>0){
        	rs.put("status", "success");
        	rs.put("oaRequestId", i);
        }else{
        	rs.put("status", "false");
        }
        
        return rs;
    }
}
