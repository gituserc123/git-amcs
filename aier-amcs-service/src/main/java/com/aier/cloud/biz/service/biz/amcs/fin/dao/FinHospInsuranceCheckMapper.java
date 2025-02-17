package com.aier.cloud.biz.service.biz.amcs.fin.dao;

import com.aier.cloud.api.amcs.fin.condition.FinHospInsuranceCheckCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospInsuranceCheck;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医院医保检查统计表 Mapper 接口
 * </p>
 *
 * @author Aier
 * @since 2025-01-09 09:24:33
 */
@Mapper
public interface FinHospInsuranceCheckMapper extends BaseMapper<FinHospInsuranceCheck> {

    List<FinHospInsuranceCheck> getAll(Page<Map<String, Object>> page, @Param("cond") FinHospInsuranceCheckCondition cond);
    List<FinHospInsuranceCheck> getAllEntity(@Param("cond") FinHospInsuranceCheckCondition cond);
}
