package com.aier.cloud.api.amcs.adverse.domain;

import lombok.Data;

import java.util.Date;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2022-12-06 14:07
 **/
@Data
public class ApiOpDomain {


    /*门诊时间*/
    private Date regDate;

    /*患者姓名*/
    private String patientName;

    /*患者ID*/
    private Long patientId;

    /*门诊诊断*/
    private String diagName;

    /*综合验光*/
    private RefractiveBlVO refractiveBlVO;
    /*配镜相关*/
    private SalesBasicBlVO salesBasicBlVO;

    /*门诊眼压*/
    private String iopOs;

    /*门诊眼压*/
    private String iopOd;

    /** 远视力-右眼(码值：far_eyesight) */
    private String dvaOd;

    /** 远视力-左眼(码值：far_eyesight) */
    private String dvaOs;

    /** 近视力-右眼(码值：far_eyesight) */
    private String nvaOd;

    /** 近视力-左眼(码值：far_eyesight) */
    private String nvaOs;

    /**
     * 门诊病历地址
     */
    private String emrUrl;
}
