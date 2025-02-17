package com.aier.cloud.api.amcs.domain;

import java.util.List;

/**
 * 规则明细自动刷新的结果
 *
 *
 * @author xiaokek
 * @since 2022年6月9日 上午9:40:20
 */
public class RefreshResult<T> {
	String msg = "";
	List<T> rds = null;
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setRds(List<T> rds) {
		this.rds = rds;
	}
	
	public static <T> RefreshResult ok(List<T> rds) {
		RefreshResult r = new RefreshResult();
		r.setMsg("刷新成功，新增（"+rds.size()+"条）");
		r.rds = rds;
		return r;
	}
}
