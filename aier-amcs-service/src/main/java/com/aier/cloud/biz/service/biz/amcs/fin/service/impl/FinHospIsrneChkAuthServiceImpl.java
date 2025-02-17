package com.aier.cloud.biz.service.biz.amcs.fin.service.impl;

import com.aier.cloud.api.amcs.fin.condition.FinHospIsrneChkAuthCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinHospIsrneChkAuthMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospIsrneChkAuth;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinHospIsrneChkAuthService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 医院医保检查省区权限配置表 服务实现类
 * </p>
 *
 * @author Aier
 * @since 2025-01-14 10:31:38
 */
@Service
public class FinHospIsrneChkAuthServiceImpl extends ServiceImpl<FinHospIsrneChkAuthMapper, FinHospIsrneChkAuth> implements FinHospIsrneChkAuthService {

    @Override
    public List<FinHospIsrneChkAuth> getAllEntity(FinHospIsrneChkAuthCondition cond) {
        return this.baseMapper.getAllEntity(cond);
    }
}
