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
package com.aier.cloud.api.amcs.domain.idr;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_AMCS_IDR_DICT_TYPE
 * 传染病字典表
 * @author 爱尔眼科
 * @since 2023-04-27 15:09:50
 */ 
public class AmcsIdrDictType extends BaseEntity  {
	/** 字典类型编码*/
	  private String typeCode;
	
	/** 字典类型描述*/
	 private String typeDesc;
	
	/** 参数值编码*/
	  private String valueCode;
	
	/** 参数值描述*/
	  private String valueDesc;
	
	/** 参数值备注*/
  private String valueRemark;
	
	/** 参数值英文名*/
	  private String valueEnglish;
	
	/** 父级所在的VALUE_CODE*/
	  private String leveles;
	
	/** 备注*/
  private String remark;
	
	/** 首拼码*/
	  private String firstSpell;
	
	/** 参数值显示序号*/ 
	private Integer orders;
	
	/** 启停标识（1启用0停用）*/ 
	private Integer usingSign;
	
	/** 医院ID*/ 
	private Long hospId;
	
	/** 打开网页位置*/ 
	 private String url;
	 /**LEVELESS	N	NUMBER(1)	Y			层次*/
	 private Integer leveless;

	public Integer getLeveless() {
		return leveless;
	}
	public void setLeveless(Integer leveless) {
		this.leveless = leveless;
	}
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