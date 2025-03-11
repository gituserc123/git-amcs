package com.aier.cloud.biz.service.biz.amcs.law.service;

import com.aier.cloud.biz.service.biz.amcs.law.entity.LawNodeInfo;
import com.baomidou.mybatisplus.service.IService;

/**
 * 节点信息表 Service 接口
 *
 * @author 爱尔眼科
 * @since 2025-02-24
 */
public interface LawNodeInfoService extends IService<LawNodeInfo> {
    // 自定义的业务逻辑方法可以写在这里

    LawNodeInfo getNodeByCode(String nodeCode);
}