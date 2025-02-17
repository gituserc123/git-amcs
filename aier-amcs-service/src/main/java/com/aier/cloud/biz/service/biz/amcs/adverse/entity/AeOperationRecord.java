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

import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;

/**
 * T_AE_OPERATION_RECORD
 * 
 * @author 爱尔眼科
 * @since 2022-10-21 10:48:39
 */
@TableName("T_AE_OPERATION_RECORD")
public class AeOperationRecord extends BaseEntity<AeOperationRecord> {

	private static final long serialVersionUID = 6480456699708322102L;

	/** 不良事件基础表ID */
	@Comment(value = "不良事件基础表ID")
	@TableField(value = "basic_id")
	private Long basicId;

	/** 医院ID */
	@Comment(value = "医院ID")
	@TableField(value = "hosp_id")
	@NotBlank
	private Long hospId;

	/** 所在节点 （1 省区 2集团 3学组/专家） */
	@Comment(value = "所在节点 （1 省区  2集团 3学组/专家）")
	@TableField(value = "node")
	private Integer node;

	/** 操作类型 （1 保存 2 暂存 3 合并 5 取消 7 点评） */
	@Comment(value = "操作类型 （1 保存 2 暂存 3 合并 5 取消 7 点评）")
	@TableField(value = "type")
	private Integer type;

	/** 意见 */
	@Comment(value = "意见")
	@TableField(value = "opinion")
	@Length(max = 500)
	private String opinion;

	public Long getBasicId() {
		return basicId;
	}

	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public Integer getNode() {
		return node;
	}

	public void setNode(Integer node) {
		this.node = node;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getOpinion() {
		return opinion;
	}

	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
}
