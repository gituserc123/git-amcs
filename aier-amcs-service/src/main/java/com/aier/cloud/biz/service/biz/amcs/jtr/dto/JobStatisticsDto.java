package com.aier.cloud.biz.service.biz.amcs.jtr.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorStatistics;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorTitle;

public class JobStatisticsDto {
	Set<Long> hospIds;
	List<DoctorStatistics> ds;
	List<DoctorTitle> ts;
	String staffCode;
	Date beginDate;
	Date endDate;
	public List<DoctorStatistics> getDs() {
		return ds;
	}
	public void setDs(List<DoctorStatistics> ds) {
		this.ds = ds;
	}
	public Date getBeginDate() {
		return beginDate;
	}
	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getStaffCode() {
		return staffCode;
	}
	public void setStaffCode(String staffCode) {
		this.staffCode = staffCode;
	}
	public List<DoctorTitle> getTs() {
		return ts;
	}
	public void setTs(List<DoctorTitle> ts) {
		this.ts = ts;
	}
	public Set<Long> getHospIds() {
		return hospIds;
	}
	public void setHospIds(Set<Long> hospIds) {
		this.hospIds = hospIds;
	}
	
}
