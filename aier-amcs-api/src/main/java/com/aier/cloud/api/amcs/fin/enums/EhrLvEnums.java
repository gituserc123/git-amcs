package com.aier.cloud.api.amcs.fin.enums;

import java.util.Arrays;
import java.util.Optional;

/**
 * @program: amcs
 * @description: 人资机构等级枚举类
 * @author: hsw
 * @create: 2023-03-14 15:51
 **/
public enum EhrLvEnums {


    GROUP(10, "集团总部"),
    PROVINCE(11, "省区"),
    PROVINCIAL_CAPITAL(12, "省会级医院"),
    PREFECTURE(13, "地市级医院"),
    COUNTIES(14, "县市级医院"),
    OUTPATIENT_SERVICE(15, "眼科门诊"),
    BIG_AREA(20, "大区"),
    AREA(30, "区域"),

    ;
    private Integer value;
    private String content;

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    EhrLvEnums(Integer value, String content) {
        this.value = value;
        this.content = content;
    }

    public static String findEhrLvEnumsByCode(String code) {
        Optional<EhrLvEnums> el = Arrays.stream(EhrLvEnums.values()).filter(e -> e.getValue().intValue() == Integer.parseInt(code)).findFirst();
        if(el.isPresent()){
            return el.get().getContent();
        }else{
            return "";
        }
    }

}
