package com.aier.cloud.biz.ui.biz.law.feign;


import com.aier.cloud.api.amcs.law.domain.LawAuditDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 审核明细表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@FeignClient(name = "aier-amcs-service")
public interface LawAuditDetailFeignService {
    // 自定义的业务逻辑方法可以写在这里

    /**
     * 获取流程审批信息
     *
     * @param instanceId 流程实例的ID
     * @return 流程实例信息
     */
    @PostMapping(value = "/api/service/biz/amcs/law/auditDetail/getLawAuditDetailListByInstanceId")
    @ResponseBody
    List<LawAuditDetail> getLawAuditDetailListByInstanceId(@RequestParam("instanceId") Long instanceId);

    @RequestMapping(value = "/api/service/biz/amcs/law/auditDetail/save",method = {RequestMethod.POST })
    @ResponseBody
    LawAuditDetail save(@RequestBody LawAuditDetail lawAuditDetail);
}