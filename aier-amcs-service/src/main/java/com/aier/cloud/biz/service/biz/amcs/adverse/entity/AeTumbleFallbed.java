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

import com.aier.cloud.basic.common.convert.EnumDict;
import com.aier.cloud.basic.core.base.BaseEntity;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IEnum;

/**
 * T_AE_TUMBLE_FALLBED
 * 
 * @author 爱尔眼科
 * @since 2022-08-16 16:29:42
 */
@TableName("T_AE_TUMBLE_FALLBED")
public class AeTumbleFallbed extends BaseEntity<AeTumbleFallbed> {

	private static final long serialVersionUID = -8098737213807382559L;
	
	public enum VisitType implements IEnum, EnumDict<VisitType> {
		/** 就诊类型(1门诊 2住院) */
		OUTP("门诊", 1), INP("住院", 2);

		private String name;
		private Integer value;

		private VisitType(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public static String getName(Integer value) {
			for (VisitType c : VisitType.values()) {
				if (c.getValue() == value) {
					return c.name;
				}
			}
			return null;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		@Override
		public String getEnumCode() {
			return String.valueOf(value);
		}

		@Override
		public String getEnumDesc() {
			return name;
		}
	}
	
	/** 就诊类型(1门诊 2住院) */
	@TableField(value = "visit_type")
	@NotBlank
	private Integer visitType;

	/** 跌倒（坠床）发生时有无约束*/
    @Comment(value="跌倒（坠床）发生时有无约束")
	@TableField(value="fall_constraint_sign")
	private Integer fallConstraintSign;
	
	/** 住院期间是否有陪护*/
    @Comment(value="住院期间是否有陪护")
	@TableField(value="in_hosp_care_sign")
	private Integer inHospCareSign;
	
	/** 陪护人员是否具备陪护能力*/
    @Comment(value="陪护人员是否具备陪护能力")
	@TableField(value="care_person_sign")
	private Integer carePersonSign;
	
	/** 该患者本次住院跌倒/坠床第几次*/
    @Comment(value="该患者本次住院跌倒/坠床第几次")
	@TableField(value="fall_times")
	private Integer fallTimes;
	
	/** 跌倒危险因素*/
    @Comment(value="跌倒危险因素")
	@TableField(value="fall_danger_factor")
	@Length(max=20) private String fallDangerFactor;
	
	/** 跌倒前用药情况*/
    @Comment(value="跌倒前用药情况")
	@TableField(value="pre_fall_condition")
	@Length(max=20) private String preFallCondition;

	/** 跌倒危险因素(其他)*/
    @Comment(value="跌倒危险因素(其他)")
	@TableField(value="fall_danger_factor_remark")
	@Length(max=100) private String fallDangerFactorRemark;

	/** 跌倒前用药情况(其他)*/
    @Comment(value="跌倒前用药情况(其他)")
	@TableField(value="pre_fall_condition_remark")
	@Length(max=100) private String preFallConditionRemark;
	
	/** 跌倒后院内处置情况*/
    @Comment(value="跌倒后院内处置情况")
	@TableField(value="post_fall_condition")
	@Length(max=100) private String postFallCondition;
	
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
	
	/** 不良事件基础表ID*/
    @Comment(value="不良事件基础表ID")
	@TableField(value="basic_id")
	private Long basicId;
	
	/** 跌倒前评估为高危患者*/
    @Comment(value="跌倒前评估为高危患者")
	@TableField(value="prefalleval_sign")
	private Integer prefallevalSign;
	
	/** 风险评估工具*/
    @Comment(value="风险评估工具")
	@TableField(value="risk_eval_tools")
	@Length(max=20) private String riskEvalTools;


	/** 风险评估工具(其他)*/
    @Comment(value="风险评估工具(其他)")
	@TableField(value="risk_eval_tools_remark")
	@Length(max=100) private String riskEvalToolsRemark;

	/** 护理级别是否与评估一致*/
    @Comment(value="护理级别是否与评估一致")
	@TableField(value="nursing_level_sign")
	private Integer nursingLevelSign;
	
	/** 是否按要求进行护理巡视*/
    @Comment(value="是否按要求进行护理巡视")
	@TableField(value="nursing_check_sign")
	private Integer nursingCheckSign;
	
	/** 最近1次跌倒/坠床风险评估距离跌倒/坠床发生的时间*/
    @Comment(value="最近1次跌倒/坠床风险评估距离跌倒/坠床发生的时间")
	@TableField(value="recent_eval_fall_time")
	private Integer recentEvalFallTime;
	
	/** 最近一次风险评估得分分级*/
    @Comment(value="最近一次风险评估得分分级")
	@TableField(value="recent_eval_score")
	private Integer recentEvalScore;
	
	/** 跌倒造成的伤害程度*/
    @Comment(value="跌倒造成的伤害程度")
	@TableField(value="fall_injury_level")
	private Integer fallInjuryLevel;
	
	/** 事件发生前的独立活动能力(activity of daily living,ADL)*/
    @Comment(value="事件发生前的独立活动能力(activity of daily living,ADL)")
	@TableField(value="pre_event_adl")
	private Integer preEventAdl;
	
	/** 当事人发生前意识状态*/
    @Comment(value="当事人发生前意识状态")
	@TableField(value="pre_event_aware_status")
	private Integer preEventAwareStatus;
	
	/** 当事人发生后意识状态*/
    @Comment(value="当事人发生后意识状态")
	@TableField(value="post_event_aware_status")
	private Integer postEventAwareStatus;
	
	/** 患者活动状态*/
    @Comment(value="患者活动状态")
	@TableField(value="patient_adl")
	@Length(max=4) private String patientAdl;
	
	/** 患者活动状态(其他,输入的文本内容)*/
    @Comment(value="患者活动状态(其他,输入的文本内容)")
	@TableField(value="patient_adl_remark")
	@Length(max=100) private String patientAdlRemark;
	
	/** 事件发生时有无其他人员在场*/
    @Comment(value="事件发生时有无其他人员在场")
	@TableField(value="event_present_person")
	@Length(max=10) private String eventPresentPerson;

	/** 事件发生时有无其他人员在场(其他)*/
    @Comment(value="事件发生时有无其他人员在场(其他)")
	@TableField(value="event_present_person_remark")
	@Length(max=100) private String eventPresentPersonRemark;

	/** 详细情况*/
	@Comment(value="详细情况")
	@TableField(value="event_desc")
	@Length(max=2000) private String eventDesc;
	
	/** 病人近一年跌倒≥1次(不含本次)*/
    @Comment(value="病人近一年跌倒≥1次(不含本次)")
	@TableField(value="patient_fall_one_year")
	private Integer patientFallOneYear;


	/** 跌倒对象(1患者本人,2患者家属,3员工) */
	@TableField(value = "fall_person")
	@NotBlank
	private Integer fallPerson;


	public Integer getPatientFallOneYear() {
		return patientFallOneYear;
	}
	public void setPatientFallOneYear(Integer patientFallOneYear) {
		this.patientFallOneYear = patientFallOneYear;
	}
	public Integer getFallConstraintSign() {
		return fallConstraintSign;
	}
	public void setFallConstraintSign(Integer fallConstraintSign) {
		this.fallConstraintSign = fallConstraintSign;
	}
	public Integer getInHospCareSign() {
		return inHospCareSign;
	}
	public void setInHospCareSign(Integer inHospCareSign) {
		this.inHospCareSign = inHospCareSign;
	}
	public Integer getCarePersonSign() {
		return carePersonSign;
	}
	public void setCarePersonSign(Integer carePersonSign) {
		this.carePersonSign = carePersonSign;
	}
	public Integer getFallTimes() {
		return fallTimes;
	}
	public void setFallTimes(Integer fallTimes) {
		this.fallTimes = fallTimes;
	}
	public String getFallDangerFactor() {
		return fallDangerFactor;
	}
	public void setFallDangerFactor(String fallDangerFactor) {
		this.fallDangerFactor = fallDangerFactor;
	}
	public String getPreFallCondition() {
		return preFallCondition;
	}
	public void setPreFallCondition(String preFallCondition) {
		this.preFallCondition = preFallCondition;
	}
	public String getPostFallCondition() {
		return postFallCondition;
	}
	public void setPostFallCondition(String postFallCondition) {
		this.postFallCondition = postFallCondition;
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
	public Long getBasicId() {
		return basicId;
	}
	public void setBasicId(Long basicId) {
		this.basicId = basicId;
	}
	public Integer getPrefallevalSign() {
		return prefallevalSign;
	}
	public void setPrefallevalSign(Integer prefallevalSign) {
		this.prefallevalSign = prefallevalSign;
	}
	public String getRiskEvalTools() {
		return riskEvalTools;
	}
	public void setRiskEvalTools(String riskEvalTools) {
		this.riskEvalTools = riskEvalTools;
	}

	public String getRiskEvalToolsRemark() {
		return riskEvalToolsRemark;
	}

	public void setRiskEvalToolsRemark(String riskEvalToolsRemark) {
		this.riskEvalToolsRemark = riskEvalToolsRemark;
	}

	public Integer getNursingLevelSign() {
		return nursingLevelSign;
	}
	public void setNursingLevelSign(Integer nursingLevelSign) {
		this.nursingLevelSign = nursingLevelSign;
	}
	public Integer getNursingCheckSign() {
		return nursingCheckSign;
	}
	public void setNursingCheckSign(Integer nursingCheckSign) {
		this.nursingCheckSign = nursingCheckSign;
	}
	public Integer getRecentEvalFallTime() {
		return recentEvalFallTime;
	}
	public void setRecentEvalFallTime(Integer recentEvalFallTime) {
		this.recentEvalFallTime = recentEvalFallTime;
	}
	public Integer getRecentEvalScore() {
		return recentEvalScore;
	}
	public void setRecentEvalScore(Integer recentEvalScore) {
		this.recentEvalScore = recentEvalScore;
	}
	public Integer getFallInjuryLevel() {
		return fallInjuryLevel;
	}
	public void setFallInjuryLevel(Integer fallInjuryLevel) {
		this.fallInjuryLevel = fallInjuryLevel;
	}
	public Integer getPreEventAdl() {
		return preEventAdl;
	}
	public void setPreEventAdl(Integer preEventAdl) {
		this.preEventAdl = preEventAdl;
	}
	public Integer getPreEventAwareStatus() {
		return preEventAwareStatus;
	}
	public void setPreEventAwareStatus(Integer preEventAwareStatus) {
		this.preEventAwareStatus = preEventAwareStatus;
	}
	public Integer getPostEventAwareStatus() {
		return postEventAwareStatus;
	}
	public void setPostEventAwareStatus(Integer postEventAwareStatus) {
		this.postEventAwareStatus = postEventAwareStatus;
	}
	public String getPatientAdl() {
		return patientAdl;
	}
	public void setPatientAdl(String patientAdl) {
		this.patientAdl = patientAdl;
	}
	public String getPatientAdlRemark() {
		return patientAdlRemark;
	}
	public void setPatientAdlRemark(String patientAdlRemark) {
		this.patientAdlRemark = patientAdlRemark;
	}
	public String getEventPresentPerson() {
		return eventPresentPerson;
	}
	public void setEventPresentPerson(String eventPresentPerson) {
		this.eventPresentPerson = eventPresentPerson;
	}

	public String getEventPresentPersonRemark() {
		return eventPresentPersonRemark;
	}

	public void setEventPresentPersonRemark(String eventPresentPersonRemark) {
		this.eventPresentPersonRemark = eventPresentPersonRemark;
	}

	public String getFallDangerFactorRemark() {
		return fallDangerFactorRemark;
	}

	public void setFallDangerFactorRemark(String fallDangerFactorRemark) {
		this.fallDangerFactorRemark = fallDangerFactorRemark;
	}

	public String getPreFallConditionRemark() {
		return preFallConditionRemark;
	}

	public void setPreFallConditionRemark(String preFallConditionRemark) {
		this.preFallConditionRemark = preFallConditionRemark;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	public Integer getVisitType() {
		return visitType;
	}

	public void setVisitType(Integer visitType) {
		this.visitType = visitType;
	}

	public Integer getFallPerson() {
		return fallPerson;
	}

	public void setFallPerson(Integer fallPerson) {
		this.fallPerson = fallPerson;
	}
}
