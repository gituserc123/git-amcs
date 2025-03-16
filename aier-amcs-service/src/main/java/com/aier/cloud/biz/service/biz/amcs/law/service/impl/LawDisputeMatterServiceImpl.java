package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.api.amcs.law.condition.LawDisputeMatterCondition;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawDisputeMatterMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawDisputeMatter;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawDisputeMatterService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 纠纷事项表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawDisputeMatterServiceImpl extends ServiceImpl<LawDisputeMatterMapper, LawDisputeMatter> implements LawDisputeMatterService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawDisputeMatterCondition cond) {
        return this.baseMapper.getAll(page, cond);
    }

    @Override
    public Object save(LawDisputeMatter lawDisputeMatter) {
        Map<String, Object> result = Maps.newHashMap();

        if (null == lawDisputeMatter.getId()) {
            lawDisputeMatter.setCreateDate(new Date());
            lawDisputeMatter.setCreator(UserContext.getUserId());
            lawDisputeMatter.setInstId(UserContext.getInstId());
            lawDisputeMatter.setInstName(UserContext.getInstName());
        }
        lawDisputeMatter.setModifyDate(new Date());
        lawDisputeMatter.setModifer(UserContext.getUserId());
        this.insertOrUpdate(lawDisputeMatter);
        result.put("msg", "成功");
        result.put("code", "200");
        result.put("disputeMatterId", lawDisputeMatter.getId());

        return result;
    }
}