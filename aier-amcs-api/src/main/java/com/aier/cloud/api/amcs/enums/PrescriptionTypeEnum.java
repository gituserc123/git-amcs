package com.aier.cloud.api.amcs.enums;

import com.aier.cloud.basic.common.convert.EnumDict;

/**
 * com.aier.cloud.avis.api.enums
 * 角膜接触镜复查-完整度
 * @author sunxuan
 * @since 2019/5/24
 */
public enum PrescriptionTypeEnum implements EnumDict<PrescriptionTypeEnum>{

    DVA("1","远用"),
    NVA("2","近用"),
    DOUBLE_PROGRESSIVE("3","双光/渐进"),
    CONTACT_LENS("4","软性角膜接触镜"),
    MEDIUM_USE("5","中用"),
    VST("6","角膜塑形镜VST"),
    CRT("7","角膜塑形镜CRT"),
    VISUAL_TRAINING("8","视觉训练"),
    RGP("9","角膜塑形镜RGP"),
    MEDICINE("10","药品");


    PrescriptionTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    String code;
    String desc;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    @Override
    public String getEnumCode() {
        return this.code;
    }

    @Override
    public String getEnumDesc() {
        return this.desc;
    }
}
