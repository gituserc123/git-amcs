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
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.response.domain.outp.OutpRegist;

/**
 * 挂号信息
 * 
 * @author lic
 * @since 2019-06-06 10:21:21
 */
@FeignClient(name = "aier-service-medical")
public interface RegistService {

	/**
	 * 
	 * @return List<OutpRegist>
	 * @author lc
	 */
	@RequestMapping(value = "/api/outp/regist/getByHospIdNoTid", method = RequestMethod.POST)
	public List<OutpRegist> getByHospIdNoTid(@RequestParam("hospId") Long hospId);

	/**
	 * 
	 * @return Integer
	 * @author lc
	 */
	@RequestMapping(value = "/api/outp/regist/getOutpNumToday", method = RequestMethod.POST)
	public Integer getOutpNumToday(@RequestParam(value = "hospId") Long hospId,
			@RequestParam(value = "regDate") Date regDate);

	/**
	 * 
	 * @return Integer
	 * @author lc
	 */
	@RequestMapping(value = "/api/outp/regist/getOutpNum", method = RequestMethod.POST)
	public Integer getOutpNum(@RequestParam(value = "regDate") Date regDate);

}
