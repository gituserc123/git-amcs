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
package com.aier.cloud.biz.service.biz.amcs.aps.entity;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * T_DCG_DICT_TYPE
 * 字典表
 * @author 爱尔眼科
 * @since 2022-10-25 10:15:30
 */
@TableName("T_DCG_DICT_TYPE")
public class DcgDictType extends BaseEntity<DcgDictType> {
	/** 字典类型编码*/
	@TableField(value="type_code")
	@NotBlank @Length(max=30) private String typeCode;
	
	/** 字典类型描述*/
	@TableField(value="type_desc")
	@NotBlank @Length(max=30) private String typeDesc;
	
	/** 上级字典类型编码*/
	@TableField(value="parent_type_code")
	@Length(max=30) private String parentTypeCode;
	
	/** 首拼码*/
	@TableField(value="first_spell")
	@Length(max=20) private String firstSpell;
	
	/** 扩展用途(暂未使用)*/
	@TableField(value="extend")
	@Length(max=20) private String extend;
	
	/** 序号*/
	@TableField(value="orders")
	private Integer orders;
	
	/** 备注*/
	@TableField(value="remarks")
	@Length(max=100) private String remarks;
	
	/** 医院允许添加值标识（1允许0禁止）*/
	@TableField(value="hosp_sign")
	private Integer hospSign;
	

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
	public String getParentTypeCode() {
		return parentTypeCode;
	}
	public void setParentTypeCode(String parentTypeCode) {
		this.parentTypeCode = parentTypeCode;
	}
	public String getFirstSpell() {
		return firstSpell;
	}
	public void setFirstSpell(String firstSpell) {
		this.firstSpell = firstSpell;
	}
	public String getExtend() {
		return extend;
	}
	public void setExtend(String extend) {
		this.extend = extend;
	}
	public Integer getOrders() {
		return orders;
	}
	public void setOrders(Integer orders) {
		this.orders = orders;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getHospSign() {
		return hospSign;
	}
	public void setHospSign(Integer hospSign) {
		this.hospSign = hospSign;
	}
}