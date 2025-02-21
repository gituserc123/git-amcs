package com.aier.cloud.biz.service.biz.amcs.law.entity;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 法务系统附件表
 * </p>
 *
 * @author YourName
 * @since 2025-02-20
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("T_LAW_ATTACHMENT")
@ApiModel(value = "LawAttachment对象", description = "法务系统附件表")
public class LawAttachment extends BaseEntity<LawAttachment> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("业务表Id")
    @TableField("BIZ_ID")
    private Long bizId;

    @ApiModelProperty("业务表类型")
    @TableField("BIZ_TYPE")
    private String bizType;

    @ApiModelProperty("业务表编码")
    @TableField("BIZ_CODE")
    private String bizCode;

    @ApiModelProperty("附件类型")
    @TableField("ATTACH_TYPE")
    private String attachType;

    @ApiModelProperty("附件编码")
    @TableField("ATTACH_CODE")
    private String attachCode;

    @ApiModelProperty("附件序号")
    @TableField("ATTACH_SN")
    private Integer attachSn;

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

    @ApiModelProperty("创建者ID")
    @TableField("CREATOR")
    private Long creator;

    @ApiModelProperty("创建时间")
    @TableField("CREATE_DATE")
    private Date createDate;

    // 如果有其他业务逻辑或额外字段，可以在这里添加

}