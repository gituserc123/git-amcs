package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawFlowInfo;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 流程信息表 Mapper 接口
 */
@Mapper
public interface LawFlowInfoMapper extends BaseMapper<LawFlowInfo> {
    // 自定义的数据库操作方法可以写在这里
}