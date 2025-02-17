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
 * T_FIN_INS_DRG_ANALYSIS
 * DRG盈亏分析表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:54
 */
@Data
public class FinInsDrgAnalysis extends BaseEntity {
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
	
	/** 病种（白内障、青光眼、角结膜、泪道、眼底、其他）*/
	private String diseaseType;
	
	/** DIP分组名称*/
	private String groupName;

	/** DRG分组编码*/
	private String groupCode;
	
	/** 2023年总病例数*/
	private java.math.BigDecimal totalCases;
	
	/** 实际住院总费用*/
	private java.math.BigDecimal actualHospitalizationCost;
	
	/** 预计DIP结算医疗总费用*/
	private java.math.BigDecimal expectedSettlementCost;
	
	/** 预计节余/超支总金额（节余填正，超支填负数）*/
	private java.math.BigDecimal expectedBalanceAmount;
	
	/** 例均实际住院费用*/
	private java.math.BigDecimal avgActualCost;
	
	/** 例均预计DIP结算医疗费用*/
	private java.math.BigDecimal avgExpectedSettlement;
	
	/** 例均预计节余/超支金额*/
	private java.math.BigDecimal avgExpectedBalanceAmount;
	
	/** 盈亏情况分析     （含医用耗材建议临床使用的价格区间）*/
	private String profitLossAnalysis;
	
	/** 医保局年度考核指标     （有年度考核指标的医院填写）*/
	private String annualAssessmentTargets;

	private Long insurancePlanCategory;

}