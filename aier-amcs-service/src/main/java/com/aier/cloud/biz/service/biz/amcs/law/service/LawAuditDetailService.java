package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditDetail;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 审核明细表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
public interface LawAuditDetailService extends IService<LawAuditDetail> {
    // 自定义的业务逻辑方法可以写在这里
    List<LawAuditDetail> getLawAuditDetailListByInstanceId(Long instanceId);
}