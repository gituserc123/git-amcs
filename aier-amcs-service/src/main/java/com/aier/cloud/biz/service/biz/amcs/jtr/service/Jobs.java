package com.aier.cloud.biz.service.biz.amcs.jtr.service;

import com.alibaba.fastjson.annotation.JSONField;

public class Jobs{

	@JSONField(name="code")
	private String code;

	@JSONField(name="certLevel")
	private int certLevel;

	@JSONField(name="institutionId")
	private int institutionId;

	@JSONField(name="usingSign")
	private int usingSign;

	@JSONField(name="hospId")
	private int hospId;

	@JSONField(name="hospName")
	private String hospName;

	@JSONField(name="ahisSign")
	private int ahisSign;

	@JSONField(name="certLevelName")
	private String certLevelName;

	@JSONField(name="emplRcd")
	private String emplRcd;

	@JSONField(name="staffId")
	private int staffId;

	@JSONField(name="isMainCode")
	private String isMainCode;

	public void setCode(String code){
		this.code = code;
	}

	public String getCode(){
		return code;
	}

	public void setCertLevel(int certLevel){
		this.certLevel = certLevel;
	}

	public int getCertLevel(){
		return certLevel;
	}

	public void setInstitutionId(int institutionId){
		this.institutionId = institutionId;
	}

	public int getInstitutionId(){
		return institutionId;
	}

	public void setUsingSign(int usingSign){
		this.usingSign = usingSign;
	}

	public int getUsingSign(){
		return usingSign;
	}

	public void setHospId(int hospId){
		this.hospId = hospId;
	}

	public int getHospId(){
		return hospId;
	}

	public void setHospName(String hospName){
		this.hospName = hospName;
	}

	public String getHospName(){
		return hospName;
	}

	public void setAhisSign(int ahisSign){
		this.ahisSign = ahisSign;
	}

	public int getAhisSign(){
		return ahisSign;
	}

	public void setCertLevelName(String certLevelName){
		this.certLevelName = certLevelName;
	}

	public String getCertLevelName(){
		return certLevelName;
	}

	public void setEmplRcd(String emplRcd){
		this.emplRcd = emplRcd;
	}

	public String getEmplRcd(){
		return emplRcd;
	}

	public void setStaffId(int staffId){
		this.staffId = staffId;
	}

	public int getStaffId(){
		return staffId;
	}

	public void setIsMainCode(String isMainCode){
		this.isMainCode = isMainCode;
	}

	public String getIsMainCode(){
		return isMainCode;
	}

	@Override
 	public String toString(){
		return 
			"Jobs{" + 
			"code = '" + code + '\'' + 
			",certLevel = '" + certLevel + '\'' + 
			",institutionId = '" + institutionId + '\'' + 
			",usingSign = '" + usingSign + '\'' + 
			",hospId = '" + hospId + '\'' + 
			",hospName = '" + hospName + '\'' + 
			",ahisSign = '" + ahisSign + '\'' + 
			",certLevelName = '" + certLevelName + '\'' + 
			",emplRcd = '" + emplRcd + '\'' + 
			",staffId = '" + staffId + '\'' + 
			",isMainCode = '" + isMainCode + '\'' + 
			"}";
		}
}