package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.api.amcs.law.condition.LawExternalLegalAdvisorCondition;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawExternalLegalAdvisorMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawExternalLegalAdvisor;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawExternalLegalAdvisorService;
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
 * 外聘法律顾问信息登记表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawExternalLegalAdvisorServiceImpl extends ServiceImpl<LawExternalLegalAdvisorMapper, LawExternalLegalAdvisor>
        implements LawExternalLegalAdvisorService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawExternalLegalAdvisorCondition cond) {
        return this.baseMapper.getAll(page, cond);
    }

    @Override
    public Object save(LawExternalLegalAdvisor lawExternalLegalAdvisor) {
        Map<String, Object> result = Maps.newHashMap();

        // 新增或更新逻辑
        if (lawExternalLegalAdvisor.getId() == null) {
            // 新增
            lawExternalLegalAdvisor.setCreateDate(new Date());
            lawExternalLegalAdvisor.setCreator(UserContext.getUserId());
            lawExternalLegalAdvisor.setInstId(UserContext.getInstId());
            lawExternalLegalAdvisor.setInstName(UserContext.getInstName());
        }
        // 更新
        lawExternalLegalAdvisor.setModifyDate(new Date());
        lawExternalLegalAdvisor.setModifer(UserContext.getUserId());

        // 保存或更新
        this.insertOrUpdate(lawExternalLegalAdvisor);

        // 返回结果
        result.put("msg", "成功");
        result.put("code", "200");
        result.put("advisorId", lawExternalLegalAdvisor.getId());

        return result;
    }
}