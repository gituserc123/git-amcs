package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawIntegrityFilingCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawIntegrityFiling;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 廉洁合规报备表 Mapper 接口
 *
 * @author 自动生成
 * @since 2023-10-18
 */
public interface LawIntegrityFilingMapper extends BaseMapper<LawIntegrityFiling> {

    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, @Param("cond") LawIntegrityFilingCondition cond);

}