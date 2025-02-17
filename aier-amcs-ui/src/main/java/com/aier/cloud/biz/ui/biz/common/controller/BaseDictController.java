package com.aier.cloud.biz.ui.biz.common.controller;

/*
 * Copyright © 2004-2018 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 版权所有
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

import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.common.feign.DictService;

/**
 * 
 * @author 爱尔眼科
 * @since 2018-02-06 14:45:09
 */
@Controller
@RequestMapping("/ui/base/dict")
public class BaseDictController extends BaseController {

	@Autowired
	private DictService ds;

	@RequestMapping(value = "/getListForComb")
	public @ResponseBody Object getListForComb(@RequestParam("tag") String tag) {
		return easyuiResult(ds.getList(tag, ""));
	}

	@RequestMapping(value = "/getList")
	public @ResponseBody Object getList(@RequestParam("paraType") String paraType) {
		return this.success(ds.getList(paraType, ""));
	}

	@RequestMapping(value = "/getMoreList")
	public @ResponseBody Object getMoreList(@RequestParam("paraType[]") List<String> paraTypes) {
		if (CollectionUtils.isNotEmpty(paraTypes)) {
			return this.success(ds.getMoreList(paraTypes));
		}
		return this.success(Collections.EMPTY_MAP);
	}

}