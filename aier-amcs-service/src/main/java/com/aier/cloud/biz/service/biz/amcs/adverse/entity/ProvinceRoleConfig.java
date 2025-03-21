package com.aier.cloud.biz.service.biz.amcs.adverse.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import com.aier.cloud.biz.service.biz.amcs.adverse.aop.Comment;
/**
 * TODO
 *
 * @author chendongdong
 * @date 2022/5/11
 */
@TableName("T_PROVINCE_ROLE_CONFIG")
public class ProvinceRoleConfig {

    @TableId(
            value = "id",
            type = IdType.ID_WORKER
    )
    private Long id;

    /** 省区名称*/
    @Comment(value="省区名称")
    @TableField(value="province_name")
    @NotBlank
    @Length(max=30)
    private String provinceName;

    /** 省区Id*/
    @Comment(value="省区Id")
    @TableField(value="province_id")
    private Long provinceId;

    /** 角色Id*/
    @Comment(value="角色Id")
    @TableField(value="role_id")
    private Long roleId;

    /** 角色名称*/
    @Comment(value="角色名称")
    @TableField(value="role_name")
    @NotBlank
    @Length(max=30)
    private String roleName;

    /** 工号*/
    @Comment(value="工号")
    @TableField(value="staff_code")
    @NotBlank
    @Length(max=10)
    private String staffCode;

    /** 用户ID*/
    @Comment(value="用户ID")
    @TableField(value="staff_id")
    @NotBlank
    private Long staffId;

    /** 姓名*/
    @Comment(value="姓名")
    @TableField(value="staff_name")
    @NotBlank @Length(max=30)
    private String staffName;

    /** 创建时间*/
    @Comment(value="创建时间")
    @TableField(value="create_date")
    @NotBlank
    private java.util.Date createDate;

    /** 创建者ID*/
    @Comment(value="创建者ID")
    @TableField(value="creator")
    @NotBlank
    private Long creator;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
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

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStaffCode() {
		return staffCode;
	}

	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}

	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	public String getStaffName() {
		return staffName;
	}

	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	public java.util.Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}

	public Long getCreator() {
		return creator;
	}

	public void setCreator(Long creator) {
		this.creator = creator;
	}


}
