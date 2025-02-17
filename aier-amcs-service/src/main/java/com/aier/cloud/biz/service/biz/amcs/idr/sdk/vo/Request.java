package com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo;

import java.io.Serializable; 

import com.alibaba.fastjson.annotation.JSONField;

public class Request<T> implements Serializable {

	@JSONField(name = "token")
	private String token;

	@JSONField(name = "userName")
	private String userName;

	@JSONField(name = "passWord")
	private String passWord;
	@JSONField(name = "signature")
	private String signature;
	@JSONField(name = "timestamp")
	private String timestamp;
	@JSONField(name = "nonce")
	private String nonce;
	@JSONField(name = "dataMD5")
	private String dataMD5;

	@JSONField(name = "data")
	private byte[] encodedData;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getDataMD5() {
		return dataMD5;
	}

	public void setDataMD5(String dataMD5) {
		this.dataMD5 = dataMD5;
	}

	public byte[] getEncodedData() {
		return encodedData;
	}

	public void setEncodedData(byte[] encodedData) {
		this.encodedData = encodedData;
	}

}
