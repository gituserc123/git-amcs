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

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.condition.EventConfigCondition;
import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.utils.NodeUtil;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeBasicInfoService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeCommonService;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.NodeConfigService;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.EventConfig;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * NodeConfig
 * 不良事件节点配置表
 * @author 爱尔眼科
 * @since 2022-07-08 09:35:14
 */
@Api("不良事件节点配置表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/nodeConfig")
public class NodeConfigController extends BaseController {
 
	@Autowired
	private NodeConfigService nodeConfigService;
	
	@Autowired
	private AeBasicInfoService aeBasicInfoService;
	
	@ApiOperation(value="根据id查询NodeConfig不良事件节点配置表实体")
	@ApiParam(name="id", value="不良事件节点配置表的id", required=true)
	@RequestMapping(value = "/getNodeConfig")
	public @ResponseBody
	EventConfig getNodeConfig(@RequestParam("id") Long id) {
		return nodeConfigService.selectById(id);
	}

	@RequestMapping(value = "/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody EventConfigCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		return returnPageResponse(page,nodeConfigService.getAll(page,cond));
	}

	@RequestMapping(value ="/save")
	@ResponseBody
	public Boolean save(@RequestBody EventConfig eventConfig){
		return nodeConfigService.save(eventConfig);
	}

	@RequestMapping(value = "/getNodeConfigByCode")
	public @ResponseBody
	EventConfig getNodeConfigByCode(@RequestParam("eventCode") String eventCode) {
		return nodeConfigService.getNodeConfigByCode(eventCode);
	}
	
	@ApiOperation(value="根据事件ID获取该事件的所有前置信息")
	@RequestMapping(value = "/getPreNodeList")
	@ResponseBody
	public PageResponse<Map<String, Object>> getPreNodeList(@RequestBody AeInfoCondition cond){
		Long basicId = cond.getBasicId();
		if(ObjectUtils.isEmpty(basicId)) return null;
	  	
    	List< Map<String,Object>> nodeList = Lists.newArrayList();
    	AeBasicInfo basic = aeBasicInfoService.selectById(basicId);
    	if(basic == null) return null;
    	String eventCode = basic.getEventCode();
    	Integer nodeValue = basic.getNode();
    	EventConfig eventConfig = nodeConfigService.getNodeConfigByCode(eventCode);
    	List<NodeEnum> list=NodeUtil.NodeList(eventConfig.getNodeValue().intValue());
    	for(NodeEnum ne:NodeEnum.values()){
    		Map<String,Object>  map=new HashMap<>();
			if(list.indexOf(ne)>=0){
				if(ne.getValue().equals(nodeValue)) break;
				map.put("nodeName",ne.getEnumDesc());
				map.put("nodeValue", ne.getEnumCode());
				nodeList.add(map);
			}
	    }
    	Page<Map<String, Object>> page =tranToPage(cond);
    	return returnPageResponse(page, nodeList);
	}
	
}