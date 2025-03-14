package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.api.amcs.law.condition.LawAdminPenaltyCondition;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawAdminPenaltyMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAdminPenalty;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAdminPenaltyService;
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
 * 行政处罚事项表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawAdminPenaltyServiceImpl extends ServiceImpl<LawAdminPenaltyMapper, LawAdminPenalty> implements LawAdminPenaltyService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawAdminPenaltyCondition cond) {
        return this.baseMapper.getAll(page, cond);
    }

    @Override
    public Object save(LawAdminPenalty lawAdminPenalty) {
        Map<String, Object> result = Maps.newHashMap();

        if (null == lawAdminPenalty.getId()) {
            lawAdminPenalty.setCreateDate(new Date());
            lawAdminPenalty.setCreator(UserContext.getUserId());
            lawAdminPenalty.setInstId(UserContext.getInstId());
            lawAdminPenalty.setInstName(UserContext.getInstName());
        }
        lawAdminPenalty.setModifyDate(new Date());
        lawAdminPenalty.setModifer(UserContext.getUserId());
        this.insertOrUpdate(lawAdminPenalty);
        result.put("msg", "成功");
        result.put("code", "200");
        result.put("adminPenaltyId", lawAdminPenalty.getId());

        return result;
    }
}