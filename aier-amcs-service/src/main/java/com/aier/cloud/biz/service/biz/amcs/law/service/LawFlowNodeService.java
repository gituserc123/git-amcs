package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowNode;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * 流程节点表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
public interface LawFlowNodeService extends IService<LawFlowNode> {
    // 自定义的业务逻辑方法可以写在这里

    List<LawFlowNode> getFlowNodeByFlowId(Long flowId);
}