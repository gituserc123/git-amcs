package com.aier.cloud.api.amcs.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class LacrimalCondition extends PageCondition implements Serializable {
    private static final long serialVersionUID = 4244299905392258927L;
    private Long id;
    private Date modifyDate;
    private Long modifer;
    /** 检查日期*/
    java.util.Date checkDate;

    /** 患者id*/
    private Long patientId;

    /** 患者姓名*/
    private String patientName;

    /** 联系电话*/
    private String mobilePhone;

    /** 渠道*/
    private Integer channel;

    /** od返流*/
    private String odBackflow;

    /** od不入喉*/
    private String odInthroat;

    /** od分泌物*/
    private String odSecretion;

    /** os返流2*/
    private String osBackflow;

    /** os不入喉*/
    private String osInthroat;

    /** os分泌物*/
    private String osSecretion;

    /** 首诊医生处置情况*/
    private String fstTreatInfo;

    /** 首诊科室ID*/
    private Long fstDeptId;

    /** 首诊科室*/
    private String fstDeptName;

    /** 首诊医生ID*/
    private Long fstDoctorId;

    /** 首诊医生*/
    private String fstDoctorName;

    /** 青光眼医生ID*/
    private Long glaucomaDoctorId;

    /** 青光眼医生姓名*/
    private String glaucomaDoctorName;

    /** 青光眼医生诊断结果*/
    private String glaucomaResult;

    /** 未治原因*/
    private String untreatReason;

    /** 是否治疗泪道*/
    private Integer isTreat;

    /** 医院Id*/
    private Long hospId;

    private List<Long> patientIds;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Long getModifer() {
        return modifer;
    }

    public void setModifer(Long modifer) {
        this.modifer = modifer;
    }

    public Date getCheckDate() {
        return checkDate;
    }

    public void setCheckDate(Date checkDate) {
        this.checkDate = checkDate;
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

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Integer getChannel() {
        return channel;
    }

    public void setChannel(Integer channel) {
        this.channel = channel;
    }

    public String getOdBackflow() {
        return odBackflow;
    }

    public void setOdBackflow(String odBackflow) {
        this.odBackflow = odBackflow;
    }

    public String getOdInthroat() {
        return odInthroat;
    }

    public void setOdInthroat(String odInthroat) {
        this.odInthroat = odInthroat;
    }

    public String getOdSecretion() {
        return odSecretion;
    }

    public void setOdSecretion(String odSecretion) {
        this.odSecretion = odSecretion;
    }

    public String getOsBackflow() {
        return osBackflow;
    }

    public void setOsBackflow(String osBackflow) {
        this.osBackflow = osBackflow;
    }

    public String getOsInthroat() {
        return osInthroat;
    }

    public void setOsInthroat(String osInthroat) {
        this.osInthroat = osInthroat;
    }

    public String getOsSecretion() {
        return osSecretion;
    }

    public void setOsSecretion(String osSecretion) {
        this.osSecretion = osSecretion;
    }

    public String getFstTreatInfo() {
        return fstTreatInfo;
    }

    public void setFstTreatInfo(String fstTreatInfo) {
        this.fstTreatInfo = fstTreatInfo;
    }

    public Long getFstDeptId() {
        return fstDeptId;
    }

    public void setFstDeptId(Long fstDeptId) {
        this.fstDeptId = fstDeptId;
    }

    public String getFstDeptName() {
        return fstDeptName;
    }

    public void setFstDeptName(String fstDeptName) {
        this.fstDeptName = fstDeptName;
    }

    public Long getFstDoctorId() {
        return fstDoctorId;
    }

    public void setFstDoctorId(Long fstDoctorId) {
        this.fstDoctorId = fstDoctorId;
    }

    public String getFstDoctorName() {
        return fstDoctorName;
    }

    public void setFstDoctorName(String fstDoctorName) {
        this.fstDoctorName = fstDoctorName;
    }

    public Long getGlaucomaDoctorId() {
        return glaucomaDoctorId;
    }

    public void setGlaucomaDoctorId(Long glaucomaDoctorId) {
        this.glaucomaDoctorId = glaucomaDoctorId;
    }

    public String getGlaucomaDoctorName() {
        return glaucomaDoctorName;
    }

    public void setGlaucomaDoctorName(String glaucomaDoctorName) {
        this.glaucomaDoctorName = glaucomaDoctorName;
    }

    public String getGlaucomaResult() {
        return glaucomaResult;
    }

    public void setGlaucomaResult(String glaucomaResult) {
        this.glaucomaResult = glaucomaResult;
    }

    public String getUntreatReason() {
        return untreatReason;
    }

    public void setUntreatReason(String untreatReason) {
        this.untreatReason = untreatReason;
    }

    public Integer getIsTreat() {
        return isTreat;
    }

    public void setIsTreat(Integer isTreat) {
        this.isTreat = isTreat;
    }

    public Long getHospId() {
        return hospId;
    }

    public void setHospId(Long hospId) {
        this.hospId = hospId;
    }

    public List<Long> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<Long> patientIds) {
        this.patientIds = patientIds;
    }
}
