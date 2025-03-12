package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawCriminalCaseCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCriminalCase;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 刑事案件登记表 Mapper 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawCriminalCaseMapper extends BaseMapper<LawCriminalCase> {

    /**
     * 分页查询刑事案件列表（支持动态条件）
     *
     * @param page 分页参数
     * @param cond 查询条件
     * @return 案件数据列表
     */
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, @Param("cond") LawCriminalCaseCondition cond);

}