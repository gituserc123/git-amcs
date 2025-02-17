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
import lombok.Data;

/**
 * T_FIN_INS_SINGLE_DISEASE
 * 单病种付费表
 * @author 爱尔眼科
 * @since 2023-04-04 16:59:21
 */
@Data
public class FinInsSingleDisease extends BaseEntity {
	/** 月主表ID*/
	private Long monthId;
	
	/** 创建者ID*/
	private Long creator;
	
	/** 创建时间*/
	private java.util.Date createDate;
	
	/** 医院ID*/
	private Long hospId;
	
	/** 超单病种结算标准原因*/
	private String exceededReason;
	
	/** 主表ID*/
	private Long mainId;
	
	/** 单病种结算政策（有单病种的医疗机构填写）*/
	private String settlementPolicy;
	
	/** 单病种耗材（晶体/羊膜等）结算标准*/
	private String consumableSettlementStandard;
	
	/** 截至填报月预计节余/超支总金额（万元，节余填正，超支填负数）*/
	private String balanceOrDeficitAmount;

}