package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.api.amcs.law.condition.LawDisputeMatterCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawDisputeMatter;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface LawDisputeMatterService extends IService<LawDisputeMatter> {

    /**
     * 分页查询纠纷事项列表（支持动态条件）
     *
     * @param page 分页参数
     * @param cond 查询条件
     * @return 纠纷事项数据列表
     */
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawDisputeMatterCondition cond);

    /**
     * 保存纠纷事项
     *
     * @param lawDisputeMatter 纠纷事项实体
     * @return 保存结果
     */
    Object save(LawDisputeMatter lawDisputeMatter);
}