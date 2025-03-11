package com.aier.cloud.api.amcs.law.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class LawNodeAuthCondition  extends PageCondition implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 案件唯一标识，主键 */
    private Long id;
    private Long nodeId; // 节点Id
    private String nodeName; // 节点名称
    private String nodeCode; // 节点编码
    private Long staffId; // 用户Id
    private String staffName; // 用户姓名
    private Long staffInstId; // 用户机构Id
    private String staffInstName; // 用户机构名称
    private Long creator; // 创建者ID
    private Date createDate; // 创建时间
}