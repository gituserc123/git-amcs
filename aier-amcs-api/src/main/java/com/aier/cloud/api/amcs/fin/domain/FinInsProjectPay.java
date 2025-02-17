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
 * T_FIN_INS_PROJECT_PAY
 * 项目付费表
 * @author 爱尔眼科
 * @since 2023-04-03 10:12:19
 */
@Data
public class FinInsProjectPay extends BaseEntity {
	/** 创建者ID*/
	private Long creator;
	
	/** 创建时间*/
	private java.util.Date createDate;
	
	/** 医院ID*/
	private Long hospId;
	
	/** 主表ID*/
	private Long mainId;

	/** monthId*/
	private Long monthId;
	
	/** 按项目付费结算病种*/
	private String settledByProjectPayment;
	
	/** 医保报销政策 */
	private String reimbursementPolicy;

	/** 年度总指标（单位：万元）*/
	private java.math.BigDecimal totalIndicator;

	/** 截至填报月实际已使用医保总额（单位：万元）*/
	private java.math.BigDecimal actualUsedAmount;

	/** 超总额原因（500字符以上）*/
	private String exceedReason;

	/** 超总额整改措施（500字符以上）*/
	private String rectificationMeasures;

	/** 是否向医院ceo、院长、临床科室等书面反馈（是/否）*/
	private String feedbackToManagement;

}