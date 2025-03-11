package com.aier.cloud.api.amcs.law.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class LawAuditOpinionCondition extends PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 案件唯一标识，主键 */
    private Long id;
    private Long detailId; // 审核明细表Id
    private String opinion; // 审核意见
    private Long creator; // 创建者ID
    private Date createDate; // 创建时间

    private String creatorName; // 创建者名称

    private String nodeName;

    private List<Long> detailIds;
}