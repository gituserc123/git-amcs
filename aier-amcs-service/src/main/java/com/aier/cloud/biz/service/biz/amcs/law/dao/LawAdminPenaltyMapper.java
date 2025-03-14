package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawAdminPenaltyCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAdminPenalty;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 行政处罚事项表 Mapper 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawAdminPenaltyMapper extends BaseMapper<LawAdminPenalty> {

    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, @Param("cond") LawAdminPenaltyCondition cond);

}