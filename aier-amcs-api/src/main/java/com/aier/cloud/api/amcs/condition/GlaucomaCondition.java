package com.aier.cloud.api.amcs.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import com.baomidou.mybatisplus.annotations.TableField;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class GlaucomaCondition extends PageCondition implements Serializable {
    private static final long serialVersionUID = 4244299905392258927L;
    private Long id;
    private Date modifyDate;
    private Long modifer;

    /** 就诊日期*/
    private java.util.Date outpatientDate;

    /** 患者id*/
    private Long patientId;

    /** 患者姓名*/
    private String patientName;

    /** 联系电话*/private String mobilePhone;

    /** od眼压*/
    private java.math.BigDecimal odPressure;

    /** os眼压*/
    private java.math.BigDecimal osPressure;

    /** 首诊科室（转出）ID*/
    private Long fstOutDeptId;

    /** 首诊科室（转出）*/
    private String fstOutDeptName;

    /** 首诊医生（转出）ID*/
    private Long fstOutDoctorId;

    /** 首诊医生（转出）*/
    private String fstOutDoctorName;

    /** 接诊医生诊断结果（转入）*/
    private String inMedicalResult;

    /** 接诊医生处置情况（转入）*/
    private String inTreatInfo;

    /** 未治疗原因*/
    private String untreatReason;

    /** 医院Id*/
    private Long hospId;

    /** 首诊医生处置情况（转出）*/
    private String outTreatInfo;

    /** 接诊医生（转入）ID*/
    private Long inDoctorId;

    /** 接诊医生（转入）*/
    private String inDoctorName;

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

    public Date getOutpatientDate() {
        return outpatientDate;
    }

    public void setOutpatientDate(Date outpatientDate) {
        this.outpatientDate = outpatientDate;
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

    public BigDecimal getOdPressure() {
        return odPressure;
    }

    public void setOdPressure(BigDecimal odPressure) {
        this.odPressure = odPressure;
    }

    public BigDecimal getOsPressure() {
        return osPressure;
    }

    public void setOsPressure(BigDecimal osPressure) {
        this.osPressure = osPressure;
    }

    public Long getFstOutDeptId() {
        return fstOutDeptId;
    }

    public void setFstOutDeptId(Long fstOutDeptId) {
        this.fstOutDeptId = fstOutDeptId;
    }

    public String getFstOutDeptName() {
        return fstOutDeptName;
    }

    public void setFstOutDeptName(String fstOutDeptName) {
        this.fstOutDeptName = fstOutDeptName;
    }

    public Long getFstOutDoctorId() {
        return fstOutDoctorId;
    }

    public void setFstOutDoctorId(Long fstOutDoctorId) {
        this.fstOutDoctorId = fstOutDoctorId;
    }

    public String getFstOutDoctorName() {
        return fstOutDoctorName;
    }

    public void setFstOutDoctorName(String fstOutDoctorName) {
        this.fstOutDoctorName = fstOutDoctorName;
    }

    public String getInMedicalResult() {
        return inMedicalResult;
    }

    public void setInMedicalResult(String inMedicalResult) {
        this.inMedicalResult = inMedicalResult;
    }

    public String getInTreatInfo() {
        return inTreatInfo;
    }

    public void setInTreatInfo(String inTreatInfo) {
        this.inTreatInfo = inTreatInfo;
    }

    public String getUntreatReason() {
        return untreatReason;
    }

    public void setUntreatReason(String untreatReason) {
        this.untreatReason = untreatReason;
    }

    public Long getHospId() {
        return hospId;
    }

    public void setHospId(Long hospId) {
        this.hospId = hospId;
    }

    public String getOutTreatInfo() {
        return outTreatInfo;
    }

    public void setOutTreatInfo(String outTreatInfo) {
        this.outTreatInfo = outTreatInfo;
    }

    public Long getInDoctorId() {
        return inDoctorId;
    }

    public void setInDoctorId(Long inDoctorId) {
        this.inDoctorId = inDoctorId;
    }

    public String getInDoctorName() {
        return inDoctorName;
    }

    public void setInDoctorName(String inDoctorName) {
        this.inDoctorName = inDoctorName;
    }

    public List<Long> getPatientIds() {
        return patientIds;
    }

    public void setPatientIds(List<Long> patientIds) {
        this.patientIds = patientIds;
    }
}
