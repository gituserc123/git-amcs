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
 * T_FIN_INS_COMMENT
 * 沟通意见表
 * @author 爱尔眼科
 * @since 2023-04-27 17:00:17
 */
@TableName("T_FIN_INS_COMMENT")
public class FinInsComment extends BaseEntity<FinInsComment> {
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 关联业务表ID*/
	@TableField(value="associated_id")
	@NotBlank private Long associatedId;
	
	/** 沟通内容*/
	@TableField(value="content")
	@Length(max=400) private String content;
	
	/** 当前状态*/
	@TableField(value="current_status")
	@NotBlank private Integer currentStatus;
	
	/** 前一流程状态*/
	@TableField(value="prev_status")
	private Integer prevStatus;
	
	/** 当前操作（1通过；9退回）*/
	@TableField(value="action")
	@NotBlank private Integer action;
	

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
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public Long getAssociatedId() {
		return associatedId;
	}
	public void setAssociatedId(Long associatedId) {
		this.associatedId = associatedId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getCurrentStatus() {
		return currentStatus;
	}
	public void setCurrentStatus(Integer currentStatus) {
		this.currentStatus = currentStatus;
	}
	public Integer getPrevStatus() {
		return prevStatus;
	}
	public void setPrevStatus(Integer prevStatus) {
		this.prevStatus = prevStatus;
	}
	public Integer getAction() {
		return action;
	}
	public void setAction(Integer action) {
		this.action = action;
	}
}