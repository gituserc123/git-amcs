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
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * T_FIN_DICT_TYPE
 * 财务字典表
 * @author 爱尔眼科
 * @since 2023-03-13 11:28:02
 */
@TableName("T_FIN_DICT_TYPE")
@Data
public class FinDictType extends BaseEntity {
	/** 字典类型编码*/
	@TableField(value="type_code")
	@NotBlank @Length(max=40) private String typeCode;
	
	/** 字典类型描述*/
	@TableField(value="type_desc")
	@NotBlank @Length(max=40) private String typeDesc;
	
	/** 参数值编码*/
	@TableField(value="value_code")
	@NotBlank @Length(max=40) private String valueCode;
	
	/** 参数值描述*/
	@TableField(value="value_desc")
	@NotBlank @Length(max=200) private String valueDesc;
	
	/** 首拼码*/
	@TableField(value="first_spell")
	@NotBlank @Length(max=40) private String firstSpell;
	
	/** 参数值显示序号*/
	@TableField(value="orders")
	@NotBlank private Integer orders;
	
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

}