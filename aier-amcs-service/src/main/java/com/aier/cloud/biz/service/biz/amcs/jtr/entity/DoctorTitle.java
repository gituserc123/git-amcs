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
import com.baomidou.mybatisplus.enums.FieldFill;
import com.aier.cloud.basic.core.base.BaseEntity;

/**
 * T_JTR_DOCTOR_TITLE
 * 员工职称（执业证）信息
 * @author 爱尔眼科
 * @since 2021-11-12 17:28:03
 */
@TableName("T_JTR_DOCTOR_TITLE")
public class DoctorTitle extends BaseEntity<DoctorTitle> {
	/** 员工编码*/
	@TableField(value="staff_code")
	@Length(max=20) private String staffCode;
	
	/** 类型：1-职称，2-执业证*/
	@TableField(value="title_type")
	@NotBlank private Integer titleType;
	
	/** 头衔编码*/
	@TableField(value="title_code")
	@Length(max=20) private String titleCode;
	
	/** 头衔名称*/
	@TableField(value="title_name")
	@Length(max=50) private String titleName;
	
	/** 头衔拓展名称*/
	@TableField(value="title_ext_name")
	@Length(max=50) private String titleExtName;
	
	/** 头衔开始时间*/
	@TableField(value="start_date")
	private java.util.Date startDate;
	

	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public Integer getTitleType() {
		return titleType;
	}
	public void setTitleType(Integer titleType) {
		this.titleType = titleType;
	}
	public String getTitleCode() {
		return titleCode;
	}
	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}
	public String getTitleName() {
		return titleName;
	}
	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getTitleExtName() {
		return titleExtName;
	}
	public void setTitleExtName(String titleExtName) {
		this.titleExtName = titleExtName;
	}
	public java.util.Date getStartDate() {
		return startDate;
	}
	public void setStartDate(java.util.Date startDate) {
		this.startDate = startDate;
	}
}