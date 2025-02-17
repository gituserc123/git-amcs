package com.aier.cloud.biz.service.biz.amcs.idr.sdk.vo;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class DataParam<T> implements Serializable{

	@JSONField(name = "ITEM")
    private List<T> ItemList;
	
	public DataParam() {
		super();
	}

	public DataParam(List<T> itemList) {
		super();
		ItemList = itemList;
	}

	public List<T> getItemList() {
		return ItemList;
	}

	public void setItemList(List<T> itemList) {
		ItemList = itemList;
	}
	
	
	
}
