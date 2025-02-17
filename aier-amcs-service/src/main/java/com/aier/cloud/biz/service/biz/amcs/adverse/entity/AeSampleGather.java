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
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_AE_SAMPLE_GATHER
 * 标本采集不良事件上报表单
 * @author 爱尔眼科
 * @since 2022-08-31 14:49:57
 */
@TableName("T_AE_SAMPLE_GATHER")
public class AeSampleGather extends BaseEntity<AeSampleGather> {
	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;
	
	/** 标本采集类别*/
    @Comment(value="标本采集类别")
	@TableField(value="sample_gather_type")
	@Length(max=512) private String sampleGatherType;

	/** 标本采集类别(其他)*/
    @Comment(value="标本采集类别(其他)")
	@TableField(value="sample_gather_type_remark")
	@Length(max=512) private String sampleGatherTypeRemark;
	
	/** 标本不合格原因*/
    @Comment(value="标本不合格原因")
	@TableField(value="sample_unqualified_reason")
	@Length(max=512) private String sampleUnqualifiedReason;
	
	/** 标本采集时间*/
    @Comment(value="标本采集时间")
	@TableField(value="sample_gather_time")
	@NotBlank private java.util.Date sampleGatherTime;
	
	/** 标本送检时间*/
    @Comment(value="标本送检时间")
	@TableField(value="sample_send_time")
	@NotBlank private java.util.Date sampleSendTime;
	
	/** 发现错误时间*/
    @Comment(value="发现错误时间")
	@TableField(value="find_mistake_time")
	@NotBlank private java.util.Date findMistakeTime;
	
	/** 详细情况*/
    @Comment(value="详细情况")
	@TableField(value="detail_info")
	@Length(max=512) private String detailInfo;
	
	/** 创建者ID*/
    @Comment(value="创建者ID")
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
    @Comment(value="创建时间")
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 医院ID*/
    @Comment(value="医院ID")
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	

	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	public String getSampleGatherType() {
		return sampleGatherType;
	}
	public void setSampleGatherType(String sampleGatherType) {
		this.sampleGatherType = sampleGatherType;
	}
	public String getSampleUnqualifiedReason() {
		return sampleUnqualifiedReason;
	}
	public void setSampleUnqualifiedReason(String sampleUnqualifiedReason) {
		this.sampleUnqualifiedReason = sampleUnqualifiedReason;
	}
	public java.util.Date getSampleGatherTime() {
		return sampleGatherTime;
	}
	public void setSampleGatherTime(java.util.Date sampleGatherTime) {
		this.sampleGatherTime = sampleGatherTime;
	}
	public java.util.Date getSampleSendTime() {
		return sampleSendTime;
	}
	public void setSampleSendTime(java.util.Date sampleSendTime) {
		this.sampleSendTime = sampleSendTime;
	}
	public java.util.Date getFindMistakeTime() {
		return findMistakeTime;
	}
	public void setFindMistakeTime(java.util.Date findMistakeTime) {
		this.findMistakeTime = findMistakeTime;
	}
	public String getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(String detailInfo) {
		this.detailInfo = detailInfo;
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
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public String getSampleGatherTypeRemark() {
		return sampleGatherTypeRemark;
	}

	public void setSampleGatherTypeRemark(String sampleGatherTypeRemark) {
		this.sampleGatherTypeRemark = sampleGatherTypeRemark;
	}
}
