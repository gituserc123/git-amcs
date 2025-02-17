/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.service.biz.amcs.fin.service.impl;

import com.aier.cloud.api.amcs.fin.condition.FinMaMainCondition;
import com.aier.cloud.api.amcs.utils.TimePeriodUtil;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinMaMainService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinMaMainMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaMain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinMaMain
 * 医保管理分析主表
 * @author 爱尔眼科
 * @since 2024-02-23 14:51:36
 */
@Service
public class FinMaMainServiceImpl extends ServiceImpl<FinMaMainMapper, FinMaMain> implements FinMaMainService {

    @Override
    public List<FinMaMain> getAllRecord(FinMaMainCondition cond){
        return this.baseMapper.getAllRecord(cond);
    }

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, FinMaMainCondition cond) {
        return this.baseMapper.getAll(page,cond);
    }

    @Override
    public List<Map<String, Object>> getStatDipRecord(FinMaMainCondition cond) {
        return this.baseMapper.getStatDipRecord(cond);
    }

    @Override
    public List<Map<String, Object>> getStatDrgRecord(FinMaMainCondition cond) {
        return this.baseMapper.getStatDrgRecord(cond);
    }

    @Override
    public Integer getStatCount(FinMaMainCondition cond) {
        return this.baseMapper.getStatCount(cond);
    }

    @Override
    public Map<String, String> save(FinMaMain finMaMain) {
        Map<String, String> result = Maps.newHashMap();
        try{
            if(finMaMain.getId()==null){
                LocalDateTime now = LocalDateTime.now();
                String curQuarter = TimePeriodUtil.getCurDatePeriod(now);
                finMaMain.setMaYear(now.getYear());
                finMaMain.setMaQuarter(Integer.parseInt(curQuarter.substring(curQuarter.length() - 1)));
                finMaMain.setMaIdentifier(curQuarter+UserContext.getTenantId());
                finMaMain.setHospId(UserContext.getTenantId());
                finMaMain.setCreator(UserContext.getUserId());
                finMaMain.setCreateDate(new Date());
            }
            finMaMain.setModifer(UserContext.getUserId());
            finMaMain.setModifyDate(new Date());
            this.insertOrUpdate(finMaMain);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code","500");
            result.put("msg","保存失败:" + e.getMessage());
            return result;
        }
        result.put("code","200");
        result.put("msg","保存成功！");
        return result;
    }
}

