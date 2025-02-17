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
 * T_FIN_INS_DIP_PAY
 * DIP付费表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:54
 */
@Data
public class FinInsDipPay extends BaseEntity {
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
	
	/** 实际执行DIP付费的时间（已执行DIP付费的医院填写并填写附表1）*/
	private java.util.Date actualExecutionTime;
	
	/** 预计执行DIP付费的时间（暂未执行DIP付费的医院填写）*/
	private java.util.Date expectedExecutionTime;
	
	/** DIP结算系数或机构系数或差异系数*/
	private java.math.BigDecimal differentialCoefficient;
	
	/** 截至填报月预计节余/超支总金额（万元，节余填正，超支填负数）*/
	private java.math.BigDecimal balanceOrOverspend;
	
	/** “三新项目”除外支付政策*/
	private String excludingThreeNew;

	/** 超总额整改措施（500字符以上）*/
	private String rectificationMeasures;

	/** 是否向医院ceo、院长、临床科室等书面反馈（是/否）*/
	private String feedbackToManagement;

	/** DIP总额控制（万元） */
	private java.math.BigDecimal totalBalanceControl;

	/** DIP总分值控制*/
	private java.math.BigDecimal totalScoreControl;

}