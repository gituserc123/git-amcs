package com.aier.cloud.biz.service.biz.amcs.fin.service;


import com.aier.cloud.api.amcs.fin.condition.FinHospIsrneChkAuthCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospIsrneChkAuth;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


/**
 * <p>
 * 医院医保检查省区权限配置表 服务类
 * </p>
 *
 * @author Aier
 * @since 2025-01-14 10:31:38
 */
public interface FinHospIsrneChkAuthService extends IService<FinHospIsrneChkAuth> {
    List<FinHospIsrneChkAuth> getAllEntity(FinHospIsrneChkAuthCondition cond);
}
