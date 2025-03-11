package com.aier.cloud.api.amcs.law.enums;

import com.aier.cloud.basic.common.convert.EnumDict;
import com.baomidou.mybatisplus.enums.IEnum;

import java.io.Serializable;

public enum CivilCaseAttachEnum implements IEnum, EnumDict<CivilCaseAttachEnum> {

    BUSINESS_LICENSE("business-license", "案涉对方主体企业法人营业执照/自然人身份证", 1),
    COMPLAINT_FILE("complaint-file", "起诉状", 2),
    SUMMONS_FILE("summons-file", "开庭传票或者预立案、诉前调解相关文书", 3),
    EVIDENCE_NOTICE_FILE("evidence-notice-file", "举证通知书", 4),
    EVIDENCE_MATERIALS("evidence-materials", "证据材料", 5),
    JUDGMENT_FILE("judgment-file", "判决书、裁定书、裁决书", 6);

    private final String code;
    private final String desc;
    private final int value;

    CivilCaseAttachEnum(String code, String desc, int value) {
        this.code = code;
        this.desc = desc;
        this.value = value;
    }

    @Override
    public String getEnumCode() {
        return code;
    }

    @Override
    public String getEnumDesc() {
        return desc;
    }

    @Override
    public Serializable getValue() {
        return value;
    }

    // 可根据需要添加字段的getter方法
    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public int getValueInt() {
        return value;
    }
}