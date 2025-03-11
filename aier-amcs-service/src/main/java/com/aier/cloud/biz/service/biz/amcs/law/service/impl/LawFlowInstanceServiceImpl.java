package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.biz.service.biz.amcs.law.dao.LawFlowInstanceMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowInstance;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawFlowInstanceService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程实例表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawFlowInstanceServiceImpl extends ServiceImpl<LawFlowInstanceMapper, LawFlowInstance> implements LawFlowInstanceService {

    @Override
    public LawFlowInstance getFlowInstanceByBizId(Long bizId) {
        EntityWrapper<LawFlowInstance> wrapper = new EntityWrapper<>();
        wrapper.eq("biz_id", bizId);
        return this.selectOne(wrapper);
    }
}