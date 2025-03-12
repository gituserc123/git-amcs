package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.api.amcs.law.condition.LawCriminalCaseCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCriminalCase;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface LawCriminalCaseService extends IService<LawCriminalCase> {

    /**
     * 分页查询刑事案件列表（支持动态条件）
     *
     * @param page 分页参数
     * @param cond 查询条件
     * @return 案件数据列表
     */
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawCriminalCaseCondition cond);

    Object save(LawCriminalCase lawCriminalCase);

}
