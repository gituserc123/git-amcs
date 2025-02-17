/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
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
package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import java.util.Date;

/**
 * T_FIN_MA_DICT_INFO
 * 医保管理分析字典表
 * @author 爱尔眼科
 * @since 2024-02-22 14:32:33
 */
public class FinMaDictInfoCondition extends PageCondition {

	private Long id;

	private java.util.Date modifyDate;

	private Long modifer;

	/** 全级别路径*/
	private String treepath;
	
	/** 父级level*/
	private String parentLevel;
	
	/** 启停标识（1启用0停用）*/
	private Integer usingSign;
	
	/** 描述*/
	private String valueDesc;
	
	/** 编码(由一,二,三级计算得出)*/
	private String valueCode;
	
	/** 第三级值*/
	private String thdLevelVal;
	
	/** 第三级(医疗细分项,如:老年性白内障,翼状胬肉等)*/
	private String thdLevel;
	
	/** 第二级值*/
	private String sndLevelVal;
	
	/** 第二级(医保类型)*/
	private String sndLevel;
	
	/** 第一级值*/
	private String fstLevelVal;
	
	/** 第一级(医保结算方式)*/
	private String fstLevel;

	/** 第三级值*/
	private String queryThdLevelVal;

	/** 第二级值*/
	private String querySndLevelVal;

	/** 第一级值*/
	private String queryFstLevelVal;
	/** 描述*/
	private String queryValueDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public Long getModifer() {
		return modifer;
	}

	public void setModifer(Long modifer) {
		this.modifer = modifer;
	}

	public String getTreepath() {
		return treepath;
	}
	public void setTreepath(String treepath) {
		this.treepath = treepath;
	}
	public String getParentLevel() {
		return parentLevel;
	}
	public void setParentLevel(String parentLevel) {
		this.parentLevel = parentLevel;
	}
	public Integer getUsingSign() {
		return usingSign;
	}
	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}
	public String getValueDesc() {
		return valueDesc;
	}
	public void setValueDesc(String valueDesc) {
		this.valueDesc = valueDesc;
	}
	public String getValueCode() {
		return valueCode;
	}
	public void setValueCode(String valueCode) {
		this.valueCode = valueCode;
	}
	public String getThdLevelVal() {
		return thdLevelVal;
	}
	public void setThdLevelVal(String thdLevelVal) {
		this.thdLevelVal = thdLevelVal;
	}
	public String getThdLevel() {
		return thdLevel;
	}
	public void setThdLevel(String thdLevel) {
		this.thdLevel = thdLevel;
	}
	public String getSndLevelVal() {
		return sndLevelVal;
	}
	public void setSndLevelVal(String sndLevelVal) {
		this.sndLevelVal = sndLevelVal;
	}
	public String getSndLevel() {
		return sndLevel;
	}
	public void setSndLevel(String sndLevel) {
		this.sndLevel = sndLevel;
	}
	public String getFstLevelVal() {
		return fstLevelVal;
	}
	public void setFstLevelVal(String fstLevelVal) {
		this.fstLevelVal = fstLevelVal;
	}
	public String getFstLevel() {
		return fstLevel;
	}
	public void setFstLevel(String fstLevel) {
		this.fstLevel = fstLevel;
	}

	public String getQueryThdLevelVal() {
		return queryThdLevelVal;
	}

	public void setQueryThdLevelVal(String queryThdLevelVal) {
		this.queryThdLevelVal = queryThdLevelVal;
	}

	public String getQuerySndLevelVal() {
		return querySndLevelVal;
	}

	public void setQuerySndLevelVal(String querySndLevelVal) {
		this.querySndLevelVal = querySndLevelVal;
	}

	public String getQueryFstLevelVal() {
		return queryFstLevelVal;
	}

	public void setQueryFstLevelVal(String queryFstLevelVal) {
		this.queryFstLevelVal = queryFstLevelVal;
	}

	public String getQueryValueDesc() {
		return queryValueDesc;
	}

	public void setQueryValueDesc(String queryValueDesc) {
		this.queryValueDesc = queryValueDesc;
	}
}