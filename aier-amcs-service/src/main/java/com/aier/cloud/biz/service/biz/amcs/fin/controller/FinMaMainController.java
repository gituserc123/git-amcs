/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
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
package com.aier.cloud.biz.service.biz.amcs.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.FinMaMainCondition;
import cn.hutool.json.JSONUtil;
import cn.hutool.json.JSONArray;
import com.aier.cloud.api.amcs.utils.TimePeriodUtil;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaDipDtl;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaDrgDtl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinMaDipDtlService;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinMaDrgDtlService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinMaMainService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaMain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * FinMaMain
 * 医保管理分析主表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:36
 */
@Api("医保管理分析主表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finMaMain")
public class FinMaMainController extends BaseController{
 
	@Autowired
	private FinMaMainService finMaMainService;

	@Autowired
	private FinMaDipDtlService finMaDipDtlService;

	@Autowired
	private FinMaDrgDtlService finMaDrgDtlService;
	
	@ApiOperation(value="根据id查询FinMaMain医保管理分析主表实体")
	@ApiParam(name="id", value="医保管理分析主表的id", required=true)
	@RequestMapping(value = "/getFinMaMain")
	public @ResponseBody FinMaMain getFinMaMain(@RequestParam("id") Long id) {
		return finMaMainService.selectById(id);
	}

	@ApiOperation(value = "保存")
	@RequestMapping(value = "/save")
	@ResponseBody
	public Map<String, String> save(@RequestBody FinMaMain finMaMain){
		return finMaMainService.save(finMaMain);
	}

	@ApiOperation(value = "保存")
	@RequestMapping(value = "/saveDipStr")
	@ResponseBody
	public Map<String, Object> saveDipStr(@RequestBody String strData){
		JSONArray jsonArray = JSONUtil.parseArray(strData);
		FinMaMain finMaMainEmp = JSONUtil.toBean(jsonArray.get(0).toString(),FinMaMain.class);
		FinMaMain finMaMainRDT = JSONUtil.toBean(jsonArray.get(0).toString(),FinMaMain.class);
		FinMaMain finMaMainRDTForm = JSONUtil.toBean(jsonArray.get(1).toString(),FinMaMain.class);
		finMaMainRDT.setId(finMaMainRDTForm.getId());
		finMaMainRDT.setInsuranceType(finMaMainRDTForm.getInsuranceType());
		finMaMainRDT.setMaIdentifier(finMaMainRDTForm.getMaIdentifier());
		// 防止开多个填报页面，保存产生多条记录的情况
		LocalDateTime now = LocalDateTime.now();
		String curQuarter = TimePeriodUtil.getCurDatePeriod(now);
		String curMaIdentifier = curQuarter+UserContext.getTenantId();
		if(null == finMaMainEmp.getId() || null == finMaMainRDT.getId()){
			FinMaMainCondition cond = new FinMaMainCondition();
			cond.setHospId(UserContext.getTenantId());
			cond.setMaYear(now.getYear());
			cond.setMaQuarter(Integer.parseInt(curQuarter.substring(curQuarter.length() - 1)));
			cond.setMaIdentifier(curMaIdentifier);
			cond.setSettlementMethod("DIP");
			List<FinMaMain> finMaMains = finMaMainService.getAllRecord(cond);
			if(Objects.nonNull(finMaMains) && finMaMains.size()>0){
				finMaMains.stream().forEach(fm -> {
					if(fm.getInsuranceType().intValue() == 1){
						finMaMainEmp.setId(fm.getId());   // 职工
					}
					if(fm.getInsuranceType().intValue() == 2){
						finMaMainRDT.setId(fm.getId());   // 居民
					}
				});
			}
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", "200");
		result.put("success", true);
		try {
			finMaMainService.save(finMaMainEmp);
			finMaMainService.save(finMaMainRDT);
			for(int i = 2; i < jsonArray.size(); i++){
				FinMaDipDtl finMaDipDtl = JSONUtil.toBean(jsonArray.get(i).toString(),FinMaDipDtl.class);
				if(Objects.isNull(finMaDipDtl.getMaIdentifier()) || finMaDipDtl.getMaIdentifier().equals("")){
					finMaDipDtl.setMaIdentifier(curMaIdentifier);
				}
				if(finMaDipDtl.getBusiSign().startsWith("DIP_EMP")){
					finMaDipDtl.setMainId(finMaMainEmp.getId());
				}
				if(finMaDipDtl.getBusiSign().startsWith("DIP_RDT")){
					finMaDipDtl.setMainId(finMaMainRDT.getId());
				}
				finMaDipDtlService.save(finMaDipDtl);
			}
		} catch (Exception e) {
			result.put("code", "500");
			result.put("msg", "保存失败" + e.getMessage());
			result.put("success", false);
			throw e;
		}
		return result;
	}

	@ApiOperation(value = "保存")
	@RequestMapping(value = "/saveDrgStr")
	@ResponseBody
	public Map<String, Object> saveDrgStr(@RequestBody String strData){
		JSONArray jsonArray = JSONUtil.parseArray(strData);
		FinMaMain finMaMainEmp = JSONUtil.toBean(jsonArray.get(0).toString(),FinMaMain.class);
		FinMaMain finMaMainRDT = JSONUtil.toBean(jsonArray.get(0).toString(),FinMaMain.class);
		FinMaMain finMaMainRDTForm = JSONUtil.toBean(jsonArray.get(1).toString(),FinMaMain.class);
		finMaMainRDT.setId(finMaMainRDTForm.getId());
		finMaMainRDT.setInsuranceType(finMaMainRDTForm.getInsuranceType());
		finMaMainRDT.setMaIdentifier(finMaMainRDTForm.getMaIdentifier());
		// 防止开多个填报页面，保存产生多条记录的情况
		LocalDateTime now = LocalDateTime.now();
		String curQuarter = TimePeriodUtil.getCurDatePeriod(now);
		String curMaIdentifier = curQuarter+UserContext.getTenantId();
		if(null == finMaMainEmp.getId() || null == finMaMainRDT.getId()){
			FinMaMainCondition cond = new FinMaMainCondition();
			cond.setHospId(UserContext.getTenantId());
			cond.setMaYear(now.getYear());
			cond.setMaQuarter(Integer.parseInt(curQuarter.substring(curQuarter.length() - 1)));
			cond.setMaIdentifier(curMaIdentifier);
			cond.setSettlementMethod("DRG");
			List<FinMaMain> finMaMains = finMaMainService.getAllRecord(cond);
			if(Objects.nonNull(finMaMains) && finMaMains.size()>0){
				finMaMains.stream().forEach(fm -> {
					if(fm.getInsuranceType().intValue() == 1){
						finMaMainEmp.setId(fm.getId());   // 职工
					}
					if(fm.getInsuranceType().intValue() == 2){
						finMaMainRDT.setId(fm.getId());   // 居民
					}
				});
			}
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("code", "200");
		result.put("success", true);
		try {
			finMaMainService.save(finMaMainEmp);
			finMaMainService.save(finMaMainRDT);
			for(int i = 2; i < jsonArray.size(); i++){
				FinMaDrgDtl finMaDrgDtl = JSONUtil.toBean(jsonArray.get(i).toString(),FinMaDrgDtl.class);
				if(Objects.isNull(finMaDrgDtl.getMaIdentifier()) || finMaDrgDtl.getMaIdentifier().equals("")){
					finMaDrgDtl.setMaIdentifier(curMaIdentifier);
				}
				if(finMaDrgDtl.getBusiSign().startsWith("DRG_EMP")){
					finMaDrgDtl.setMainId(finMaMainEmp.getId());
				}
				if(finMaDrgDtl.getBusiSign().startsWith("DRG_RDT")){
					finMaDrgDtl.setMainId(finMaMainRDT.getId());
				}
				finMaDrgDtlService.save(finMaDrgDtl);
			}
		} catch (Exception e) {
			result.put("code", "500");
			result.put("msg", "保存失败" + e.getMessage());
			result.put("success", false);
			throw e;
		}
		return result;
	}

	@ApiOperation(value = "不分页查询")
	@RequestMapping(value = "/getAllRecord")
	@ResponseBody
	public List<FinMaMain> getAllRecord(@RequestBody FinMaMainCondition cond){
		return finMaMainService.getAllRecord(cond);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody FinMaMainCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		return  returnPageResponse(page,finMaMainService.getAll(page,cond));
	}

	@RequestMapping(value = "/getStatDipRecord")
	@ResponseBody
	public List<Map<String, Object>> getStatDipRecord(@RequestBody FinMaMainCondition cond){
		return finMaMainService.getStatDipRecord(cond);
	}

	@RequestMapping(value = "/getStatDrgRecord")
	@ResponseBody
	public List<Map<String, Object>> getStatDrgRecord(@RequestBody FinMaMainCondition cond){
		return finMaMainService.getStatDrgRecord(cond);
	}

	@RequestMapping(value = "/getStatCount")
	@ResponseBody
	public Integer getStatCount(@RequestBody FinMaMainCondition cond){
		return finMaMainService.getStatCount(cond);
	}
}