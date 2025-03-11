package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class LawAuditOpinion extends BaseEntity {
    private Long detailId; // 审核明细表Id
    private String opinion; // 审核意见
    private Long creator; // 创建者ID
    private Date createDate; // 创建时间

    private String creatorName; // 创建者名称

    private String nodeName;
}