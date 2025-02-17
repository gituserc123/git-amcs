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
package com.aier.cloud.biz.service.biz.amcs.coll.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.coll.service.AmcsCollAttachmentService;
import com.aier.cloud.biz.service.biz.amcs.coll.entity.AmcsCollAttachment;
 
/**
 * AmcsCollAttachment
 * 医院采集信息附件表
 * @author 爱尔眼科
 * @since 2023-03-29 09:43:33
 */
@Api("医院采集信息附件表相关接口")
@Controller
@RequestMapping("/api/service/biz/amcs/coll/amcsCollAttachment")
public class AmcsCollAttachmentController extends BaseController{
 
	@Autowired
	private AmcsCollAttachmentService amcsCollAttachmentService;
	
	@ApiOperation(value="根据id查询AmcsCollAttachment医院采集信息附件表实体")
	@ApiParam(name="id", value="医院采集信息附件表的id", required=true)
	@RequestMapping(value = "/getAmcsCollAttachment")
	public @ResponseBody AmcsCollAttachment getAmcsCollAttachment(@RequestParam("id") Long id) {
		return amcsCollAttachmentService.selectById(id);
	}
}