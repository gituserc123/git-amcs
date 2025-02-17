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
package com.aier.cloud.biz.ui.biz.aps.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.request.condition.amcs.MeritsConfigsCondition;
import com.aier.cloud.basic.api.request.domain.amcs.DcgMeritsConfigs;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.aps.feign.DcgMeritsConfigsService;

/**
 * DcgMeritsConfigs
 * 绩效联合术式配置
 * @author 爱尔眼科
 * @since 2022-02-11 15:44:54
 */
@Controller
@RequestMapping("/ui/aps/meritsConfigs")
public class DcgMeritsConfigsController extends BaseController{
	
	private static final String LIST = "aps/merits_configs_list";
	private static final String CREATE = "aps/merits_configs_create";
	private static final String UPDATE = "aps/merits_configs_update";

	@Autowired
	private DcgMeritsConfigsService dcgMeritsConfigsService;

	@RequiresPermissions("ApsDcgMeritsConfigs:view")
	@RequestMapping(value = "/getDcgMeritsConfigs")
	public @ResponseBody DcgMeritsConfigs getDcgMeritsConfigs(@RequestParam("id") Long id) {
		return dcgMeritsConfigsService.getDcgMeritsConfigs(id);
	}
	
	@RequiresPermissions("ApsDcgMeritsConfigs:view")
    @GetMapping(value="/list")
    public String list(Map<String, Object> map) {
        return LIST;
    }
	
	@RequiresPermissions("ApsDcgMeritsConfigs:view")
    @PostMapping(value="/page")
    public @ResponseBody PageResponse<Map<String, Object>>  page(Map<String, Object> map, MeritsConfigsCondition condition) {
        PageResponse<Map<String, Object>> page = dcgMeritsConfigsService.getPage(condition);
        return page;
    }
	
	@RequiresPermissions("ApsDcgMeritsConfigs:view")
	@RequestMapping(value = "/query")
    public @ResponseBody List<Map<String,Object>> query(@RequestParam(value="q",required=false) String  q) {
        /** 不输入任何查询关键字，不请求查询结果 */
        if (StringUtils.isBlank(q)) {
            return Collections.emptyList();
        }
        
        return dcgMeritsConfigsService.meritsLevelQuery(q);
    }
	
	@RequiresPermissions("ApsDcgMeritsConfigs:save")
    @GetMapping(value="/create")
    public String preCreate(Map<String, Object> map) {
        return CREATE;
    }
	
	@RequiresPermissions("ApsDcgMeritsConfigs:save")
    @PostMapping(value="/create")
    public @ResponseBody Map<String,Object> create(DcgMeritsConfigs module) {
        module.setModifer(ShiroUtils.getId());
        module.setCreator(ShiroUtils.getId());
        String message = hasValid(module);
        if(StringUtils.isNotEmpty(message))  {
            return fail(message);
        }
        dcgMeritsConfigsService.create(module);
        return success("添加绩效手术分级配置成功！");
    }
	
	@RequiresPermissions("ApsDcgMeritsConfigs:edit")
    @GetMapping(value="/update/{id}")
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		DcgMeritsConfigs module = dcgMeritsConfigsService.getDcgMeritsConfigs(id);
        map.put("meritsConfigs", module);
        return UPDATE;
    }
    
    @RequiresPermissions("ApsDcgMeritsConfigs:edit")
    @PostMapping(value="/update")
    public @ResponseBody Map<String,Object> update(DcgMeritsConfigs module) {
        module.setModifer(ShiroUtils.getId());
        String message = hasValid(module);
        if(StringUtils.isNotEmpty(message))  {
            return fail(message);
        }
        dcgMeritsConfigsService.update(module);
        return success("修改绩效联合术式配置成功！");
    }
}
