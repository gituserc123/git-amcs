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
package com.aier.cloud.biz.service.biz.amcs.fin.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_FIN_PERSON
 * 医保系统相关人员表
 * @author 爱尔眼科
 * @since 2023-08-29 15:14:28
 */
@TableName("T_FIN_PERSON")
public class Person extends BaseEntity<Person> {
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 财务负责人*/
	@TableField(value="fin_per_in_charge")
	@Length(max=20) private String finPerInCharge;
	
	/** 职务*/
	@TableField(value="posi")
	@Length(max=20) private String posi;
	
	/** 联系方式*/
	@TableField(value="cont_info")
	@Length(max=20) private String contInfo;
	
	/** 医保负责人*/
	@TableField(value="med_ins_per_in_charge")
	@Length(max=20) private String medInsPerInCharge;
	
	/** 医保联系方式*/
	@TableField(value="ins_cont_info")
	@Length(max=20) private String insContInfo;
	
	/** 医保专员1*/
	@TableField(value="med_ins_spe1")
	@Length(max=20) private String medInsSpe1;
	
	/** 医保专员1联系方式*/
	@TableField(value="spe1_cont_info")
	@Length(max=20) private String spe1ContInfo;
	
	/** 医保专员2*/
	@TableField(value="med_ins_spe2")
	@Length(max=20) private String medInsSpe2;
	
	/** 医保专员2联系方式*/
	@TableField(value="spe2_cont_info")
	@Length(max=20) private String spe2ContInfo;
	
	/** 医保专员3*/
	@TableField(value="med_ins_spe3")
	@Length(max=20) private String medInsSpe3;
	
	/** 医保专员3联系方式*/
	@TableField(value="spe3_cont_info")
	@Length(max=20) private String spe3ContInfo;
	
	/** 价格负责人*/
	@TableField(value="pri_per_in_charge")
	@Length(max=20) private String priPerInCharge;
	
	/** 价格联系方式*/
	@TableField(value="pri_cont_info")
	@Length(max=20) private String priContInfo;
	
	/** 价格专干1*/
	@TableField(value="pri_spe1")
	@Length(max=20) private String priSpe1;
	
	/** 价格专干1联系方式*/
	@TableField(value="spe1_pri_cont_info")
	@Length(max=20) private String spe1PriContInfo;
	
	/** 价格专干2*/
	@TableField(value="pri_spe2")
	@Length(max=20) private String priSpe2;
	
	/** 价格专干2联系方式*/
	@TableField(value="spe2_pri_cont_info")
	@Length(max=20) private String spe2PriContInfo;
	
	/** 价格专干3*/
	@TableField(value="pri_spe3")
	@Length(max=20) private String priSpe3;
	
	/** 价格专干3联系方式*/
	@TableField(value="spe3_pri_cont_info")
	@Length(max=20) private String spe3PriContInfo;
	

	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public String getFinPerInCharge() {
		return finPerInCharge;
	}
	public void setFinPerInCharge(String finPerInCharge) {
		this.finPerInCharge = finPerInCharge;
	}
	public String getPosi() {
		return posi;
	}
	public void setPosi(String posi) {
		this.posi = posi;
	}
	public String getContInfo() {
		return contInfo;
	}
	public void setContInfo(String contInfo) {
		this.contInfo = contInfo;
	}
	public String getMedInsPerInCharge() {
		return medInsPerInCharge;
	}
	public void setMedInsPerInCharge(String medInsPerInCharge) {
		this.medInsPerInCharge = medInsPerInCharge;
	}
	public String getInsContInfo() {
		return insContInfo;
	}
	public void setInsContInfo(String insContInfo) {
		this.insContInfo = insContInfo;
	}
	public String getMedInsSpe1() {
		return medInsSpe1;
	}
	public void setMedInsSpe1(String medInsSpe1) {
		this.medInsSpe1 = medInsSpe1;
	}
	public String getSpe1ContInfo() {
		return spe1ContInfo;
	}
	public void setSpe1ContInfo(String spe1ContInfo) {
		this.spe1ContInfo = spe1ContInfo;
	}
	public String getMedInsSpe2() {
		return medInsSpe2;
	}
	public void setMedInsSpe2(String medInsSpe2) {
		this.medInsSpe2 = medInsSpe2;
	}
	public String getSpe2ContInfo() {
		return spe2ContInfo;
	}
	public void setSpe2ContInfo(String spe2ContInfo) {
		this.spe2ContInfo = spe2ContInfo;
	}
	public String getMedInsSpe3() {
		return medInsSpe3;
	}
	public void setMedInsSpe3(String medInsSpe3) {
		this.medInsSpe3 = medInsSpe3;
	}
	public String getSpe3ContInfo() {
		return spe3ContInfo;
	}
	public void setSpe3ContInfo(String spe3ContInfo) {
		this.spe3ContInfo = spe3ContInfo;
	}
	public String getPriPerInCharge() {
		return priPerInCharge;
	}
	public void setPriPerInCharge(String priPerInCharge) {
		this.priPerInCharge = priPerInCharge;
	}
	public String getPriContInfo() {
		return priContInfo;
	}
	public void setPriContInfo(String priContInfo) {
		this.priContInfo = priContInfo;
	}
	public String getPriSpe1() {
		return priSpe1;
	}
	public void setPriSpe1(String priSpe1) {
		this.priSpe1 = priSpe1;
	}
	public String getSpe1PriContInfo() {
		return spe1PriContInfo;
	}
	public void setSpe1PriContInfo(String spe1PriContInfo) {
		this.spe1PriContInfo = spe1PriContInfo;
	}
	public String getPriSpe2() {
		return priSpe2;
	}
	public void setPriSpe2(String priSpe2) {
		this.priSpe2 = priSpe2;
	}
	public String getSpe2PriContInfo() {
		return spe2PriContInfo;
	}
	public void setSpe2PriContInfo(String spe2PriContInfo) {
		this.spe2PriContInfo = spe2PriContInfo;
	}
	public String getPriSpe3() {
		return priSpe3;
	}
	public void setPriSpe3(String priSpe3) {
		this.priSpe3 = priSpe3;
	}
	public String getSpe3PriContInfo() {
		return spe3PriContInfo;
	}
	public void setSpe3PriContInfo(String spe3PriContInfo) {
		this.spe3PriContInfo = spe3PriContInfo;
	}
}