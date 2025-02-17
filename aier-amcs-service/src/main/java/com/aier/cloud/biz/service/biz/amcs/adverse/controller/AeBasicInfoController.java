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
package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.adverse.enums.OperateEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOperationRecord;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeBasicInfoService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeOperationRecordService;
import com.aier.cloud.biz.service.biz.amcs.adverse.utils.EntityUtils;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * AeBasicInfo
 * 
 * @author 爱尔眼科
 * @since 2022-08-15 17:53:42
 */
@Api("相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/aeBasicInfo")
public class AeBasicInfoController extends BaseController {

	@Autowired
	private AeBasicInfoService aeBasicInfoService;

	@Autowired
	private AeOperationRecordService aeOperationRecordService;

	@RequestMapping(value = "/update")
	@ResponseBody
	public Boolean update(@RequestBody List<AeBasicInfo> basicList) {
		try {
			aeBasicInfoService.update(basicList);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	@ApiOperation(value = "根据id查询AeBasicInfo实体")
	@ApiParam(name = "id", value = "的id", required = true)
	@RequestMapping(value = "/getAeBasicInfo")
	public @ResponseBody AeBasicInfo getAeBasicInfo(@RequestParam("id") Long id) {
		return aeBasicInfoService.selectById(id);
	}

	@RequestMapping(value = "/updateOaRequestById")
	@ResponseBody
	public Integer updateOaRequestById(@RequestParam("id") Long id, @RequestParam("requestId") Integer requestId) {
		return aeBasicInfoService.updateOaRequestById(id, requestId);
	}

	@RequestMapping(value = "/findListByCond")
	@ResponseBody
	public PageResponse<Map<String, Object>> findListByCond(@RequestBody AeInfoCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		return returnPageResponse(page, aeBasicInfoService.findListByCond(page, cond));
	}

	@RequestMapping(value = "/getLast")
	public @ResponseBody AeBasicInfo getLast(@RequestParam("basicId") Long basicId) {
		return aeBasicInfoService.getLast(basicId);
	}

	@RequestMapping(value = "/getLastReportTimes")
	public @ResponseBody Integer getLastReportTimes(@RequestParam("prevId") Long prevId) {
		return aeBasicInfoService.getLastReportTimes(prevId);
	}

	@RequestMapping(value = "/getLastEvent")
	public @ResponseBody Map<String, Object> getLastEvent(@RequestParam("prevId") Long prevId,
			@RequestParam EventEnum eEnum, @RequestParam("isLast") Boolean isLast) {
		Map<String, Object> mEventInfo = Maps.newHashMap();
		try {
			mEventInfo = aeBasicInfoService.getLastEvent(prevId, eEnum, isLast);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mEventInfo;
	}

	@RequestMapping(value = "/getLastEventInfo")
	public @ResponseBody List<Map<String, Object>> getLastEventInfo(@RequestParam("basicIds") List<Long> basicIds) {
		return aeBasicInfoService.getLastEventInfo(basicIds);
	}

	@RequestMapping(value = "/sumAmount")
	@ResponseBody
	public AeBasicInfo sumAmount(@RequestBody AeInfoCondition cond){
		return aeBasicInfoService.sumAmount(cond);
	}

	@RequestMapping(value = "/findReturnList")
	@ResponseBody
	public PageResponse<Map<String, Object>> findReturnList(@RequestBody AeInfoCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("modifer", "t_sys_staff|id|name", "auditName");
		return returnPageResponse(page, aeBasicInfoService.findReturnList(page, cond));
	}

	@RequestMapping(value = "/findReviewList")
	@ResponseBody
	public PageResponse<Map<String, Object>> findReviewList(@RequestBody AeInfoCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("modifer", "t_sys_staff|id|name", "auditName");
		return returnPageResponse(page, aeBasicInfoService.findReviewList(page, cond));
	}

	@RequestMapping(value = "/queryListByCond")
	@ResponseBody
	public PageResponse<Map<String, Object>> queryListByCond(@RequestBody AeInfoCondition cond) {
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		return returnPageResponse(page, aeBasicInfoService.queryListByCond(page, cond));
	}

	@RequestMapping(value = "/updateNode")
	public @ResponseBody boolean updateNode(@RequestParam("id") Long id, @RequestParam("node") Integer node) {
		AeBasicInfo aeBasicInfo = aeBasicInfoService.selectById(id);
		aeBasicInfo.setNode(node);
		return aeBasicInfoService.updateById(aeBasicInfo);
	}

	@RequestMapping(value = "/updateGroupReview")
	public @ResponseBody boolean updateGroupReview(@RequestParam("id") Long id) {
		return aeBasicInfoService.updateGroupReview(id);
	}

	@RequestMapping(value = "/queryByQueryMapper")
	@ResponseBody
	public List<Map<String, Object>> queryByQueryMapper(@RequestParam("table") String table,
			@RequestParam("queryFeild") String queryFeild, @RequestParam("cond") String cond) {
		return aeBasicInfoService.queryByQueryMapper(table, queryFeild, cond);
	}

	@RequestMapping(value = "/countIndex")
	@ResponseBody
	public Map<String, Object> countIndex(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countIndex(cond);
	}

	@RequestMapping(value = "/countByGradeOne")
	@ResponseBody
	public List<Map<String, Object>> countByGradeOne(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countByGradeOne(cond);
	}

	@RequestMapping(value = "/countByDepartment")
	@ResponseBody
	public List<Map<String, Object>> countByDepartment(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countByDepartment(cond);
	}

	@RequestMapping(value = "/countByHospId")
	@ResponseBody
	public List<Map<String, Object>> countByHospId(@RequestBody AeInfoCondition cond) {
		if (CollectionUtils.isNotEmpty(cond.getHospList())) {
			add("hospid", "t_sys_institution|ahis_hosp|short_name", "text");
		}
		return aeBasicInfoService.countByHospId(cond);
	}

	@RequestMapping(value = "/countByEventLevel")
	@ResponseBody
	public List<Map<String, Object>> countByEventLevel(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countByEventLevel(cond);
	}

	@RequestMapping(value = "/countBySeverityLevel")
	@ResponseBody
	public List<Map<String, Object>> countBySeverityLevel(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countBySeverityLevel(cond);
	}
	
	@RequestMapping(value = "/countBySubspecialty")
	@ResponseBody
	public List<Map<String, Object>> countBySubspecialty(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countBySubspecialty(cond);
	}

	@RequestMapping(value = "/countProvinceByHospId")
	@ResponseBody
	public List<Map<String, Object>> countProvinceByHospId(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countProvinceByHospId(cond);
	}

	@RequestMapping(value = "/findByParamsPage")
	@ResponseBody
	public PageResponse<Map<String, Object>> findByParamsPage(@RequestBody AeInfoCondition cond) {
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("modifer", "t_sys_staff|id|name", "auditName");
		add("hospid", "t_sys_institution|ahis_hosp|name", "hospName");
		cond.setOrderByAsc(false);
		cond.setOrderField("create_date");
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page, aeBasicInfoService.findByParams(page, cond));
	}

	@RequestMapping(value = "/archivedById")
	@ResponseBody
	public Integer archivedById(@RequestParam("id") Long id) {
		return aeBasicInfoService.archivedById(id);
	}

	@RequestMapping(value = "/updateOaEventAmount")
	@ResponseBody
	public Boolean updateOaEventAmount(@RequestParam("id") Long oaId, @RequestParam("amount") BigDecimal amount,
			@RequestParam(value = "aeId") Long aeId) {
		if (aeBasicInfoService.updateOaEventAmount(oaId, amount, aeId)) {
			return true;
		} else {
			return false;
		}
	}
	
	@RequestMapping(value = "/delById")
	public @ResponseBody boolean delById(@RequestParam("id") Long id) {
		return aeBasicInfoService.deleteById(id);
	}

	@RequestMapping(value = "/delOa")
	@ResponseBody
	public Boolean delOa(@RequestParam("id") Long id) {
		return aeBasicInfoService.delOa(id);
	}



	@RequestMapping(value = "/delAmount")
	@ResponseBody
	public Boolean delAmount(@RequestParam("id") Long id, @RequestParam("type") String type) {
		return aeBasicInfoService.delAmount(id, type);
	}


	@RequestMapping(value = "/updateEhrDeptReview")
	public @ResponseBody boolean updateEhrDeptReview(@RequestParam("id") Long id) {
		return aeBasicInfoService.updateEhrDeptReview(id);
	}

	@RequestMapping(value = "/countHospitalByHospName")
	@ResponseBody
	public List<Map<String, Object>> countHospitalByHospName(@RequestBody AeInfoCondition cond) {
		return aeBasicInfoService.countHospitalByHospName(cond);
	}

	@RequestMapping(value = "/countByParamsPage")
	@ResponseBody
	public PageResponse<Map<String, Object>> countByParamsPage(@RequestBody AeInfoCondition cond) {
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("modifer", "t_sys_staff|id|name", "auditName");
		add("hospid", "t_sys_institution|ahis_hosp|name", "hospName");
		cond.setOrderByAsc(false);
		cond.setOrderField("create_date");
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page, aeBasicInfoService.countByParams(page, cond));
	}

	@RequestMapping(value = "/queryInfectionByParams")
	@ResponseBody
	public PageResponse<Map<String, Object>> queryInfectionByParams(@RequestBody AeInfoCondition cond) {
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("modifer", "t_sys_staff|id|name", "auditName");
		add("hospid", "t_sys_institution|ahis_hosp|name", "hospName");
		cond.setOrderByAsc(false);
		cond.setOrderField("create_date");
		return aeBasicInfoService.queryInfectionByParams(cond);
	}

	@RequestMapping(value = "/queryOtherMultipleByParams")
	@ResponseBody
	public PageResponse<Map<String, Object>> queryOtherMultipleByParams(@RequestBody AeInfoCondition cond) {
		add("creator", "t_sys_staff|id|name", "creatorName");
		add("modifer", "t_sys_staff|id|name", "auditName");
		add("hospid", "t_sys_institution|ahis_hosp|name", "hospName");
		cond.setOrderByAsc(false);
		cond.setOrderField("create_date");
		return aeBasicInfoService.queryOtherMultipleByParams(cond);
	}

	@RequestMapping(value = "/countProvReviewOver10Days")
	@ResponseBody
	public List<Map<String, Object>> countProvReviewOver10Days(@RequestBody AeInfoCondition cond) {
		if (CollectionUtils.isNotEmpty(cond.getHospList())) {
			add("hospid", "t_sys_institution|ahis_hosp|short_name", "text");
		}
		return aeBasicInfoService.countProvReviewOver10Days(cond);
	}
}