package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
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
public class LawAttachment extends BaseEntity {
    private static final long serialVersionUID = 1L;


    /**
     * 业务表Id
     */
    private Long bizId;

    /**
     * 业务表类型
     */
    private String bizType;

    /**
     * 业务表编码
     */
    private String bizCode;

    /**
     * 附件类型
     */
    private String attachType;

    /**
     * 附件编码
     */
    private String attachCode;

    /**
     * 附件序号
     */
    private Integer attachSn;

    /**
     * 附件文件ID
     */
    private String fileId;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 文件大小
     */
    private Integer fileSize;

    /**
     * 相对路径
     */
    private String filePath;

    /**
     * web路径
     */
    private String webUrl;

    /**
     * 创建者ID
     */
    private Long creator;

    /**
     * 创建时间
     */
    private Date createDate;

    // 如果有其他业务逻辑或额外方法，可以在这里添加
}