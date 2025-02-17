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
package com.aier.cloud.biz.service.biz.amcs.idr.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AMCS_IDR_AFP
 * AFP病附卡
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
@TableName("T_AMCS_IDR_AFP")
public class AmcsIdrAfp extends BaseEntity<AmcsIdrAfp> {
	/** 卡片ID*/
	@TableField(value="afpcardid")
	@Length(max=40) private String afpcardid;
	
	/** 病人所属地类型编码*/
	@TableField(value="patientresidencetypecode")
	private Integer patientresidencetypecode;
	
	/** 病人所属地类型名称*/
	@TableField(value="patientresidencetypename")
	@Length(max=20) private String patientresidencetypename;
	
	/** 麻痹日期*/
	@TableField(value="palsydate")
	private java.util.Date palsydate;
	
	/** 来现就诊地日期*/
	@TableField(value="treatmentlanddate")
	private java.util.Date treatmentlanddate;
	
	/** 现就诊地住址类型编码*/
	@TableField(value="treatmentlandtypecode")
	private Integer treatmentlandtypecode;
	
	/** 现就诊地住址类型名称*/
	@TableField(value="treatmentlandtypename")
	@Length(max=80) private String treatmentlandtypename;
	
	/** 现就诊地住址国标编码*/
	@TableField(value="treatmentlandzonecode")
	private Integer treatmentlandzonecode;
	
	/** 现就诊地住址国标名称*/
	@TableField(value="treatmentlandzonename")
	@Length(max=80) private String treatmentlandzonename;
	
	/** 现就诊地详细住址*/
	@TableField(value="addressdetails")
	@Length(max=100) private String addressdetails;
	
	/** 麻痹症状*/
	@TableField(value="palsysymptom")
	@Length(max=30) private String palsysymptom;
	
	/** key1*/
	@TableField(value="key1")
	@Length(max=100) private String key1;
	
	/** key2*/
	@TableField(value="key2")
	@Length(max=100) private String key2;
	
	/** key3*/
	@TableField(value="key3")
	@Length(max=100) private String key3;
	
	/** key4*/
	@TableField(value="key4")
	@Length(max=100) private String key4;
	
	/** key5*/
	@TableField(value="key5")
	@Length(max=100) private String key5;
	
	/** key6*/
	@TableField(value="key6")
	@Length(max=100) private String key6;
	
	/** key7*/
	@TableField(value="key7")
	@Length(max=100) private String key7;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	 
	
	/** "事件状态 （0 未审 1 已审 2 删除）
"*/
	@TableField(value="status")
	private Integer status;
	
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** T_ID*/
	@TableField(value="t_id")
	private Long tId;
	

	public String getAfpcardid() {
		return afpcardid;
	}
	public void setAfpcardid(String afpcardid) {
		this.afpcardid = afpcardid;
	}
	public Integer getPatientresidencetypecode() {
		return patientresidencetypecode;
	}
	public void setPatientresidencetypecode(Integer patientresidencetypecode) {
		this.patientresidencetypecode = patientresidencetypecode;
	}
	public String getPatientresidencetypename() {
		return patientresidencetypename;
	}
	public void setPatientresidencetypename(String patientresidencetypename) {
		this.patientresidencetypename = patientresidencetypename;
	}
	public java.util.Date getPalsydate() {
		return palsydate;
	}
	public void setPalsydate(java.util.Date palsydate) {
		this.palsydate = palsydate;
	}
	public java.util.Date getTreatmentlanddate() {
		return treatmentlanddate;
	}
	public void setTreatmentlanddate(java.util.Date treatmentlanddate) {
		this.treatmentlanddate = treatmentlanddate;
	}
	public Integer getTreatmentlandtypecode() {
		return treatmentlandtypecode;
	}
	public void setTreatmentlandtypecode(Integer treatmentlandtypecode) {
		this.treatmentlandtypecode = treatmentlandtypecode;
	}
	public String getTreatmentlandtypename() {
		return treatmentlandtypename;
	}
	public void setTreatmentlandtypename(String treatmentlandtypename) {
		this.treatmentlandtypename = treatmentlandtypename;
	}
	public Integer getTreatmentlandzonecode() {
		return treatmentlandzonecode;
	}
	public void setTreatmentlandzonecode(Integer treatmentlandzonecode) {
		this.treatmentlandzonecode = treatmentlandzonecode;
	}
	public String getTreatmentlandzonename() {
		return treatmentlandzonename;
	}
	public void setTreatmentlandzonename(String treatmentlandzonename) {
		this.treatmentlandzonename = treatmentlandzonename;
	}
	public String getAddressdetails() {
		return addressdetails;
	}
	public void setAddressdetails(String addressdetails) {
		this.addressdetails = addressdetails;
	}
	public String getPalsysymptom() {
		return palsysymptom;
	}
	public void setPalsysymptom(String palsysymptom) {
		this.palsysymptom = palsysymptom;
	}
	public String getKey1() {
		return key1;
	}
	public void setKey1(String key1) {
		this.key1 = key1;
	}
	public String getKey2() {
		return key2;
	}
	public void setKey2(String key2) {
		this.key2 = key2;
	}
	public String getKey3() {
		return key3;
	}
	public void setKey3(String key3) {
		this.key3 = key3;
	}
	public String getKey4() {
		return key4;
	}
	public void setKey4(String key4) {
		this.key4 = key4;
	}
	public String getKey5() {
		return key5;
	}
	public void setKey5(String key5) {
		this.key5 = key5;
	}
	public String getKey6() {
		return key6;
	}
	public void setKey6(String key6) {
		this.key6 = key6;
	}
	public String getKey7() {
		return key7;
	}
	public void setKey7(String key7) {
		this.key7 = key7;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
 
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public Long getTId() {
		return tId;
	}
	public void setTId(Long tId) {
		this.tId = tId;
	}
}