package com.aier.cloud.biz.service.biz.amcs.fin.dao;

import com.aier.cloud.api.amcs.fin.condition.FinHospIsrneChkAuthCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospIsrneChkAuth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 医院医保检查省区权限配置表 Mapper 接口
 * </p>
 *
 * @author Aier
 * @since 2025-01-14 10:31:38
 */
@Mapper
public interface FinHospIsrneChkAuthMapper extends BaseMapper<FinHospIsrneChkAuth> {

    List<FinHospIsrneChkAuth> getAllEntity(@Param("cond") FinHospIsrneChkAuthCondition cond);
}
