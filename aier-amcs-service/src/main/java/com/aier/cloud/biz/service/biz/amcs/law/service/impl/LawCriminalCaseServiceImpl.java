package com.aier.cloud.biz.service.biz.amcs.law.service.impl;

import com.aier.cloud.api.amcs.law.condition.LawCriminalCaseCondition;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawCriminalCaseMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCriminalCase;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawCriminalCaseService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 刑事案件登记表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawCriminalCaseServiceImpl extends ServiceImpl<LawCriminalCaseMapper, LawCriminalCase> implements LawCriminalCaseService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawCriminalCaseCondition cond) {
        return this.baseMapper.getAll(page, cond);
    }

    @Override
    public Object save(LawCriminalCase lawCriminalCase) {
        Map<String, Object> result = Maps.newHashMap();

        if(null == lawCriminalCase.getId()){
            lawCriminalCase.setCreateDate(new Date());
            lawCriminalCase.setCreator(UserContext.getUserId());
            lawCriminalCase.setInstId(UserContext.getInstId());
            lawCriminalCase.setInstName(UserContext.getInstName());
        }
        lawCriminalCase.setModifyDate(new Date());
        lawCriminalCase.setModifer(UserContext.getUserId());
        this.insertOrUpdate(lawCriminalCase);
        result.put("msg","成功");
        result.put("code","200");
        result.put("criminalCaseId",lawCriminalCase.getId());

        return result;
    }
}
