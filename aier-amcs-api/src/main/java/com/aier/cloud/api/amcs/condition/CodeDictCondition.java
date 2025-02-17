package com.aier.cloud.api.amcs.condition;

import java.io.Serializable;

import com.aier.cloud.basic.api.request.condition.base.PageCondition;

public class CodeDictCondition extends PageCondition implements Serializable {
	

	private static final long serialVersionUID = -8702385187257040343L;

	/** 参数类型 */
	private String typeCode;

	/** 参数类型描述 */
	private String typeDesc;

	/** 启停标识（1启用0停用） */
	private Integer usingSign;

	/** like 参数类型 */
	private String likeTypeCode;

	/** eq 参数类型 */
	private String eqTypeCode;

	/** eq 参数值编码 */
	private String eqValueCode;

	/** eq 参数值编码描述 */
	private String eqValueDesc;

	/** like 参数值编码描述 */
	private String likeValueDesc;

	/** like 参数类型描述 */
	private String likeTypeDesc;

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public Integer getUsingSign() {
		return usingSign;
	}

	public void setUsingSign(Integer usingSign) {
		this.usingSign = usingSign;
	}

	public String getLikeTypeCode() {
		return likeTypeCode;
	}

	public void setLikeTypeCode(String likeTypeCode) {
		this.likeTypeCode = likeTypeCode;
	}

	public String getEqTypeCode() {
		return eqTypeCode;
	}

	public void setEqTypeCode(String eqTypeCode) {
		this.eqTypeCode = eqTypeCode;
	}

	public String getEqValueCode() {
		return eqValueCode;
	}

	public void setEqValueCode(String eqValueCode) {
		this.eqValueCode = eqValueCode;
	}

	public String getEqValueDesc() {
		return eqValueDesc;
	}

	public void setEqValueDesc(String eqValueDesc) {
		this.eqValueDesc = eqValueDesc;
	}

	public String getLikeValueDesc() {
		return likeValueDesc;
	}

	public void setLikeValueDesc(String likeValueDesc) {
		this.likeValueDesc = likeValueDesc;
	}

	public String getLikeTypeDesc() {
		return likeTypeDesc;
	}

	public void setLikeTypeDesc(String likeTypeDesc) {
		this.likeTypeDesc = likeTypeDesc;
	}


}
