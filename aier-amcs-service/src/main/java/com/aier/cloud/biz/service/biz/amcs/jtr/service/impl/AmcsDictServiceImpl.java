package com.aier.cloud.biz.service.biz.amcs.jtr.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aier.cloud.basic.starter.service.controller.TransCodeService;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.AmcsDictService;

@Service
public class AmcsDictServiceImpl implements AmcsDictService{

	@Autowired
    private TransCodeService tch;

	@Override
	public List<Map> getList(String type, String filter, String code) {
		// TODO Auto-generated method stub
        List<Map> result = tch.getList(type, filter, code);
        return new ArrayList<>(result);
	}
	
}
