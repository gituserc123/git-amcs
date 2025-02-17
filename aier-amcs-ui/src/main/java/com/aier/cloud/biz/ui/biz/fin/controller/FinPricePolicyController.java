/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.fin.condition.FinInsPricePolicyCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsPricePolicy;
import com.aier.cloud.api.amcs.fin.enums.EhrLvEnums;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.InstCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.adverse.service.HospHandleService;
import com.aier.cloud.biz.ui.biz.fin.feign.FinInsPricePolicyService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.compress.utils.Lists;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.assertj.core.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.web.controller.BaseController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


/**
 * FinInsPricePolicy
 * 价格政策表
 * @author 爱尔眼科
 * @since 2023-08-07 15:09:57
 */
@Controller
@RequestMapping("/ui/service/biz/amcs/fin/finInsPricePolicy")
public class FinPricePolicyController extends BaseController{

	@Autowired
	private FinInsPricePolicyService finInsPricePolicyService;

	@Autowired
	private InstitutionService institutionService;

	@Autowired
	private HospHandleService hospHandleService;

	@Autowired
	private AdverseEventService adverseEventService;

	private final static String PRICE_POLICY_LIST= "amcs/fin/business/FinPricePolicyList";
	private final static String PRICE_POLICY_INFO= "amcs/fin/business/FinPricePolicyInfo";

	@RequestMapping(value = "/getFinInsPricePolicy")
	public @ResponseBody FinInsPricePolicy getFinInsPricePolicy(@RequestParam("id") Long id) {
		return finInsPricePolicyService.getFinInsPricePolicy(id);
	}

	@RequestMapping(value = "/listPage")
	public String listPage(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		ProvinceRoleCondition provinceRoleCondition=new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(ShiroUtils.getLoginCode());
		List<ProvinceRoleConfig> provinceRoleConfigList=adverseEventService.getListAll(provinceRoleCondition);
		if(shiroUser.getIsHosp()&&provinceRoleConfigList.size()==0){
			// 医院人员登录
			request.setAttribute("empType", 3);
			request.setAttribute("insiId", shiroUser.getInstId());
		}else{
			// 集团或省区登录
			request.setAttribute("insiId", shiroUser.getInstId());
			if(shiroUser.getInstId() == 100002L){
				// 集团登录
				request.setAttribute("empType", 1);
			}else{
				// 省区登录
				request.setAttribute("empType", 2);
				request.setAttribute("insiId", provinceRoleConfigList.size()>0?provinceRoleConfigList.get(0).getProvinceId():shiroUser.getInstId());
			}
		}

		return PRICE_POLICY_LIST;
	}

	@RequestMapping(value = "/findList", method = { RequestMethod.POST })
	@ResponseBody
	public PageResponse<Map<String, Object>> findList(FinInsPricePolicyCondition cond){
		boolean isRetNull=false;
		// 组装查询条件
		this.wrapperCond(cond,isRetNull);
		if(isRetNull){
			return new PageResponse<>();
		}
		// 组装查询条件 --end
		PageResponse<Map<String, Object>> response = finInsPricePolicyService.getAll(cond);
		List<Map<String, Object>> dataList = response.getRows();
		// 组装返回数据
		this.wrapperData(dataList);
		return response;
	}

	@RequestMapping(value = "/pricePolicyInfo", method = { RequestMethod.GET, RequestMethod.POST })
	public String pricePolicyInfo(Map<String, Object> map,Long id){
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat format = new SimpleDateFormat("yyyy");
		// 获取当前年
		int curYear = calendar.get(Calendar.YEAR);
		if(null!=id){
			map.put("id",id);
			FinInsPricePolicy finInsPricePolicy = finInsPricePolicyService.getFinInsPricePolicy(id);
			map.put("pricePolicy",finInsPricePolicy);
			map.put("hospId", finInsPricePolicy.getHospId());
			map.put("hospName", finInsPricePolicy.getHospName());
			map.put("curYear", format.format(finInsPricePolicy.getCreateDate()));
		}else{
			FinInsPricePolicyCondition cond = new FinInsPricePolicyCondition();
			cond.setHospId(ShiroUtils.getTenantId());
			cond.setCurYear(String.valueOf(curYear));
			PageResponse<Map<String, Object>> pageResponse = finInsPricePolicyService.getAll(cond);
			if(Objects.nonNull(pageResponse.getRows()) && pageResponse.getRows().size()>0){
				map.put("pricePolicy",pageResponse.getRows().get(0));
				map.put("id",pageResponse.getRows().get(0).get("id"));
				map.put("curYear", String.valueOf(pageResponse.getRows().get(0).get("createDate")).substring(0,4));
			}else{
				map.put("curYear", String.valueOf(curYear));
			}
			map.put("hospName", ShiroUtils.getInstName());
			map.put("hospId", ShiroUtils.getTenantId());
		}
		return PRICE_POLICY_INFO;
	}

	@RequestMapping(value = "/save", method = { RequestMethod.GET, RequestMethod.POST })
	@ResponseBody
	public  Map<String, Object> save(@RequestBody FinInsPricePolicy finInsPricePolicy){
		return finInsPricePolicyService.save(finInsPricePolicy);
	}

	@RequestMapping(value = "/exportExcel", method = {RequestMethod.GET,RequestMethod.POST})
	public void exportExcel(String paramJson) throws IOException {
		FinInsPricePolicyCondition cond = JSON.parseObject(paramJson,FinInsPricePolicyCondition.class);
		boolean isRetNull=false;
		// 组装查询条件
		this.wrapperCond(cond,isRetNull);
		if(isRetNull){
			this.returnResult(Lists.newArrayList(),"WEB-INF/views/amcs/fin/excelTemplate/","FinPricePolicyList","价格政策_"+getDateYMD());
		}else{
			cond.setRows(20000);
			PageResponse<Map<String, Object>> response = finInsPricePolicyService.getAll(cond);
			List<Map<String, Object>> dataList = response.getRows();
			this.wrapperData(dataList);
			this.returnResult(dataList,"WEB-INF/views/amcs/fin/excelTemplate/","FinPricePolicyList","价格政策_"+getDateYMD());
		}
	}

	private void wrapperCond(FinInsPricePolicyCondition cond,boolean isRetNull){
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser) subject.getPrincipal();
		ProvinceRoleCondition provinceRoleCondition = new ProvinceRoleCondition();
		provinceRoleCondition.setStaffCode(shiroUser.getLoginCode());
		List<ProvinceRoleConfig> provinceRoleConfigList = adverseEventService.getListAll(provinceRoleCondition);
		if (shiroUser.getIsHosp() && provinceRoleConfigList.size() == 0) {
			// 医院登录
			cond.setHospId(shiroUser.getTenantId());
		} else {
			if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
				// 集团登录
				if (cond.getProvince() != null && cond.getProvince() > 0) {
					if (cond.getHospId() != null && cond.getHospId() > 0) {
						Institution inst = institutionService.getById(cond.getHospId());
						cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
						cond.setHospList(null);
					} else {
						Object instList = hospHandleService.allHospFromParent(cond.getProvince());
						if (instList != null) {
							JSONArray ja = (JSONArray) instList;
							ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
							ja.stream().forEach(j -> {
								JSONObject jo = (JSONObject) j;
								hospList.add(jo.getLong("ahisHosp"));
							});
							cond.setHospId(null);
							// 如果hospList为空，说明当前机构下没有医院，直接返回
							if (hospList.size() > 0) {
								cond.setHospList(hospList);
							} else {
								isRetNull=true;
							}
						}
					}
				}
			} else {
				// 省区登录
				if (cond.getHospId() != null && cond.getHospId() > 0) {
					Institution inst = institutionService.getById(cond.getHospId());
					cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
					cond.setHospList(null);
				} else {
					Object instList;
					if (provinceRoleConfigList.size() > 0) {
						instList = hospHandleService.allHospFromParent(provinceRoleConfigList.get(0).getProvinceId());
					} else {
						instList = hospHandleService.allHospFromParent(shiroUser.getInstId());
					}

					if (instList != null) {
						JSONArray ja = (JSONArray) instList;
						ArrayList<Long> hospList = org.assertj.core.util.Lists.newArrayList();
						ja.stream().forEach(j -> {
							JSONObject jo = (JSONObject) j;
							hospList.add(jo.getLong("ahisHosp"));
						});
						cond.setHospId(null);
						// 如果hospList为空，说明当前机构下没有医院，直接返回
						if (hospList.size() > 0) {
							cond.setHospList(hospList);
						} else {
							isRetNull=true;
						}
					}
				}
			}
		}
		/** inst_id=>hosp_id */
		if (Objects.nonNull(cond.getHospId()) && cond.getHospId() > 9999) {
			Institution inst = institutionService.getById(cond.getHospId());
			if (!ObjectUtils.isEmpty(inst)) {
				cond.setHospId(Long.parseLong(inst.getAhisHosp().toString()));
			}
		}
	}

	private void wrapperData(List<Map<String, Object>> dataList){
		if (Objects.nonNull(dataList) && dataList.size() > 0) {
			Map<String, Map> existHosp = new HashMap<>();
			dataList.stream().forEach(obj -> {
				// 医院类型和省区
				if (Objects.nonNull(obj.get("hospId"))) {
					if (existHosp.get(String.valueOf(obj.get("hospId"))) != null) {
						obj.put("hospParent", existHosp.get(String.valueOf(obj.get("hospId"))).get("PARENT_NAME"));
						obj.put("investNature", existHosp.get(String.valueOf(obj.get("hospId"))).get("INVEST_NATURE"));
						if(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL") != null){
							obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(existHosp.get(String.valueOf(obj.get("hospId"))).get("EHR_LEVEL"))));
						}
					} else {
						InstCondition cond_1 = new InstCondition();
						cond_1.setInstId(Long.parseLong(String.valueOf(obj.get("hospId"))));
						cond_1.setInstType(InstEnum.HOSP.getEnumCode());
						List<Map> hosps = institutionService.getInstByConditionForSelect(cond_1);
						if (CollectionUtils.isNotEmpty(hosps) && hosps.size() > 0) {
							Map resultMap = hosps.get(0);
							/** 查询一级省区 */
							InstCondition cond_2 = new InstCondition();
							cond_2.setInstId(Long.parseLong(resultMap.get("ID").toString()));
							List<Map> list = institutionService.getProvince(cond_2);
							List<Map> l = list.stream().filter(e -> e.get("grade").equals(1)).collect(Collectors.toList());
							obj.put("hospParent", l.get(0).get("name"));
							obj.put("investNature", resultMap.get("INVEST_NATURE"));
							if(resultMap.get("EHR_LEVEL")!=null){
								obj.put("ehrLevel", EhrLvEnums.findEhrLvEnumsByCode(String.valueOf(resultMap.get("EHR_LEVEL"))));
							}
							resultMap.put("PARENT_NAME", l.get(0).get("name"));
							existHosp.put(String.valueOf(obj.get("hospId")), resultMap);
						}
					}
				}
				if (Objects.nonNull(obj.get("gjYlj"))) {
					obj.put("gjYljDesc", MapUtils.getString(obj,"gjYlj").equals("1")?"有":"无");
				}else{
					obj.put("gjYljDesc","");
				}
				if (Objects.nonNull(obj.get("vipJg"))) {
					obj.put("vipJgDesc", MapUtils.getString(obj,"vipJg").equals("1")?"有":"无");
				}else{
					obj.put("vipJgjDesc","");
				}
				if (Objects.nonNull(obj.get("zzDj"))) {
					obj.put("zzDjDesc", MapUtils.getString(obj,"zzDj").equals("1")?"有":"无");
				}else{
					obj.put("zzDjDesc","");
				}
				if (Objects.nonNull(obj.get("ybZfj"))) {
					obj.put("ybZfjDesc", MapUtils.getString(obj,"ybZfj").equals("1")?"有":"无");
				}else{
					obj.put("ybZfjDesc","");
				}
				if (Objects.nonNull(obj.get("zlxmZxGlyyygj"))) {
					obj.put("zlxmZxGlyyygjDesc", MapUtils.getString(obj,"zlxmZxGlyyygj").equals("1")?"是":"否");
				}else{
					obj.put("zlxmZxGlyyygjDesc","");
				}
				if (Objects.nonNull(obj.get("ypCgjljc"))) {
					obj.put("ypCgjljcDesc", MapUtils.getString(obj,"ypCgjljc").equals("1")?"是":"否");
				}else{
					obj.put("ypCgjljcDesc","");
				}
				if (Objects.nonNull(obj.get("ypZbjljc"))) {
					obj.put("ypZbjljcDesc", MapUtils.getString(obj,"ypZbjljc").equals("1")?"是":"否");
				}else{
					obj.put("ypZbjljcDesc","");
				}
				if (Objects.nonNull(obj.get("hcCgjljc"))) {
					obj.put("hcCgjljcDesc", MapUtils.getString(obj,"hcCgjljc").equals("1")?"是":"否");
				}else{
					obj.put("hcCgjljcDesc","");
				}
				if (Objects.nonNull(obj.get("hcZbjljc"))) {
					obj.put("hcZbjljcDesc", MapUtils.getString(obj,"hcZbjljc").equals("1")?"是":"否");
				}else{
					obj.put("hcZbjljcDesc","");
				}
				if (Objects.nonNull(obj.get("qgssZxsqzdj"))) {
					obj.put("qgssZxsqzdjDesc", MapUtils.getString(obj,"qgssZxsqzdj").equals("1")?"符合省区指导价区间":"部分项目低于省区全年最大优惠价格");
				}else{
					obj.put("qgssZxsqzdjDesc","");
				}
				if (Objects.nonNull(obj.get("sgzlxmZxjtzdj"))) {
					if(MapUtils.getString(obj,"sgzlxmZxjtzdj").equals("1")){
						obj.put("sgzlxmZxjtzdjDesc", "基本医疗服务项目执行医保协议价，自费项目执行或高于集团指导价");
					}else if(MapUtils.getString(obj,"sgzlxmZxjtzdj").equals("2")){
						obj.put("sgzlxmZxjtzdjDesc", "基本医疗服务项目执行医保协议价，部分自费项目价格低于集团指导价");
					}else if(MapUtils.getString(obj,"sgzlxmZxjtzdj").equals("3")){
						obj.put("sgzlxmZxjtzdjDesc", "视光相关诊疗项目自主定价，部分项目低于集团指导价");
					}else if(MapUtils.getString(obj,"sgzlxmZxjtzdj").equals("4")){
						obj.put("sgzlxmZxjtzdjDesc", "视光相关诊疗项目自主定价");
					}else{
						obj.put("sgzlxmZxjtzdjDesc", "");
					}
				}else{
					obj.put("sgzlxmZxjtzdjDesc","");
				}
			});
		}
	}

	public void returnResult(List<Map<String, Object>> dataList,String tmpPath,String docPreSuffix,String docName) throws IOException {
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
		excelWriter.fill(dataList,fillConfig,writeSheet);

		excelWriter.finish();
	}

	public static String getDateYMD(){
		StringBuffer sDate = new StringBuffer();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());                    //放入Date类型数据
		return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
	}

}
