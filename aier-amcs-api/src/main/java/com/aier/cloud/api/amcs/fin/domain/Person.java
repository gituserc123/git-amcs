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
 * T_FIN_PERSON
 * 医保系统相关人员表
 * @author 爱尔眼科
 * @since 2023-08-29 15:14:28
 */
@TableName("T_FIN_PERSON")
@Data
public class Person extends BaseEntity {
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 财务负责人*/
	@TableField(value="fin_per_in_charge")
	@Length(max=20) private String finPerInCharge;
	
	/** 职务*/
	@TableField(value="posi")
	@Length(max=20) private String posi;
	
	/** 联系方式*/
	@TableField(value="cont_info")
	@Length(max=20) private String contInfo;
	
	/** 医保负责人*/
	@TableField(value="med_ins_per_in_charge")
	@Length(max=20) private String medInsPerInCharge;
	
	/** 医保联系方式*/
	@TableField(value="ins_cont_info")
	@Length(max=20) private String insContInfo;
	
	/** 医保专员1*/
	@TableField(value="med_ins_spe1")
	@Length(max=20) private String medInsSpe1;
	
	/** 医保专员1联系方式*/
	@TableField(value="spe1_cont_info")
	@Length(max=20) private String spe1ContInfo;
	
	/** 医保专员2*/
	@TableField(value="med_ins_spe2")
	@Length(max=20) private String medInsSpe2;
	
	/** 医保专员2联系方式*/
	@TableField(value="spe2_cont_info")
	@Length(max=20) private String spe2ContInfo;
	
	/** 医保专员3*/
	@TableField(value="med_ins_spe3")
	@Length(max=20) private String medInsSpe3;
	
	/** 医保专员3联系方式*/
	@TableField(value="spe3_cont_info")
	@Length(max=20) private String spe3ContInfo;
	
	/** 价格负责人*/
	@TableField(value="pri_per_in_charge")
	@Length(max=20) private String priPerInCharge;
	
	/** 价格联系方式*/
	@TableField(value="pri_cont_info")
	@Length(max=20) private String priContInfo;
	
	/** 价格专干1*/
	@TableField(value="pri_spe1")
	@Length(max=20) private String priSpe1;
	
	/** 价格专干1联系方式*/
	@TableField(value="spe1_pri_cont_info")
	@Length(max=20) private String spe1PriContInfo;
	
	/** 价格专干2*/
	@TableField(value="pri_spe2")
	@Length(max=20) private String priSpe2;
	
	/** 价格专干2联系方式*/
	@TableField(value="spe2_pri_cont_info")
	@Length(max=20) private String spe2PriContInfo;
	
	/** 价格专干3*/
	@TableField(value="pri_spe3")
	@Length(max=20) private String priSpe3;
	
	/** 价格专干3联系方式*/
	@TableField(value="spe3_pri_cont_info")
	@Length(max=20) private String spe3PriContInfo;

}