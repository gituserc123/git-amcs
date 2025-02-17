package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;

import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.domain.ProvinceRole;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.ProvinceRoleMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.ProvinceRoleConfig;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.ProvinceRoleService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * TODO
 *
 * @author chendongdong
 * @date 2022/5/11
 */
@Service
public class ProvinceRoleServiceImpl extends ServiceImpl<ProvinceRoleMapper, ProvinceRoleConfig> implements ProvinceRoleService {
    @Autowired
    private ProvinceRoleMapper provinceRoleMapper;

    @Override
    public List<ProvinceRoleConfig> queryListPage(ProvinceRoleCondition m, Page p) {
        return provinceRoleMapper.queryList(m, p);
    }
    @Override
    public List<ProvinceRoleConfig> queryListPage(ProvinceRoleCondition m) {
        return provinceRoleMapper.queryList(m);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(ProvinceRole provinceRole) {
        if (Objects.isNull(provinceRole) || CollectionUtils.isEmpty(provinceRole.getStaffList())) {
            return false;
        }
        List<ProvinceRoleConfig> list = new ArrayList<>();
        for (Staff staff : provinceRole.getStaffList()) {
            ProvinceRoleConfig roleConfig = new ProvinceRoleConfig();
            roleConfig.setCreateDate(new Date());
            roleConfig.setCreator(UserContext.getUserId());
            roleConfig.setProvinceId(provinceRole.getProvinceId());
            roleConfig.setRoleId(provinceRole.getRoleId());
            roleConfig.setProvinceName(provinceRole.getProvinceName());
            roleConfig.setRoleName(provinceRole.getRoleName());
            roleConfig.setStaffId(staff.getId());
            roleConfig.setStaffCode(staff.getCode());
            roleConfig.setStaffName(staff.getName());
            if (checkProvinceStaffRole(provinceRole.getProvinceId(),provinceRole.getRoleId(),staff.getCode())){
                throw new BizException(provinceRole.getProvinceName()+" "+provinceRole.getRoleName()+" 用户:"+staff.getName() +" 已存在绑定关系，请重新选择后添加");
            }
            list.add(roleConfig);
        }
        return insertBatch(list);
    }

    @Override
    public boolean remove(Long id) {
        int c = provinceRoleMapper.deleteById(id);
        if (c > 0) {
            return true;
        }
        return false;
    }

    @Override
    public ProvinceRoleConfig getById(Long id) {
        return provinceRoleMapper.selectById(id);
    }

    @Override
    public ProvinceRoleConfig getByRoleAndStaff(Long provinceId, Long roleId, Long staffId) {
        ProvinceRoleConfig con = new ProvinceRoleConfig();
        con.setProvinceId(provinceId);
        con.setStaffId(staffId);
        con.setRoleId(roleId);
        return provinceRoleMapper.selectOne(con);
    }

    @Override
    public List<ProvinceRoleConfig> findByStaff(Long staffId) {
        if (Objects.isNull(staffId) || staffId.equals(0L)) {
            return new ArrayList<>();
        }
        Wrapper<ProvinceRoleConfig> wrapper = new EntityWrapper<>();
        wrapper.eq("staff_id", staffId);
        return provinceRoleMapper.selectList(wrapper);
    }

    @Override
    public boolean checkProvinceRole(Long provinceId, Long roleId) {
        if (Objects.isNull(provinceId) || Objects.isNull(roleId)) {
            return false;
        }
        Wrapper<ProvinceRoleConfig> wrapper = new EntityWrapper<>();
        wrapper.eq("province_id", provinceId);
        wrapper.eq("role_id", roleId);
        List<ProvinceRoleConfig> list = provinceRoleMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }

    public boolean checkProvinceStaffRole(Long provinceId, Long roleId,String staffCode) {
        if (Objects.isNull(provinceId) || Objects.isNull(roleId)) {
            return false;
        }
        Wrapper<ProvinceRoleConfig> wrapper = new EntityWrapper<>();
        wrapper.eq("province_id", provinceId);
        wrapper.eq("role_id", roleId);
        wrapper.eq("staff_code",staffCode);
        List<ProvinceRoleConfig> list = provinceRoleMapper.selectList(wrapper);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }
}
