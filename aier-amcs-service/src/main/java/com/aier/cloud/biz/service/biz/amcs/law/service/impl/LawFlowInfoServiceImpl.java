package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.biz.service.biz.amcs.law.dao.LawFlowInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowInfo;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawFlowInfoService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 流程信息表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawFlowInfoServiceImpl extends ServiceImpl<LawFlowInfoMapper, LawFlowInfo> implements LawFlowInfoService {

    // 在这里可以实现自定义的业务逻辑方法
}