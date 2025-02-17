package com.aier.cloud.biz.service.biz.amcs.fin.service;

import com.aier.cloud.api.amcs.fin.condition.FinHospInsuranceCheckCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospInsuranceCheck;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医院医保检查统计表 服务类
 * </p>
 *
 * @author Aier
 * @since 2025-01-09 09:24:33
 */
public interface FinHospInsuranceCheckService extends IService<FinHospInsuranceCheck> {


    List<FinHospInsuranceCheck> getAll(Page<Map<String, Object>> page, FinHospInsuranceCheckCondition cond);
    List<FinHospInsuranceCheck> getAllEntity(FinHospInsuranceCheckCondition cond);

    Map<String, Object> save(FinHospInsuranceCheck stdAdaptation);

}
