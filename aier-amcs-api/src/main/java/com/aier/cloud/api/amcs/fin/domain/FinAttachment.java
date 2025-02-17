package com.aier.cloud.api.amcs.fin.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

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
public class FinAttachment extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long modifer;

    private Date modifyDate;

    private Date createDate;

    private Long creator;

    private Long bizId;

    private String bizCode;

    private String fileId;

    private String fileName;

    private String fileType;

    private Integer fileSize;

    private String filePath;

    private String webUrl;

    private Long attachId;


}
