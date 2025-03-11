package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawFlowNodeMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowNode;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawFlowNodeService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 流程节点表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawFlowNodeServiceImpl extends ServiceImpl<LawFlowNodeMapper, LawFlowNode> implements LawFlowNodeService {

    @Resource
    private JdbcHelper db;

    // 在这里可以实现自定义的业务逻辑方法
    @Override
    public List<LawFlowNode> getFlowNodeByFlowId(Long flowId) {
        return db.queryBeanList("select * from t_law_flow_node where flow_id = ? order by step_order asc", LawFlowNode.class, flowId);
    }
}