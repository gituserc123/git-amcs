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
package com.aier.cloud.api.amcs.fin.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_FIN_INS_CONSUMABLES_INFO
 * 耗材附属信息表
 * @author 爱尔眼科
 * @since 2023-04-20 08:36:26
 */
public class FinInsConsumablesInfo extends BaseEntity {
	/** 月主表ID*/
	private Long monthId;
	
	/** 医院ID*/
	private Long hospId;
	
	/** 创建时间*/
	private java.util.Date createDate;
	
	/** 创建者ID*/
	private Long creator;
	
	/** 备注*/
	private String remark;
	
	/** 最大值*/
	private java.math.BigDecimal consumablesMax;
	
	/** 最小值*/
	private java.math.BigDecimal consumablesMin;
	
	/** 耗材名称*/
	private String consumables;
	
	/** 耗材所属类型(1-单病种付费,2-DIP,3-DRG)*/
	private Integer consumablesType;
	
	/** 主表ID*/
	private Long mainId;
	

	public Long getMonthId() {
		return monthId;
	}
	public void setMonthId(Long monthId) {
		this.monthId = monthId;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public java.math.BigDecimal getConsumablesMax() {
		return consumablesMax;
	}
	public void setConsumablesMax(java.math.BigDecimal consumablesMax) {
		this.consumablesMax = consumablesMax;
	}
	public java.math.BigDecimal getConsumablesMin() {
		return consumablesMin;
	}
	public void setConsumablesMin(java.math.BigDecimal consumablesMin) {
		this.consumablesMin = consumablesMin;
	}
	public String getConsumables() {
		return consumables;
	}
	public void setConsumables(String consumables) {
		this.consumables = consumables;
	}
	public Integer getConsumablesType() {
		return consumablesType;
	}
	public void setConsumablesType(Integer consumablesType) {
		this.consumablesType = consumablesType;
	}
	public Long getMainId() {
		return mainId;
	}
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}
}