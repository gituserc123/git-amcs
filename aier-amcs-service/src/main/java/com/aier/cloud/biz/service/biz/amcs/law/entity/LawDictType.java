package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
import lombok.Data;


/**
 * T_LAW_DICT_TYPE
 *
 * @since 2023-10-18
 */
@TableName("T_LAW_DICT_TYPE")
@Data
public class LawDictType extends BaseEntity<LawDictType> {

    private static final long serialVersionUID = -4413922036810547519L;

    /** 字典类型编码 */
    @Comment(value = "字典类型编码")
    @TableField(value = "type_code")
    private String typeCode;

    /** 字典类型描述 */
    @Comment(value = "字典类型描述")
    @TableField(value = "type_desc")
    private String typeDesc;

    /** 参数值编码 */
    @Comment(value = "参数值编码")
    @TableField(value = "value_code")
    private String valueCode;

    /** 参数值描述 */
    @Comment(value = "参数值描述")
    @TableField(value = "value_desc")
    private String valueDesc;

    /** 首拼码 */
    @Comment(value = "首拼码")
    @TableField(value = "first_spell")
    private String firstSpell;

    /** 参数值显示序号 */
    @Comment(value = "参数值显示序号")
    @TableField(value = "orders")
    private Integer orders;

    /** 启停标识（1启用0停用） */
    @Comment(value = "启停标识（1启用0停用）")
    @TableField(value = "using_sign")
    private Integer usingSign;

    /** 父级字典ID */
    @Comment(value = "父级字典ID")
    @TableField(value = "parent_id")
    private Long parentId;
}