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
package com.aier.cloud.biz.ui.biz.aps.util;


import cn.afterturn.easypoi.excel.annotation.Excel;

public class DcgOprCalResultVO {




	/** 医院id*/
	@Excel(name = "医院id" ,orderNum = "1")
	 private Long hospId;

	/** 患者id*/
	@Excel(name = "患者id" ,orderNum = "2")
	private Long patientId;

	/** 患者姓名*/
	@Excel(name = "患者姓名" ,orderNum = "3")
	 private String patientName;

	/** 眼别*/
	@Excel(name = "眼别" ,orderNum = "4")
	 private String eyeTypeName;

	/** 手术开始日期*/
	@Excel(name = "手术开始日期" ,orderNum = "5")
	 private String oprStartDate;

	/** 错误值  0时为正常，>0 时异常 */
	@Excel(name = "错误值" ,orderNum = "6")
	private Long errorCode;

	/** 叠加手术code*/
	@Excel(name = "叠加手术code" ,orderNum = "7")
	 private String mergeOprCode;

	/** 叠加手术名称*/
	@Excel(name = "叠加手术名称" ,orderNum = "8")
	 private String mergeOprName;

	/** 计算后绩效术式*/
	@Excel(name = "计算后绩效术式" ,orderNum = "9")
	 private String calOprShushi;

	/** 中间优先级*/
	@Excel(name = "优先级" ,orderNum = "10")
	private Long tempPriority;

	/** 计算后手术等级*/
	@Excel(name = "手术等级" ,orderNum = "11")
	private String calOprGrade;

	/** 计算后护理等级*/
	@Excel(name = "护理等级" ,orderNum = "12")
	private String calNurGrade;

	/** 计算时间*/
	@Excel(name = "计算时间" ,orderNum = "13")
	private String calTime;

	/** 计算备注*/
	@Excel(name = "计算备注" ,orderNum = "14")
	 private String calRemarks;

	/** 计算结果*/
	@Excel(name = "计算结果" ,orderNum = "15")
	 private String calResult;

	/** 组合手术分类*/
	@Excel(name = "组合手术分类" ,orderNum = "16")
	 private String zhOprClassify;

	/** 组合手术分类名称*/
	@Excel(name = "组合手术分类名称" ,orderNum = "17")
	 private String zhOprClassifyName;

	/** 组合优先级*/
	@Excel(name = "组合优先级" ,orderNum = "18")
	 private String zhPriority;

	/** 手术收入*/
	@Excel(name = "手术收入" ,orderNum = "19")
	private String oprPrice;

	/** 手术应收*/
	@Excel(name = "手术应收" ,orderNum = "20")
	private String oprAr;

	/** 麻醉方式名称*/
	@Excel(name = "麻醉方式" ,orderNum = "21")
	private String anesthesiaName;

	/** 手术医生姓名*/
	@Excel(name = "手术医生姓名" ,orderNum = "22")
	private String surgeonName;

	/** 中间key */
	@Excel(name = "中间key" ,orderNum = "23")
	private String tempKey;

	/** 中间值*/
	@Excel(name = "中间值" ,orderNum = "24")
	private String tempValue;

	/** 计算后 病种名称*/
	@Excel(name = "计算后 病种名称" ,orderNum = "25")
	private String calDiseaseName;

	/**  病种code*/
	@Excel(name = "病种code" ,orderNum = "26")
	private String diseaseCode;

	/** 晶体材料code*/
	@Excel(name = "晶体材料code" ,orderNum = "27")
	private String lensCode;

	/** 晶体材料名称*/
	@Excel(name = "晶体材料名称" ,orderNum = "28")
	private String lensName;

	/** 晶体材料规格*/
	@Excel(name = "晶体材料规格" ,orderNum = "29")
	private String lensSpecif;

	/** 第1助手ID*/
	@Excel(name = "第1助手ID" ,orderNum = "29")
	private Long asst1Id;

	/** 第1助手姓名*/
	@Excel(name = "第1助手姓名" ,orderNum = "29")
	private String asst1Name;
	/** 第2助手ID*/
	@Excel(name = "第2助手ID" ,orderNum = "29")
	private Long asst2Id;

	/** 第2助手姓名*/
	@Excel(name = "第2助手姓名" ,orderNum = "29")
	private String asst2Name;

	/** 巡回护士1ID*/
	@Excel(name = "巡回护士1ID" ,orderNum = "29")
	private Long circuitNurseId1;

	/** 巡回护士1姓名*/
	@Excel(name = "巡回护士1姓名" ,orderNum = "29")
	private String circuitNurseName1;

	/** 巡回护士2ID*/
	@Excel(name = "巡回护士2ID" ,orderNum = "29")
	private Long circuitNurseId2;

	/** 巡回护士2姓名*/
	@Excel(name = "巡回护士2姓名" ,orderNum = "29")
	private String circuitNurseName2;

	/** 巡回护士3ID*/
	@Excel(name = "巡回护士3ID" ,orderNum = "29")
	private Long circuitNurseId3;

	/** 巡回护士3姓名*/
	@Excel(name = "巡回护士3姓名" ,orderNum = "29")
	private String circuitNurseName3;

	/** 器械护士1ID*/
	@Excel(name = "器械护士1ID" ,orderNum = "29")
	private Long apparatusNurseId1;

	/** 器械护士1姓名*/
	@Excel(name = "器械护士1姓名" ,orderNum = "29")
	private String apparatusNurseName1;

	/** 器械护士2ID*/
	@Excel(name = "器械护士2ID" ,orderNum = "29")
	private Long apparatusNurseId2;

	/** 器械护士2姓名*/
	@Excel(name = "器械护士2姓名" ,orderNum = "29")
	private String apparatusNurseName2;

	/** 器械护士3ID*/
	@Excel(name = "器械护士3ID" ,orderNum = "29")
	private Long apparatusNurseId3;
	/** 器械护士3姓名*/
	@Excel(name = "器械护士3姓名" ,orderNum = "29")
	private String apparatusNurseName3;

	/** 跟台护士1ID*/
	@Excel(name = "跟台护士1ID" ,orderNum = "29")
	private Long followNurseId1;

	/** 跟台护士1姓名*/
	@Excel(name = "跟台护士1姓名" ,orderNum = "29")
	private String followNurseName1;

	/** 跟台护士2ID*/
	@Excel(name = "跟台护士2ID" ,orderNum = "29")
	private Long followNurseId2;

	/** 跟台护士2姓名*/
	@Excel(name = "跟台护士2姓名" ,orderNum = "29")
	private String followNurseName2;

	/** 洗眼护士1ID*/
	@Excel(name = "洗眼护士1ID" ,orderNum = "29")
	private Long eyeWashNurseId1;

	/** 洗眼护士1姓名*/
	@Excel(name = "洗眼护士1姓名" ,orderNum = "29")
	private String eyeWashNurseName1;

	/** 洗眼护士2ID*/
	@Excel(name = "洗眼护士2ID" ,orderNum = "29")
	private Long eyeWashNurseId2;

	/** 洗眼护士2姓名*/
	@Excel(name = "洗眼护士2姓名" ,orderNum = "29")
	private String eyeWashNurseName2;

	/** 组合病种code*/
	 private String zhDiseaseCode;

	/** 组合病种名称*/
	 private String zhDiseaseName;



	/** 药物收入*/
	private String dragPrice;

	/** 治疗收入*/
	private String treatPrice;

	/** 总收入*/
	private String totalPrice;



	/** 手术医生id*/
	private Long surgeonId;



	/** 手术科室id*/
	private Long surgeonDeptId;



	/** 手术开始时间*/
	private String actualOprStartTime;

	/** 计算后手术分类code*/
	 private String operationClassifyCode;



	/** 功能性晶体标识*/
	 private String funcLensTag;











	/** 手术科室名称*/
	 private String surgeonDeptName;

	/** 结算日期*/
	private String transDate;


	public String getApparatusNurseName3() {
		return apparatusNurseName3;
	}
	public void setApparatusNurseName3(String apparatusNurseName3) {
		this.apparatusNurseName3 = apparatusNurseName3;
	}
	public Long getFollowNurseId1() {
		return followNurseId1;
	}
	public void setFollowNurseId1(Long followNurseId1) {
		this.followNurseId1 = followNurseId1;
	}
	public String getFollowNurseName1() {
		return followNurseName1;
	}
	public void setFollowNurseName1(String followNurseName1) {
		this.followNurseName1 = followNurseName1;
	}
	public Long getFollowNurseId2() {
		return followNurseId2;
	}
	public void setFollowNurseId2(Long followNurseId2) {
		this.followNurseId2 = followNurseId2;
	}
	public String getFollowNurseName2() {
		return followNurseName2;
	}
	public void setFollowNurseName2(String followNurseName2) {
		this.followNurseName2 = followNurseName2;
	}
	public Long getEyeWashNurseId1() {
		return eyeWashNurseId1;
	}
	public void setEyeWashNurseId1(Long eyeWashNurseId1) {
		this.eyeWashNurseId1 = eyeWashNurseId1;
	}
	public String getEyeWashNurseName1() {
		return eyeWashNurseName1;
	}
	public void setEyeWashNurseName1(String eyeWashNurseName1) {
		this.eyeWashNurseName1 = eyeWashNurseName1;
	}
	public Long getEyeWashNurseId2() {
		return eyeWashNurseId2;
	}
	public void setEyeWashNurseId2(Long eyeWashNurseId2) {
		this.eyeWashNurseId2 = eyeWashNurseId2;
	}
	public String getEyeWashNurseName2() {
		return eyeWashNurseName2;
	}
	public void setEyeWashNurseName2(String eyeWashNurseName2) {
		this.eyeWashNurseName2 = eyeWashNurseName2;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getEyeTypeName() {
		return eyeTypeName;
	}
	public void setEyeTypeName(String eyeTypeName) {
		this.eyeTypeName = eyeTypeName;
	}
	public String getOprStartDate() {
		return oprStartDate;
	}
	public void setOprStartDate(String oprStartDate) {
		this.oprStartDate = oprStartDate;
	}
	public String getMergeOprCode() {
		return mergeOprCode;
	}
	public void setMergeOprCode(String mergeOprCode) {
		this.mergeOprCode = mergeOprCode;
	}
	public String getMergeOprName() {
		return mergeOprName;
	}
	public void setMergeOprName(String mergeOprName) {
		this.mergeOprName = mergeOprName;
	}
	public String getCalOprShushi() {
		return calOprShushi;
	}
	public void setCalOprShushi(String calOprShushi) {
		this.calOprShushi = calOprShushi;
	}
	public String getCalOprGrade() {
		return calOprGrade;
	}
	public void setCalOprGrade(String calOprGrade) {
		this.calOprGrade = calOprGrade;
	}
	public String getCalNurGrade() {
		return calNurGrade;
	}
	public void setCalNurGrade(String calNurGrade) {
		this.calNurGrade = calNurGrade;
	}
	public String getCalRemarks() {
		return calRemarks;
	}
	public void setCalRemarks(String calRemarks) {
		this.calRemarks = calRemarks;
	}
	public String getCalResult() {
		return calResult;
	}
	public void setCalResult(String calResult) {
		this.calResult = calResult;
	}
	public String getZhOprClassify() {
		return zhOprClassify;
	}
	public void setZhOprClassify(String zhOprClassify) {
		this.zhOprClassify = zhOprClassify;
	}
	public String getZhOprClassifyName() {
		return zhOprClassifyName;
	}
	public void setZhOprClassifyName(String zhOprClassifyName) {
		this.zhOprClassifyName = zhOprClassifyName;
	}
	public String getZhPriority() {
		return zhPriority;
	}
	public void setZhPriority(String zhPriority) {
		this.zhPriority = zhPriority;
	}
	public String getZhDiseaseCode() {
		return zhDiseaseCode;
	}
	public void setZhDiseaseCode(String zhDiseaseCode) {
		this.zhDiseaseCode = zhDiseaseCode;
	}
	public String getZhDiseaseName() {
		return zhDiseaseName;
	}
	public void setZhDiseaseName(String zhDiseaseName) {
		this.zhDiseaseName = zhDiseaseName;
	}
	public String getOprPrice() {
		return oprPrice;
	}
	public void setOprPrice(String oprPrice) {
		this.oprPrice = oprPrice;
	}
	public String getDragPrice() {
		return dragPrice;
	}
	public void setDragPrice(String dragPrice) {
		this.dragPrice = dragPrice;
	}
	public String getTreatPrice() {
		return treatPrice;
	}
	public void setTreatPrice(String treatPrice) {
		this.treatPrice = treatPrice;
	}
	public String getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getAnesthesiaName() {
		return anesthesiaName;
	}
	public void setAnesthesiaName(String anesthesiaName) {
		this.anesthesiaName = anesthesiaName;
	}
	public Long getSurgeonId() {
		return surgeonId;
	}
	public void setSurgeonId(Long surgeonId) {
		this.surgeonId = surgeonId;
	}
	public String getSurgeonName() {
		return surgeonName;
	}
	public void setSurgeonName(String surgeonName) {
		this.surgeonName = surgeonName;
	}
	public Long getSurgeonDeptId() {
		return surgeonDeptId;
	}
	public void setSurgeonDeptId(Long surgeonDeptId) {
		this.surgeonDeptId = surgeonDeptId;
	}
	public String getTempKey() {
		return tempKey;
	}
	public void setTempKey(String tempKey) {
		this.tempKey = tempKey;
	}
	public Long getTempPriority() {
		return tempPriority;
	}
	public void setTempPriority(Long tempPriority) {
		this.tempPriority = tempPriority;
	}
	public String getTempValue() {
		return tempValue;
	}
	public void setTempValue(String tempValue) {
		this.tempValue = tempValue;
	}
	public Long getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Long errorCode) {
		this.errorCode = errorCode;
	}
	public String getCalDiseaseName() {
		return calDiseaseName;
	}
	public void setCalDiseaseName(String calDiseaseName) {
		this.calDiseaseName = calDiseaseName;
	}
	public String getOperationClassifyCode() {
		return operationClassifyCode;
	}
	public void setOperationClassifyCode(String operationClassifyCode) {
		this.operationClassifyCode = operationClassifyCode;
	}
	public Long getAsst1Id() {
		return asst1Id;
	}
	public void setAsst1Id(Long asst1Id) {
		this.asst1Id = asst1Id;
	}
	public String getAsst1Name() {
		return asst1Name;
	}
	public void setAsst1Name(String asst1Name) {
		this.asst1Name = asst1Name;
	}
	public String getDiseaseCode() {
		return diseaseCode;
	}
	public void setDiseaseCode(String diseaseCode) {
		this.diseaseCode = diseaseCode;
	}
	public String getLensCode() {
		return lensCode;
	}
	public void setLensCode(String lensCode) {
		this.lensCode = lensCode;
	}
	public String getLensName() {
		return lensName;
	}
	public void setLensName(String lensName) {
		this.lensName = lensName;
	}
	public String getFuncLensTag() {
		return funcLensTag;
	}
	public void setFuncLensTag(String funcLensTag) {
		this.funcLensTag = funcLensTag;
	}
	public String getLensSpecif() {
		return lensSpecif;
	}
	public void setLensSpecif(String lensSpecif) {
		this.lensSpecif = lensSpecif;
	}
	public String getOprAr() {
		return oprAr;
	}
	public void setOprAr(String oprAr) {
		this.oprAr = oprAr;
	}
	public Long getAsst2Id() {
		return asst2Id;
	}
	public void setAsst2Id(Long asst2Id) {
		this.asst2Id = asst2Id;
	}
	public String getAsst2Name() {
		return asst2Name;
	}
	public void setAsst2Name(String asst2Name) {
		this.asst2Name = asst2Name;
	}
	public Long getCircuitNurseId1() {
		return circuitNurseId1;
	}
	public void setCircuitNurseId1(Long circuitNurseId1) {
		this.circuitNurseId1 = circuitNurseId1;
	}
	public String getCircuitNurseName1() {
		return circuitNurseName1;
	}
	public void setCircuitNurseName1(String circuitNurseName1) {
		this.circuitNurseName1 = circuitNurseName1;
	}
	public Long getCircuitNurseId2() {
		return circuitNurseId2;
	}
	public void setCircuitNurseId2(Long circuitNurseId2) {
		this.circuitNurseId2 = circuitNurseId2;
	}
	public String getCircuitNurseName2() {
		return circuitNurseName2;
	}
	public void setCircuitNurseName2(String circuitNurseName2) {
		this.circuitNurseName2 = circuitNurseName2;
	}
	public Long getCircuitNurseId3() {
		return circuitNurseId3;
	}
	public void setCircuitNurseId3(Long circuitNurseId3) {
		this.circuitNurseId3 = circuitNurseId3;
	}
	public String getCircuitNurseName3() {
		return circuitNurseName3;
	}
	public void setCircuitNurseName3(String circuitNurseName3) {
		this.circuitNurseName3 = circuitNurseName3;
	}
	public Long getApparatusNurseId1() {
		return apparatusNurseId1;
	}
	public void setApparatusNurseId1(Long apparatusNurseId1) {
		this.apparatusNurseId1 = apparatusNurseId1;
	}
	public String getApparatusNurseName1() {
		return apparatusNurseName1;
	}
	public void setApparatusNurseName1(String apparatusNurseName1) {
		this.apparatusNurseName1 = apparatusNurseName1;
	}
	public Long getApparatusNurseId2() {
		return apparatusNurseId2;
	}
	public void setApparatusNurseId2(Long apparatusNurseId2) {
		this.apparatusNurseId2 = apparatusNurseId2;
	}
	public String getApparatusNurseName2() {
		return apparatusNurseName2;
	}
	public void setApparatusNurseName2(String apparatusNurseName2) {
		this.apparatusNurseName2 = apparatusNurseName2;
	}
	public Long getApparatusNurseId3() {
		return apparatusNurseId3;
	}
	public void setApparatusNurseId3(Long apparatusNurseId3) {
		this.apparatusNurseId3 = apparatusNurseId3;
	}
	public String getSurgeonDeptName() {
		return surgeonDeptName;
	}

	public String getCalTime() {
		return calTime;
	}

	public void setCalTime(String calTime) {
		this.calTime = calTime;
	}

	public String getActualOprStartTime() {
		return actualOprStartTime;
	}

	public void setActualOprStartTime(String actualOprStartTime) {
		this.actualOprStartTime = actualOprStartTime;
	}

	public String getTransDate() {
		return transDate;
	}

	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}

	public void setSurgeonDeptName(String surgeonDeptName) {
		this.surgeonDeptName = surgeonDeptName;
	}
}
