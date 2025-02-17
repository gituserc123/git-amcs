package com.aier.cloud.biz.service.biz.amcs.jtr.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface AmcsDictService {

	/**
     * 查码表
     * @param type
     * @param filter 过滤条件
     * @return
     */
    List<Map> getList(String type, @RequestParam(value = "filter", required = false) String filter, @RequestParam(value = "code", required = false) String code);
}
