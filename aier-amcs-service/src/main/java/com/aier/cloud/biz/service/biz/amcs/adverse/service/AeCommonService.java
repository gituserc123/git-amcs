package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.api.amcs.adverse.enums.EventEnum;


public interface AeCommonService {
	
	public Map<String, Object> save(Map<String, Object> mData, EventEnum eEnum) throws Exception;
	
	public boolean merge(Long masterId, List<Long> mergeIds) throws Exception;
	
	public boolean reback(Long basicId, EventEnum eEnum, Integer node) throws Exception;
	
	public Map<String, Object> findById(Long id, EventEnum eEnum) throws Exception;

}
