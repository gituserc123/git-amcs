package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class LawAuditDetail extends BaseEntity {
    private Long instanceId; // 流程实例表Id
    private Long nodeId; // 当前处理节点Id

    private String nodeName; // 当前处理节点Id
    private Long staffId; // 当前操作用户
    private String action; // 操作类型(值: '提交-SUBMIT', '退回-REJECT', ...)
    private Long nextNodeId; // 下一处理节点Id
    private Long creator; // 创建者ID
    private Date createDate; // 创建时间
}