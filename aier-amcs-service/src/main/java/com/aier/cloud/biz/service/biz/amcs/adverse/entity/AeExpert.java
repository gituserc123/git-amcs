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
package com.aier.cloud.biz.service.biz.amcs.adverse.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_EXPERT
 * 专家记录表
 * @author 爱尔眼科
 * @since 2023-02-16 17:14:32
 */
@Data
@TableName("T_AE_EXPERT")
public class AeExpert extends BaseEntity<AeExpert> {
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	private Integer usingSign;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@Length(max=50) private String hospName;
	
	/** 所属学组*/
	@TableField(value="group_value")
	private Integer groupValue;
	
	/** 工号*/
	@TableField(value="staff_code")
	@Length(max=20) private String staffCode;
	
	/** 姓名*/
	@TableField(value="staff_name")
	@Length(max=50) private String staffName;
	
	/** 人员ID*/
	@TableField(value="staff_id")
	private Long staffId;

	/** 省区编号*/
	@TableField(value="province_codes")
	@Length(max=20) private String provinceCodes;


	/** 省区名称*/
	@TableField(value="province_names")
	@Length(max=20) private String provinceNames;

}