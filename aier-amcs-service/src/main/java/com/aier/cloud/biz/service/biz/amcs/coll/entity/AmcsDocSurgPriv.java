/*
 * Copyright © 2004-2020 ier EYE Hospital Group.
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
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

import lombok.Data;

import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AMCS_DOC_SURG_PRIV
 * 医生手术权限表
 * @author 爱尔眼科
 * @since 2023-05-16 10:36:22
 */
@TableName("T_AMCS_DOC_SURG_PRIV")
@Data
public class AmcsDocSurgPriv extends BaseEntity<AmcsDocSurgPriv> {
	/** 医生ID*/
	@TableField(value="doctor_id")
	@NotBlank private Long doctorId;
	
	/** 医生姓名*/
	@TableField(value="doctor_name")
	@Length(max=50) private String doctorName;
	
	/** 手术类型）*/
	@TableField(value="surg_type_code")
	@NotBlank private String surgTypeCode;
	
	/** 手术类型名称*/
	@TableField(value="surg_type_name")
	@NotBlank private String surgTypeName;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@Length(max=50) private String hospName;
	
	/** 科室ID*/
	@TableField(value="dept_id")
	@NotBlank private Long deptId;
	
	/** 科室名称*/
	@TableField(value="dept_name")
	@Length(max=50) private String deptName;
	
	/** 所在省区ID*/
	@TableField(value="province_id")
	private Long provinceId;
	
	/** 所在省区名称*/
	@TableField(value="province_name")
	@Length(max=50) private String provinceName;
	
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign = 1;
	
}