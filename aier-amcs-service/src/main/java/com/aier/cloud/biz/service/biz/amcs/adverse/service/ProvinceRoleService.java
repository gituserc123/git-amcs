package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.domain.ProvinceRole;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.ProvinceRoleConfig;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * TODO
 *
 * @author chendongdong
 * @date 2022/5/11
 */
public interface ProvinceRoleService extends IService<ProvinceRoleConfig> {
    /**
     * 分页获取配置列表
     * @param m
     * @param p
     * @return
     */
    List<ProvinceRoleConfig> queryListPage(ProvinceRoleCondition m, Page p);
    /**
     * 不分页获取配置列表
     * @param m
     * @return
     */
    List<ProvinceRoleConfig> queryListPage(ProvinceRoleCondition m);

    /**
     * 保存省区角色跟用户关系
     * @param provinceRole
     * @return
     */
    boolean save(ProvinceRole provinceRole);

    /**
     * 删除
     * @param id
     * @return
     */
    boolean remove(Long id);

    /**
     * ID获取
     * @param id
     * @return
     */
    ProvinceRoleConfig getById(Long id);

    /**
     * 用户角色是否存在
     * @return
     */
    ProvinceRoleConfig getByRoleAndStaff(Long provinceId,Long roleId,Long staffId);

    /**
     * 查询用户绑定了哪些省区角色
     * @param staffId
     * @return
     */
    List<ProvinceRoleConfig> findByStaff(Long staffId);

    /**
     * 查询省区是否存在该角色
     * @param provinceId
     * @param roleId
     * @return
     */
    boolean checkProvinceRole(Long provinceId, Long roleId);
}
