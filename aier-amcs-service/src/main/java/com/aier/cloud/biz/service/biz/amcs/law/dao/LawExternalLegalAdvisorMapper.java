package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawExternalLegalAdvisorCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawExternalLegalAdvisor;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 外聘法律顾问信息登记表 Mapper 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawExternalLegalAdvisorMapper extends BaseMapper<LawExternalLegalAdvisor> {

    /**
     * 分页查询外聘法律顾问信息
     *
     * @param page 分页对象
     * @param cond 查询条件
     * @return 查询结果列表
     */
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, @Param("cond") LawExternalLegalAdvisorCondition cond);

}