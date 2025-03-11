package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditDetail;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 审核明细表 Mapper 接口
 */
@Mapper
public interface LawAuditDetailMapper extends BaseMapper<LawAuditDetail> {
    // 自定义的数据库操作方法可以写在这里
}