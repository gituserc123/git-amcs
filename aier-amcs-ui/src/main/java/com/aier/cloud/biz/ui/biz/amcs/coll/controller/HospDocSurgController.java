package com.aier.cloud.biz.ui.biz.amcs.coll.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.DocSurgCondition;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.based.DcgOprTypeCondition;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.amcs.coll.feign.HospDocSurgService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.biz.ui.biz.sys.feign.OprTypeService;
import com.aier.cloud.biz.ui.biz.sys.feign.StaffService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;


@Controller
@RequestMapping("/ui/amcs/coll/hosp/docsurg")
public class HospDocSurgController extends BaseController {
	
	private final static String LIST_PAGE = "amcs/coll/hospDocSurgList";
	
	private final static String PROV_LIST_PAGE = "amcs/coll/provDocSurgList";
	
	private final static String DOC_LIST_PAGE = "amcs/coll/docSurgList";
	
	private final static Integer EXPORT_ROWS = 100000;
	
	
	@Autowired
	private HospDocSurgService hospDocSurgService;
	
	@Autowired
    private InstitutionService institutionService;
	
	@Autowired
	private OprTypeService oprTypeService;
	
	@Autowired
	private StaffService staffService;
	

	@RequestMapping(value = "/listPage")
    public String listPage(HttpServletRequest request) {
		request.setAttribute("hospId", ShiroUtils.getTenantId());
		request.setAttribute("hospName", ShiroUtils.getInstName());
		//获取所在省区信息
		Map provinceInfo = this.getProvinceByHosp(ShiroUtils.getTenantId());
		request.setAttribute("provinceId", provinceInfo.get("id").toString());
		request.setAttribute("provinceName", provinceInfo.get("name").toString());
        return LIST_PAGE;
    }
	
	
	@RequestMapping(value = "/findOprTypeList", method = RequestMethod.POST)
	public @ResponseBody List<Map<String, Object>> findOprTypeList(){
		DcgOprTypeCondition cond = new DcgOprTypeCondition();
		cond.setOprTypeKind(2); //亚专科术式
		return oprTypeService.getForTree(cond);
	}
	
	@RequestMapping(value = "/provListPage")
    public String provListPage(HttpServletRequest request) {
		//判断是否为集团角色
		if(ShiroUtils.getInstId().longValue() == Constants.GroupInstId.longValue()) {
			request.setAttribute("isGroup", 1);
		}else {
			request.setAttribute("isGroup", 0);
		}
		if(ShiroUtils.getIsHosp()) {
			request.setAttribute("isHosp", 1);
		}else {
			request.setAttribute("instId", ShiroUtils.getInstId());
			request.setAttribute("instName", ShiroUtils.getInstName());
		}
		
        return PROV_LIST_PAGE;
    }
	
	@RequestMapping(value = "/docListPage")
    public String docListPage(HttpServletRequest request) {
		//判断是否为集团角色
		if(ShiroUtils.getInstId().longValue() == Constants.GroupInstId.longValue()) {
			request.setAttribute("isGroup", 1);
		}else {
			request.setAttribute("isGroup", 0);
		}
		if(ShiroUtils.getIsHosp()) {
			request.setAttribute("isHosp", 1);
		}else {
			request.setAttribute("instId", ShiroUtils.getInstId());
			request.setAttribute("instName", ShiroUtils.getInstName());
		}
        return DOC_LIST_PAGE;
    }
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> save(@RequestBody Map<String, Object> mData) {
		return hospDocSurgService.save(mData);
	}
	
	@RequestMapping(value = "/del", method = RequestMethod.POST)
    public @ResponseBody
    Boolean del(@RequestParam Long id) {
		return hospDocSurgService.del(id);
	}
	
	
	@RequestMapping(value = "/findListByDoctor", method = RequestMethod.POST)
	public @ResponseBody
	List<Map<String, Object>> findListByDoctor( @RequestParam Long doctorId) {
		return hospDocSurgService.findListByDoctor(doctorId);
	}
	
	@RequestMapping(value = "/findListByHosp", method = RequestMethod.POST)
	public @ResponseBody
	List<Map<String, Object>> findListByHosp() {
		// 获取当前用户所在医院
		Long hospId = ShiroUtils.getTenantId();
		List<Map<String, Object>> docSurgList = hospDocSurgService.findListByHosp(hospId);
		List<Map<String, Object>> resultList = this.getDoctorSurgeryInfoList(docSurgList);

		return resultList;
	}
	
	
	@RequestMapping(value = "/findCountByProv", method = RequestMethod.POST)
	public @ResponseBody
	PageResponse<Map<String, Object>> findCountByProv(DocSurgCondition cond) {
		return hospDocSurgService.findCountByProv(cond);
	}
	
	
	@RequestMapping(value = "/findListByHospDoctor", method = RequestMethod.POST)
	public @ResponseBody
	PageResponse<Map<String, Object>> findListByHospDoctor(DocSurgCondition cond) {
		return addParentSurg(hospDocSurgService.findListByHospDoctor(cond));
	}
	
	@RequestMapping(value = "/getHospStaff")
    public @ResponseBody Object getHospStaff(@RequestBody StaffCondition cond) {
		cond.setInstId(ShiroUtils.getInstId());
    	List<Map<String, Object>> o =  staffService.getHospStaff(cond);
    	this.addStaffExt(o);
		return this.easyuiResult(o);
	}
	
	private List addStaffExt(List<Map<String, Object>> staffList) {
		List<Long> staffIdsArray = hospDocSurgService.findStaffIdByHosp(ShiroUtils.getTenantId());
		System.out.print(staffIdsArray);
		staffList.stream().forEach(obj -> {
			Long curId = Long.parseLong(obj.get("id").toString());
			if(staffIdsArray.contains(curId)) {
				obj.put("hasPriv", true);
			}else {
				obj.put("hasPriv", false);
			}
		});
		return staffList;
	}
	
	@RequestMapping(value = "/exportExcel", method = {RequestMethod.GET,RequestMethod.POST})
    public void exportExcel(String paramJson) throws IOException {
		
		DocSurgCondition cond = JSON.parseObject(paramJson, DocSurgCondition.class);
        cond.setRows(EXPORT_ROWS);
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
        // 根据查询条件查询数据
        PageResponse<Map<String, Object>> retVal =addParentSurg(hospDocSurgService.findListByHospDoctor(cond));
        // 返回excel
        this.returnResult(retVal,"WEB-INF/views/amcs/adverseEvent/excelTemplate/","surgDocList","医生手术分类_"+getDateYMD());
    }
	
	 public void returnResult(PageResponse<Map<String, Object>> retVal,String tmpPath,String docPreSuffix,String docName) throws IOException {
	        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	        String templateAbsolutePath = Strings.concat(tmpPath, docPreSuffix, ".xlsx");
	        response.reset();
	        ClassPathResource config = new ClassPathResource(templateAbsolutePath);
	        if(!config.exists()) {
	            throw new BizException("没有找到模版");
	        }
	        response.setContentType("application/vnd.ms-excel");
	        response.setCharacterEncoding("utf-8");
	        String fileRealName = URLEncoder.encode(docName, "UTF-8");
	        response.setHeader("Content-disposition", "attachment;filename=" + fileRealName + ".xlsx");
	        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(config.getInputStream()).build();
	        WriteSheet writeSheet = EasyExcel.writerSheet().build();
	        FillConfig fillConfig = FillConfig.builder().forceNewRow(false).build();
	        //System.out.println(paramJson);
	        excelWriter.fill(retVal.getRows(),fillConfig,writeSheet);

	        excelWriter.finish();
	}
	
	public static String getDateYMD(){
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());					//放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }
	
	
	//增加亚专科
	private PageResponse<Map<String, Object>> addParentSurg(PageResponse<Map<String, Object>> res) {
		//获取所有分类
		DcgOprTypeCondition cond = new DcgOprTypeCondition();
		cond.setOprTypeKind(2); //亚专科术式
		List<Map<String, Object>> oprTree = oprTypeService.getForTree(cond);
		Map<Object, Object> oprMap = oprTree.stream()
		        .filter(map -> map.containsKey("grade") && map.get("grade").equals(1))
		        .collect(Collectors.toMap(map -> map.get("code"), map -> map.get("name")));

		
		List<Map<String, Object>> dataList = res.getRows();
		dataList.stream().forEach(obj -> {
			System.out.print(obj);
			String surgTypeCode = obj.get("surgTypeCode").toString();
			String parentCode = surgTypeCode.substring(0,2);
			obj.put("pSurgTypeName", oprMap.get(parentCode));
		});
		
		return res;
		
		
	}
	
	
	private List<Map<String, Object>> getDoctorSurgeryInfoList(List<Map<String, Object>> docSurgList) {
	    return groupSurgeryTypesByDoctor(docSurgList).entrySet().stream()
	            .map(entry -> buildDoctorSurgeryInfo(entry, docSurgList))
	            .collect(Collectors.toList());
	}

	private Map<String, List<String>> groupSurgeryTypesByDoctor(List<Map<String, Object>> docSurgList) {
	    return docSurgList.stream()
	            .collect(Collectors.groupingBy(map -> map.get("doctorName") + "_" + map.get("doctorId") +"_" + map.get("deptName"),
	                    Collectors.mapping(map -> map.get("surgTypeName") + "|" + map.get("surgTypeCode"), Collectors.toList())));
	}

	private Map<String, Object> buildDoctorSurgeryInfo(Map.Entry<String, List<String>> entry, List<Map<String, Object>> docSurgList) {
	    String[] keysArray = entry.getKey().split("_");
	    String doctorName = keysArray[0];
	    String doctorId = keysArray[1];
	    String deptName = keysArray[2];
	    List<String> surgeryList = entry.getValue();
	    Map<String, Object> modifyInfo = getLastModifyForDoctor(doctorId, docSurgList);
	    Map<String, Object> resultItem = new HashMap<>();
	    resultItem.put("doctorName", doctorName);
	    resultItem.put("doctorId", doctorId);
	    resultItem.put("certLevelName", deptName);
	    resultItem.put("surgTypeName", surgeryList.stream()
	    	    .map(s -> s.split("\\|")[0])
	    	    .collect(Collectors.joining(", ")));
	    resultItem.put("surgTypeCode", surgeryList.stream()
	    	    .map(s -> s.split("\\|")[1])
	    	    .collect(Collectors.joining(", ")));
	    resultItem.putAll(modifyInfo);
	    return resultItem;
	}

	private Map<String, Object> getLastModifyForDoctor(String doctorId, List<Map<String, Object>> docSurgList) {
	    Optional<Map<String, Object>> latestRecord = docSurgList.stream()
	            .filter(map -> doctorId.equals(map.get("doctorId").toString()))
	            .filter(map -> map.get("modifyDate") != null)
	            .max(Comparator.comparing(map -> parseDateFromString((String)map.get("modifyDate"))));

	    if (latestRecord.isPresent()) {
	        Map<String, Object> record = latestRecord.get();
	        Date latestModifyDate = parseDateFromString((String)record.get("modifyDate"));
	        Object modifier = record.get("modiferName");
	        Map<String, Object> resultMap = new HashMap<>();
	        resultMap.put("modifyDate", latestModifyDate);
	        resultMap.put("modiferName", modifier);
	        return resultMap;
	    } else {
	        return null;
	    }
	}

	private Date parseDateFromString(String dateString) {
	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    try {
	        return dateFormat.parse(dateString);
	    } catch (ParseException e) {
	        return null;
	    }
	}
	
	
	
	/**
	 * 根据医院ID获取省区信息
	 * @param hospId
	 * @return
	 */
	private Map getProvinceByHosp(Long hospId) {
		Map province = Maps.newHashMap();
		InstCondition cond_1 = new InstCondition();
        cond_1.setInstId(hospId);
        cond_1.setInstType(InstEnum.HOSP.getEnumCode());
        List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
        if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
            Map resultMap = hosps.get(0);
            /** 查询一级省区 */
            InstCondition cond_2 = new InstCondition();
            cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
            List<Map> list = institutionService.getProvince(cond_2);
            List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
            province = l.get(0);
        }
        
        return province;
	}
}
