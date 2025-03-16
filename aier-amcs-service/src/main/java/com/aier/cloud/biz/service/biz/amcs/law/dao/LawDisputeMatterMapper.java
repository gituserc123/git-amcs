package com.aier.cloud.biz.service.biz.amcs.law.dao;

import com.aier.cloud.api.amcs.law.condition.LawDisputeMatterCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawDisputeMatter;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 纠纷事项表 Mapper 接口
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
public interface LawDisputeMatterMapper extends BaseMapper<LawDisputeMatter> {

    /**
     * 分页查询纠纷事项列表（含条件筛选）
     *
     * @param page 分页对象
     * @param cond 查询条件
     * @return 映射结果集
     */
    List<Map<String, Object>> getAll(Page<Map<String, Object>> page, @Param("cond") LawDisputeMatterCondition cond);

}