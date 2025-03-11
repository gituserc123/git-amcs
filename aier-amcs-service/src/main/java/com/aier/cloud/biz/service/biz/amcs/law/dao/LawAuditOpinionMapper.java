package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawAuditOpinionCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawAuditOpinion;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审核意见表 Mapper 接口
 */
@Mapper
public interface LawAuditOpinionMapper extends BaseMapper<LawAuditOpinion> {
    // 自定义的数据库操作方法可以写在这里

    List<LawAuditOpinion> getListByDetailIds(@Param("cond") LawAuditOpinionCondition cond);
}