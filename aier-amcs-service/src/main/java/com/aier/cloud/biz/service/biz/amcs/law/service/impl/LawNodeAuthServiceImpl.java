package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.api.amcs.law.condition.LawNodeAuthCondition;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawNodeAuthMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawNodeAuth;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawNodeAuthService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 节点权限表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2025-02-25
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawNodeAuthServiceImpl extends ServiceImpl<LawNodeAuthMapper, LawNodeAuth> implements LawNodeAuthService {

    @Override
    public List<LawNodeAuth> getAll(Page<Map<String, Object>> page, LawNodeAuthCondition cond) {
        List<LawNodeAuth> retLists = this.baseMapper.getAll(page,cond);
        return retLists;
    }

    @Override
    public List<LawNodeAuth> getLawNodeAuthListByStaffId(Long staffId) {
        EntityWrapper<LawNodeAuth> wrapper = new EntityWrapper<>();
        wrapper.eq("staff_id", staffId);
        return this.selectList(wrapper);
    }

    @Override
    public Object save(LawNodeAuth lawNodeAuth) {
        Map<String, Object> result = Maps.newHashMap();
        if(null == lawNodeAuth.getId()){
            lawNodeAuth.setCreateDate(new Date());
            lawNodeAuth.setCreator(UserContext.getUserId());
        }
        lawNodeAuth.setModifyDate(new Date());
        lawNodeAuth.setModifer(UserContext.getUserId());
        this.insertOrUpdate(lawNodeAuth);
        result.put("msg","成功");
        result.put("code","200");
        return result;
    }
}