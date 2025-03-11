package com.aier.cloud.api.amcs.law.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

@Data
public class LawNodeAuth extends BaseEntity {
    private Long nodeId; // 节点Id
    private String nodeName; // 节点名称
    private String nodeCode; // 节点编码
    private Long staffId; // 用户Id
    private String staffName; // 用户姓名

    private String staffCode; // 用户工号
    private Long staffInstId; // 用户机构Id
    private String staffInstName; // 用户机构名称
    private Long creator; // 创建者ID
    private Date createDate; // 创建时间
}