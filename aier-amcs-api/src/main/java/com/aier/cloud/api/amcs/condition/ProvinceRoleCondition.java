package com.aier.cloud.api.amcs.condition;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;
import org.hibernate.validator.constraints.Length;

/**
 * TODO
 *
 * @author chendongdong
 * @date 2022/5/11
 */
public class ProvinceRoleCondition extends PageCondition {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /** 工号*/
    @Length(max=10)
    private String staffCode;

    /** 姓名*/
    @Length(max=30)
    private String staffName;

    /** 省区ID*/
    private Long provinceId;

    private Long roleId;

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
