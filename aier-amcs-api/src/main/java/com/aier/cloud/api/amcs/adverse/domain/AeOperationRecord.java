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
package com.aier.cloud.api.amcs.adverse.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;

/**
 * T_AE_OPERATION_RECORD
 * 
 * @author 爱尔眼科
 * @since 2022-10-21 10:48:39
 */

public class AeOperationRecord extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3583124045979340524L;

	/** 不良事件基础表ID */
	private Long basicId;

	/** 医院ID */
	private Long hospId;

	/** 所在节点 （1 省区 2集团 3学组/专家） */
	private Integer node;

	/** 操作类型 （1 保存 2 暂存 3 合并 5 取消 7 点评） */
	@TableField(value = "type")
	private Integer type;

	/** 意见 */
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
