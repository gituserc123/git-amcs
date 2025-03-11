package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawAuditDetailMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditDetail;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAuditDetailService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 审核明细表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawAuditDetailServiceImpl extends ServiceImpl<LawAuditDetailMapper, LawAuditDetail> implements LawAuditDetailService {

    @Resource
    private JdbcHelper db;

    @Override
    public List<LawAuditDetail> getLawAuditDetailListByInstanceId(Long instanceId) {
        return db.queryBeanList("select * from t_law_audit_detail where instance_id = ? order by create_date desc", LawAuditDetail.class, instanceId);
    }

    // 在这里可以实现自定义的业务逻辑方法
}