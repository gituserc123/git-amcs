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
import javax.validation.constraints.NotNull;

import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_EXPERT_EVENT
 * 不良事件专家关联表
 * @author 爱尔眼科
 * @since 2023-02-19 21:12:21
 */
@Data
@TableName("T_AE_EXPERT_EVENT")
public class AeExpertEvent extends BaseEntity<AeExpertEvent> {
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign;
	
	/** 专家工号*/
	@TableField(exist=false)
	private String expertCode;
	
	/** 专家ID*/
	@TableField(value="expert_id")
	@NotBlank private Long expertId;
	
	/** 事件名称*/
	@TableField(exist=false)
	private String eventName;
	
	/** 事件ID*/
	@TableField(value="event_id")
	@NotBlank private Long eventId;

	/** 是否是省区分配事件（1是 0否）*/
	@TableField(value="is_province")
	private Integer isProvince;


	

}