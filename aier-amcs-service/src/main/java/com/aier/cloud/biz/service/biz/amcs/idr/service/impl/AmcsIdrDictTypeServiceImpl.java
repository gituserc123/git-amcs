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
package com.aier.cloud.biz.service.biz.amcs.idr.service.impl;

import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.apache.commons.lang3.EnumUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.aier.cloud.biz.service.biz.amcs.idr.service.AmcsIdrDictTypeService;
import com.aier.cloud.center.common.context.UserContext;
import com.aier.cloud.basic.api.response.domain.inp.InpRegist;
import com.aier.cloud.basic.api.response.domain.outp.OutpRegist;
import com.aier.cloud.basic.api.response.domain.outp.PatientInfo;
//import com.aier.cloud.basic.api.response.domain.outp.PatientInfo;
import com.aier.cloud.basic.common.convert.EnumDict;
import com.aier.cloud.biz.service.biz.amcs.idr.dao.AmcsIdrDictTypeMapper;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrBaseInfo;
import com.aier.cloud.biz.service.biz.amcs.idr.entity.AmcsIdrDictType;
import com.aier.cloud.biz.service.biz.amcs.idr.enums.AmcsIdrDictEnum;
import com.aier.cloud.biz.service.biz.amcs.idr.feign.MedicalFeignService;

/**
 * AmcsIdrDictType 传染病字典表
 * 
 * @author 爱尔眼科
 * @since 2023-04-27 15:09:50
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AmcsIdrDictTypeServiceImpl extends ServiceImpl<AmcsIdrDictTypeMapper, AmcsIdrDictType>
		implements AmcsIdrDictTypeService {
	private final ConcurrentMap<String, List> local = Maps.newConcurrentMap();
	@Autowired
	private MedicalFeignService medicalFeignService;

	@Override
	public List<AmcsIdrDictType> getTypeCodeForList(AmcsIdrDictType apply) {

		return this.baseMapper.getTypeCodeForList(apply);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public List<Map> getEnum(AmcsIdrDictType c) {

		String packageName = "com.aier.cloud.biz.service.biz.amcs.idr.enums." + c.getValueCode();
		List value = local.get(packageName);
		if (value == null) {
			try {
				Class cC = Class.forName(packageName);
				this.add(cC);
			} catch (Exception e) {
				local.putIfAbsent(packageName, Collections.emptyList());
			}
			return local.get(packageName);
		} else {
			return value;
		}

	}

	@Override
	public AmcsIdrBaseInfo getPatient(String patientName) {
		InpRegist inpRegist = null;
		Long id = (long) 0;
		AmcsIdrBaseInfo amcsIdrBaseInfo = new AmcsIdrBaseInfo();
		Date inpTime = new Date();
		String iDate = DateFormatUtils.format(inpTime, "yyyy-MM-dd");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			inpTime = sdf.parse(iDate);
		} catch (Exception e) {

		}
		amcsIdrBaseInfo.setDiagnosisdate(inpTime);
		amcsIdrBaseInfo.setCardfillingtime(inpTime);
		amcsIdrBaseInfo.setOnsetdate(inpTime);
		amcsIdrBaseInfo.setCreateDate(inpTime);
		List<OutpRegist> inpRegist1 = new ArrayList();
		if (patientName.substring(0, 2).toUpperCase().equals("ZY")) {
			inpRegist = medicalFeignService.getInpRegistByInpNumber(patientName);
			id = inpRegist.getPatientId();
			amcsIdrBaseInfo.setPatientname(inpRegist.getPatientName());
			amcsIdrBaseInfo.setHospitalnum(inpRegist.getInpNumber());
			amcsIdrBaseInfo.setOnsetdate(inpRegist.getInpTime());
			amcsIdrBaseInfo.setDiagnosisdate(inpRegist.getInpTime());

		} else {
			inpRegist1 = medicalFeignService.getRegistByRegNumber(patientName);
			if (inpRegist1 != null && inpRegist1.size() > 0) {
				OutpRegist outpRegist = inpRegist1.get(0);
				id = outpRegist.getPatientId();
				amcsIdrBaseInfo.setPatientname(outpRegist.getPatientName());
				amcsIdrBaseInfo.setPatientnum(outpRegist.getRegNumber());
				amcsIdrBaseInfo.setOnsetdate(outpRegist.getRegDate());
				amcsIdrBaseInfo.setDiagnosisdate(outpRegist.getRegDate());

			}
		}
		if (!id.equals((long) 0)) {
			PatientInfo patientInfo = medicalFeignService.getPatientInfoByPatientId(id);
			if (patientInfo != null) {
				amcsIdrBaseInfo.setIdcardtype(patientInfo.getDocumentType().toString());
				amcsIdrBaseInfo.setIdcardcode(patientInfo.getIdNumber());
				if (patientInfo.getNation() != null) {
					int iI = Integer.parseInt(patientInfo.getNation());
					amcsIdrBaseInfo.setNationcode(String.valueOf(iI));
				}
				amcsIdrBaseInfo.setEmployerorgname(patientInfo.getOrganizationName());
				amcsIdrBaseInfo.setTelecom(patientInfo.getTel1());
				if(patientInfo.getInfectiousPatientType()!=null) {
					amcsIdrBaseInfo.setIdrOccupationcode(convertInfectious(patientInfo.getInfectiousPatientType()));
				}
				amcsIdrBaseInfo.setLivingaddresscode(patientInfo.getCurrentAddress());
				if (patientInfo.getSex() != null) {
					amcsIdrBaseInfo.setGendercode(Integer.parseInt(patientInfo.getSex().toString()));
				}

				amcsIdrBaseInfo.setBirthdate(patientInfo.getBirthday());

				amcsIdrBaseInfo.setMaritalstatuscode(
						patientInfo.getMarriage() == null ? "" : patientInfo.getMarriage().toString() + "0");

			}

		}

		return amcsIdrBaseInfo;

	}

	private String convertInfectious(Integer infectiousPatientType) {
		String iConvert = infectiousPatientType.toString();
		switch (infectiousPatientType) {
		case 21:
			iConvert = "99";
			break; // 可选 
		default: // 可选
			// 语句
		}
		if(iConvert.length()==1)
		{
			iConvert = "0"+iConvert;	
		}
		return iConvert ;
	}

	/**
	 * 添加一個字典配置項
	 * 
	 * @param clazz
	 */
	private void add(Class<? extends EnumDict> clazz) {
		if (local.containsKey(clazz.getName())) {
			return;
		}
		List<EnumDict> enums = EnumUtils.getEnumList((Class) clazz);
		List<Map<String, String>> result = Lists.newArrayList();
		for (EnumDict e : enums) {
			Map<String, String> value = Maps.newHashMap();
			value.put("valueCode", e.getEnumCode());
			value.put("valueDesc", e.getEnumDesc());
			value.put("frstSpell", e.getFirstSpell());
			result.add(value);
		}
		local.putIfAbsent(clazz.getName(), result);
	}

	@Override
	public List<AmcsIdrDictType> getAmcsIdrDictType(AmcsIdrDictType apply) {

		List<AmcsIdrDictType> applyList = this.baseMapper.getAmcsIdrDictType(apply);
		if (applyList == null || applyList.size() < 1) {
			AmcsIdrDictType applyList1 = new AmcsIdrDictType();
			applyList1.setTypeCode(AmcsIdrDictEnum.Ktoken.getEnumCode());
			applyList1.setTypeDesc(AmcsIdrDictEnum.Ktoken.getEnumDesc());
			applyList1.setValueCode(UserContext.getInstName());
			applyList1.setValueCode(UserContext.getTenantId().toString());
			applyList1.setValueDesc(UserContext.getInstName());
			applyList1.setModifer(UserContext.getUserId());
			applyList1.setModifyDate(new Date());
			applyList1.setUsingSign(1);
			baseMapper.insert(applyList1);
			applyList.add(applyList1);
		}
//		for (AmcsIdrDictType code : applyList) {
//			if(apply.getTypeCode().equals(AmcsIdrDictEnum.Ktoken.getEnumCode())){
//			//查询查询url
//			String i1=code.getUrl()==null ? "": code.getUrl();
//			i1+=code.getFirstSpell()==null ? "":code.getFirstSpell();
//			code.setUrl(i1 );
//			}
//		}
//		  
		return applyList;
	}

	@Override
	public AmcsIdrDictType save(AmcsIdrDictType apply, Long loginUserId, String loginUserName) {
//		if(apply.getTypeCode().equals(AmcsIdrDictEnum.Ktoken.getEnumCode())&& (apply.getUrl()!=null && apply.getUrl().length()>50))
//		{
//			//保存查询url
//			String i1=apply.getUrl().substring(0,50) ;
//			String i2=apply.getUrl().substring(50) ;
//			apply.setUrl(i1);
//			apply.setFirstSpell(i2);
//			
//			
//		}
		apply.setModifer(loginUserId);
		apply.setModifyDate(new Date());
		// apply.setTypeCode(AmcsIdrDictEnum.Ktoken.getEnumCode());
		// apply.setTypeDesc(AmcsIdrDictEnum.Ktoken.getEnumDesc());
		this.baseMapper.updateById(apply);
		return apply;
	}

	@Override
	public int getdiagCodeIDR(String diagCode) {
		AmcsIdrDictType apply = new AmcsIdrDictType();
		apply.setTypeCode("DiseaseName");
		apply.setUsingSign(1);
		apply.setValueCode(diagCode);
		List<AmcsIdrDictType> mAmcsIdrDictType = baseMapper.getTypeCodeForList(apply);
		int iSize = 0;
		if (mAmcsIdrDictType != null && mAmcsIdrDictType.size() > 0) {
			iSize = 1;
		}
		return iSize;// amcsIdrDictTypeService.getTypeCodeForList(apply);
	}

}
