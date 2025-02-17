package com.aier.cloud.biz.service.biz.amcs.fin.service.impl;


import com.aier.cloud.api.amcs.fin.condition.FinHospInsuranceCheckCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinHospInsuranceCheckMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospInsuranceCheck;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinHospInsuranceCheckService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 医院医保检查统计表 服务实现类
 * </p>
 *
 * @author Aier
 * @since 2025-01-09 09:24:33
 */
@Service
public class FinHospInsuranceCheckServiceImpl extends ServiceImpl<FinHospInsuranceCheckMapper, FinHospInsuranceCheck> implements FinHospInsuranceCheckService {

    @Override
    public List<FinHospInsuranceCheck> getAll(Page<Map<String, Object>> page, FinHospInsuranceCheckCondition cond) {
        List<FinHospInsuranceCheck> retLists = this.baseMapper.getAll(page,cond);
        return retLists;
    }

    @Override
    public List<FinHospInsuranceCheck> getAllEntity(FinHospInsuranceCheckCondition cond) {
        return this.baseMapper.getAllEntity(cond);
    }

    @Override
    public Map<String, Object> save(FinHospInsuranceCheck finHospInsuranceCheck){
        Map<String, Object> result = Maps.newHashMap();
        try{
            if(null == finHospInsuranceCheck.getId()){
                finHospInsuranceCheck.setModifer(UserContext.getUserId());
                finHospInsuranceCheck.setCreator(UserContext.getUserId());
            }
            this.insertOrUpdate(finHospInsuranceCheck);
        }catch (Exception e){
            e.printStackTrace();
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            result.put("msg","保存失败:" + sw);
            result.put("code","500");
        }
        result.put("msg","保存成功！");
        result.put("code","200");
        return result;
    }
}
