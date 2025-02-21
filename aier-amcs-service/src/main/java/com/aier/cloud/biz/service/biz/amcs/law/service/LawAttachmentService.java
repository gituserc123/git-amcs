package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAttachment;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


/**
 * 法务系统附件表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-20
 */
public interface LawAttachmentService extends IService<LawAttachment> {

    List<LawAttachment> selectByBizId(Long bizId);

}