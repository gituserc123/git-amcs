package com.aier.cloud.biz.service.biz.amcs.fin.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 医院医保检查省区权限配置表
 * </p>
 *
 * @author Aier
 * @since 2025-01-14 10:31:38
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("T_FIN_HOSP_ISRNE_CHK_AUTH")
@ApiModel(value = "FinHospIsrneChkAuth对象", description = "医院医保检查省区权限配置表")
public class FinHospIsrneChkAuth extends BaseEntity<FinHospIsrneChkAuth> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("省区ID")
    @TableField("PROVINCE_ID")
    private Long provinceId;

    @ApiModelProperty("省区名称")
    @TableField("PROVINCE_NAME")
    private String provinceName;

    @ApiModelProperty("权限编码")
    @TableField("AUTH_CODE")
    private String authCode;

    @ApiModelProperty("工号")
    @TableField("STAFF_CODE")
    private String staffCode;

    @ApiModelProperty("姓名")
    @TableField("STAFF_NAME")
    private String staffName;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_DATE")
    private Date createDate;

    @ApiModelProperty("创建人")
    @TableField("CREATOR")
    private Long creator;

    @TableField("STAFF_ID")
    private Long staffId;


    @TableField(exist = false)
    private String provinceNames;


    @TableField(exist = false)
    private String provinceIds;


}
