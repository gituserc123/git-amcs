package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowInstance;
import com.baomidou.mybatisplus.service.IService;

/**
 * 流程实例表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
public interface LawFlowInstanceService extends IService<LawFlowInstance> {
    // 自定义的业务逻辑方法可以写在这里

    LawFlowInstance getFlowInstanceByBizId(Long bizId);
}