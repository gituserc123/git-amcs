package com.aier.cloud.api.amcs.adverse.domain.amrs;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * <b>类名称：</b>AmrsPatient<br/>
 * <b>类描述：</b>对象首页内容封装<br/>
 * <b>创建人：</b>wangrs<br/>
 * <b>修改人：</b>wangrs<br/>
 * <b>修改时间：</b>2018年12月18日 上午9:23:18<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 *
 */
public class AmrsMedicalRecord {
	private AmrsPatientBase base;
	private List<AmrsPatientDiag> diag;
	private List<AmrsPatientOper> oper;
	private List<AmrsPatientTransfer> transfer;

	public AmrsMedicalRecord() {
		base = new AmrsPatientBase();
		diag = new ArrayList<>();
		oper = new ArrayList<>();
		transfer = new ArrayList<>();
	}

	public AmrsPatientBase getBase() {
		return base;
	}
	
	public void setBase(AmrsPatientBase base) {
		this.base = base;
	}
	
	public List<AmrsPatientDiag> getDiag() {
		return diag;
	}
	
	public void setDiag(List<AmrsPatientDiag> diag) {
		this.diag = diag;
	}
	
	public List<AmrsPatientOper> getOper() {
		return oper;
	}
	
	public void setOper(List<AmrsPatientOper> oper) {
		this.oper = oper;
	}
	
	public List<AmrsPatientTransfer> getTransfer() {
		return transfer;
	}
	
	public void setTransfer(List<AmrsPatientTransfer> transfer) {
		this.transfer = transfer;
	}
	
}
