package com.aier.cloud.biz.service.biz.amcs.fin.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 医保附件表
 * </p>
 *
 * @author Aier
 * @since 2025-01-09 05:25:21
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("T_FIN_ATTACHMENT")
@ApiModel(value = "FinAttachment对象", description = "医保附件表")
public class FinAttachment extends BaseEntity<FinAttachment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_DATE")
    private Date createDate;

    @ApiModelProperty("创建人")
    @TableField("CREATOR")
    private Long creator;

    @ApiModelProperty("业务表Id")
    @TableField("BIZ_ID")
    private Long bizId;

    @ApiModelProperty("业务表编码")
    @TableField("BIZ_CODE")
    private String bizCode;

    @ApiModelProperty("附件文件ID")
    @TableField("FILE_ID")
    private String fileId;

    @ApiModelProperty("文件名称")
    @TableField("FILE_NAME")
    private String fileName;

    @ApiModelProperty("文件类型")
    @TableField("FILE_TYPE")
    private String fileType;

    @ApiModelProperty("文件大小")
    @TableField("FILE_SIZE")
    private Integer fileSize;

    @ApiModelProperty("相对路径")
    @TableField("FILE_PATH")
    private String filePath;

    @ApiModelProperty("web路径")
    @TableField("WEB_URL")
    private String webUrl;

    @TableField(exist = false)
    private Long attachId;


}
