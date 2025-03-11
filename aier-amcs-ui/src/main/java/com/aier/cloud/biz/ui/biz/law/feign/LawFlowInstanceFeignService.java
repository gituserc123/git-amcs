package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.domain.LawFlowInstance;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawFlowInstanceFeignService {

    /**
     * 获取流程实例信息
     *
     * @param bizId 流程实例的ID
     * @return 流程实例信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowInstance/getFlowInstanceByBizId")
    @ResponseBody
    LawFlowInstance getFlowInstanceByBizId(@RequestParam("bizId") Long bizId);

    @RequestMapping(value = "/api/service/biz/amcs/law/flowInstance/save",method = { RequestMethod.POST })
    @ResponseBody
    Object save(@RequestBody LawFlowInstance lawFlowInstance);

    /**
     * 查询流程实例列表
     *
     * @param params 查询参数
     * @return 流程实例列表
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowInstance/queryFlowInstances")
    @ResponseBody
    List<Map<String, Object>> queryFlowInstances(@RequestBody Map<String, Object> params);

    /**
     * 创建新的流程实例
     *
     * @param instanceData 流程实例数据
     * @return 创建结果
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowInstance/createFlowInstance")
    @ResponseBody
    Map<String, Object> createFlowInstance(@RequestBody Map<String, Object> instanceData);

    /**
     * 更新流程实例信息
     *
     * @param id 流程实例的ID
     * @param updateData 更新数据
     * @return 更新结果
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowInstance/updateFlowInstance")
    @ResponseBody
    Map<String, Object> updateFlowInstance(@RequestParam("id") Long id, @RequestBody Map<String, Object> updateData);

    /**
     * 删除流程实例
     *
     * @param id 流程实例的ID
     * @return 删除结果
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowInstance/deleteFlowInstance")
    @ResponseBody
    Map<String, Object> deleteFlowInstance(@RequestParam("id") Long id);
}