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
package com.aier.cloud.api.amcs.adverse.domain.amrs;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import com.aier.cloud.basic.api.domain.base.BaseEntity;
import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

import java.math.BigDecimal;

/**
 * T_AMRS_PATIENT_BASE 住院病案首页基础信息表
 * 
 * @author 爱尔眼科
 * @since 2019-06-03 17:27:33
 * @version 1.0
 * 			1.1 增加属性-日间手术
 */
@Data
public class AmrsPatientBase extends BaseEntity {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = 1L;

	public enum State implements IEnum, EnumDict<State> {
		// 状态 (0保存 1提交)
		SAVE("保存", 0), SUBMIT("提交", 1);

		private String name;
		private Integer value;

		private State(String name, Integer value) {
			this.name = name;
			this.value = value;
		}

		public static String getName(Integer value) {
			for (State c : State.values()) {
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

		@Override
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

		@Override
		public String toString() {
			return String.valueOf(this.getValue());
		}
	}

	/** 入院方式（码表入院方式admission_mode） */
	@Length(max = 20)
	private String inpWay;

	/** 入院方式名 */
	private String inpWayName;

	/** 日常生活能力评定量表（入院）得分（0-100） */
	private Integer inpAdl;

	/** 日常生活能力评定量表（出院）得分（0-100） */
	private Integer dischargeAdl;

	/** 抗生素使用目的（码表抗生素使用目的antibiotics_intended） */
	private String antibioticsIntended;

	/** 抗生素使用目的名 */
	private String antibioticsIntendedName;

	/** 抗生素使用方案（码表抗生素使用方案antibiotics_scenario） */
	private String antibioticsScenario;

	/** 抗生素使用方案名 */
	private String antibioticsScenarioName;

	/** 抗生素使用天数 */
	private Integer antibioticsDays;

	/** 传染病报告（1无 2有） */
	private Integer contagionReport;

	/** 护理级别（码表护理级别nursing_level） */
	private String nursingLevel;

	/** 护理级别名称 */
	private String nursingLevelName;

	/** 主诊医师ID（北京）*/
	private Long attendingPhysicianId;

	/** 主诊医师姓名（北京）*/
	@Length(max=50) private String attendingPhysicianName;

	/** 输血反应（1是 2否）*/
	private Integer transfusionReaction;

	/** 自费金额（上海）*/
	private BigDecimal selfExpense;

	/** 其他支付（上海）*/
	private BigDecimal totalOtherPay;

	/** 随诊（1是 2否）*/
	private Integer followUp;

	/** 随诊期限（周）*/
	private Integer followUpWeek;

	/** 随诊期限（月）*/
	private Integer followUpMonth;

	/** 随诊期限（年）*/
	private Integer followUpYear;

	/** Ⅰ类手术切口预防性应用抗菌药物（1是 2否）*/
	private Integer incisionIAntibiotics;

	/** 抗菌药使用时间（小时）*/
	private BigDecimal antibioticsHours;

	/** 抗菌药物联合用药（1是 2否）*/
	private Integer antibioticsCombination;

	/** 是否因同一病种再入院（1是 2否）*/
	private Integer readmidttedSameDisease;

	/** 与上次出院日期间隔天数（天）*/
	private Integer lastIntervalDays;

	/** 病人来源地址（重庆 街道地址） */
	private String patientSourceAddrName;

	/** 出院与门诊符合情况（码表diag_compliance） */
	private String discOutpCoincide;

	/** 出院与门诊符合情况名称 */
	private String discOutpCoincideName;

	/** 入院与出院符合情况（码表diag_compliance） */
	private String inpDiscCoincide;

	/** 入院与出院符合情况名称 */
	private String inpDiscCoincideName;

	/** 术前与术后符合情况（码表diag_compliance） */
	private String prePostOperCoincide;

	/** 术前与术后符合情况名称 */
	private String prePostOperCoincideName;

	/** 临床与病理符合情况（码表diag_compliance） */
	private String clinPathCoincide;

	/** 临床与病理符合情况名称 */
	private String clinPathCoincideName;

	/** 放射与病理符合情况（码表diag_compliance） */
	private String radioPathCoincide;

	/** 放射与病理符合情况名称 */
	private String radioPathCoincideName;

	/** 单病种管理(1是 2否) */
	private Integer singleDiseaseManage;

	/** 实施DRGS管理（码表drgs_mode） */
	private String drgsManage;

	/** 实施DRGS管理名称 */
	private String drgsManageName;

	/** 入院视力类型（OS） 1数值 2描述*/
	private String inpVisionOsType;

	/** 入院视力编号（OS）*/
	private String inpVisionOs;

	/** 入院视力（OS）*/
	private String inpVisionOsName;

	/** 入院视力类型（OD） 1数值 2描述*/
	private String inpVisionOdType;

	/** 入院视力编号（OD）*/
	private String inpVisionOd;

	/** 入院视力（OD）*/
	private String inpVisionOdName;

	/** 出院视力类型（OS） 1数值 2描述*/
	private String dischargeVisionOsType;

	/** 出院视力编号（OS）*/
	private String dischargeVisionOs;

	/** 出院视力（OS）*/
	private String dischargeVisionOsName;

	/** 出院视力类型（OD） 1数值 2描述*/
	private String dischargeVisionOdType;

	/** 出院视力编号（OD）*/
	private String dischargeVisionOd;

	/** 出院视力（OD）*/
	private String dischargeVisionOdName;

	/** 抗生素使用情况（1使用 2未使用） */
	private Integer antibioticsUsage;

	/** 细菌培养标本送检(1是 2否) */
	private Integer bacterialSpecInsp;

	/** 法定传染病（码表contagion_type） */
	private String infectDisease;

	/** 法定传染病名 */
	private String infectDiseaseName;

	/** 肿瘤分期T */
	private String tumorT;

	/** 肿瘤分钟N */
	private String tumorN;

	/** 肿瘤分期M */
	private String tumorM;

	/** 肿瘤总分期(码表tumor_stage) */
	private String tumorStaging;

	/** 肿瘤总分期名称 */
	private String tumorStagingName;

	/** 病人来源地址编码（重庆 街道编码） */
	private String patientSourceAddrCode;

	/** 诊疗机构名 */
	private String hospName;

	/** 医院组织机代码 */
	private String hospOrgCode;

	/** 付费方式(码表结算费别cost_type) */
	private String costType;

	/** 付费方式名称 */
	private String costTypeName;

	/** 健康卡号 */
	private String cardNumber;

	/** 病案号 */
	private String medicalNumber;

	/** 住院次数 */
	private Integer inpTimes;

	/** 住院流水号 */
	private String inpNumber;

	/** 患者姓名 */
	private String patientName;

	/** 性别编码(码表性别sex) */
	private String sex;

	/** 性别 */
	private String sexName;

	/** 出生日期 */
	private java.util.Date birthday;

	/** 年龄(岁) */
	private Integer age;

	/** 月龄(月) */
	private BigDecimal ageMonth;

	/** 国籍编码(码表国家country) */
	private String country;

	/** 国籍 */
	private String countryName;

	/** 新生儿出生重量(克) */
	private Integer birthWeight;

	/** 新生儿入院重量(克) */
	private Integer inpWeight;

	/** 出生地省(地址字典) */
	private String birthProvCode;

	/** 出生地省 */
	private String birthProvName;

	/** 出生地市(地址字典) */
	private String birthCityCode;

	/** 出生地市 */
	private String birthCityName;

	/** 出生地区(地址字典) */
	private String birthAreaCode;

	/** 出生地区 */
	private String birthAreaName;

	/** 籍贯省(地址字典) */
	private String nativeProvCode;

	/** 籍贯省 */
	private String nativeProvName;

	/** 籍贯市(地址字典) */
	private String nativeCityCode;

	/** 籍贯市 */
	private String nativeCityName;

	/** 籍贯区(地址字典) */
	private String nativeAreaCode;

	/** 籍贯区 */
	private String nativeAreaName;

	/** 民族(码表民族nation) */
	private String nation;

	/** 民族名称 */
	private String nationName;

	/** 证件类型(码表证件类型document_type) */
	private String idType;

	/** 身份证号 */
	private String idNumber;

	/** 职业编码(码表职业occu_type) */
	private String occuType;

	/** 职业名称 */
	private String occuTypeName;

	/** 婚姻状况(码表婚姻marriage) */
	private String marriage;

	/** 婚姻状况 */
	private String marriageName;

	/** 现住址省(地址字典) */
	private String curProvCode;

	/** 现住址省 */
	private String curProvName;

	/** 现住址市(地址字典) */
	private String curCityCode;

	/** 现住址市 */
	private String curCityName;

	/** 现住址区(地址字典) */
	private String curAreaCode;

	/** 现住址区 */
	private String curAreaName;

	/** 现住址街道(地址字典) */
	private String curStreetCode;

	/** 现住址街道 */
	private String curStreetName;

	/** 现住址 */
	private String curAddr;

	/** 现住址邮编(地址字典) */
	private String curZipCode;

	/** 电话 */
	private String tel;

	/** 户口地址省(地址字典) */
	private String regProvCode;

	/** 户口地址省 */
	private String regProvName;

	/** 户口地址市(地址字典) */
	private String regCityCode;

	/** 户口地址市 */
	private String regCityName;

	/** 户口地址区(地址字典) */
	private String regAreaCode;

	/** 户口地址区 */
	private String regAreaName;

	/** 户口地址 */
	private String regAddr;

	/** 户口地址邮编(地址字典) */
	private String regZipCode;

	/** 单位名称 */
	private String compName;

	/** 单位地址省(地址字典) */
	private String compProvCode;

	/** 单位地址省 */
	private String compProvName;

	/** 单位地址市(地址字典) */
	private String compCityCode;

	/** 单位地址市 */
	private String compCityName;

	/** 单位地址区(地址字典) */
	private String compAreaCode;

	/** 单位地址区 */
	private String compAreaName;

	/** 单位地址 */
	private String compAddr;

	/** 单位电话 */
	private String compTel;

	/** 单位邮编(地址字典) */
	private String compZipCode;

	/** 联系人姓名 */
	private String consName;

	/** 联系人关系（联系人关系relation） */
	private String consRelaCode;

	/** 联系人关系 */
	private String consRelaName;

	/** 联系人地址省(地址字典) */
	private String consProvCode;

	/** 联系人地址省 */
	private String consProvName;

	/** 联系人地址市(地址字典) */
	private String consCityCode;

	/** 联系人地址市 */
	private String consCityName;

	/** 联系人地址区(地址字典) */
	private String consAreaCode;

	/** 联系人地址区 */
	private String consAreaName;

	/** 联系人地址 */
	private String consAddr;

	/** 联系人电话 */
	private String consTel;

	/** 入院途径(码表入院途径inp_route) */
	private String inpRoute;

	/** 入院途径名称 */
	private String inpRouteName;

	/** 入院转诊机构 */
	private String inpRouteInst;

	/** 入院时间 */
	private java.util.Date inpTime;

	/** 入院科室 */
	private Long inpDeptId;

	/** 入院科室名称 */
	private String inpDeptName;

	/** 入院病房 */
	private Long inpWardId;

	/** 入院病房名称 */
	private String inpWardName;

	/** 转科科室 */
	private Long transDeptId;

	/** 转科科室名称 */
	private String transDeptName;

	/** 出院时间 */
	private java.util.Date dischargeTime;

	/** 出院科室 */
	private Long dischargeDeptId;

	/** 出院科室名称 */
	private String dischargeDeptName;

	/** 出院病房 */
	private Long dischargeWardId;

	/** 出院病房名称 */
	private String dischargeWardName;

	/** 住院天数 */
	private Integer inpDays;

	/** 门（急）诊诊断 */
	private String outpDiagName;

	/** 门（急）诊诊断疾病(ICD编码) */
	private String outpDiagIcd;

	/** 门（急）诊医师 */
	private Long outpDoctor;

	/** 门（急）诊医师姓名 */
	private String outpDoctorName;

	/** 入院病情(码表入院病情inp_cond) */
	private String inpCond;

	/** 入院病情 */
	private String inpCondName;

	/** 入院情况(码表入院情况inp_status) */
	private String inpStatus;

	/** 入院情况 */
	private String inpStatusName;

	/** 入院诊断(ICD编码) */
	private String inpDiagIcd;

	/** 入院诊断 */
	private String inpDiagName;

	/** 入院确诊日期 */
	private java.util.Date inpDiagDate;

	/** 首要诊断确诊日期 */
	private java.util.Date priDiagDate;

	/** 病危(1是、2否) */
	private Integer criticallyIll;

	/** 是否抢救(1无、2有) */
	private Integer rescue;

	/** 抢救次数 */
	private Integer rescueTimes;

	/** 抢救成功次数 */
	private Integer rescueSuccTimes;

	/** 病例分型(码表病例分型case_class) */
	private String caseClass;

	/** 病例分型 */
	private String caseClassName;

	/** 实施临床路径(1是、2否) */
	private Integer clncPatw;

	/** 完成临床路径(1是、2否) */
	private Integer clncPatwFinish;

	/** 是否变异(1是、2否) */
	private Integer clncPatwMuta;

	/** 诊疗机构 */
	private Long hospId;

	/** 退出原因 */
	private String clncPatwExitR;

	/** 变异原因（变异原因mrs_variation_reason）*/
	private String clncPatwMutaR;

	/** 变异原因名称 */
	@Length(max=50) private String clncPatwMutaRName;

	/** 损伤中毒的外部原因 */
	private String injuryPoisonName;

	/** 损伤中毒的外部原因疾病编码(ICD编码) */
	private String injuryPoisonIcd;

	/** 病理诊断 */
	private String pathlgDiagName;

	/** 病理诊断(ICD编码) */
	private String pathlgDiagIcd;

	/** 病理号 */
	private String pathlgNumber;

	/** 有无药物过敏(1无2有) */
	private Integer drugAllergy;

	/** 过敏药物 */
	private String allergyDrug;

	/** 死亡患者尸检(1是2否) */
	private Integer deadAutopsy;

	/** 血型(码表血型blood_type) */
	private String bloodType;

	/** 血型名称 */
	private String bloodTypeName;

	/** RH类型(码表RH类型rh_type) */
	private String rhType;

	/** RH类型名称 */
	private String rhTypeName;

	/** 科主任ID */
	private Long deptDirectorId;

	/** 科主任姓名 */
	private String deptDirectorName;

	/** 主任（副主任）医师 */
	private Long archiaterId;

	/** 主任（副主任）医师姓名 */
	private String archiaterName;

	/** 主治医师 */
	private Long attendingId;

	/** 主治医师姓名 */
	private String attendingName;

	/** 住院医师 */
	private Long residentId;

	/** 住院医师姓名 */
	private String residentName;

	/** 责任护士 */
	private Long priNurseId;

	/** 责任护士姓名 */
	private String priNurseName;

	/** 进修医师 */
	private Long refresherId;

	/** 进修医师姓名 */
	private String refresherName;

	/** 实习医师 */
	private Long internId;

	/** 实习医生姓名 */
	private String internName;

	/** 编码员 */
	private Long codePersonId;

	/** 编码员姓名 */
	private String codePersonName;

	/** 病案质量(码表病案mrs_quality) */
	private String amrsQuality;

	/** 病案质量 */
	private String amrsQualityName;

	/** 质控医师 */
	private Long qcDoctorId;

	/** 质控医师姓名 */
	private String qcDoctorName;

	/** 质控护士 */
	private Long qcNruseId;

	/** 质控护士姓名 */
	private String qcNruseName;

	/** 质控日期 */
	private java.util.Date qcDate;

	/** 是否非计划重返手术室(11 48小时重返手术室、12 31天内非预期重返手术室、2无) */
	private Integer nonPlanOpr;

	/** 晶体类型L(码表晶体类型crystal_type) */
	private String crystalTypeL;

	/** 晶体类型名L */
	private String crystalTypeNameL;

	/** 晶体类型R(码表晶体类型crystal_type) */
	private String crystalTypeR;

	/** 晶体类型名R */
	private String crystalTypeNameR;

	/** 晶体型号L */
	private String crystalBrandL;

	/** 晶体型号R */
	private String crystalBrandR;

	/** 出院方式(码表出院方式departure_mode) */
	private String dischargeWay;

	/** 出院方式名 */
	private String dischargeWayName;

	/** 出院情况(码表出院情况leave_status) */
	private String dischargeCond;

	/** 出院情况 */
	private String dischargeCondName;

	/** 接收医疗机构 */
	private String receivingInst;

	/** 31天内再住院计划(1无、2有) */
	private Integer inpPlan;

	/** 目的 */
	private String planPurpose;

	/** 入院前昏迷天 */
	private Integer bComaDays;

	/** 入院前昏迷时 */
	private Integer bComaHours;

	/** 入院前昏迷分 */
	private Integer bComaMins;

	/** 入院后昏迷天 */
	private Integer aComaDays;

	/** 入院后昏迷时 */
	private Integer aComaHours;

	/** 入院后昏迷分 */
	private Integer aComaMins;

	/** 总费用 */
	private BigDecimal totalFee;

	/** 自付费用 */
	private BigDecimal selfPay;

	/** 一般医疗服务费 */
	private BigDecimal medicalServiceFee;

	/** 一般治疗操作费 */
	private BigDecimal treatHandleFee;

	/** 护理费 */
	private BigDecimal nursingFee;

	/** 病理诊断费 */
	private BigDecimal pthlgDiagFee;

	/** 实验室诊断费 */
	private BigDecimal labDiagFee;

	/** 其它费用 */
	private BigDecimal serviceOtherFee;

	/** 影像学诊断费 */
	private BigDecimal pacsDiagFee;

	/** 临床诊断费 */
	private BigDecimal clinicDiagFee;

	/** 非手术费用 */
	private BigDecimal nonOprFee;

	/** 临床物理治疗费 */
	private BigDecimal clinicalPhyFee;

	/** 手术治疗费 */
	private BigDecimal oprTreatFee;

	/** 麻醉费 */
	private BigDecimal narcsFee;

	/** 手术费 */
	private BigDecimal oprFee;

	/** 康复费 */
	private BigDecimal recureFee;

	/** 中医治疗费 */
	private BigDecimal tcmTreatFee;

	/** 西药费 */
	private BigDecimal wmFee;

	/** 抗菌素费 */
	private BigDecimal antibioticsFee;

	/** 中成药费 */
	private BigDecimal cpdFee;

	/** 中草药费 */
	private BigDecimal chmFee;

	/** 血费 */
	private BigDecimal bloodFee;

	/** 白蛋白类制品费 */
	private BigDecimal albuminFee;

	/** 球蛋白类制品费 */
	private BigDecimal globulinFee;

	/** 凝血因子类制品费 */
	private BigDecimal cogltFact;

	/** 细胞因子类制品费 */
	private BigDecimal cellFact;

	/** 检查用一次性医用材料费 */
	private BigDecimal examMaterialFee;

	/** 治疗用一次性医用材料费 */
	private BigDecimal treatMaterialFee;

	/** 手术用一次性医用材料费 */
	private BigDecimal oprMaterialFee;

	/** 其它费用 */
	private BigDecimal otherFee;

	/** 状态 （0保存 1提交） */
	private String state;

	/** 备注 */
	private String remarks;

	/** 创建人 */
	private Long creator;

	/** 创建时间 */
	private java.util.Date createDate;

	/** 证件类型名称 */
	private String idTypeName;

	/** 接收医疗机构-社区卫生服务机构或者乡镇卫生院 */
	private String receivingHealthCenter;

	/** 病人来源(码表patient_source) */
	private String patientSource;

	/** 病人来源 */
	private String patientSourceName;

	/** 出生地详细地址 */
	private String birthAddr;

	/** ICD-O-3编码 */
	private String icdo3;

	/** ICD-O-3名称 */
	private String icdo3Name;

	/** 最高诊断依据编码 */
	private String highestDiagBasicCode;

	/** 最高诊断依据名称 */
	private String highestDiagBasicName;

	/** 特级护理（天） */
	private Integer nursingSpecialD;

	/** 特级护理（时） */
	private Integer nursingSpecialH;

	/** Ⅰ级护理（天） */
	private Long nursingFirst;

	/** Ⅱ级护理（天） */
	private Long nursingSecond;

	/** Ⅲ级护理（天） */
	private Long nursingThird;

	/** 医疗组长 */
	private Long treatLeaderId;

	/** 医疗组长姓名 */
	private String treatLeaderName;

	/** 晶体型号名L */
	private String crystalBrandNameL;

	/** 晶体型号名R */
	private String crystalBrandNameR;

	/** 日间手术 */
	private Integer daySurgery;

	/** 入院右眼眼压类型（OD）(码表iop_type)*/
	private String inpTonometryOdType;

	/** 入院右眼眼压类型名称（OD）(码表iop_type)*/
	private String inpTonometryOdTypeName;

	/** 入院右眼眼压数值（OD）(单位mmHg)*/
	private java.math.BigDecimal inpIopOd;

	/** 入院右眼指测眼压结果编码（OD）（码表：finger_tonometry）*/
	private String inpFingerTonometryOd;

	/** 入院右眼指测眼压结果名称（OD）（码表：finger_tonometry）*/
	private String inpFingerTonometryOdName;

	/** 入院左眼眼压类型（OS）(码表iop_type)*/
	private String inpTonometryOsType;

	/** 入院左眼眼压类型名称（OS）(码表iop_type)*/
	private String inpTonometryOsTypeName;

	/** 入院左眼眼压数值（OS）(单位mmHg)*/
	private java.math.BigDecimal inpIopOs;

	/** 入院左眼指测眼压结果编码（OS）（码表：finger_tonometry）*/
	private String inpFingerTonometryOs;

	/** 入院左眼指测眼压结果名称（OS）（码表：finger_tonometry）*/
	private String inpFingerTonometryOsName;

	/** 出院右眼眼压类型（OD）(码表iop_type)*/
	private String discTonometryOdType;

	/** 出院右眼眼压类型名称（OD）(码表iop_type)*/
	private String discTonometryOdTypeName;

	/** 出院右眼眼压数值（OD）(单位mmHg)*/
	private java.math.BigDecimal discIopOd;

	/** 出院右眼指测眼压结果编码（OD）（码表：finger_tonometry）*/
	private String discFingerTonometryOd;

	/** 出院右眼指测眼压结果名称（OD）（码表：finger_tonometry）*/
	private String discFingerTonometryOdName;

	/** 出院左眼眼压类型（OS）(码表iop_type)*/
	private String discTonometryOsType;

	/** 出院左眼眼压类型名称（OS）(码表iop_type)*/
	private String discTonometryOsTypeName;

	/** 出院左眼眼压数值（OS）(单位mmHg)*/
	java.math.BigDecimal discIopOs;

	/** 出院左眼指测眼压结果编码（OS）（码表：finger_tonometry）*/
	private String discFingerTonometryOs;

	/** 出院左眼指测眼压结果名称（OS）（码表：finger_tonometry）*/
	private String discFingerTonometryOsName;

}