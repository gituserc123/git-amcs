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
 * T_AE_OTHER
 * 信息、行政、公共安全事件
 * @author 爱尔眼科
 * @since 2023-09-04 15:47:30
 */
@TableName("T_AE_OTHER")
public class AeOther extends BaseEntity<AeOther> {
	/** 不良事件基础表ID*/
	@TableField(value="basic_id")
	private Long basicId;
	
	/** 事件详细情况描述*/
	@TableField(value="detail_info")
	@Length(max=2000) private String detailInfo;
	
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	/** 事件详细情况描述_1*/
	@TableField(value="detail_info_one")
	@Length(max=2000) private String detailInfoOne;

	/** 事件详细情况描述_2*/
	@TableField(value="detail_info_two")
	@Length(max=2000) private String detailInfoTwo;
	

	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
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

	public String getDetailInfoOne() {
		return detailInfoOne;
	}

	public void setDetailInfoOne(String detailInfoOne) {
		this.detailInfoOne = detailInfoOne;
	}

	public String getDetailInfoTwo() {
		return detailInfoTwo;
	}

	public void setDetailInfoTwo(String detailInfoTwo) {
		this.detailInfoTwo = detailInfoTwo;
	}
}