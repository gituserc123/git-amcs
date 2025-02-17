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
 * T_AMCS_IDR_DICT_TYPE
 * 传染病字典表
 * @author 爱尔眼科
 * @since 2023-04-27 15:09:50
 */
@TableName("T_AMCS_IDR_DICT_TYPE")
public class AmcsIdrDictType extends BaseEntity<AmcsIdrDictType> {
	/** 字典类型编码*/
	@TableField(value="type_code")
	@NotBlank @Length(max=20) private String typeCode;
	
	/** 字典类型描述*/
	@TableField(value="type_desc")
	@Length(max=40) private String typeDesc;
	
	/** 参数值编码*/
	@TableField(value="value_code")
	@NotBlank @Length(max=40) private String valueCode;
	
	/** 参数值描述*/
	@TableField(value="value_desc")
	@NotBlank @Length(max=200) private String valueDesc;
	
	/** 参数值备注*/
	@TableField(value="value_remark")
	@Length(max=200) private String valueRemark;
	
	/** 参数值英文名*/
	@TableField(value="value_english")
	@Length(max=200) private String valueEnglish;
	
	/** 父级所在的VALUE_CODE*/
	@TableField(value="leveles")
	@Length(max=40) private String leveles;
	/** 层次*/
	@TableField(value="leveless")
	@Length(max=40) private String leveless;
	
	/** 备注*/
	@TableField(value="remark")
	@Length(max=100) private String remark;
	
	public String getLeveless() {
		return leveless;
	}
	public void setLeveless(String leveless) {
		this.leveless = leveless;
	}
	/** 首拼码*/
	@TableField(value="first_spell")
	@Length(max=40) private String firstSpell;
	
	/** 参数值显示序号*/
	@TableField(value="orders")
	private Integer orders;
	
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	private Integer usingSign;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	private Long hospId;
	
	/** 打开网页位置*/
	@TableField(value="url")
	@Length(max=50) private String url;
	

	public String getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public String getValueCode() {
		return valueCode;
	}
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}
	public String getValueDesc() {
		return valueDesc;
	}
	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}
	public String getValueRemark() {
		return valueRemark;
	}
	public void setValueRemark(String valueRemark) {
		this.valueRemark = valueRemark;
	}
	public String getValueEnglish() {
		return valueEnglish;
	}
	public void setValueEnglish(String valueEnglish) {
		this.valueEnglish = valueEnglish;
	}
	public String getLeveles() {
		return leveles;
	}
	public void setLeveles(String leveles) {
		this.leveles = leveles;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getFirstSpell() {
		return firstSpell;
	}
	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public Integer getUsingSign() {
		return usingSign;
	}
	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}