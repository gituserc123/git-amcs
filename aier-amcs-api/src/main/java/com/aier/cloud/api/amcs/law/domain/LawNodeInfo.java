package com.aier.cloud.api.amcs.law.domain;


import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * T_LAW_NODE_INFO
 *
 * @since 2023-10-18
 */
@Data
public class LawNodeInfo extends BaseEntity {

    /** 节点编码 */
    private String nodeCode;

    /** 节点名称 */
    private String nodeName;

    /** 节点序号(值:1,2,3,4,5,6....) */
    private Integer nodeOrder;

    /** 节点描述 */
    private String nodeDesc;

    /** 创建者ID */
    private Long creator;

    /** 创建时间 */
    private Date createDate;
}