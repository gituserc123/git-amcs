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

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

/**
 * T_JTR_DOCTOR_STATISTICS
 * 职改医生统计表
 * @author 爱尔眼科
 * @since 2021-09-26 14:29:33
 */
@TableName("T_JTR_DOCTOR_STATISTICS")
public class DoctorStatistics extends BaseEntity<DoctorStatistics> {
	/** 创建时间*/
	@TableField(value="create_date", fill=FieldFill.INSERT)
	@NotBlank private java.util.Date createDate;
	
	/** 医院ID*/
	@TableField(value="hosp_id")
	@NotBlank private Long hospId;
	
	/** 医院名称*/
	@TableField(value="hosp_name")
	@NotBlank @Length(max=50) private String hospName;
	
	/** 工号*/
	@TableField(value="user_code")
	@NotBlank @Length(max=10) private String userCode;
	
	/** 姓名*/
	@TableField(value="user_name")
	@NotBlank @Length(max=30) private String userName;
	
	/** 统计日期*/
	@TableField(value="statistics_date")
	@NotBlank private java.util.Date statisticsDate;
	
	/** 统计类型(1:JOB自动取数;2:手工填报）*/
	@TableField(value="statistics_type")
	@NotBlank private Integer statisticsType;
	
	/** 上午门诊量*/
	@TableField(value="morning_outpatient")
	private Integer morningOutpatient;
	
	/** 下午门诊量*/
	@TableField(value="afternoon_outpatient")
	private Integer afternoonOutpatient;
	
	/** 总住院人次*/
	@TableField(value="total_hospitalization")
	private Integer totalHospitalization;
	
	/** 基本住院人次*/
	@TableField(value="basic_hospitalization")
	private Integer basicHospitalization;
	
	/** 疑难住院人次*/
	@TableField(value="difficult_hospitalization")
	private Integer difficultHospitalization;
	
	/** 基本病种数(暂填0)*/
	@TableField(value="basic_hos_species")
	private Integer basicHosSpecies;
	
	/** 疑难病种数(暂填0)*/
	@TableField(value="difficult_hos_species")
	private Integer difficultHosSpecies;
	
	/** 基本病种覆盖数(暂填0)*/
	@TableField(value="real_basic_hos_species")
	private Integer realBasicHosSpecies;
	
	/** 疑难病种覆盖数(暂填0)*/
	@TableField(value="real_difficult_hos_species")
	private Integer realDifficultHosSpecies;
	
	/** 总手术人次*/
	@TableField(value="total_operation")
	private Integer totalOperation;
	
	/** 基本手术人次*/
	@TableField(value="basic_operation")
	private Integer basicOperation;
	
	/** 疑难手术人次*/
	@TableField(value="difficult_operation")
	private Integer difficultOperation;
	
	/** 基本手术种类(暂填0)*/
	@TableField(value="basic_op_species")
	private Integer basicOpSpecies;
	
	/** 疑难手术种类(暂填0)*/
	@TableField(value="difficult_operation_species")
	private Integer difficultOperationSpecies;
	
	/** 基本手术覆盖数(暂填0)*/
	@TableField(value="real_basic_op_species")
	private Integer realBasicOpSpecies;
	
	/** 疑难手术覆盖数(暂填0)*/
	@TableField(value="real_difficult_op_species")
	private Integer realDifficultOpSpecies;
	
	/** 总门诊手术人次*/
	@TableField(value="total_op_operation")
	private Integer totalOpOperation;
	
	/** 门诊基本手术人次*/
	@TableField(value="op_basic_operation")
	private Integer opBasicOperation;
	
	/** 门诊疑难手术人次*/
	@TableField(value="op_difficult_op_species")
	private Integer opDifficultOpSpecies;
	
	/** 门诊基本手术种类(暂填0)*/
	@TableField(value="op_basic_op_species")
	private Integer opBasicOpSpecies;
	
	/** 门诊疑难手术种类(暂填0)*/
	@TableField(value="op_difficult_operation_species")
	private Integer opDifficultOperationSpecies;
	
	/** 门诊基本手术覆盖数(暂填0)*/
	@TableField(value="op_real_basic_op_species")
	private Integer opRealBasicOpSpecies;
	
	/** 门诊疑难手术覆盖数(暂填0)*/
	@TableField(value="op_real_difficult_op_species")
	private Integer opRealDifficultOpSpecies;
	
	/** 创建者ID*/
	@TableField(value="creator")
	@NotBlank private Long creator;
	

	public java.util.Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(java.util.Date createDate) {
		this.createDate = createDate;
	}
	public Long getHospId() {
		return hospId;
	}
	public void setHospId(Long hospId) {
		this.hospId = hospId;
	}
	public String getHospName() {
		return hospName;
	}
	public void setHospName(String hospName) {
		this.hospName = hospName;
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
	public java.util.Date getStatisticsDate() {
		return statisticsDate;
	}
	public void setStatisticsDate(java.util.Date statisticsDate) {
		this.statisticsDate = statisticsDate;
	}
	public Integer getStatisticsType() {
		return statisticsType;
	}
	public void setStatisticsType(Integer statisticsType) {
		this.statisticsType = statisticsType;
	}
	public Integer getMorningOutpatient() {
		return morningOutpatient;
	}
	public void setMorningOutpatient(Integer morningOutpatient) {
		this.morningOutpatient = morningOutpatient;
	}
	public Integer getAfternoonOutpatient() {
		return afternoonOutpatient;
	}
	public void setAfternoonOutpatient(Integer afternoonOutpatient) {
		this.afternoonOutpatient = afternoonOutpatient;
	}
	public Integer getTotalHospitalization() {
		return totalHospitalization;
	}
	public void setTotalHospitalization(Integer totalHospitalization) {
		this.totalHospitalization = totalHospitalization;
	}
	public Integer getBasicHospitalization() {
		return basicHospitalization;
	}
	public void setBasicHospitalization(Integer basicHospitalization) {
		this.basicHospitalization = basicHospitalization;
	}
	public Integer getDifficultHospitalization() {
		return difficultHospitalization;
	}
	public void setDifficultHospitalization(Integer difficultHospitalization) {
		this.difficultHospitalization = difficultHospitalization;
	}
	public Integer getBasicHosSpecies() {
		return basicHosSpecies;
	}
	public void setBasicHosSpecies(Integer basicHosSpecies) {
		this.basicHosSpecies = basicHosSpecies;
	}
	public Integer getDifficultHosSpecies() {
		return difficultHosSpecies;
	}
	public void setDifficultHosSpecies(Integer difficultHosSpecies) {
		this.difficultHosSpecies = difficultHosSpecies;
	}
	public Integer getRealBasicHosSpecies() {
		return realBasicHosSpecies;
	}
	public void setRealBasicHosSpecies(Integer realBasicHosSpecies) {
		this.realBasicHosSpecies = realBasicHosSpecies;
	}
	public Integer getRealDifficultHosSpecies() {
		return realDifficultHosSpecies;
	}
	public void setRealDifficultHosSpecies(Integer realDifficultHosSpecies) {
		this.realDifficultHosSpecies = realDifficultHosSpecies;
	}
	public Integer getTotalOperation() {
		return totalOperation;
	}
	public void setTotalOperation(Integer totalOperation) {
		this.totalOperation = totalOperation;
	}
	public Integer getBasicOperation() {
		return basicOperation;
	}
	public void setBasicOperation(Integer basicOperation) {
		this.basicOperation = basicOperation;
	}
	public Integer getDifficultOperation() {
		return difficultOperation;
	}
	public void setDifficultOperation(Integer difficultOperation) {
		this.difficultOperation = difficultOperation;
	}
	public Integer getBasicOpSpecies() {
		return basicOpSpecies;
	}
	public void setBasicOpSpecies(Integer basicOpSpecies) {
		this.basicOpSpecies = basicOpSpecies;
	}
	public Integer getDifficultOperationSpecies() {
		return difficultOperationSpecies;
	}
	public void setDifficultOperationSpecies(Integer difficultOperationSpecies) {
		this.difficultOperationSpecies = difficultOperationSpecies;
	}
	public Integer getRealBasicOpSpecies() {
		return realBasicOpSpecies;
	}
	public void setRealBasicOpSpecies(Integer realBasicOpSpecies) {
		this.realBasicOpSpecies = realBasicOpSpecies;
	}
	public Integer getRealDifficultOpSpecies() {
		return realDifficultOpSpecies;
	}
	public void setRealDifficultOpSpecies(Integer realDifficultOpSpecies) {
		this.realDifficultOpSpecies = realDifficultOpSpecies;
	}
	public Integer getTotalOpOperation() {
		return totalOpOperation;
	}
	public void setTotalOpOperation(Integer totalOpOperation) {
		this.totalOpOperation = totalOpOperation;
	}
	public Integer getOpBasicOperation() {
		return opBasicOperation;
	}
	public void setOpBasicOperation(Integer opBasicOperation) {
		this.opBasicOperation = opBasicOperation;
	}
	public Integer getOpDifficultOpSpecies() {
		return opDifficultOpSpecies;
	}
	public void setOpDifficultOpSpecies(Integer opDifficultOpSpecies) {
		this.opDifficultOpSpecies = opDifficultOpSpecies;
	}
	public Integer getOpBasicOpSpecies() {
		return opBasicOpSpecies;
	}
	public void setOpBasicOpSpecies(Integer opBasicOpSpecies) {
		this.opBasicOpSpecies = opBasicOpSpecies;
	}
	public Integer getOpDifficultOperationSpecies() {
		return opDifficultOperationSpecies;
	}
	public void setOpDifficultOperationSpecies(Integer opDifficultOperationSpecies) {
		this.opDifficultOperationSpecies = opDifficultOperationSpecies;
	}
	public Integer getOpRealBasicOpSpecies() {
		return opRealBasicOpSpecies;
	}
	public void setOpRealBasicOpSpecies(Integer opRealBasicOpSpecies) {
		this.opRealBasicOpSpecies = opRealBasicOpSpecies;
	}
	public Integer getOpRealDifficultOpSpecies() {
		return opRealDifficultOpSpecies;
	}
	public void setOpRealDifficultOpSpecies(Integer opRealDifficultOpSpecies) {
		this.opRealDifficultOpSpecies = opRealDifficultOpSpecies;
	}
	public Long getCreator() {
		return creator;
	}
	public void setCreator(Long creator) {
		this.creator = creator;
	}


}