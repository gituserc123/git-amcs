package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawNodeInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawNodeInfo;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawNodeInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 节点信息表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-24
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawNodeInfoServiceImpl extends ServiceImpl<LawNodeInfoMapper, LawNodeInfo> implements LawNodeInfoService {

    @Resource
    private JdbcHelper db;

    @Override
    public LawNodeInfo getNodeByCode(String nodeCode) {
        return db.queryBean("select * from t_law_node_info where node_code = ?", LawNodeInfo.class, nodeCode);
    }
}