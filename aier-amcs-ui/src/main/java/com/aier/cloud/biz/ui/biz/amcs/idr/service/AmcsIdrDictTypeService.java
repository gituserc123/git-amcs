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
package com.aier.cloud.biz.ui.biz.amcs.idr.service;

/**
 * AmcsIdrDictType
 * 传染病字典表
 * @author 爱尔眼科
 * @since 2023-04-27 15:09:50
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
 
import com.aier.cloud.api.amcs.domain.idr.AmcsIdrBaseInfo;
import com.aier.cloud.api.amcs.domain.idr.AmcsIdrDictType; 

import java.util.List;
import java.util.Map;
 

@FeignClient(name = "aier-amcs-service")
public interface AmcsIdrDictTypeService{

	@RequestMapping(value = "/api/amcs/idr/amcsIdrDictType/getAmcsIdrDictType")
    @ResponseBody
    Map<String,Object> getAmcsIdrDictType(@RequestParam("id") Long id);
    
   
	@PostMapping(value = "/api/amcs/idr/amcsIdrDictType/getTypeCodeForList")
	@ResponseBody
	List<AmcsIdrDictType> getTypeCodeForList(@RequestBody AmcsIdrDictType apply);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrDictType/getEnum", method = RequestMethod.POST)
	@ResponseBody
	List<Map> getEnum(@RequestBody AmcsIdrDictType c);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrDictType/getPatient", method = RequestMethod.POST)
	@ResponseBody

	Object  getPatient(@RequestParam("patientName")  String patientName);

	@PostMapping(value = "/api/amcs/idr/amcsIdrDictType/getAmcsIdrDictType")
	@ResponseBody
	List<AmcsIdrDictType> getAmcsIdrDictType(@RequestBody AmcsIdrDictType apply);
	 
	@RequestMapping(value = "/api/amcs/idr/amcsIdrDictType/save", method = RequestMethod.POST)	 
	AmcsIdrDictType save(AmcsIdrDictType apply, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName); 
}
