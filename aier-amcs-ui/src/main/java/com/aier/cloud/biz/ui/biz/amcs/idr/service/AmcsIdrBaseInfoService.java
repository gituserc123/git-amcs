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
 * AmcsIdrBaseInfo
 * 传染病基本信息表
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.domain.idr.AmcsIdrBaseInfo;
import com.aier.cloud.basic.api.request.condition.based.BasedCommonCondition;
import com.aier.cloud.basic.api.request.condition.based.DcgDiagCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

@FeignClient(name = "aier-amcs-service")
public interface AmcsIdrBaseInfoService {

	@PostMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/getAmcsIdrBaseInfo")
	@ResponseBody
	Map<String, Object> getAmcsIdrBaseInfo(@RequestParam("id") Long id);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/save", method = RequestMethod.POST)
	AmcsIdrBaseInfo save(AmcsIdrBaseInfo apply, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/getList", method = RequestMethod.POST)
	@ResponseBody
	Map<String, Object> getList(BasedCommonCondition d);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/getDcgDiagList", method = RequestMethod.POST)
	@ResponseBody
	Object getDcgDiagList(DcgDiagCondition d);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/getHospStaff", method = RequestMethod.POST)
	@ResponseBody
	List<Map> getHospStaff(StaffCondition d);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/getByMainId", method = RequestMethod.POST)
	@ResponseBody
	Object getByMainId(@RequestParam(value = "id") Long id);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/getMainByCondForList", method = RequestMethod.POST)
	List<AmcsIdrBaseInfo> getMainByCondForList(AmcsIdrBaseInfo apply);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/audit", method = RequestMethod.POST)
	AmcsIdrBaseInfo audit(AmcsIdrBaseInfo apply, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/upload", method = RequestMethod.POST)
	String upload(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/del", method = RequestMethod.POST)
	String del(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/uploadDel", method = RequestMethod.POST)

	String uploadDel(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/uploadMod", method = RequestMethod.POST)

	String uploadMod(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/uploadAud", method = RequestMethod.POST)
	String uploadAud(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/uploadRes", method = RequestMethod.POST)
	String uploadRes(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/auditById", method = RequestMethod.POST)
	String auditById(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);
	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/auditUn", method = RequestMethod.POST)
	String auditUn(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

	@RequestMapping(value = "/api/amcs/idr/amcsIdrBaseInfo/uploadSeach", method = RequestMethod.POST)
	String uploadSeach(@RequestParam(value = "id") String id, @RequestParam(value = "loginUserId") Long loginUserId,
			@RequestParam(value = "loginUserName") String loginUserName);

}
