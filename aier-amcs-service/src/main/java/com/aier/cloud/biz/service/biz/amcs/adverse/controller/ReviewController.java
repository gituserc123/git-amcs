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

import com.aier.cloud.api.amcs.adverse.condition.ReviewCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.biz.service.biz.amcs.adverse.service.ReviewService;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.Review;

import java.util.List;
import java.util.Map;

/**
 * Review
 * 
 * @author 爱尔眼科
 * @since 2022-08-01 09:45:25
 */
@Api("相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/adverse/review")
public class ReviewController extends BaseController {
 
	@Autowired
	private ReviewService reviewService;
	
	@ApiOperation(value="根据id查询Review实体")
	@ApiParam(name="id", value="的id", required=true)
	@RequestMapping(value = "/getReview")
	public @ResponseBody Review getReview(@RequestParam("id") Long id) {
		return reviewService.selectById(id);
	}

	@RequestMapping(value="/getAll")
	@ResponseBody
	public PageResponse<Map<String, Object>> getAll(@RequestBody ReviewCondition cond){
		Page<Map<String, Object>> page = tranToPage(cond);
		add("creator", "t_sys_staff|id|name", "creatorName");
		return returnPageResponse(page,reviewService.getAll(cond));
	}

	@RequestMapping(value="/save")
	@ResponseBody
	public Boolean save(@RequestBody Review review){

		return reviewService.save(review);
	}
}