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
 * T_AMCS_IDR_HB
 * HB乙肝副卡
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
@TableName("T_AMCS_IDR_HB")
public class AmcsIdrHb extends BaseEntity<AmcsIdrHb> {
	/** 卡片ID*/
	@TableField(value="hbcardid")
	@Length(max=40) private String hbcardid;
	
	/** 乙肝HBsAg阳性时间*/
	@TableField(value="hbsagtestpositivetypecode")
	private Integer hbsagtestpositivetypecode;
	
	/** 乙肝HBsAg阳性时间*/
	@TableField(value="hbsagtestpositivetypename")
	private String hbsagtestpositivetypename;
	
	/** 首次出现乙肝症状和体征时间*/
	@TableField(value="firsthbsymptomtime")
	private java.util.Date firsthbsymptomtime;
	
	/** 乙肝本次ALT*/
	@TableField(value="lastaltvalue")
	@Length(max=20) private String lastaltvalue;
	
	/** 抗-HBcIgM1：1000检测结果*/
	@TableField(value="hbcabigmtestcode")
	@Length(max=20) private String hbcabigmtestcode;
	
	/** 抗-HBcIgM1：1000检测结果值*/
	@TableField(value="hbcabigmtestname")
	@Length(max=20) private String hbcabigmtestname;
	
	/** 肝穿检测结果*/
	@TableField(value="liverpuncturetestcode")
	@Length(max=20) private String liverpuncturetestcode;
	
	/** 肝穿检测结果值*/
	@TableField(value="liverpuncturetestname")
	@Length(max=20) private String liverpuncturetestname;
	
	/** 恢复期血清HBsAg阴转,抗HBs阳转*/
	@TableField(value="hbsagnorhbsabpcode")
	@Length(max=20) private String hbsagnorhbsabpcode;
	
	/** 恢复期血清HBsAg阴转,抗HBs阳转值*/
	@TableField(value="hbsagnorhbsabpname")
	@Length(max=20) private String hbsagnorhbsabpname;
	
	/** 无症状/不详*/
	@TableField(value="firsthbsymptomunsurecode")
	@Length(max=20) private String firsthbsymptomunsurecode;
	
	/** 乙肝HBsAg阳性时间*/
	@TableField(value="hbsagcode")
	private Integer hbsagcode;
	
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
	

	public String getHbcardid() {
		return hbcardid;
	}
	public void setHbcardid(String hbcardid) {
		this.hbcardid = hbcardid;
	}
	public Integer getHbsagtestpositivetypecode() {
		return hbsagtestpositivetypecode;
	}
	public void setHbsagtestpositivetypecode(Integer hbsagtestpositivetypecode) {
		this.hbsagtestpositivetypecode = hbsagtestpositivetypecode;
	}
	public String getHbsagtestpositivetypename() {
		return hbsagtestpositivetypename;
	}
	public void setHbsagtestpositivetypename(String hbsagtestpositivetypename) {
		this.hbsagtestpositivetypename = hbsagtestpositivetypename;
	}
	public java.util.Date getFirsthbsymptomtime() {
		return firsthbsymptomtime;
	}
	public void setFirsthbsymptomtime(java.util.Date firsthbsymptomtime) {
		this.firsthbsymptomtime = firsthbsymptomtime;
	}
	public String getLastaltvalue() {
		return lastaltvalue;
	}
	public void setLastaltvalue(String lastaltvalue) {
		this.lastaltvalue = lastaltvalue;
	}
	public String getHbcabigmtestcode() {
		return hbcabigmtestcode;
	}
	public void setHbcabigmtestcode(String hbcabigmtestcode) {
		this.hbcabigmtestcode = hbcabigmtestcode;
	}
	public String getHbcabigmtestname() {
		return hbcabigmtestname;
	}
	public void setHbcabigmtestname(String hbcabigmtestname) {
		this.hbcabigmtestname = hbcabigmtestname;
	}
	public String getLiverpuncturetestcode() {
		return liverpuncturetestcode;
	}
	public void setLiverpuncturetestcode(String liverpuncturetestcode) {
		this.liverpuncturetestcode = liverpuncturetestcode;
	}
	public String getLiverpuncturetestname() {
		return liverpuncturetestname;
	}
	public void setLiverpuncturetestname(String liverpuncturetestname) {
		this.liverpuncturetestname = liverpuncturetestname;
	}
	public String getHbsagnorhbsabpcode() {
		return hbsagnorhbsabpcode;
	}
	public void setHbsagnorhbsabpcode(String hbsagnorhbsabpcode) {
		this.hbsagnorhbsabpcode = hbsagnorhbsabpcode;
	}
	public String getHbsagnorhbsabpname() {
		return hbsagnorhbsabpname;
	}
	public void setHbsagnorhbsabpname(String hbsagnorhbsabpname) {
		this.hbsagnorhbsabpname = hbsagnorhbsabpname;
	}
	public String getFirsthbsymptomunsurecode() {
		return firsthbsymptomunsurecode;
	}
	public void setFirsthbsymptomunsurecode(String firsthbsymptomunsurecode) {
		this.firsthbsymptomunsurecode = firsthbsymptomunsurecode;
	}
	public Integer getHbsagcode() {
		return hbsagcode;
	}
	public void setHbsagcode(Integer hbsagcode) {
		this.hbsagcode = hbsagcode;
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