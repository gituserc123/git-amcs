package com.aier.cloud.biz.ui.biz.adverse.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.utils.DateUtil;
import com.aier.cloud.biz.ui.biz.adverse.utils.ExportUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import org.apache.commons.compress.utils.Lists;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.api.amcs.adverse.condition.AeExpertCondition;
import com.aier.cloud.api.amcs.adverse.enums.ExpertEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.AeExpertService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/ui/service/biz/amcs/adverse/expert")
public class AeExpertController extends BaseController {
	
	private final static String INDEX = "amcs/adverseEvent/eventManage/expertList";
	
	private final static String EVENT = "amcs/adverseEvent/eventManage/expertEventList";

	@Autowired
    private AeExpertService aeExpertService;
	
	@Autowired
    private InstitutionService institutionService;


	@RequestMapping(value = "/findEventList", method = { RequestMethod.POST })
	@ResponseBody
	PageResponse<Map<String, Object>> findEventList(AeInfoCondition cond) {
		return aeExpertService.findEventList(cond);
	}
	
	
	@RequestMapping(value = "/index",method = { RequestMethod.GET, RequestMethod.POST})
    public String index(Model model){
        return INDEX;
    };
    
    @RequestMapping(value = "/event",method = { RequestMethod.GET, RequestMethod.POST})
    public String event(HttpServletRequest request,
						@RequestParam(value = "isProvince", required = false, defaultValue = "false") Boolean isProvince){
		request.setAttribute("isProvince", isProvince);
		request.setAttribute("provinceCode", getProvinceCode());
		return EVENT;
    };
    
    @RequestMapping(value = "/delById", method = { RequestMethod.POST })
	@ResponseBody
	public Object delById(@RequestParam("id") Long id){
		return aeExpertService.delById(id);
	}


	@RequestMapping(value = "/refresh", method = { RequestMethod.POST })
	@ResponseBody
	public Object refresh(@RequestParam("staffCode") String staffCode){
		return aeExpertService.refresh(staffCode);
	}



    @RequestMapping(value = "/findList", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> findList(AeExpertCondition cond) {
		if (Boolean.TRUE.equals(cond.getIsProvince())) {
			setProvinceCode(cond);
		}else if(Boolean.FALSE.equals(cond.getIsProvince())){
			cond.setProvinceCode(Constants.GroupInstId.toString());
		}
    	return this.addGroup(aeExpertService.findListByCond(cond));
    }

	@RequestMapping(value = "/exportExcel", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportExcel() throws IOException {
		AeExpertCondition cond = new AeExpertCondition();
		List<Map<String,Object>> dataList = aeExpertService.getAllList(cond);
		this.addGroupForList(dataList);
		ExportUtil.returnResult(dataList,"WEB-INF/views/amcs/adverseEvent/excelTemplate/","expert","专家列表_"+ DateUtil.getDateYMD());
	}


	private void setProvinceCode(AeExpertCondition cond) {
		cond.setProvinceCode(this.getProvinceCode());
	}

	private String getProvinceCode(){
		String provinceCode = "";
		InstCondition c = new InstCondition();
		c.setInstId(ShiroUtils.getInstId());
		List<Map> provinceList = institutionService.getProvince(c);

		if (provinceList != null && !provinceList.isEmpty()) {
			provinceCode = Optional.ofNullable(provinceList.get(0).get("id"))
					.map(Object::toString)
					.orElse(null);

		}
		return provinceCode;
	}
    
    
    @RequestMapping(value = "/findListByEvent", method = {RequestMethod.POST})
    @ResponseBody
    public PageResponse<Map<String, Object>> findListByEvent(AeExpertCondition cond) {
    	return this.addGroup(aeExpertService.findListByEvent(cond));
    }
    
    
    
    @RequestMapping(value = "/updateGroup", method = {RequestMethod.POST})
    @ResponseBody
    public Map<String, Object>  updateGroup(AeExpertCondition cond) {
    	if(aeExpertService.updateGroup(cond)) {
    		return this.success();
    	}else {
    		return this.fail();
    	}
    }

	@PostMapping(value = "/updateList")
	public @ResponseBody Map<String, Object> updateList(@RequestBody List<Map<String, Object>> expertList) {
		return aeExpertService.updateList(expertList);
	}
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> save(@RequestBody Map<String, Object> mData) {
    	return aeExpertService.save(mData);
	}
    
    @RequestMapping(value = "/bind", method = RequestMethod.POST)
    public @ResponseBody Map<String, Object> bind(@RequestBody AeExpertCondition bindData) {

    	return aeExpertService.bind(bindData);
	}
    
    /**
     * 增加学组显示
     * @param res
     * @return
     */
    private PageResponse<Map<String, Object>> addGroup(PageResponse<Map<String, Object>> res){
    	List<Map<String, Object>> dataList = res.getRows();
    	this.addGroupForList(dataList);
    	return res;
    }

	private void addGroupForList(List<Map<String, Object>> dataList) {
		if(ObjectUtils.isEmpty(dataList)) return;
		dataList.stream().forEach(obj -> {
			if (Objects.nonNull(obj.get("groupValue"))) {
				Integer groupValue = Integer.parseInt(obj.get("groupValue").toString());
				String groupName = this.decodeGroup(groupValue);
				obj.put("groupName",groupName);
			}else {
				obj.put("groupName","暂未绑定学组");
			}
		});
	}
    
    private String decodeGroup(Integer groupValue) {
    	String groupDesc = "";
    	List<ExpertEnum> expertList=new ArrayList();
    	for(ExpertEnum expertEnum:ExpertEnum.values()) {
    		Integer expertValue = expertEnum.getValue();
    		if((expertValue & groupValue) == expertValue) {
    			groupDesc += expertEnum.getEnumDesc().concat(",");
    		}
    	}
    	
    	if(ObjectUtils.isEmpty(groupDesc)) {
    		groupDesc = "暂未绑定学组";
    	}else {
    		groupDesc = groupDesc.substring(0, groupDesc.length() - 1);
    	}
    	
    	return groupDesc;
    }
    
    
    

}
