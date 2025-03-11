package com.aier.cloud.biz.ui.biz.law.feign;

import com.aier.cloud.api.amcs.law.condition.LawNodeAuthCondition;
import com.aier.cloud.api.amcs.law.domain.LawNodeAuth;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LawNodeAuthFeignService {


    @PostMapping(value = "/api/service/biz/amcs/law/nodeAuth/getLawNodeAuthListByStaffId")
    @ResponseBody
    List<LawNodeAuth> getLawNodeAuthListByStaffId(@RequestParam("staffId") Long staffId);

    @RequestMapping(value = "/api/service/biz/amcs/law/nodeAuth/getAll")
    @ResponseBody
    PageResponse<Map<String, Object>> getAll(@RequestBody LawNodeAuthCondition cond);

    /**
     * 根据ID查询节点权限信息
     *
     * @param id 节点权限的ID
     * @return 节点权限信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/nodeAuth/getNodeAuth")
    @ResponseBody
    Map<String, Object> getNodeAuth(@RequestParam("id") Long id);

    @PostMapping(value = "/api/service/biz/amcs/law/nodeAuth/save")
    @ResponseBody
    Object save(@RequestBody LawNodeAuth lawNodeAuth);

    /**
     * 查询节点权限列表
     *
     * @param params 查询参数
     * @return 节点权限列表
     */
    @PostMapping(value = "/api/service/biz/amcs/law/nodeAuth/queryNodeAuths")
    @ResponseBody
    List<Map<String, Object>> queryNodeAuths(@RequestBody Map<String, Object> params);

    /**
     * 创建新的节点权限
     *
     * @param authData 节点权限数据
     * @return 创建结果
     */
    @PostMapping(value = "/api/service/biz/amcs/law/nodeAuth/createNodeAuth")
    @ResponseBody
    Map<String, Object> createNodeAuth(@RequestBody Map<String, Object> authData);

    /**
     * 保存节点权限信息
     *
     * @return 更新结果
     */
    @RequestMapping(value = "/api/service/biz/amcs/law/nodeAuth/saveNodeAuth")
    @ResponseBody
    Object saveNodeAuth(@RequestBody List<LawNodeAuth> list);

    @RequestMapping(value = "/api/service/biz/amcs/law/nodeAuth/deleteNodeAuth")
    @ResponseBody
    Object deleteNodeAuth(@RequestParam("staffId") Long staffId,@RequestParam("staffInstId") Long staffInstId);

    @PostMapping(value = "/api/service/biz/amcs/law/nodeAuth/getLawNodeAuthListByParams")
    @ResponseBody
    List<LawNodeAuth> getLawNodeAuthListByParams(@RequestParam("instIds") List<Long> instIds,@RequestParam("nodeCode") String nodeCode);


}