package com.aier.cloud.api.amcs.fin.condition;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <p>
 * 医院医保检查省区权限配置表
 * </p>
 *
 * @author Aier
 * @since 2025-01-14 10:31:38
 */
@Getter
@Setter
public class FinHospIsrneChkAuthCondition extends BaseEntity {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long provinceId;

    private String provinceName;

    private String authCode;

    private String staffCode;

    private String staffName;

    private Date createDate;

    private Long creator;

    private Long staffId;


}
