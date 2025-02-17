package com.aier.cloud.biz.service.biz.sys.feign;
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

import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "aier-service-alis")
public interface AlisService {

	/**
	 * 提供给平台组的接口，根据hosp_id判断该医院已审核的报告数量，平台组定时判断这个count数，如果大于等于三，就认识alis项目已上线成功
	 * 
	 * @param hosp_id
	 * @return Integer
	 */
	@RequestMapping(value = "/api/outinterface/adp/checkAlisOnlineByHospId", method = RequestMethod.POST)
	public Map<String, Object> checkAlisOnlineByHospId(@RequestParam("hosp_id") Long hosp_id);
}