package com.aier.cloud.biz.service.biz.amcs.law.service.impl;


import com.aier.cloud.api.amcs.law.condition.LawCivilCaseCondition;
import com.aier.cloud.biz.service.biz.amcs.law.dao.LawCivilCaseMapper;
import com.aier.cloud.biz.service.biz.amcs.law.entity.LawCivilCase;
import com.aier.cloud.biz.service.biz.amcs.law.service.LawCivilCaseService;
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
 * 民事诉讼仲裁案件主表 Service 实现类
 *
 * @author 爱尔眼科
 * @since 2023-10-18
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class LawCivilCaseServiceImpl extends ServiceImpl<LawCivilCaseMapper, LawCivilCase> implements LawCivilCaseService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LawCivilCaseCondition cond) {
        return this.baseMapper.getAll(page, cond);
    }

    @Override
    public List<LawCivilCase> findListByCaseId(Long caseId) {
        return null;
    }

    @Override
    public String findTagsById(Long caseId) {
        return null;
    }

    @Override
    public String findTagDescsById(Long caseId) {
        return null;
    }

    @Override
    public Object save(LawCivilCase lawCivilCase) {
        Map<String, Object> result = Maps.newHashMap();

        if(null == lawCivilCase.getId()){
            lawCivilCase.setCreateDate(new Date());
            lawCivilCase.setCreator(UserContext.getUserId());
            lawCivilCase.setInstId(UserContext.getInstId());
            lawCivilCase.setInstName(UserContext.getInstName());
        }
        lawCivilCase.setModifyDate(new Date());
        lawCivilCase.setModifer(UserContext.getUserId());
        this.insertOrUpdate(lawCivilCase);
        result.put("msg","成功");
        result.put("code","200");
        result.put("civilCaseId",lawCivilCase.getId());

        return result;
    }
}

