package com.aier.cloud.api.amcs.law.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * T_LAW_DICT_TYPE
 *
 * @since 2023-10-18
 */
@Data
public class LawBaseCondition extends PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 机构ID，关联医院或集团 */
    private Long instId;

    /** 机构名称，包含医院或集团名称 */
    private String instName;

    /** 省区/上级机构ID */
    private Long superInstId;

    /** 省区/上级机构名称 */
    private String superInstName;

    // 医院列表
    private List<Long> instList;

    // 省区Id
    private Long province;


}