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

import javax.validation.constraints.NotBlank;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_NODE_CONFIG
 * 不良事件节点配置表
 * @author 爱尔眼科
 * @since 2022-07-08 09:35:14
 */
@TableName("T_AE_EVENT_CONFIG")
public class EventConfig extends BaseEntity<EventConfig> {
	/** 事件类型*/
    @Comment(value="事件类型")
	@TableField(value="event_type")
	@NotBlank private String eventType;

	/** 事件编码*/
    @Comment(value="事件编码")
	@TableField(value="event_code")
	@NotBlank private String eventCode;

	/** 事件名称*/
    @Comment(value="事件名称")
	@TableField(value="event_name")
	@NotBlank private String eventName;

	/** 事件URL*/
    @Comment(value="事件URL")
	@TableField(value="event_url")
	@NotBlank private String eventUrl;
	
	/** 事件值*/
    @Comment(value="事件值")
	@TableField(value="node_value")
	@NotBlank private Long nodeValue;

	/** 创建者ID*/
    @Comment(value="创建者ID")
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
    @Comment(value="创建时间")
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;


	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public Long getNodeValue() {
		return nodeValue;
	}
	public void setNodeValue(Long nodeValue) {
		this.nodeValue = nodeValue;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}
	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventUrl() {
		return eventUrl;
	}

	public void setEventUrl(String eventUrl) {
		this.eventUrl = eventUrl;
	}
}
