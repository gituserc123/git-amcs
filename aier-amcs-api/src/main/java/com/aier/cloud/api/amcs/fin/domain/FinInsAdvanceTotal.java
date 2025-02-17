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
 * T_FIN_INS_ADVANCE_TOTAL
 * 总额预付表
 * @author 爱尔眼科
 * @since 2023-04-04 16:59:21
 */
@Data
public class FinInsAdvanceTotal extends BaseEntity {
	/** 创建者ID*/
	private Long creator;
	
	/** 创建时间*/
	private java.util.Date createDate;
	
	/** 医院ID*/
	private Long hospId;
	
	/** 月主表ID*/
	private Long monthId;
	
	/** 主表ID*/
	private Long mainId;
	
	/** 2023年总额指标（万元）*/
	private java.math.BigDecimal totalAmount;
	
	/** 截至填报月实际已使用医保总额（万元）*/
	private java.math.BigDecimal actualMedicalInsuranceTotal;
	
	/** 超总额原因*/
	private String exceededTotalAmountReason;
	
	/** 总额指标是否包含单病种结算费用（1是，2否）*/
	private Integer singleDiseaseSettlementSign;

	/** 超总额整改措施（500字符以上）*/
	private String rectificationMeasures;

	/** 是否向医院ceo、院长、临床科室等书面反馈（是/否）*/
	private String feedbackToManagement;

}