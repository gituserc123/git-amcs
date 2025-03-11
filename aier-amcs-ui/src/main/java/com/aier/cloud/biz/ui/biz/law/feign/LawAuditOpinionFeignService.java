package com.aier.cloud.biz.ui.biz.law.feign;


import com.aier.cloud.api.amcs.law.condition.LawAuditOpinionCondition;
import com.aier.cloud.api.amcs.law.domain.LawAuditDetail;
import com.aier.cloud.api.amcs.law.domain.LawAuditOpinion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 审核意见表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@FeignClient(name = "aier-amcs-service")
public interface LawAuditOpinionFeignService {
    // 自定义的业务逻辑方法可以写在这里

    @RequestMapping(value = "/api/service/biz/amcs/law/auditOpinion/save",method = {RequestMethod.POST })
    @ResponseBody
    Object save(@RequestBody LawAuditOpinion lawAuditOpinion);

    @RequestMapping(value = "/api/service/biz/amcs/law/auditOpinion/getListByDetailIds",method = { RequestMethod.POST })
    @ResponseBody
    List<LawAuditOpinion> getListByDetailIds(@RequestBody LawAuditOpinionCondition cond);
}