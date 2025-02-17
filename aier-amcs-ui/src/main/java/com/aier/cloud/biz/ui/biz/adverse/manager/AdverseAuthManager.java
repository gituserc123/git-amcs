package com.aier.cloud.biz.ui.biz.adverse.manager;

import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * TODO
 *
 * @author chendongdong
 * @date 2022/5/11
 */
@Component
public class AdverseAuthManager {
    @Autowired
    private AdverseEventService adverseEventService;

    /**
     * 判断省区是否存在对应角色
     *
     * @return
     */
    public boolean checkProvinceRole(Long provinceId, Long roleId) {
        return adverseEventService.checkProvinceRole(provinceId,roleId);
    }

    /**
     * 判断省区是否存在对应角色
     *
     * @return
     */
    public boolean checkUserProvinceRole(Long staffId, Long provinceId, Long roleId) {
        Object obj = adverseEventService.queryByProvinceAndRole(provinceId, roleId, staffId);
        if (Objects.isNull(obj)) {
            return false;
        }
        return true;
    }

}
