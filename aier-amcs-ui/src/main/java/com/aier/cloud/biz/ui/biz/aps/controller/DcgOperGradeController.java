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

import com.aier.cloud.basic.api.request.condition.amcs.OperGradeCondition;
import com.aier.cloud.basic.api.request.domain.amcs.DcgMeritsLevel;
import com.aier.cloud.basic.api.request.domain.amcs.DcgOperGrade;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.aps.feign.DcgMeritsLevelService;
import com.aier.cloud.biz.ui.biz.aps.feign.DcgOperGradeService;

/**
 * DcgOperGrade
 * 手术项目对应手术分级配置
 * @author 爱尔眼科
 * @since 2022-02-11 15:44:54
 */
@Controller
@RequestMapping("/ui/aps/operGrade")
public class DcgOperGradeController extends BaseController{
	
	private static final String LIST = "aps/oper_grade_list";
	private static final String CREATE = "aps/oper_grade_create";
	private static final String UPDATE = "aps/oper_grade_update";

	@Autowired
	private DcgOperGradeService dcgOperGradeService;
	
	@Autowired
	private DcgMeritsLevelService dcgMeritsLevelService;

	@RequiresPermissions("ApsDcgOperGrade:view")
	@RequestMapping(value = "/getDcgOperGrade")
	public @ResponseBody DcgOperGrade getDcgOperGrade(@RequestParam("id") Long id) {
		return dcgOperGradeService.getDcgOperGrade(id);
	}
	
	@RequiresPermissions("ApsDcgOperGrade:view")
    @GetMapping(value="/list")
    public String list(Map<String, Object> map) {
        return LIST;
    }
	
	@RequiresPermissions("ApsDcgOperGrade:view")
    @PostMapping(value="/page")
    public @ResponseBody PageResponse<Map<String, Object>>  page(Map<String, Object> map, OperGradeCondition condition) {
        PageResponse<Map<String, Object>> page = dcgOperGradeService.getPage(condition);
        return page;
    }
	
	@RequiresPermissions("ApsDcgOperGrade:view")
	@RequestMapping(value = "/query")
    public @ResponseBody List<Map<String,Object>> query(@RequestParam(value="q",required=false) String  q) {
        /** 不输入任何查询关键字，不请求查询结果 */
        if (StringUtils.isBlank(q)) {
            return Collections.emptyList();
        }
        
        return dcgOperGradeService.apsQuery(q);
    }
	
	@RequiresPermissions("ApsDcgOperGrade:save")
    @GetMapping(value="/create")
    public String preCreate(Map<String, Object> map) {
        return CREATE;
    }
	
	@RequiresPermissions("ApsDcgOperGrade:save")
    @PostMapping(value="/create")
    public @ResponseBody Map<String,Object> create(DcgOperGrade module) {
        module.setModifer(ShiroUtils.getId());
        module.setCreator(ShiroUtils.getId());
        String message = hasValid(module);
        if(StringUtils.isNotEmpty(message))  {
            return fail(message);
        }
        dcgOperGradeService.create(module);
        return success("添加手术项目对应手术分级配置成功！");
    }
	
	@RequiresPermissions("ApsDcgOperGrade:edit")
    @GetMapping(value="/update/{id}")
    public String preUpdate(@PathVariable Long id, Map<String, Object> map) {
		DcgOperGrade module = dcgOperGradeService.getDcgOperGrade(id);
		DcgMeritsLevel meritsLevel = dcgMeritsLevelService.getDcgMeritsLevelById(module.getOperationClassify());
        map.put("operGrade", module);
        map.put("meritsLevel", meritsLevel);
        return UPDATE;
    }
    
    @RequiresPermissions("ApsDcgOperGrade:edit")
    @PostMapping(value="/update")
    public @ResponseBody Map<String,Object> update(DcgOperGrade module) {
        module.setModifer(ShiroUtils.getId());
        String message = hasValid(module);
        if(StringUtils.isNotEmpty(message))  {
            return fail(message);
        }
        dcgOperGradeService.update(module);
        return success("修改手术项目对应手术分级配置成功！");
    }
}
