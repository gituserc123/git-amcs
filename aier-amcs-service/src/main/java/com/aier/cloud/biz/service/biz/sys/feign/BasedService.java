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

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.domain.constant.BizConfig;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.common.util.AierConfigType;

/**
 * 病案信息
 * 
 * @author lic
 * @since 2019-06-06 10:21:21
 */
@FeignClient(name = "aier-service-based")
public interface BasedService {
	/**
	 * 获取参数值
	 * @param code
	 * @return
	 */
	@RequestMapping(value="/api/based/config/getValue", method=RequestMethod.POST)
	String getValue(@RequestParam(value = "code") BizConfig code);

	@RequestMapping(value = "/api/based/sysConfig/getValue", method = RequestMethod.POST)
	String getValue(AierConfigType type);

	@RequestMapping(value = "/api/based/sysConfig/getValues", method = RequestMethod.POST)
	Map<String, String> getValues(@RequestParam(value = "code") List<AierConfigType> types);

	@RequestMapping(value = "/api/based/dchDrugs/getThreeCatalog", method = RequestMethod.POST)
	List<Map> getHospItems(@RequestHeader(required=false, value="type") String type, @RequestBody Set<String> itemCodes);
    @PostMapping(value = "/api/aps/dcgMeritsLevel/page")
    PageResponse<Map<String, Object>> getDcgMeritsLevel(@RequestBody Map moduleCondition);
    

	@RequestMapping(value = "/api/based/dchMedicalService/query")
	public @ResponseBody PageResponse<Map> dchMedicalService(@RequestBody Map m);
	@RequestMapping(value = "/api/based/dchMaterial/query")
	public @ResponseBody PageResponse<Map> dchMaterial(@RequestBody Map m);
	@RequestMapping(value = "/api/based/dchDrugs/query")
	public @ResponseBody PageResponse<Map> dchDrugs(@RequestBody Map m);
}
