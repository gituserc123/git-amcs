package com.aier.cloud.api.amcs.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import com.aier.cloud.basic.api.response.domain.sys.Staff;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TODO
 *
 * @author chendongdong
 * @date 2022/5/11
 */

public class ProvinceRole extends BaseEntity {
    private static final long serialVersionUID = 3581786498964868406L;
    @NotBlank
    private String roleName;
    @NotNull
    private Long roleId;
    @NotNull
    private Long provinceId;
    @NotBlank
    private String provinceName;
    @NotBlank
    private String staffIds;

    private List<Staff> staffList;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getStaffIds() {
        return staffIds;
    }

    public void setStaffIds(String staffIds) {
        this.staffIds = staffIds;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }
}
