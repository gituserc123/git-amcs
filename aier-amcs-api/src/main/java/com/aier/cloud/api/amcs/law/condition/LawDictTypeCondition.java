package com.aier.cloud.api.amcs.law.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * T_LAW_DICT_TYPE
 *
 * @since 2023-10-18
 */
@Data
public class LawDictTypeCondition extends PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 案件唯一标识，主键 */
    private Long id;

    /** 字典类型编码 */
    private String typeCode;

    /** 字典类型描述 */
    private String typeDesc;

    /** 参数值编码 */
    private String valueCode;

    /** 参数值描述 */
    private String valueDesc;

    /** 首拼码 */
    private String firstSpell;

    /** 参数值显示序号 */
    private Integer orders;

    /** 启停标识（1启用0停用） */
    private Integer usingSign;


    /** 父级字典ID */
    private Long parentId;

    /** 修改者ID */
    private Long modifierId;

    /** 修改时间 */
    private Date modifyDate;
}