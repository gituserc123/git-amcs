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
 * FinInsDipInfo
 * DIP盈亏分析表/DIP付费表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:54
 */
@Data
public class FinInsDipInfo extends BaseEntity {
	/** analysisId*/
	private Long analysisId;

	/** payId*/
	private Long payId;

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
	
	/** 主要诊断名称*/
	private String mainDiagnosisName;
	
	/** 主要手术及操作名称*/
	private String mainSurgeryName;
	
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
	/** 实际执行DIP付费的时间（已执行DIP付费的医院填写并填写附表1）*/
	private java.util.Date actualExecutionTime;

	/** 预计执行DIP付费的时间（暂未执行DIP付费的医院填写）*/
	private java.util.Date expectedExecutionTime;

	/** DIP结算系数或机构系数或差异系数*/
	private java.math.BigDecimal differentialCoefficient;

	/** 截至填报月预计节余/超支总金额（万元，节余填正，超支填负数）*/
	private java.math.BigDecimal balanceOrOverspend;

	/** DIP人工晶体/羊膜等耗材     建议临床科室选用的价格区间*/
	private String dipConsumables;

	/** 最小值*/
	private java.math.BigDecimal dipConsumablesMin;

	/** 最大值*/
	private java.math.BigDecimal dipConsumablesMax;

	/** “三新项目”除外支付政策*/
	private String excludingThreeNew;


	/** 超总额整改措施（500字符以上）*/
	private String rectificationMeasures;

	/** 是否向医院ceo、院长、临床科室等书面反馈（是/否）*/
	private String feedbackToManagement;

}