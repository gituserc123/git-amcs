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

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_EVENT_TAGS
 * 事件标签关联表
 * @author 爱尔眼科
 * @since 2023-03-10 17:12:11
 */
@TableName("T_AE_EVENT_TAGS")
public class AeEventTags extends BaseEntity<AeEventTags> {
	/** 启停标识（1启用0停用）*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign = 1;
	
	/** 标签CODE*/
	@TableField(value="tag_code")
	@NotBlank @Length(max=40) private String tagCode;
	
	/** 不良事件ID*/
	@TableField(value="event_id")
	@NotBlank private Long eventId;
	
	@TableField(exist=false)
	private String tagCodeName;
	

	public Integer getUsingSign() {
		return usingSign;
	}
	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}
	public String getTagCode() {
		return tagCode;
	}
	public void setTagCode(String tagCode) {
		this.tagCode = tagCode;
	}
	public Long getEventId() {
		return eventId;
	}
	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}
	public String getTagCodeName() {
		return tagCodeName;
	}
	public void setTagCodeName(String tagCodeName) {
		this.tagCodeName = tagCodeName;
	}
	
}