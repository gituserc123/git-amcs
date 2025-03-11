package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawCivilCaseCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCivilCase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 民事诉讼仲裁案件主表 Mapper 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawCivilCaseMapper extends BaseMapper<LawCivilCase> {

    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, @Param("cond") LawCivilCaseCondition cond);

}
