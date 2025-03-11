package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class LawFlowInstance extends BaseEntity {
    private Long flowId; // 流程信息ID
    private Long bizId; // 业务表Id
    private String bizCode; // 业务表Code;值:各个业务表表英文名,比如:civil-case
    private Integer bizType; // 业务表类型Type;值:1,2,3,4,5,6....
    private String bizName; // 业务表名称;各个业务表表中文名,比如:民事诉讼仲裁案件主表
    private String prevNode; // 上一处理节点
    private String currentNode; // 当前处理节点
    private Long creator; // 创建者ID
    private Date createDate; // 创建时间
}