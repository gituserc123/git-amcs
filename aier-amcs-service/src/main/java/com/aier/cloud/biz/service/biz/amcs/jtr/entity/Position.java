/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.service.biz.amcs.jtr.entity;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_JTR_POSITION
 * 医生跨院岗位表
 * @author 爱尔眼科
 * @since 2021-09-26 14:29:33
 */
@TableName("T_JTR_POSITION")
public class Position extends BaseEntity<Position> {
	/** 医生工号*/
	@TableField(value="user_code")
	@NotBlank @Length(max=10) private String userCode;
	
	/** 姓名*/
	@TableField(value="user_name")
	@NotBlank @Length(max=30) private String userName;
	
	/** 兼岗工号*/
	@TableField(value="current_user_code")
	@NotBlank @Length(max=10) private String currentUserCode;
	
	/** 医院Id*/
	@TableField(value="hosp_id")
	@NotBlank private int hospId;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@NotBlank @Length(max=50) private String hospName;
	
	/** 是否主岗(1:是,0:否)*/
	@TableField(value="main_sign")
	@NotBlank private Integer mainSign;
	
	/** 是否开通AHIS(1:是,0:否)*/
	@TableField(value="ahis_sign")
	@NotBlank private Integer ahisSign;

	/** 是否启用(1:是,:否)*/
	@TableField(value="using_sign")
	@NotBlank private Integer usingSign;

	public Integer getUsingSign() {
		return usingSign;
	}

	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}

	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCurrentUserCode() {
		return currentUserCode;
	}
	public void setCurrentUserCode(String currentUserCode) {
		this.currentUserCode = currentUserCode;
	}
	public int getHospId() {
		return hospId;
	}
	public void setHospId(int hospId) {
		this.hospId = hospId;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
	}
	public Integer getMainSign() {
		return mainSign;
	}
	public void setMainSign(Integer mainSign) {
		this.mainSign = mainSign;
	}
	public Integer getAhisSign() {
		return ahisSign;
	}
	public void setAhisSign(Integer ahisSign) {
		this.ahisSign = ahisSign;
	}
}