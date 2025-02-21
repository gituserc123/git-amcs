package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.biz.service.biz.amcs.law.dao.LawAttachmentMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAttachment;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawAttachmentService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 法务系统附件表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-20
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawAttachmentServiceImpl extends ServiceImpl<LawAttachmentMapper, LawAttachment> implements LawAttachmentService {
    @Override
    public List<LawAttachment> selectByBizId(Long bizId) {
        EntityWrapper<LawAttachment> ew = new EntityWrapper<>();
        ew.eq("biz_id", bizId);
        return this.baseMapper.selectList(ew);
    }

    // 如果有其他业务逻辑需要实现，可以在这里添加方法
}