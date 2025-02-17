package com.aier.cloud.api.amcs.adverse.domain;


import lombok.Data;


/**
 * com.aier.cloud.avis.api.response.opt
 * 屈光检查-不良事件对象
 * @author sunxuan
 * @since 2019/5/27
 */
@Data
public class RefractiveBlVO {

    /** 视力-裸眼视力-远od*/
    private String scdOd;
    private String scdOdValue;

    /** 视力-裸眼视力-远os*/
    private String scdOs;
    private String scdOsValue;

    /** 眼生物学参数-眼压od*/
    private String iopOd;

    /** 眼生物学参数-眼压os*/
    private String iopOs;

    /** 矫正视力描述(OD)（码表：eyesight_desc）*/
    private String scdDescOd;

    private String scdDescOdValue;

    /** 矫正视力描述(OS)（码表：eyesight_desc）*/
    private String scdDescOs;
    private String scdDescOsValue;

    /** 右眼球镜*/
    private String sphOd;

    /** 左眼球镜*/
    private String sphOs;

    /** 右眼柱镜 */
    private String cylOd;

    /** 左眼柱镜*/
    private String cylOs;

    /** 右眼轴向*/
    private String axisOd;

    /** 左眼轴向*/
    private String axisOs;

    /** 矫正视力*/
    private String dvaOd;
    private String dvaOdValue;

    /** 矫正视力*/
    private String dvaOs;
    private String dvaOsValue;

    /** 客观验光-电脑验光平K1od*/
    private String fkValueOd;

    /** 客观验光-电脑验光平K1os*/
    private String fkValueOs;
    /** 客观验光-电脑验光平K1od轴位*/
    private String fkAxOd;

    /** 客观验光-电脑验光平K1os轴位*/
    private String fkAxOs;

    public String getScdOdValue() {
        return scdOdValue;
    }

    public void setScdOdValue(String scdOdValue) {
        this.scdOdValue = scdOdValue;
    }

    public String getScdOsValue() {
        return scdOsValue;
    }

    public void setScdOsValue(String scdOsValue) {
        this.scdOsValue = scdOsValue;
    }

    public String getFkAxOs() {
        return fkAxOs;
    }

    public void setFkAxOs(String fkAxOs) {
        this.fkAxOs = fkAxOs;
    }

    public String getScdDescOdValue() {
        return scdDescOdValue;
    }

    public void setScdDescOdValue(String scdDescOdValue) {
        this.scdDescOdValue = scdDescOdValue;
    }

    public String getScdDescOsValue() {
        return scdDescOsValue;
    }

    public void setScdDescOsValue(String scdDescOsValue) {
        this.scdDescOsValue = scdDescOsValue;
    }

    public String getDvaOdValue() {
        return dvaOdValue;
    }

    public void setDvaOdValue(String dvaOdValue) {
        this.dvaOdValue = dvaOdValue;
    }

    public String getDvaOsValue() {
        return dvaOsValue;
    }

    public void setDvaOsValue(String dvaOsValue) {
        this.dvaOsValue = dvaOsValue;
    }

    public String getScdOd() {
        return scdOd;
    }

    public void setScdOd(String scdOd) {
        this.scdOd = scdOd;
    }

    public String getScdOs() {
        return scdOs;
    }

    public void setScdOs(String scdOs) {
        this.scdOs = scdOs;
    }

    public String getIopOd() {
        return iopOd;
    }

    public void setIopOd(String iopOd) {
        this.iopOd = iopOd;
    }

    public String getIopOs() {
        return iopOs;
    }

    public void setIopOs(String iopOs) {
        this.iopOs = iopOs;
    }

    public String getScdDescOd() {
        return scdDescOd;
    }

    public void setScdDescOd(String scdDescOd) {
        this.scdDescOd = scdDescOd;
    }

    public String getScdDescOs() {
        return scdDescOs;
    }

    public void setScdDescOs(String scdDescOs) {
        this.scdDescOs = scdDescOs;
    }

    public String getSphOd() {
        return sphOd;
    }

    public void setSphOd(String sphOd) {
        this.sphOd = sphOd;
    }

    public String getSphOs() {
        return sphOs;
    }

    public void setSphOs(String sphOs) {
        this.sphOs = sphOs;
    }

    public String getCylOd() {
        return cylOd;
    }

    public void setCylOd(String cylOd) {
        this.cylOd = cylOd;
    }

    public String getCylOs() {
        return cylOs;
    }

    public void setCylOs(String cylOs) {
        this.cylOs = cylOs;
    }

    public String getAxisOd() {
        return axisOd;
    }

    public void setAxisOd(String axisOd) {
        this.axisOd = axisOd;
    }

    public String getAxisOs() {
        return axisOs;
    }

    public void setAxisOs(String axisOs) {
        this.axisOs = axisOs;
    }

    public String getDvaOd() {
        return dvaOd;
    }

    public void setDvaOd(String dvaOd) {
        this.dvaOd = dvaOd;
    }

    public String getDvaOs() {
        return dvaOs;
    }

    public void setDvaOs(String dvaOs) {
        this.dvaOs = dvaOs;
    }

    public String getFkValueOd() {
        return fkValueOd;
    }

    public void setFkValueOd(String fkValueOd) {
        this.fkValueOd = fkValueOd;
    }

    public String getFkValueOs() {
        return fkValueOs;
    }

    public void setFkValueOs(String fkValueOs) {
        this.fkValueOs = fkValueOs;
    }
}
