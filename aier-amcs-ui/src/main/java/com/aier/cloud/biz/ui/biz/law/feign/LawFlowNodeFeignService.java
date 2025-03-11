package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.domain.LawFlowNode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawFlowNodeFeignService {


    @PostMapping("/api/service/biz/amcs/law/flowNode/getFlowNodeByFlowId")
    @ResponseBody
    List<LawFlowNode> getFlowNodeByFlowId(@RequestParam("flowId") Long flowId);

    /**
     * 根据ID查询流程节点信息
     *
     * @param id 流程节点的ID
     * @return 流程节点信息
     */
    @GetMapping(value = "/api/service/biz/amcs/law/flowNode/getFlowNode")
    @ResponseBody
    Map<String, Object> getFlowNode(@RequestParam("id") Long id);

    /**
     * 查询流程节点列表
     *
     * @param params 查询参数
     * @return 流程节点列表
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowNode/queryFlowNodes")
    @ResponseBody
    List<Map<String, Object>> queryFlowNodes(@RequestBody Map<String, Object> params);

    /**
     * 创建新的流程节点
     *
     * @param nodeData 流程节点数据
     * @return 创建结果
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowNode/createFlowNode")
    @ResponseBody
    Map<String, Object> createFlowNode(@RequestBody Map<String, Object> nodeData);

    /**
     * 更新流程节点信息
     *
     * @param id 流程节点的ID
     * @param updateData 更新数据
     * @return 更新结果
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowNode/updateFlowNode")
    @ResponseBody
    Map<String, Object> updateFlowNode(@RequestParam("id") Long id, @RequestBody Map<String, Object> updateData);

    /**
     * 删除流程节点
     *
     * @param id 流程节点的ID
     * @return 删除结果
     */
    @PostMapping(value = "/api/service/biz/amcs/law/flowNode/deleteFlowNode")
    @ResponseBody
    Map<String, Object> deleteFlowNode(@RequestParam("id") Long id);
}