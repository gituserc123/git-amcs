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
package com.aier.cloud.api.amcs.domain.idr;

import java.util.Date;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_AMCS_IDR_BASE_INFO 传染病基本信息表
 * 
 * @author 爱尔眼科
 * @since 2023-04-24 10:31:27
 */
public class AmcsIdrBaseInfo extends BaseEntity {
	/** '卡片ID' */
	private String cardid;

	/** 患者/死者姓名 */
	private String patientname;

	/** 出生日期 */
	private java.util.Date birthdate;

	/** 性别编码 */
	private Integer gendercode;

	/** 性别名称 */
	private String gendername;

	/** 民族编码 */
	private String nationcode;

	/** 民族名称 */
	private String nationname;

	/** 工作单位 */
	private String employerorgname;

	/** 传染病人群分类编码 */
	private Integer idrOccupationcode;

	/** 慢病职业编码 */
	private Integer ncdOccupationcode;

	/** 死者生前职业编码 */
	private Integer codrisOccupationcode;

	/** 职业名称 */
	private String occupationname;

	/** 具体的其他职业信息 */
	private String otheroccupationname;

	/** 患者/死者家属姓名 */
	private String guardianname;

	/** 联系电话 */
	private String telecom;

	/** 报告单位所属县区编码 */
	private String orgcountycode;

	/** 报告单位所属县区名称 */
	private String orgcountyname;

	/** 报告单位编码 */
	private String orgcode;

	/** 报告单位名称 */
	private String orgname;

	/** 现住地址类型编码 */
	private String livingaddressattributioncode;

	/** 现住地址类型名称 */
	private String livingaddressattributionname;

	/** 现住地址编码 */
	private String livingaddresscode;

	/** 现住地址名称 */
	private String livingaddressname;

	/** 详细现住地址 */
	private String livingaddressdetails;

	/** 有效身份证件类型 */
	private String idcardtype;

	/** 有效身份证件类型名称 */
	private String idcardtypename;

	/** 证件号码 */
	private String idcardcode;

	/** 国家或地区 */
	private String country;

	/** 婚姻状况编码 */
	private String maritalstatuscode;

	/** 婚姻状况名称 */
	private String maritalstatusname;

	/** 文化程度编码 */
	private String educationlevelcode;

	/** 文化程度名称 */
	private String educationlevelname;

	/** 疾病病种编码 */
	private String diseasecode;

	/** 疾病病种名称 */
	private String diseasename;

	/** 诊断日期 */
	private java.util.Date diagnosisdate;

	/** 疾病诊断代码ICD10编码 */
	private String diseasecause;

	/** 备注 */
	private String cardnotes;

	/** 死亡日期 */
	private java.util.Date deathdate;

	/** 填卡医生姓名 */
	private String fillingdoctorname;

	/** 报告日期 */
	private java.util.Date cardfillingtime;

	/** 户籍地类型编码 */
	private String domicileaddressattributioncode;

	/** 户籍地类型名称 */
	private String domicileaddressattributionname;

	/** 户籍地址编码 */
	private String domicileaddresscode;

	/** 户籍地址名称 */
	private String domicileaddressname;
	/** 上传人时间 */

	private java.util.Date uploadTime;

	/** 上传人名称 */
	private String uploadName;

	/** 上传人 */
	private Long uploador;

	/** 制单人 */
	private Long maker;

	public java.util.Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(java.util.Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadName() {
		return uploadName;
	}

	public void setUploadName(String uploadName) {
		this.uploadName = uploadName;
	}

	public Long getUploador() {
		return uploador;
	}

	public void setUploador(Long uploador) {
		this.uploador = uploador;
	}

	public Long getMaker() {
		return maker;
	}

	public void setMaker(Long maker) {
		this.maker = maker;
	}

	public java.util.Date getMakedTime() {
		return makedTime;
	}

	public void setMakedTime(java.util.Date makedTime) {
		this.makedTime = makedTime;
	}

	public String getMakerName() {
		return makerName;
	}

	public void setMakerName(String makerName) {
		this.makerName = makerName;
	}

	public Long getAuditor() {
		return auditor;
	}

	public void setAuditor(Long auditor) {
		this.auditor = auditor;
	}

	public java.util.Date getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(java.util.Date auditTime) {
		this.auditTime = auditTime;
	}

	/** 制单时间 */
	private java.util.Date makedTime;

	/** 制单人名称 */
	private String makerName;

	/** 审核人 */
	private Long auditor;
	/** 审核人名称*/
	  private String auditorName;
	/** 审核时间 */
	private java.util.Date auditTime;

	/** 户籍地详细地址 */
	private String domicileadrressdetails;

	/** 常住地址 */
	private String lifetimeaddr;

	/** 死者生前常住地址国标 */
	private String lifetimezonecode;

	/** 死者生前常住地址国标名称 */
	private String lifetimezonecodename;

	/** 生前常住村编码 */
	private String lifetimevillagecode;

	/** 生前常住村名称 */
	private String lifetimevillagecodename;

	/** 生前常住址类型 */
	private String lifetimeaddrtypecode;

	/** 生前常住址类型名称 */
	private String lifetimeaddrtypecodename;

	/** 户籍所在村编码 */
	private String registervillagecode;

	/** 户籍所在村名称 */
	private String registervillagecodename;

	/** 死亡地点 */
	private String deathplacecode;

	/** 死亡地点名称 */
	private String deathplacecodename;

	/** 诊断类型编码 */
	private String diagnosistypecode;

	/** 诊断类型名称 */
	private String diagnosistypename;

	/** 住院号 */
	private String hospitalnum;

	/** 病例分类编码 */
	private String caseclassificationcode;

	/** 病例分类名称 */
	private String caseclassificationname;

	/** 其他具体疾病名称/病毒分型 */
	private String otherdiseasename;

	/** 发病日期 */
	private java.util.Date onsetdate;

	/** 密切接触者有无相同症状编码 */
	private String closecontactssymptomcode;

	/** 密切接触者有无相同症状名称 */
	private String closecontactssymptomname;

	/** 录入人/操作人 */
	private String customer;

	/** 舒张压 */
	private String lastxycdzsz;

	/** 收缩压 */
	private String lastxycdzss;

	/** 空腹血糖测定值 */
	private String lastkfxtcdz;

	/** 餐后血糖测定值 */
	private String lastchxtcdz;

	/** 转归 */
	private String outcomecode;

	/** 转归名称 */
	private String outcomecodename;

	/** 病理诊断 */
	private String pathology;

	/** 门诊号 */
	private String patientnum;

	/** 病理号 */
	private String pathologynum;

	/** 是否首发病例 */
	private String firstcase;

	/** ICD-O-3形态学 */
	private String icdo3morphology;

	/** ICD-O-3分化程度 */
	private String icdo3degree;

	/** 肿瘤分期是否不详 */
	private String stagingunknown;

	/** 肿瘤分期T */
	private String tumorstaget;

	/** 肿瘤分期N */
	private String tumorstagen;

	/** 肿瘤分期M */
	private String tumorstagem;

	/** 肿瘤分期0-Ⅳ */
	private String tumorstageiv;

	/** 入院时间 */
	private java.util.Date admissiontime;

	/** 出院时间 */
	private java.util.Date dischargetime;

	/** 死亡原因 */
	private String deathcause;

	/** 死亡原因编码名称 */
	private String deathcausename;

	/** 具体死亡原因 */
	private String specifideathcause;

	/** 最高诊断依据 */
	private String heartbraindiagnosis;

	/** 最高诊断依据 */
	private String tumordiagnosis;

	/** 管理地区编码 */
	private String managerzonecode;

	/** 管理地区名称 */
	private String managerzonecodename;

	/** 管理单位编码 */
	private String managerorgcode;

	/** 管理单位名称 */
	private String managerorgcodename;

	/** 最高诊断单位 */
	private String highestdiagnosisunit;

	/** 最高诊断单位名称 */
	private String highestdiagnosisunitname;

	/** 报告卡状态 */
	private String delflag;

	/** 用户ID */
	private Long useridcreate;

	/** （a）直接死亡原因疾病名称 */
	private String causea;

	/** （a）直接死亡原因疾病ICD10编码 */
	private String icdcodea;

	/** (a)发病至死亡时间间隔 */
	private String intervaltimea;

	/** a发病至死亡时间间隔单位 */
	private String intervalunitcodea;

	/** a发病至死亡时间间隔单位名称 */
	private String intervalunitcodeaname;

	/** (b)引起(a)的疾病或情况 */
	private String causeb;

	/** b引起a的疾病或情况ICD10 */
	private String icdcodeb;

	/** b发病到死亡的时间间隔 */
	private String intervaltimeb;

	/** b发病至死亡时间间隔单位 */
	private String intervalunitcodeb;

	/** b发病至死亡时间间隔单位名称 */
	private String intervalunitcodebname;

	/** I引起(b)的疾病或情况 */
	private String causec;

	/** I引起(b)的疾病或情况ICD10 */
	private String icdcodec;

	/** c发病到死亡的大概时间间隔 */
	private String intervaltimec;

	/** c发病至死亡时间间隔单位 */
	private String intervalunitcodec;

	/** c发病至死亡时间间隔单位名称 */
	private String intervalunitcodecname;

	/** (d)引起（c）直接导致死亡的疾病 */
	private String caused;

	/** d直接导致死亡的疾病ICD10编码 */
	private String icdcoded;

	/** d发病到死亡的时间间隔 */
	private String intervaltimed;

	/** d发病至死亡时间间隔单位 */
	private String intervalunitcoded;

	/** d发病至死亡时间间隔单位名称 */
	private String intervalunitcodedname;

	/** 其它疾病诊断1 */
	private String causeother;

	/** 其他疾病诊断（促进死亡但与 */
	private String icdcodeother;

	/** 根本死亡原因 */
	private String basiccause;

	/** 根本死亡原因ICD10编码 */
	private String basicicdcode;

	/** 生前主要疾病最高诊断单位 */
	private String diagnosticunitcode;

	/** 生前主要疾病最高诊断单位名称 */
	private String diagnosticunitcodename;

	/** 死亡时是否处于妊娠期或妊娠终止后42天内 */
	private String womantypecode;

	/** 生前主要疾病最高诊断依据 */
	private String diagnosticbasiscode;

	/** 生前主要疾病最高诊断依据名称 */
	private String diagnosticbasiscodename;

	/** 医师签名 */
	private String doctorname;

	/** 民警签名 */
	private String policename;

	/** 填表日期 */
	private java.util.Date fillcarddate;

	/** 死者生前病史及症状体征 */
	private String symptoms;

	/** 被调查者姓名 */
	private String investigatedname;

	/** 与死者关系 */
	private String relationship;

	/** 被调查者联系电话 */
	private String investigatedtel;

	/** 死因推断 */
	private String infercause;

	/** 调查者签名 */
	private String investigator;

	/** 调查日期 */
	private java.util.Date investigatedate;

	/** 报告人ID */
	private String entrypeoplename;

	/** 审核时间 */
	private java.util.Date validtime;

	/** 订正状态 */
	private String correctionstate;

	/** 审核人 */
	private String auditpeoplename;

	/** 第一次审核时间 */
	private java.util.Date firstvalidtime;

	/** 卡片状态 */
	private String auditstate;

	/** 删除类型编码 */
	private String deletingtypecode;

	/** 删除类型名称 */
	private String deletingtypename;

	/** 具体删除原因 */
	private String deletingreasondetails;

	/** XML流水号 */
	private String eventid;

	/** 临床严重程度编码 */
	private String newpneumseveritycode;

	/** 临床严重程度名称 */
	private String newpneumseverityname;

	/** 输入来源地 */
	private String placecode;

	/** 输入病例类型 */
	private String foreigntypecode;

	/** 输入来源地-其他国家 */
	private String placeother;

	/** 出院日期 */
	private java.util.Date outhosdate;

	/** 收治状态/转诊状态 */
	private String mgmtstatuscode;

	/** 收治机构/转诊机构 */
	private String currmgmtorgcode;

	/** ORF1ab靶标Ct值 */
	private String ncvorfctv;

	/** N靶标Ct值 */
	private String ncvnctv;

	/** 死亡原因是否与新冠感染有关 */
	private String isdeadbythiscode;

	/** 直接死亡诊断 */
	private String symptomscode;

	/** key1 */
	private String key1;

	/** key2 */
	private String key2;

	/** key3 */
	private String key3;

	/** key4 */
	private String key4;

	/** key5 */
	private String key5;

	/** key6 */
	private String key6;

	/** key7 */
	private String key7;

	/** 医院ID */
	private Long hospId;

	/** 医院名称 */
	private String hospName;

	/**
	 * "事件状态 （0 未审 1 已审 2 删除） "
	 */
	private Integer status;

	/** 创建者ID */
	private Long creator;

	/** 创建时间 */
	private java.util.Date createDate;
	/**
	 * AmcsIdrAids详情列表
	 */

	AmcsIdrAids amcsIdrAids;
	/**
	 * AmcsIdrHFMD详情列表
	 */

	AmcsIdrHfmd amcsIdrHfmd;
	/**
	 * AmcsIdrAfp详情列表
	 */

	AmcsIdrAfp amcsIdrAfp;

	/**
	 * AmcsIdrHb详情列表
	 */
	AmcsIdrHb amcsIdrHb;

	Date timeBegin;

	Date timeEnd;

	public Date getTimeBegin() {
		return timeBegin;
	}

	public void setTimeBegin(Date timeBegin) {
		this.timeBegin = timeBegin;
	}

	public Date getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(Date timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getPatientname() {
		return patientname;
	}

	public void setPatientname(String patientname) {
		this.patientname = patientname;
	}

	public java.util.Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(java.util.Date birthdate) {
		this.birthdate = birthdate;
	}

	public Integer getGendercode() {
		return gendercode;
	}

	public void setGendercode(Integer gendercode) {
		this.gendercode = gendercode;
	}

	public String getGendername() {
		return gendername;
	}

	public void setGendername(String gendername) {
		this.gendername = gendername;
	}

	public String getNationcode() {
		return nationcode;
	}

	public void setNationcode(String nationcode) {
		this.nationcode = nationcode;
	}

	public String getNationname() {
		return nationname;
	}

	public void setNationname(String nationname) {
		this.nationname = nationname;
	}

	public String getEmployerorgname() {
		return employerorgname;
	}

	public void setEmployerorgname(String employerorgname) {
		this.employerorgname = employerorgname;
	}

	public Integer getIdrOccupationcode() {
		return idrOccupationcode;
	}

	public void setIdrOccupationcode(Integer idrOccupationcode) {
		this.idrOccupationcode = idrOccupationcode;
	}

	public Integer getNcdOccupationcode() {
		return ncdOccupationcode;
	}

	public void setNcdOccupationcode(Integer ncdOccupationcode) {
		this.ncdOccupationcode = ncdOccupationcode;
	}

	public Integer getCodrisOccupationcode() {
		return codrisOccupationcode;
	}

	public void setCodrisOccupationcode(Integer codrisOccupationcode) {
		this.codrisOccupationcode = codrisOccupationcode;
	}

	public String getOccupationname() {
		return occupationname;
	}

	public void setOccupationname(String occupationname) {
		this.occupationname = occupationname;
	}

	public String getOtheroccupationname() {
		return otheroccupationname;
	}

	public void setOtheroccupationname(String otheroccupationname) {
		this.otheroccupationname = otheroccupationname;
	}

	public String getGuardianname() {
		return guardianname;
	}

	public void setGuardianname(String guardianname) {
		this.guardianname = guardianname;
	}

	public String getTelecom() {
		return telecom;
	}

	public void setTelecom(String telecom) {
		this.telecom = telecom;
	}

	public String getOrgcountycode() {
		return orgcountycode;
	}

	public void setOrgcountycode(String orgcountycode) {
		this.orgcountycode = orgcountycode;
	}

	public String getOrgcountyname() {
		return orgcountyname;
	}

	public void setOrgcountyname(String orgcountyname) {
		this.orgcountyname = orgcountyname;
	}

	public String getOrgcode() {
		return orgcode;
	}

	public void setOrgcode(String orgcode) {
		this.orgcode = orgcode;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getLivingaddressattributioncode() {
		return livingaddressattributioncode;
	}

	public void setLivingaddressattributioncode(String livingaddressattributioncode) {
		this.livingaddressattributioncode = livingaddressattributioncode;
	}

	public String getLivingaddressattributionname() {
		return livingaddressattributionname;
	}

	public void setLivingaddressattributionname(String livingaddressattributionname) {
		this.livingaddressattributionname = livingaddressattributionname;
	}

	public String getLivingaddresscode() {
		return livingaddresscode;
	}

	public void setLivingaddresscode(String livingaddresscode) {
		this.livingaddresscode = livingaddresscode;
	}

	public String getLivingaddressname() {
		return livingaddressname;
	}

	public void setLivingaddressname(String livingaddressname) {
		this.livingaddressname = livingaddressname;
	}

	public String getLivingaddressdetails() {
		return livingaddressdetails;
	}

	public void setLivingaddressdetails(String livingaddressdetails) {
		this.livingaddressdetails = livingaddressdetails;
	}

	public String getIdcardtype() {
		return idcardtype;
	}

	public void setIdcardtype(String idcardtype) {
		this.idcardtype = idcardtype;
	}

	public String getIdcardtypename() {
		return idcardtypename;
	}

	public void setIdcardtypename(String idcardtypename) {
		this.idcardtypename = idcardtypename;
	}

	public String getIdcardcode() {
		return idcardcode;
	}

	public void setIdcardcode(String idcardcode) {
		this.idcardcode = idcardcode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMaritalstatuscode() {
		return maritalstatuscode;
	}

	public void setMaritalstatuscode(String maritalstatuscode) {
		this.maritalstatuscode = maritalstatuscode;
	}

	public String getMaritalstatusname() {
		return maritalstatusname;
	}

	public void setMaritalstatusname(String maritalstatusname) {
		this.maritalstatusname = maritalstatusname;
	}

	public String getEducationlevelcode() {
		return educationlevelcode;
	}

	public void setEducationlevelcode(String educationlevelcode) {
		this.educationlevelcode = educationlevelcode;
	}

	public String getEducationlevelname() {
		return educationlevelname;
	}

	public void setEducationlevelname(String educationlevelname) {
		this.educationlevelname = educationlevelname;
	}

	public String getDiseasecode() {
		return diseasecode;
	}

	public void setDiseasecode(String diseasecode) {
		this.diseasecode = diseasecode;
	}

	public String getDiseasename() {
		return diseasename;
	}

	public void setDiseasename(String diseasename) {
		this.diseasename = diseasename;
	}

	public java.util.Date getDiagnosisdate() {
		return diagnosisdate;
	}

	public void setDiagnosisdate(java.util.Date diagnosisdate) {
		this.diagnosisdate = diagnosisdate;
	}

	public String getDiseasecause() {
		return diseasecause;
	}

	public void setDiseasecause(String diseasecause) {
		this.diseasecause = diseasecause;
	}

	public String getCardnotes() {
		return cardnotes;
	}

	public void setCardnotes(String cardnotes) {
		this.cardnotes = cardnotes;
	}

	public java.util.Date getDeathdate() {
		return deathdate;
	}

	public void setDeathdate(java.util.Date deathdate) {
		this.deathdate = deathdate;
	}

	public String getFillingdoctorname() {
		return fillingdoctorname;
	}

	public void setFillingdoctorname(String fillingdoctorname) {
		this.fillingdoctorname = fillingdoctorname;
	}

	public java.util.Date getCardfillingtime() {
		return cardfillingtime;
	}

	public void setCardfillingtime(java.util.Date cardfillingtime) {
		this.cardfillingtime = cardfillingtime;
	}

	public String getDomicileaddressattributioncode() {
		return domicileaddressattributioncode;
	}

	public void setDomicileaddressattributioncode(String domicileaddressattributioncode) {
		this.domicileaddressattributioncode = domicileaddressattributioncode;
	}

	public String getDomicileaddressattributionname() {
		return domicileaddressattributionname;
	}

	public void setDomicileaddressattributionname(String domicileaddressattributionname) {
		this.domicileaddressattributionname = domicileaddressattributionname;
	}

	public String getDomicileaddresscode() {
		return domicileaddresscode;
	}

	public void setDomicileaddresscode(String domicileaddresscode) {
		this.domicileaddresscode = domicileaddresscode;
	}

	public String getDomicileaddressname() {
		return domicileaddressname;
	}

	public void setDomicileaddressname(String domicileaddressname) {
		this.domicileaddressname = domicileaddressname;
	}

	public String getDomicileadrressdetails() {
		return domicileadrressdetails;
	}

	public void setDomicileadrressdetails(String domicileadrressdetails) {
		this.domicileadrressdetails = domicileadrressdetails;
	}

	public String getLifetimeaddr() {
		return lifetimeaddr;
	}

	public void setLifetimeaddr(String lifetimeaddr) {
		this.lifetimeaddr = lifetimeaddr;
	}

	public String getLifetimezonecode() {
		return lifetimezonecode;
	}

	public void setLifetimezonecode(String lifetimezonecode) {
		this.lifetimezonecode = lifetimezonecode;
	}

	public String getLifetimezonecodename() {
		return lifetimezonecodename;
	}

	public void setLifetimezonecodename(String lifetimezonecodename) {
		this.lifetimezonecodename = lifetimezonecodename;
	}

	public String getLifetimevillagecode() {
		return lifetimevillagecode;
	}

	public void setLifetimevillagecode(String lifetimevillagecode) {
		this.lifetimevillagecode = lifetimevillagecode;
	}

	public String getLifetimevillagecodename() {
		return lifetimevillagecodename;
	}

	public void setLifetimevillagecodename(String lifetimevillagecodename) {
		this.lifetimevillagecodename = lifetimevillagecodename;
	}

	public String getLifetimeaddrtypecode() {
		return lifetimeaddrtypecode;
	}

	public void setLifetimeaddrtypecode(String lifetimeaddrtypecode) {
		this.lifetimeaddrtypecode = lifetimeaddrtypecode;
	}

	public String getLifetimeaddrtypecodename() {
		return lifetimeaddrtypecodename;
	}

	public void setLifetimeaddrtypecodename(String lifetimeaddrtypecodename) {
		this.lifetimeaddrtypecodename = lifetimeaddrtypecodename;
	}

	public String getRegistervillagecode() {
		return registervillagecode;
	}

	public void setRegistervillagecode(String registervillagecode) {
		this.registervillagecode = registervillagecode;
	}

	public String getRegistervillagecodename() {
		return registervillagecodename;
	}

	public void setRegistervillagecodename(String registervillagecodename) {
		this.registervillagecodename = registervillagecodename;
	}

	public String getDeathplacecode() {
		return deathplacecode;
	}

	public void setDeathplacecode(String deathplacecode) {
		this.deathplacecode = deathplacecode;
	}

	public String getDeathplacecodename() {
		return deathplacecodename;
	}

	public void setDeathplacecodename(String deathplacecodename) {
		this.deathplacecodename = deathplacecodename;
	}

	public String getDiagnosistypecode() {
		return diagnosistypecode;
	}

	public void setDiagnosistypecode(String diagnosistypecode) {
		this.diagnosistypecode = diagnosistypecode;
	}

	public String getDiagnosistypename() {
		return diagnosistypename;
	}

	public void setDiagnosistypename(String diagnosistypename) {
		this.diagnosistypename = diagnosistypename;
	}

	public String getHospitalnum() {
		return hospitalnum;
	}

	public void setHospitalnum(String hospitalnum) {
		this.hospitalnum = hospitalnum;
	}

	public String getCaseclassificationcode() {
		return caseclassificationcode;
	}

	public void setCaseclassificationcode(String caseclassificationcode) {
		this.caseclassificationcode = caseclassificationcode;
	}

	public String getCaseclassificationname() {
		return caseclassificationname;
	}

	public void setCaseclassificationname(String caseclassificationname) {
		this.caseclassificationname = caseclassificationname;
	}

	public String getOtherdiseasename() {
		return otherdiseasename;
	}

	public void setOtherdiseasename(String otherdiseasename) {
		this.otherdiseasename = otherdiseasename;
	}

	public java.util.Date getOnsetdate() {
		return onsetdate;
	}

	public void setOnsetdate(java.util.Date onsetdate) {
		this.onsetdate = onsetdate;
	}

	public String getClosecontactssymptomcode() {
		return closecontactssymptomcode;
	}

	public void setClosecontactssymptomcode(String closecontactssymptomcode) {
		this.closecontactssymptomcode = closecontactssymptomcode;
	}

	public String getClosecontactssymptomname() {
		return closecontactssymptomname;
	}

	public void setClosecontactssymptomname(String closecontactssymptomname) {
		this.closecontactssymptomname = closecontactssymptomname;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getLastxycdzsz() {
		return lastxycdzsz;
	}

	public void setLastxycdzsz(String lastxycdzsz) {
		this.lastxycdzsz = lastxycdzsz;
	}

	public String getLastxycdzss() {
		return lastxycdzss;
	}

	public void setLastxycdzss(String lastxycdzss) {
		this.lastxycdzss = lastxycdzss;
	}

	public String getLastkfxtcdz() {
		return lastkfxtcdz;
	}

	public void setLastkfxtcdz(String lastkfxtcdz) {
		this.lastkfxtcdz = lastkfxtcdz;
	}

	public String getLastchxtcdz() {
		return lastchxtcdz;
	}

	public void setLastchxtcdz(String lastchxtcdz) {
		this.lastchxtcdz = lastchxtcdz;
	}

	public String getOutcomecode() {
		return outcomecode;
	}

	public void setOutcomecode(String outcomecode) {
		this.outcomecode = outcomecode;
	}

	public String getOutcomecodename() {
		return outcomecodename;
	}

	public void setOutcomecodename(String outcomecodename) {
		this.outcomecodename = outcomecodename;
	}

	public String getPathology() {
		return pathology;
	}

	public void setPathology(String pathology) {
		this.pathology = pathology;
	}

	public String getPatientnum() {
		return patientnum;
	}

	public void setPatientnum(String patientnum) {
		this.patientnum = patientnum;
	}

	public String getPathologynum() {
		return pathologynum;
	}

	public void setPathologynum(String pathologynum) {
		this.pathologynum = pathologynum;
	}

	public String getFirstcase() {
		return firstcase;
	}

	public void setFirstcase(String firstcase) {
		this.firstcase = firstcase;
	}

	public String getIcdo3morphology() {
		return icdo3morphology;
	}

	public void setIcdo3morphology(String icdo3morphology) {
		this.icdo3morphology = icdo3morphology;
	}

	public String getIcdo3degree() {
		return icdo3degree;
	}

	public void setIcdo3degree(String icdo3degree) {
		this.icdo3degree = icdo3degree;
	}

	public String getStagingunknown() {
		return stagingunknown;
	}

	public void setStagingunknown(String stagingunknown) {
		this.stagingunknown = stagingunknown;
	}

	public String getTumorstaget() {
		return tumorstaget;
	}

	public void setTumorstaget(String tumorstaget) {
		this.tumorstaget = tumorstaget;
	}

	public String getTumorstagen() {
		return tumorstagen;
	}

	public void setTumorstagen(String tumorstagen) {
		this.tumorstagen = tumorstagen;
	}

	public String getTumorstagem() {
		return tumorstagem;
	}

	public void setTumorstagem(String tumorstagem) {
		this.tumorstagem = tumorstagem;
	}

	public String getTumorstageiv() {
		return tumorstageiv;
	}

	public void setTumorstageiv(String tumorstageiv) {
		this.tumorstageiv = tumorstageiv;
	}

	public java.util.Date getAdmissiontime() {
		return admissiontime;
	}

	public void setAdmissiontime(java.util.Date admissiontime) {
		this.admissiontime = admissiontime;
	}

	public java.util.Date getDischargetime() {
		return dischargetime;
	}

	public void setDischargetime(java.util.Date dischargetime) {
		this.dischargetime = dischargetime;
	}

	public String getDeathcause() {
		return deathcause;
	}

	public void setDeathcause(String deathcause) {
		this.deathcause = deathcause;
	}

	public String getDeathcausename() {
		return deathcausename;
	}

	public void setDeathcausename(String deathcausename) {
		this.deathcausename = deathcausename;
	}

	public String getSpecifideathcause() {
		return specifideathcause;
	}

	public void setSpecifideathcause(String specifideathcause) {
		this.specifideathcause = specifideathcause;
	}

	public String getHeartbraindiagnosis() {
		return heartbraindiagnosis;
	}

	public void setHeartbraindiagnosis(String heartbraindiagnosis) {
		this.heartbraindiagnosis = heartbraindiagnosis;
	}

	public String getTumordiagnosis() {
		return tumordiagnosis;
	}

	public void setTumordiagnosis(String tumordiagnosis) {
		this.tumordiagnosis = tumordiagnosis;
	}

	public String getManagerzonecode() {
		return managerzonecode;
	}

	public void setManagerzonecode(String managerzonecode) {
		this.managerzonecode = managerzonecode;
	}

	public String getManagerzonecodename() {
		return managerzonecodename;
	}

	public void setManagerzonecodename(String managerzonecodename) {
		this.managerzonecodename = managerzonecodename;
	}

	public String getManagerorgcode() {
		return managerorgcode;
	}

	public void setManagerorgcode(String managerorgcode) {
		this.managerorgcode = managerorgcode;
	}

	public String getManagerorgcodename() {
		return managerorgcodename;
	}

	public void setManagerorgcodename(String managerorgcodename) {
		this.managerorgcodename = managerorgcodename;
	}

	public String getHighestdiagnosisunit() {
		return highestdiagnosisunit;
	}

	public void setHighestdiagnosisunit(String highestdiagnosisunit) {
		this.highestdiagnosisunit = highestdiagnosisunit;
	}

	public String getHighestdiagnosisunitname() {
		return highestdiagnosisunitname;
	}

	public void setHighestdiagnosisunitname(String highestdiagnosisunitname) {
		this.highestdiagnosisunitname = highestdiagnosisunitname;
	}

	public String getDelflag() {
		return delflag;
	}

	public void setDelflag(String delflag) {
		this.delflag = delflag;
	}

	public Long getUseridcreate() {
		return useridcreate;
	}

	public void setUseridcreate(Long useridcreate) {
		this.useridcreate = useridcreate;
	}

	public String getCausea() {
		return causea;
	}

	public void setCausea(String causea) {
		this.causea = causea;
	}

	public String getIcdcodea() {
		return icdcodea;
	}

	public void setIcdcodea(String icdcodea) {
		this.icdcodea = icdcodea;
	}

	public String getIntervaltimea() {
		return intervaltimea;
	}

	public void setIntervaltimea(String intervaltimea) {
		this.intervaltimea = intervaltimea;
	}

	public String getIntervalunitcodea() {
		return intervalunitcodea;
	}

	public void setIntervalunitcodea(String intervalunitcodea) {
		this.intervalunitcodea = intervalunitcodea;
	}

	public String getIntervalunitcodeaname() {
		return intervalunitcodeaname;
	}

	public void setIntervalunitcodeaname(String intervalunitcodeaname) {
		this.intervalunitcodeaname = intervalunitcodeaname;
	}

	public String getCauseb() {
		return causeb;
	}

	public void setCauseb(String causeb) {
		this.causeb = causeb;
	}

	public String getIcdcodeb() {
		return icdcodeb;
	}

	public void setIcdcodeb(String icdcodeb) {
		this.icdcodeb = icdcodeb;
	}

	public String getIntervaltimeb() {
		return intervaltimeb;
	}

	public void setIntervaltimeb(String intervaltimeb) {
		this.intervaltimeb = intervaltimeb;
	}

	public String getIntervalunitcodeb() {
		return intervalunitcodeb;
	}

	public void setIntervalunitcodeb(String intervalunitcodeb) {
		this.intervalunitcodeb = intervalunitcodeb;
	}

	public String getIntervalunitcodebname() {
		return intervalunitcodebname;
	}

	public void setIntervalunitcodebname(String intervalunitcodebname) {
		this.intervalunitcodebname = intervalunitcodebname;
	}

	public String getCausec() {
		return causec;
	}

	public void setCausec(String causec) {
		this.causec = causec;
	}

	public String getIcdcodec() {
		return icdcodec;
	}

	public void setIcdcodec(String icdcodec) {
		this.icdcodec = icdcodec;
	}

	public String getIntervaltimec() {
		return intervaltimec;
	}

	public void setIntervaltimec(String intervaltimec) {
		this.intervaltimec = intervaltimec;
	}

	public String getIntervalunitcodec() {
		return intervalunitcodec;
	}

	public void setIntervalunitcodec(String intervalunitcodec) {
		this.intervalunitcodec = intervalunitcodec;
	}

	public String getIntervalunitcodecname() {
		return intervalunitcodecname;
	}

	public void setIntervalunitcodecname(String intervalunitcodecname) {
		this.intervalunitcodecname = intervalunitcodecname;
	}

	public String getCaused() {
		return caused;
	}

	public void setCaused(String caused) {
		this.caused = caused;
	}

	public String getIcdcoded() {
		return icdcoded;
	}

	public void setIcdcoded(String icdcoded) {
		this.icdcoded = icdcoded;
	}

	public String getIntervaltimed() {
		return intervaltimed;
	}

	public void setIntervaltimed(String intervaltimed) {
		this.intervaltimed = intervaltimed;
	}

	public String getIntervalunitcoded() {
		return intervalunitcoded;
	}

	public void setIntervalunitcoded(String intervalunitcoded) {
		this.intervalunitcoded = intervalunitcoded;
	}

	public String getIntervalunitcodedname() {
		return intervalunitcodedname;
	}

	public void setIntervalunitcodedname(String intervalunitcodedname) {
		this.intervalunitcodedname = intervalunitcodedname;
	}

	public String getCauseother() {
		return causeother;
	}

	public void setCauseother(String causeother) {
		this.causeother = causeother;
	}

	public String getIcdcodeother() {
		return icdcodeother;
	}

	public void setIcdcodeother(String icdcodeother) {
		this.icdcodeother = icdcodeother;
	}

	public String getBasiccause() {
		return basiccause;
	}

	public void setBasiccause(String basiccause) {
		this.basiccause = basiccause;
	}

	public String getBasicicdcode() {
		return basicicdcode;
	}

	public void setBasicicdcode(String basicicdcode) {
		this.basicicdcode = basicicdcode;
	}

	public String getDiagnosticunitcode() {
		return diagnosticunitcode;
	}

	public void setDiagnosticunitcode(String diagnosticunitcode) {
		this.diagnosticunitcode = diagnosticunitcode;
	}

	public String getDiagnosticunitcodename() {
		return diagnosticunitcodename;
	}

	public void setDiagnosticunitcodename(String diagnosticunitcodename) {
		this.diagnosticunitcodename = diagnosticunitcodename;
	}

	public String getWomantypecode() {
		return womantypecode;
	}

	public void setWomantypecode(String womantypecode) {
		this.womantypecode = womantypecode;
	}

	public String getDiagnosticbasiscode() {
		return diagnosticbasiscode;
	}

	public void setDiagnosticbasiscode(String diagnosticbasiscode) {
		this.diagnosticbasiscode = diagnosticbasiscode;
	}

	public String getDiagnosticbasiscodename() {
		return diagnosticbasiscodename;
	}

	public void setDiagnosticbasiscodename(String diagnosticbasiscodename) {
		this.diagnosticbasiscodename = diagnosticbasiscodename;
	}

	public String getDoctorname() {
		return doctorname;
	}

	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	public String getPolicename() {
		return policename;
	}

	public void setPolicename(String policename) {
		this.policename = policename;
	}

	public java.util.Date getFillcarddate() {
		return fillcarddate;
	}

	public void setFillcarddate(java.util.Date fillcarddate) {
		this.fillcarddate = fillcarddate;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getInvestigatedname() {
		return investigatedname;
	}

	public void setInvestigatedname(String investigatedname) {
		this.investigatedname = investigatedname;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getInvestigatedtel() {
		return investigatedtel;
	}

	public void setInvestigatedtel(String investigatedtel) {
		this.investigatedtel = investigatedtel;
	}

	public String getInfercause() {
		return infercause;
	}

	public void setInfercause(String infercause) {
		this.infercause = infercause;
	}

	public String getInvestigator() {
		return investigator;
	}

	public void setInvestigator(String investigator) {
		this.investigator = investigator;
	}

	public java.util.Date getInvestigatedate() {
		return investigatedate;
	}

	public void setInvestigatedate(java.util.Date investigatedate) {
		this.investigatedate = investigatedate;
	}

	public String getEntrypeoplename() {
		return entrypeoplename;
	}

	public void setEntrypeoplename(String entrypeoplename) {
		this.entrypeoplename = entrypeoplename;
	}

	public java.util.Date getValidtime() {
		return validtime;
	}

	public void setValidtime(java.util.Date validtime) {
		this.validtime = validtime;
	}

	public String getCorrectionstate() {
		return correctionstate;
	}

	public void setCorrectionstate(String correctionstate) {
		this.correctionstate = correctionstate;
	}

	public String getAuditpeoplename() {
		return auditpeoplename;
	}

	public void setAuditpeoplename(String auditpeoplename) {
		this.auditpeoplename = auditpeoplename;
	}

	public java.util.Date getFirstvalidtime() {
		return firstvalidtime;
	}

	public void setFirstvalidtime(java.util.Date firstvalidtime) {
		this.firstvalidtime = firstvalidtime;
	}

	public String getAuditstate() {
		return auditstate;
	}

	public void setAuditstate(String auditstate) {
		this.auditstate = auditstate;
	}

	public String getDeletingtypecode() {
		return deletingtypecode;
	}

	public void setDeletingtypecode(String deletingtypecode) {
		this.deletingtypecode = deletingtypecode;
	}

	public String getDeletingtypename() {
		return deletingtypename;
	}

	public void setDeletingtypename(String deletingtypename) {
		this.deletingtypename = deletingtypename;
	}

	public String getDeletingreasondetails() {
		return deletingreasondetails;
	}

	public void setDeletingreasondetails(String deletingreasondetails) {
		this.deletingreasondetails = deletingreasondetails;
	}

	public String getEventid() {
		return eventid;
	}

	public void setEventid(String eventid) {
		this.eventid = eventid;
	}

	public String getNewpneumseveritycode() {
		return newpneumseveritycode;
	}

	public void setNewpneumseveritycode(String newpneumseveritycode) {
		this.newpneumseveritycode = newpneumseveritycode;
	}

	public String getNewpneumseverityname() {
		return newpneumseverityname;
	}

	public void setNewpneumseverityname(String newpneumseverityname) {
		this.newpneumseverityname = newpneumseverityname;
	}

	public String getPlacecode() {
		return placecode;
	}

	public void setPlacecode(String placecode) {
		this.placecode = placecode;
	}

	public String getForeigntypecode() {
		return foreigntypecode;
	}

	public void setForeigntypecode(String foreigntypecode) {
		this.foreigntypecode = foreigntypecode;
	}

	public String getPlaceother() {
		return placeother;
	}

	public void setPlaceother(String placeother) {
		this.placeother = placeother;
	}

	public java.util.Date getOuthosdate() {
		return outhosdate;
	}

	public void setOuthosdate(java.util.Date outhosdate) {
		this.outhosdate = outhosdate;
	}

	public String getMgmtstatuscode() {
		return mgmtstatuscode;
	}

	public void setMgmtstatuscode(String mgmtstatuscode) {
		this.mgmtstatuscode = mgmtstatuscode;
	}

	public String getCurrmgmtorgcode() {
		return currmgmtorgcode;
	}

	public void setCurrmgmtorgcode(String currmgmtorgcode) {
		this.currmgmtorgcode = currmgmtorgcode;
	}

	public String getNcvorfctv() {
		return ncvorfctv;
	}

	public void setNcvorfctv(String ncvorfctv) {
		this.ncvorfctv = ncvorfctv;
	}

	public String getNcvnctv() {
		return ncvnctv;
	}

	public void setNcvnctv(String ncvnctv) {
		this.ncvnctv = ncvnctv;
	}

	public String getIsdeadbythiscode() {
		return isdeadbythiscode;
	}

	public void setIsdeadbythiscode(String isdeadbythiscode) {
		this.isdeadbythiscode = isdeadbythiscode;
	}

	public String getSymptomscode() {
		return symptomscode;
	}

	public void setSymptomscode(String symptomscode) {
		this.symptomscode = symptomscode;
	}

	public String getKey1() {
		return key1;
	}

	public void setKey1(String key1) {
		this.key1 = key1;
	}

	public String getKey2() {
		return key2;
	}

	public void setKey2(String key2) {
		this.key2 = key2;
	}

	public String getKey3() {
		return key3;
	}

	public void setKey3(String key3) {
		this.key3 = key3;
	}

	public String getKey4() {
		return key4;
	}

	public void setKey4(String key4) {
		this.key4 = key4;
	}

	public String getKey5() {
		return key5;
	}

	public void setKey5(String key5) {
		this.key5 = key5;
	}

	public String getKey6() {
		return key6;
	}

	public void setKey6(String key6) {
		this.key6 = key6;
	}

	public String getKey7() {
		return key7;
	}

	public void setKey7(String key7) {
		this.key7 = key7;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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

	public AmcsIdrAids getAmcsIdrAids() {
		return amcsIdrAids;
	}

	public void setAmcsIdrAids(AmcsIdrAids amcsIdrAids) {
		this.amcsIdrAids = amcsIdrAids;
	}

	public AmcsIdrHfmd getAmcsIdrHfmd() {
		return amcsIdrHfmd;
	}

	public void setAmcsIdrHfmd(AmcsIdrHfmd amcsIdrHfmd) {
		this.amcsIdrHfmd = amcsIdrHfmd;
	}

	public AmcsIdrAfp getAmcsIdrAfp() {
		return amcsIdrAfp;
	}

	public void setAmcsIdrAfp(AmcsIdrAfp amcsIdrAfp) {
		this.amcsIdrAfp = amcsIdrAfp;
	}

	public AmcsIdrHb getAmcsIdrHb() {
		return amcsIdrHb;
	}

	public void setAmcsIdrHb(AmcsIdrHb amcsIdrHb) {
		this.amcsIdrHb = amcsIdrHb;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
}