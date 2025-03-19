package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.api.amcs.law.condition.LawExternalLegalAdvisorCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawExternalLegalAdvisor;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 外聘法律顾问信息登记表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawExternalLegalAdvisorService extends IService<LawExternalLegalAdvisor> {

    /**
     * 分页查询外聘法律顾问信息
     *
     * @param page 分页对象
     * @param cond 查询条件
     * @return 查询结果列表
     */
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawExternalLegalAdvisorCondition cond);

    /**
     * 保存外聘法律顾问信息
     *
     * @param lawExternalLegalAdvisor 外聘法律顾问信息实体
     * @return 保存结果
     */
    Object save(LawExternalLegalAdvisor lawExternalLegalAdvisor);

}