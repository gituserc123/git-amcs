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
package com.aier.cloud.api.amcs.adverse.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import org.hibernate.validator.constraints.Length;

/**
 * T_AE_DICT_TYPE
 * 
 * @author 爱尔眼科
 * @since 2022-08-02 17:10:10
 */
@TableName("T_AE_DICT_TYPE")
public class AeDictType extends BaseEntity {
	/** 过滤条件*/
	@TableField(value="filter")
	@Length(max=40) private String filter;
	
	/** 字典类型编码*/
	@TableField(value="type_code")
	@Length(max=40) private String typeCode;
	
	/** 字典类型描述*/
	@TableField(value="type_desc")
	@Length(max=40) private String typeDesc;
	
	/** 参数值编码*/
	@TableField(value="value_code")
	@Length(max=40) private String valueCode;
	
	/** 参数值描述*/
	@TableField(value="value_desc")
	@Length(max=40) private String valueDesc;
	
	/** 首拼码*/
	@TableField(value="first_spell")
	@Length(max=20) private String firstSpell;
	
	/** 参数值显示序号*/
	@TableField(value="orders")
	private Integer orders;
	
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	private Integer usingSign;
	


	/** 公共combobox过滤标识*/
	@TableField(value="combobox_filter")
	private Integer comboboxFilter;

	public Integer getComboboxFilter() {
		return comboboxFilter;
	}

	public void setComboboxFilter(Integer comboboxFilter) {
		this.comboboxFilter = comboboxFilter;
	}

	public String getFilter() {
		return filter;
	}
	public void setFilter(String filter) {
		this.filter = filter;
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

}