package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.api.amcs.law.condition.LawAuditOpinionCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditOpinion;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 审核意见表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
public interface LawAuditOpinionService extends IService<LawAuditOpinion> {
    // 自定义的业务逻辑方法可以写在这里
    Object jhInsert(LawAuditOpinion lawAuditOpinion);

    List<LawAuditOpinion> getListByDetailIds(LawAuditOpinionCondition cond);
}