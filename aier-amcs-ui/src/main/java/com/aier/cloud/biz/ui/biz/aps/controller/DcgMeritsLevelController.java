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

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.request.condition.amcs.MeritsLevelCondition;
import com.aier.cloud.basic.api.request.domain.amcs.DcgMeritsLevel;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.aps.feign.DcgMeritsLevelService;



@Controller
@RequestMapping("/ui/aps/meritslevel")
public class DcgMeritsLevelController extends BaseController{

	@Autowired
	private DcgMeritsLevelService dcgMeritsLevelService;
	
	private static final String LIST = "aps/merits_level_list";
	private static final String CREATE = "aps/merits_level_create";
	private static final String UPDATE = "aps/merits_level_update";
	

	@RequiresPermissions("ApsDcgMeritsLevel:view")
	@PostMapping(value = "/getDcgMeritsLevel")
	public @ResponseBody DcgMeritsLevel getDcgMeritsLevel(@RequestParam("id") Long id) {
		return dcgMeritsLevelService.getDcgMeritsLevelById(id);
	}
	
	@RequiresPermissions("ApsDcgMeritsLevel:view")
    @GetMapping(value="/list")
    public String list(Map<String, Object> map) {
        return LIST;
    }
	
	@RequiresPermissions("ApsDcgMeritsLevel:view")
    @PostMapping(value="/page")
    public @ResponseBody PageResponse<Map<String, Object>>  page(Map<String, Object> map, MeritsLevelCondition condition) {
        PageResponse<Map<String, Object>> page = dcgMeritsLevelService.getPage(condition);
        return page;
    }
	
	@RequiresPermissions("ApsDcgMeritsLevel:save")
    @GetMapping(value="/create")
    public String preCreate(Map<String, Object> map) {
        return CREATE;
    }
	
	@RequiresPermissions("ApsDcgMeritsLevel:save")
    @PostMapping(value="/create")
    public @ResponseBody Map<String,Object> create(DcgMeritsLevel module) {
        module.setModifer(ShiroUtils.getId());
        module.setCreator(ShiroUtils.getId());
        String message = hasValid(module);
        if(StringUtils.isNotEmpty(message))  {
            return fail(message);
        }
        dcgMeritsLevelService.create(module);
        return success("添加绩效手术分级配置成功！");
    }
	
	@RequiresPermissions("ApsDcgMeritsLevel:edit")
    @GetMapping(value="/update/{id}")
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		DcgMeritsLevel module = dcgMeritsLevelService.getDcgMeritsLevelById(id);
        map.put("meritsLevel", module);
        return UPDATE;
    }
    
    @RequiresPermissions("ApsDcgMeritsLevel:edit")
    @PostMapping(value="/update")
    public @ResponseBody Map<String,Object> update(DcgMeritsLevel module) {
        module.setModifer(ShiroUtils.getId());
        String message = hasValid(module);
        if(StringUtils.isNotEmpty(message))  {
            return fail(message);
        }
        dcgMeritsLevelService.update(module);
        return success("修改绩效手术分级配置成功！");
    }
    
    /**
     * 检查系统编码是否唯一
     */
    @RequestMapping(value = "/checkName", method = RequestMethod.POST)
    public @ResponseBody boolean checkName(String previousName, String operationClassify) {
        if (StringUtils.isEmpty(operationClassify)) {
            return false;
        }
        if (StringUtils.isNotBlank(previousName) && previousName.equalsIgnoreCase(operationClassify)){
            return true;
        } 
        return dcgMeritsLevelService.getMeritsLevelUnique(previousName, operationClassify);
    }

}
