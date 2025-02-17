package com.aier.cloud.api.amcs.adverse.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

public class AeFocusDomain extends BaseEntity {

    /** 不良事件基础表ID */
    private Long basicId;


    public Long getBasicId() {
        return basicId;
    }

    public void setBasicId(Long basicId) {
        this.basicId = basicId;
    }
}
