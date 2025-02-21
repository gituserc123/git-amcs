package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_DICT_TYPE
 *
 * @since 2023-10-18
 */
@Data
public class LawDictType extends BaseEntity {

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
}