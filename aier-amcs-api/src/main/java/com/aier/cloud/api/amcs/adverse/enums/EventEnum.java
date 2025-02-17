package com.aier.cloud.api.amcs.adverse.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

public enum EventEnum  implements IEnum,EnumDict<EventEnum> {
	OUTP("101", "门诊患者不良事件", 15, "AeOutp", 1,1<<1,"ae_outp_report"),
	INPATIENT("102","住院患者不良事件",15, "AeInp", 1,1,"ae_inp_report"),
	CORNEAL("103","角膜接触镜不良事件",15, "AeCornealContact", 1,1<<3,"ae_cornealContact_report"),
	FRAMEGLASSES("104","框架眼镜不良反应",15, "AeFrameGlasses", 1,1<<3,"ae_frameGlasses_report"),
	VISUALTRAIN("105","视觉训练不良反应",15, "AeVisualTrain", 1,1<<3,"ae_visualTrain_report"),
	OTHERMEDICAL("106","其他医疗类不良事件(不涉及患者本人)",15, "AeOtherMedical", 1,1<<4,"ae_otherMedical_report"),
	OTHEROPTOMETRY("107","其他视光患者不良事件",15, "AeOtherOptometry", 1,1<<3,"ae_otherOptometry_report"),
	OUTPPATIENT("108","门诊患者不良事件(手术患者)",15, "AeOutpPatient", 1,1<<2,"ae_outpPatient_report"),
    UNPLREOPERATION("109","非计划再手术上报",15, "AeUnplReoperation", 1,1,"ae_unplReoperation_report"),

	TUMBLE("201","跌倒/坠床事件",15, "AeTumbleFallbed", 2,1<<5,"ae_tumbleFallbed_report"),
    DRUGMISTAKE("202","给药错误事件",15, "AeDrugMistake", 2,1<<6,"ae_drugMistake_report"),
	SAMPLE("203","标本采集不良事件",15, "AeSampleGather", 2,1<<7,"ae_sampleGather_report"),
	OTHERCARE("204","其他护理不良事件",15, "AeOtherCare", 2,1<<8,"ae_otherCare_report"),

	INFECTION("301","医院感染事件",15, "AeInfection", 3,1<<9,"ae_infection_report"),
    OCCUPATIONEXPOSURE("303","职业暴露事件",15, "AeOccupationExposure", 3,1<<10,"ae_occupationExposure_report"),
    
    OTHER("901", "信息、行政、公共安全事件", 15, "AeOther", 9, 1<<11, "ae_other_report"),
    PENALTY("902", "行政处罚事件", 15, "AeOtherPenalty", 9, 1<<12, "ae_other_penalty"),
    ;

    private String value ;
    private String desc;
    private Integer node;
    private String name;
    private Integer type; // 1 医疗 2 护理 3 院感 4 其他
    private Integer filter;//一二级分类过滤字段
    private String reportType; // 帆软报表类型


    EventEnum(String value, String desc,Integer node, String name, Integer type,Integer filter,String reportType) {
        this.value = value;
        this.desc = desc;
        this.node = node;
        this.name = name;
        this.type = type;
        this.filter=filter;
        this.reportType=reportType;
    }

    public Integer getFilter() {
        return filter;
    }

    public void setFilter(Integer filter) {
        this.filter = filter;
    }

    public Integer getNode() {
		return node;
	}

    public String getReportType() {
        return reportType;
    }

    @Override
    public String getEnumCode() {
        return String.valueOf(value);
    }

    @Override
    public String getEnumDesc() {
        return desc;
    }

    @Override
    public String getFirstSpell() {
        return EnumDict.super.getFirstSpell();
    }

    @Override
    public boolean is(Object o) {
        return EnumDict.super.is(o);
    }

    @Override
    public String getValue() {
        return value;
    }

	public String getName() {
		return name;
	}


	public Integer getType() {
		return type;
	}


	public static EventEnum findEnumByCode(String code) {
		 
        for (EventEnum statusEnum : EventEnum.values()) {
        	System.out.print(statusEnum.getEnumCode());;
            if (statusEnum.getEnumCode().equals(code)) {
                return statusEnum;
            }
        }
        return null;
    }
    public static EventEnum nameOf(String name) {

        for (EventEnum statusEnum : EventEnum.values()) {
            System.out.print(statusEnum.getEnumCode());;
            if (statusEnum.getEnumCode().equals(name)) {
                return statusEnum;
            }
        }
        return null;
    }

}
