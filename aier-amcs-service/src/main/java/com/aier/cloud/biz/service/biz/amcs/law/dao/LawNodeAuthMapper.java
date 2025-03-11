package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawNodeAuthCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawNodeAuth;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 节点权限表 Mapper 接口
 */
@Mapper
public interface LawNodeAuthMapper extends BaseMapper<LawNodeAuth> {
    // 自定义的数据库操作方法可以写在这里
    List<LawNodeAuth> getAll(Page<Map<String, Object>> page, @Param("cond") LawNodeAuthCondition cond);
}