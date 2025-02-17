package com.aier.cloud.biz.ui.biz.adverse.feign;
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

import com.aier.cloud.basic.api.request.condition.base.PatientInfoCondition;
import com.aier.cloud.basic.api.request.condition.outp.PatientCardCondition;
import com.aier.cloud.basic.api.request.domain.outp.MedicareCardRequestParam;
import com.aier.cloud.basic.api.request.domain.outp.PatientInfo;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.common.ResponseResult;
import com.aier.cloud.basic.api.response.domain.other.OneStopPatientVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 患者信息
 * 
 * @author lic
 * @since 2018-02-02 10:21:21
 */
@FeignClient(name = "aier-service-medical")
public interface OutpPatientInfoService {

	/**
	 * (模糊查询患者列表)
	 * 
	 * @Title: blurFindByParam
	 * @param cond
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/blurFindByParam", method = RequestMethod.POST, consumes = "application/json")
	List<Map<String, Object>> blurFindByParam(@RequestBody PatientInfoCondition cond);

	/**
	 * (按卡号查询患者)
	 * 
	 * @Title: pageBlurFindByParam
	 * @param cond
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/pageFindByCardNumber", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> pageFindByCardNumber(@RequestBody PatientCardCondition cond);

	/**
	 * (模糊查询患者列表)
	 * 
	 * @Title: pageBlurFindByParam
	 * @param cond
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/pageBlurFindByParam", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> pageBlurFindByParam(@RequestBody PatientInfoCondition cond);

	/**
	 * (模糊查询患者列表)
	 * 
	 * @Title: blurUnionFindByParam
	 * @param cond
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/blurUnionFindByParam", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> blurUnionFindByParam(@RequestBody PatientInfoCondition cond);

	/**
	 * (模糊查询患者、潜客列表)
	 *
	 * @Title: blurUnionFindDeclarePatientByParam
	 * @param cond
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/blurUnionFindDeclarePatientByParam", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> blurUnionFindDeclarePatientByParam(@RequestBody PatientInfoCondition cond);

	/**
	 * (患者列表)
	 * 
	 * @Title: findByParam
	 * @param entity
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/findByParam", method = RequestMethod.POST, consumes = "application/json")
	List<Map<String, Object>> findByParam(@RequestBody PatientInfo entity);

	/**
	 * (保存患者)
	 * 
	 * @Title: save
	 * @param entity
	 * @return Boolean
	 */
	@RequestMapping(value = "/api/outp/patientInfo/insert", method = RequestMethod.POST, consumes = "application/json")
	PatientInfo insert(@RequestBody PatientInfo entity);

	/**
	 * (保存患者)
	 * 
	 * @Title: updateById
	 * @param entity
	 * @return PatientInfo
	 */
	@RequestMapping(value = "/api/outp/patientInfo/updateById", method = RequestMethod.POST, consumes = "application/json")
	PatientInfo updateById(@RequestBody PatientInfo entity);

	/**
	 * (保存患者)
	 * 
	 * @Title: eitdAllColumnById
	 * @param entity
	 * @return Boolean
	 */
	@RequestMapping(value = "/api/outp/patientInfo/updateByIdAhis", method = RequestMethod.POST, consumes = "application/json")
	Boolean updateByIdAhis(@RequestBody PatientInfo entity);

	/**
	 * (保存患者)
	 *
	 * @Title: save
	 * @param entity
	 * @return Boolean
	 */
	@RequestMapping(value = "/api/outp/patientInfo/updateOutById", method = RequestMethod.POST, consumes = "application/json")
	PatientInfo updateOutById(@RequestBody PatientInfo entity);

	/**
	 * (保存患者姓名)
	 *
	 * @Title: save
	 * @param entity
	 * @return Boolean
	 */
	@RequestMapping(value = "/api/outp/patientInfo/updateNameById", method = RequestMethod.POST, consumes = "application/json")
	PatientInfo updateNameById(@RequestBody PatientInfo entity);

	/**
	 * (按ID查询患者)
	 * 
	 * @Title: selectById
	 * @param id
	 * @return PatientInfo
	 */
	@RequestMapping(value = "/api/outp/patientInfo/selectById", method = RequestMethod.POST, consumes = "application/json")
	PatientInfo selectById(@RequestParam("id") Long id);

	@RequestMapping(value = "/api/outp/patientInfo/getByNameAndIdNumber", method = RequestMethod.POST, consumes = "application/json")
	List<PatientInfo> getByNameAndIdNumber(@RequestBody PatientInfo patientInfo);
	
	@RequestMapping(value = "/api/outp/patientInfo/getArchives", method = RequestMethod.POST, consumes = "application/json")
	List<PatientInfo> getArchives(@RequestBody PatientInfo patientInfo);
	
	/**
	 * (建档)
	 * 
	 * @Title: createArchives
	 * @param entity
	 * @param idCard
	 * @param cardType
	 * @return PatientInfo
	 */
	@RequestMapping(value = "/api/outp/patientInfo/createArchives", method = RequestMethod.POST, consumes = "application/json")
	PatientInfo createArchives(@RequestBody PatientInfo entity, @RequestParam("idCard") String idCard,
			@RequestParam("cardType") String cardType);

	/**
	 * (建档)
	 * 
	 * @Title: createArchives
	 * @param idCard
	 * @param cardType
	 * @return PatientInfo
	 */
	@RequestMapping(value = "/api/outp/patientInfo/medicalCreateArchives", method = RequestMethod.POST, consumes = "application/json")
	PatientInfo medicalCreateArchives(@RequestBody MedicareCardRequestParam param,
			@RequestParam("idCard") String idCard, @RequestParam("cardType") String cardType);

	/**
	 * (按证件号码或姓名查询患者列表)
	 * 
	 * @Title: findByDocumentOrName
	 * @param obj
	 * @return List<PatientInfo>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/findByDocumentOrName", method = RequestMethod.POST, consumes = "application/json")
	List<Map<String, Object>> findByDocumentOrName(@RequestBody PatientInfo obj);

	/**
	 * (读卡没有查询到患者，按页面表单信息查询患者列表)
	 * 
	 * @Title: findPatientInfoList
	 * @param obj
	 * @return List<PatientInfo>
	 */
	@RequestMapping(value = "/api/outp/patientInfo/findPatientInfoList", method = RequestMethod.POST, consumes = "application/json")
	List<Map<String, Object>> findPatientInfoList(@RequestBody PatientInfo obj);

}