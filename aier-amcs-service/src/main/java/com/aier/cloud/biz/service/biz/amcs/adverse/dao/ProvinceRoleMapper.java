package com.aier.cloud.biz.service.biz.amcs.adverse.dao;

import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.ProvinceRoleConfig;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;

import java.util.List;

/**
 * TODO
 *
 * @author chendongdong
 * @date 2022/5/11
 */
public interface ProvinceRoleMapper extends BaseMapper<ProvinceRoleConfig> {
    List<ProvinceRoleConfig> queryList(ProvinceRoleCondition m, Page p);
    List<ProvinceRoleConfig> queryList(ProvinceRoleCondition m);
}
