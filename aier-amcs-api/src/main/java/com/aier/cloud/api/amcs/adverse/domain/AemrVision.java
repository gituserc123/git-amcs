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
package com.aier.cloud.api.amcs.adverse.domain;

import com.aier.cloud.basic.api.domain.base.BaseEntity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * T_AEMR_VISION
 * 
 * @author 爱尔眼科
 * @since 2019-12-20 14:29:49
 */
@TableName("T_AEMR_VISION")
public class AemrVision extends BaseEntity {
	/** 文书类型(1:入院记录;2:出院记录)*/
	@TableField(value="type")
	@NotBlank private Integer type;
	
	/** 文书ID*/
	@TableField(value="doc_id")
	private Long docId;
	
	/** 住院号*/
	@TableField(value="inp_number")
	@NotBlank @Length(max=20) private String inpNumber;
	
	/** 病案号*/
	@TableField(value="medical_number")
	@NotBlank @Length(max=20) private String medicalNumber;
	
	/** 住院次*/
	@TableField(value="inp_times")
	@NotBlank private Integer inpTimes;
	
	/** 右眼裸视力*/
	@TableField(value="right_vision")
	@Length(max=8) private String rightVision;
	
	/** 右眼裸视力描述*/
	@TableField(value="right_vision_desc")
	@Length(max=50) private String rightVisionDesc;
	
	/** 左眼裸视力*/
	@TableField(value="left_vision")
	@Length(max=8) private String leftVision;
	
	/** 左眼裸视力描述*/
	@TableField(value="left_vision_desc")
	@Length(max=50) private String leftVisionDesc;

	/** 左眼眼压*/
	@TableField(value="iop_os")
	@Length(max=50) private String iopOs;

	/** 右眼眼压*/
	@TableField(value="iop_od")
	@Length(max=50) private String iopOd;

	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;

	public String getIopOs() {
		return iopOs;
	}

	public void setIopOs(String iopOs) {
		this.iopOs = iopOs;
	}

	public String getIopOd() {
		return iopOd;
	}

	public void setIopOd(String iopOd) {
		this.iopOd = iopOd;
	}

	public Long getHospId() {
		return hospId;
	}

	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}

	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Long getDocId() {
		return docId;
	}
	public void setDocId(Long docId) {
		this.docId = docId;
	}
	public String getInpNumber() {
		return inpNumber;
	}
	public void setInpNumber(String inpNumber) {
		this.inpNumber = inpNumber;
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
	public String getRightVisionDesc() {
		return rightVisionDesc;
	}
	public void setRightVisionDesc(String rightVisionDesc) {
		this.rightVisionDesc = rightVisionDesc;
	}
	public String getLeftVisionDesc() {
		return leftVisionDesc;
	}
	public void setLeftVisionDesc(String leftVisionDesc) {
		this.leftVisionDesc = leftVisionDesc;
	}

	public String getRightVision() {
		return rightVision;
	}

	public void setRightVision(String rightVision) {
		this.rightVision = rightVision;
	}

	public String getLeftVision() {
		return leftVision;
	}

	public void setLeftVision(String leftVision) {
		this.leftVision = leftVision;
	}
}