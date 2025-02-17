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

import com.aier.cloud.basic.api.request.condition.amcs.OprCalResultCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.biz.ui.biz.aps.feign.DcgOprRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.web.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * DcgOprRecord
 * 人资手术记录表
 * @author 爱尔眼科
 * @since 2022-03-19 23:41:00
 */
@Controller
@RequestMapping("/ui/aps/dcgOprRecord")
public class DcgOprRecordController extends BaseController{
	private static final String LIST = "aps/operation/rec_list";

	@Autowired
	private DcgOprRecordService recService;

	//http://localhost:8056/ui/aps/dcgOprRecord/list#
	@RequiresPermissions("ApsDcgOprRecord:view")
	@GetMapping(value="/list")
	public String list(Map<String, Object> map) {
		return LIST;
	}

	@RequiresPermissions("ApsDcgOprRecord:view")
	@PostMapping(value="/page")
	public @ResponseBody
	PageResponse<Map<String, Object>> page(Map<String, Object> map, OprCalResultCondition condition) {
		PageResponse<Map<String, Object>> page = recService.getPage(condition);
		return page;
	}
}
