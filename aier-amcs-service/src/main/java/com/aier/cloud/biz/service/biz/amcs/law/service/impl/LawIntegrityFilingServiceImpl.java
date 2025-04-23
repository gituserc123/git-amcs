package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.api.amcs.law.condition.LawIntegrityFilingCondition;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawIntegrityFilingMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawIntegrityFiling;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawIntegrityFilingService;
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
 * 廉洁合规报备表 Service 实现类
 *
 * @author 自动生成
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawIntegrityFilingServiceImpl extends ServiceImpl<LawIntegrityFilingMapper, LawIntegrityFiling> implements LawIntegrityFilingService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawIntegrityFilingCondition cond) {
        return this.baseMapper.getAll(page, cond);
    }

    @Override
    public Object save(LawIntegrityFiling lawIntegrityFiling) {

        Map<String, Object> result = Maps.newHashMap();

        if (null == lawIntegrityFiling.getId()) {
            lawIntegrityFiling.setCreateDate(new Date());
            lawIntegrityFiling.setCreator(UserContext.getUserId());
            lawIntegrityFiling.setInstId(UserContext.getInstId());
            lawIntegrityFiling.setInstName(UserContext.getInstName());
            lawIntegrityFiling.setHospId(UserContext.getTenantId());
        }
        lawIntegrityFiling.setModifyDate(new Date());
        lawIntegrityFiling.setModifer(UserContext.getUserId());
        this.insertOrUpdate(lawIntegrityFiling);
        result.put("msg", "成功");
        result.put("code", "200");
        result.put("integrityFilingId", lawIntegrityFiling.getId());

        return result;
    }

    // 可以根据需要添加其他业务逻辑方法

}