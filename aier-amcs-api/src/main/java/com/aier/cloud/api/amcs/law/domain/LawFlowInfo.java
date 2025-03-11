package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class LawFlowInfo extends BaseEntity {
    private String flowName; // 流程名称
    private String flowDesc; // 流程描述
    private Long creator; // 创建者ID
    private Date createDate; // 创建时间

}