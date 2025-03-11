package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.api.amcs.law.condition.LawNodeAuthCondition;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawNodeAuth;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * 节点权限表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
public interface LawNodeAuthService extends IService<LawNodeAuth> {
    // 自定义的业务逻辑方法可以写在这里
    List<LawNodeAuth> getAll(Page<Map<String, Object>> page, LawNodeAuthCondition cond);
    List<LawNodeAuth> getLawNodeAuthListByStaffId(Long staffId);

    Object save(LawNodeAuth lawNodeAuth);
}