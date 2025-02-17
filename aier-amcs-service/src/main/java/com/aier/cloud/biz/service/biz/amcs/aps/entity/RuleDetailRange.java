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

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AMCS_APS_RULE_DETAIL_RANGE
 * 人资绩效计分规则范围表
 * @author 爱尔眼科
 * @since 2022-03-16 17:06:22
 */
@TableName("T_AMCS_APS_RULE_DETAIL_RANGE")
public class RuleDetailRange extends BaseEntity<RuleDetailRange> {
	/** 医院id*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 明细id*/
	@TableField(value="detail_id")
	@NotBlank private Long detailId;
	
	/** 范围最小值*/
	@TableField(value="min_range")
	private java.math.BigDecimal minRange;
	
	/** 范围最大值*/
	@TableField(value="max_range")
	private java.math.BigDecimal maxRange;
	
	/** 分值*/
	@TableField(value="range_score")
	private java.math.BigDecimal rangeScore;
	
	/** 排序号*/
	@TableField(value="range_order")
	private Integer rangeOrder;
	

	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public Long getDetailId() {
		return detailId;
	}
	public void setDetailId(Long detailId) {
		this.detailId = detailId;
	}
	public java.math.BigDecimal getMinRange() {
		return minRange;
	}
	public void setMinRange(java.math.BigDecimal minRange) {
		this.minRange = minRange;
	}
	public java.math.BigDecimal getMaxRange() {
		return maxRange;
	}
	public void setMaxRange(java.math.BigDecimal maxRange) {
		this.maxRange = maxRange;
	}
	public java.math.BigDecimal getRangeScore() {
		return rangeScore;
	}
	public void setRangeScore(java.math.BigDecimal rangeScore) {
		this.rangeScore = rangeScore;
	}
	public Integer getRangeOrder() {
		return rangeOrder;
	}
	public void setRangeOrder(Integer rangeOrder) {
		this.rangeOrder = rangeOrder;
	}
}