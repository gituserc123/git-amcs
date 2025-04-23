package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.api.amcs.law.condition.LawIntegrityFilingCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawIntegrityFiling;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 廉洁合规报备表 Service 接口
 *
 * @author 自动生成
 * @since 2023-10-18
 */
public interface LawIntegrityFilingService extends IService<LawIntegrityFiling> {

    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawIntegrityFilingCondition cond);

    public Object save(LawIntegrityFiling lawIntegrityFiling);
}