/*
 * Copyright © 2004-2018 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 版权所有
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
package com.aier.cloud.api.amcs.adverse.domain.amrs;

import com.aier.cloud.basic.api.domain.base.BaseEntity;

/**
 * T_AMRS_PATIENT_TRANSFER 患者转科信息表
 * 
 * @author lc
 * @since 2018-12-14 10:18:12
 */
public class AmrsPatientTransfer extends BaseEntity {
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since 1.0.0
	 */

	private static final long serialVersionUID = -3740642695588562957L;

	/** 转入科室名称 */
	private String inDeptName;

	/** 转入日期 */
	private java.util.Date inDate;

	/** 住院号 */
	private String inpNumber;

	/** 诊疗机构 */
	private Long hospId;

	/** 病案号（年4位+6位ID，按年重算清零） */
	private String medicalNumber;

	/** 住院次数（2位住院次数，患者在本机构住院次数） */
	private Integer inpTimes;

	/** 转入科室ID */
	private Long inDeptId;

	public String getInDeptName() {
		return inDeptName;
	}

	public void setInDeptName(String inDeptName) {
		this.inDeptName = inDeptName;
	}

	public java.util.Date getInDate() {
		return inDate;
	}

	public void setInDate(java.util.Date inDate) {
		this.inDate = inDate;
	}

	public String getInpNumber() {
		return inpNumber;
	}

	public void setInpNumber(String inpNumber) {
		this.inpNumber = inpNumber;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public String getMedicalNumber() {
		return medicalNumber;
	}

	public void setMedicalNumber(String medicalNumber) {
		this.medicalNumber = medicalNumber;
	}

	public Integer getInpTimes() {
		return inpTimes;
	}

	public void setInpTimes(Integer inpTimes) {
		this.inpTimes = inpTimes;
	}

	public Long getInDeptId() {
		return inDeptId;
	}

	public void setInDeptId(Long inDeptId) {
		this.inDeptId = inDeptId;
	}
}