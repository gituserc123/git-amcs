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
package com.aier.cloud.biz.service.biz.amcs.fin.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_FIN_MA_DICT_INFO
 * 医保管理分析字典表
 * @author 爱尔眼科
 * @since 2024-02-22 14:32:33
 */
@TableName("T_FIN_MA_DICT_INFO")
public class FinMaDictInfo extends BaseEntity<FinMaDictInfo> {
	/** 全级别路径*/
	@TableField(value="treepath")
	@Length(max=50) private String treepath;
	
	/** 父级level*/
	@TableField(value="parent_level")
	@Length(max=10) private String parentLevel;
	
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	private Integer usingSign;
	
	/** 描述*/
	@TableField(value="value_desc")
	@Length(max=40) private String valueDesc;
	
	/** 编码(由一,二,三级计算得出)*/
	@TableField(value="value_code")
	@Length(max=40) private String valueCode;
	
	/** 第三级值*/
	@TableField(value="thd_level_val")
	@Length(max=10) private String thdLevelVal;
	
	/** 第三级(医疗细分项,如:老年性白内障,翼状胬肉等)*/
	@TableField(value="thd_level")
	@Length(max=40) private String thdLevel;
	
	/** 第二级值*/
	@TableField(value="snd_level_val")
	@Length(max=10) private String sndLevelVal;
	
	/** 第二级(医保类型)*/
	@TableField(value="snd_level")
	@Length(max=10) private String sndLevel;
	
	/** 第一级值*/
	@TableField(value="fst_level_val")
	@Length(max=10) private String fstLevelVal;
	
	/** 第一级(医保结算方式)*/
	@TableField(value="fst_level")
	@Length(max=10) private String fstLevel;
	

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
}