package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.api.amcs.law.condition.LawAdminPenaltyCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAdminPenalty;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface LawAdminPenaltyService extends IService<LawAdminPenalty> {

    /**
     * 分页查询行政处罚事项列表（支持动态条件）
     *
     * @param page 分页参数
     * @param cond 查询条件
     * @return 案件数据列表
     */
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawAdminPenaltyCondition cond);

    /**
     * 保存行政处罚事项
     *
     * @param lawAdminPenalty 行政处罚事项实体
     * @return 保存结果
     */
    Object save(LawAdminPenalty lawAdminPenalty);
}