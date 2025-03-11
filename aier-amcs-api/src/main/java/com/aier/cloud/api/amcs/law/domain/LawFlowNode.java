package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_FLOW_NODE
 *
 * @since 2025-02-25
 */
@Data
public class LawFlowNode extends BaseEntity {

    /** 流程信息ID */
    private Long flowId;

    /** 节点ID */
    private Long nodeId;

    /** 节点编码 */
    private String nodeCode;

    /** 节点名称 */
    private String nodeName;

    /** 节点序号(值:1,2,3,4,5,6....) */
    private Integer nodeOrder;

    /** 流程顺序号(值:1,5,10...) */
    private Integer stepOrder;

    /** 节点类型(如：审批-Audit, 抄送-CC,...) */
    private String nodeType;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;
}