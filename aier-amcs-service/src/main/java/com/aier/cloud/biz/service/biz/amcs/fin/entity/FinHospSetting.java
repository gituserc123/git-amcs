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
package com.aier.cloud.biz.service.biz.amcs.fin.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_FIN_HOSP_SETTING
 * 财务医院设置
 * @author 爱尔眼科
 * @since 2023-03-22 14:23:52
 */
@TableName("T_FIN_HOSP_SETTING")
public class FinHospSetting extends BaseEntity<FinHospSetting> {
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 医保统筹区*/
	@TableField(value="pooling_area")
	@Length(max=20) private String poolingArea;
	
	/** 医保类别*/
	@TableField(value="insurance_type")
	private Integer insuranceType;
	
	/** 删除标识*/
	@TableField(value="using_sign")
	private Integer usingSign;
	

	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public String getPoolingArea() {
		return poolingArea;
	}
	public void setPoolingArea(String poolingArea) {
		this.poolingArea = poolingArea;
	}
	public Integer getInsuranceType() {
		return insuranceType;
	}
	public void setInsuranceType(Integer insuranceType) {
		this.insuranceType = insuranceType;
	}
	public Integer getUsingSign() {
		return usingSign;
	}
	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}
}