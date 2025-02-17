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
package com.aier.cloud.biz.service.biz.amcs.coll.entity;

import org.hibernate.validator.constraints.Length;

import java.util.List;

import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;

import lombok.Data;

import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AMCS_COLL_HOSP_GRADE
 * 医院等级采集表
 * @author 爱尔眼科
 * @since 2023-03-30 10:46:35
 */
@Data
@TableName("T_AMCS_COLL_HOSP_GRADE")
public class AmcsCollHospGrade extends BaseEntity<AmcsCollHospGrade> {
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Integer hospId;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@NotBlank @Length(max=50) private String hospName;
	
	/** 医院等级(码表:hosp_grade)*/
	@TableField(value="grade")
	private Integer grade;
	
	/** 医院等级名称*/
	@TableField(value="grade_name")
	@Length(max=10) private String gradeName;
	
	/** 医院等次(码表:hosp_level)*/
	@TableField(value="grade_level")
	private Integer gradeLevel;
	
	/** 医院等次名称*/
	@TableField(value="grade_level_name")
	@Length(max=10) private String gradeLevelName;
	
	/** 是否准备申报 (0 否 1 是)*/
	@TableField(value="is_apply")
	private Integer isApply;
	
	/** 申报等级*/
	@TableField(value="apply_grade")
	private Integer applyGrade;
	
	/** 申报等级名称*/
	@TableField(value="apply_grade_name")
	@Length(max=10) private String applyGradeName;
	
	/** 备注*/
	@TableField(value="remark")
	@Length(max=500) private String remark;
	
	@TableField(exist=false)
	private List<AmcsCollAttachment> attachments;
	
}