package com.aier.cloud.api.amcs.fin.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum FinMaMedicalItemEnum  implements IEnum, EnumDict<FinMaMedicalItemEnum> {
    DIP_EMP_ARC_001("DIP_EMP_ARC_001", "职工_老年性白内障(不含飞白)", 1, "DIP",1),
    DIP_EMP_ARC_002("DIP_EMP_ARC_002", "职工_老年性白内障(含飞白)", 2, "DIP",1),
    DIP_EMP_PRGM_001("DIP_EMP_PRGM_001", "职工_翼状胬肉(不含羊膜移植)", 3, "DIP",1),
    DIP_EMP_PRGM_002("DIP_EMP_PRGM_002", "职工_翼状胬肉(含羊膜移植)", 4, "DIP",1),
    DIP_EMP_FD_001("DIP_EMP_FD_001", "职工_眼底病(眼底注药)", 5, "DIP",1),
    DIP_EMP_FD_002("DIP_EMP_FD_002", "职工_眼底病(眼底玻切)", 6, "DIP",1),
    DIP_RDT_ARC_001("DIP_RDT_ARC_001", "居民_老年性白内障(不含飞白)", 1, "DIP",2),
    DIP_RDT_ARC_002("DIP_RDT_ARC_002", "居民_老年性白内障(含飞白)", 2, "DIP",2),
    DIP_RDT_PRGM_001("DIP_RDT_PRGM_001", "居民_翼状胬肉(不含羊膜移植)", 3, "DIP",2),
    DIP_RDT_PRGM_002("DIP_RDT_PRGM_002", "居民_翼状胬肉(含羊膜移植)", 4, "DIP",2),
    DIP_RDT_FD_001("DIP_RDT_FD_001", "居民_眼底病(眼底注药)", 5, "DIP",2),
    DIP_RDT_FD_002("DIP_RDT_FD_002", "居民_眼底病(眼底玻切)", 6, "DIP",2),

    DRG_EMP_ARC_001("DRG_EMP_ARC_001", "职工_老年性白内障", 1, "DRG",1),
    DRG_EMP_PRGM_001("DRG_EMP_PRGM_001", "职工_翼状胬肉", 2, "DRG",1),
    DRG_EMP_II_001("DRG_EMP_II_001", "职工_眼底注药", 3, "DRG",1),
    DRG_EMP_VTTM_001("DRG_EMP_VTTM_001", "职工_眼底玻切", 4, "DRG",1),
    DRG_RDT_ARC_001("DRG_RDT_ARC_001", "居民_老年性白内障", 1, "DRG",2),
    DRG_RDT_PRGM_001("DRG_RDT_PRGM_001", "居民_翼状胬肉", 2, "DRG",2),
    DRG_RDT_II_001("DRG_RDT_II_001", "居民_眼底注药", 3, "DRG",2),
    DRG_RDT_VTTM_001("DRG_RDT_VTTM_001", "居民_眼底玻切", 4, "DRG",2),
    ;

    FinMaMedicalItemEnum(String value, String desc,Integer sn, String type,Integer insuranceType) {
        this.value = value;
        this.desc = desc;
        this.sn = sn;
        this.type = type;
        this.insuranceType = insuranceType;
    }

    // 值
    private String value ;

    // 描述，释义
    private String desc;

    // 排序号
    private Integer sn;

    // 类型
    private String type;

    /** 医保类型(1-职工/2-居民)*/
    private Integer insuranceType;

    public void setValue(String value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Integer getSn() {
        return sn;
    }

    public void setSn(Integer sn) {
        this.sn = sn;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(Integer insuranceType) {
        this.insuranceType = insuranceType;
    }

    @Override
    public String getEnumCode() {
        return null;
    }

    @Override
    public String getEnumDesc() {
        return null;
    }

    @Override
    public Serializable getValue() {
        return value;
    }
}
