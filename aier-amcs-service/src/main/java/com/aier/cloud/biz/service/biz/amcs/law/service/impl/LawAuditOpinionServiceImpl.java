package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.api.amcs.law.condition.LawAuditOpinionCondition;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawAuditOpinionMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditOpinion;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAuditOpinionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审核意见表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawAuditOpinionServiceImpl extends ServiceImpl<LawAuditOpinionMapper, LawAuditOpinion> implements LawAuditOpinionService {

    @Resource
    private JdbcHelper db;

    // 在这里可以实现自定义的业务逻辑方法
    @Override
    public Object jhInsert(LawAuditOpinion lawAuditOpinion){
        db.insert(lawAuditOpinion);
        return null;
    }

    @Override
    public List<LawAuditOpinion> getListByDetailIds(LawAuditOpinionCondition cond) {
        return this.baseMapper.getListByDetailIds(cond);
    }
}