package com.aier.cloud.ui.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * 用户服务
 * @author xiaokek
 * @since 2018年1月29日 上午10:11:40
 */
@FeignClient(name="aier-service-medical")
public interface MedicalService {
    /**
     * 根据ids查询包含id：name的键值对，主要用于翻译
     * @param ids
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getNamesByIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<Long,String> getNamesByIds(@RequestBody List<Long> ids);
    
    /**
     * 查询当前用户的住院医嘱消息
     * @return
     */
    @RequestMapping(value = "/api/inpOrder/getMessagePush", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object getMessagePush();
}
