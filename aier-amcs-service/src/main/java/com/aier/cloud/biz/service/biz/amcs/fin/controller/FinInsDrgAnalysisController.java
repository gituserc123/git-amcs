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
package com.aier.cloud.biz.service.biz.amcs.fin.controller;

import com.aier.cloud.api.amcs.fin.condition.FinInsDrgAnalysisCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
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

import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsDrgAnalysisService;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsDrgAnalysis;

import java.util.List;
import java.util.Map;

/**
 * FinInsDrgAnalysis
 * DRG盈亏分析表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:54
 */
@Api("DRG盈亏分析表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/fin/finInsDrgAnalysis")
public class FinInsDrgAnalysisController extends BaseController {
 
	@Autowired
	private FinInsDrgAnalysisService finInsDrgAnalysisService;
	
	@ApiOperation(value="根据id查询FinInsDrgAnalysisDRG盈亏分析表实体")
	@ApiParam(name="id", value="DRG盈亏分析表的id", required=true)
	@RequestMapping(value = "/getFinInsDrgAnalysis")
	public @ResponseBody FinInsDrgAnalysis getFinInsDrgAnalysis(@RequestParam("id") Long id) {
		return finInsDrgAnalysisService.selectById(id);
	}

	@RequestMapping(value = "/save")
	public @ResponseBody Boolean save(@RequestBody FinInsDrgAnalysis finInsDrgAnalysis) {
		return finInsDrgAnalysisService.save(finInsDrgAnalysis);
	}

	@RequestMapping(value = "/getByMainId")
	public @ResponseBody FinInsDrgAnalysis getByMainId(@RequestParam("mainId") Long mainId){
		return finInsDrgAnalysisService.getByMainId(mainId);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody FinInsDrgAnalysisCondition finInsDrgAnalysisCondition) {
		add("creator", "T_SYS_STAFF|ID|NAME", "creatorName");
		add("modifer", "T_SYS_STAFF|ID|NAME", "modiferName");
		Page<Map<String, Object>> page = tranToPage(finInsDrgAnalysisCondition);
		return returnPageResponse(page,finInsDrgAnalysisService.getAll(page,finInsDrgAnalysisCondition));
	}

	@RequestMapping(value = "/lastList")
	@ResponseBody
	public List<Map<String,Object>> lastList(){
		return finInsDrgAnalysisService.lastList();
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public Object del(@RequestBody FinInsDrgAnalysis finInsDrgAnalysis){
		Map<String,Object> result = Maps.newHashMap();
		if(finInsDrgAnalysisService.del(finInsDrgAnalysis) > 0){
			result.put("code","200");
			result.put("msg","保存成功");
		}else{
			result.put("code","500");
			result.put("msg","保存失败");
		}
		return result;
	}

	@RequestMapping(value = "/lastCommitData")
	@ResponseBody
	public List<FinInsDrgAnalysis> lastCommitData(){
		return finInsDrgAnalysisService.lastCommitData();
	}
}