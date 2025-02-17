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

import java.util.Date;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 
 * 
 * @author lic
 * @since 2019-06-06 10:21:21
 */
@FeignClient(name = "aier-service-medical")
public interface TransInpRecordService {

	/**
	 * 
	 * @return Integer
	 * @author lc
	 */
	@RequestMapping(value = "/api/trans/inpRecord/getDischargeAmount", method = RequestMethod.POST)
	public Integer getDischargeAmount(@RequestParam(value = "hospId") Long hospId,
			@RequestParam(value = "transDate") Date transDate);

}
