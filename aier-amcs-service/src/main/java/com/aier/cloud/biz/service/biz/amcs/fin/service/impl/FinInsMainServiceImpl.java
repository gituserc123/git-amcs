/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
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

import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMainCondition;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.biz.service.biz.amcs.fin.service.*;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsMainMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsMain
 * 医保政策主表
 * @author 爱尔眼科
 * @since 2023-03-28 15:47:55
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsMainServiceImpl extends ServiceImpl<FinInsMainMapper, FinInsMain> implements FinInsMainService {
    @Autowired
    FinHospSettingService finHospSettingService;

    @Autowired
    private FinInsProjectPayService projectPayService;
    @Autowired
    private FinInsAdvanceTotalService totalService;
    @Autowired
    private FinInsSingleDiseaseService singleDiseaseService;
    @Autowired
    private FinInsDipPayService dipAnalysisService;
    @Autowired
    private FinInsDrgPayService drgAnalysisService;
    @Autowired
    private FinInsPertimePayService pertimePayService;

    @Override
    public Boolean newLines(Long hospId, Long monthId) {
        FinHospSettingCondition cond=new FinHospSettingCondition();
        cond.setHospId(hospId);
        //只初始化没被停用的项目
        cond.setUsingSign(1);
        List<Map<String, Object>> list=finHospSettingService.getList(cond);

        try {
            list.forEach(i -> {
                FinInsMain finInsMain=new FinInsMain();
                finInsMain.setCreator(UserContext.getUserId());
                finInsMain.setModifer(UserContext.getUserId());
                finInsMain.setCreateDate(new Date());
                finInsMain.setModifyDate(new Date());
                finInsMain.setMonthId(monthId);
                finInsMain.setHospId(hospId);
                finInsMain.setPoolingArea((String)i.get("poolingArea"));
                finInsMain.setInsuranceType(new BigDecimal(i.get("insuranceType").toString()).intValue());
                this.insert(finInsMain);
            });
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<Map<String, Object>> getList(FinInsMainCondition cond) {
        return this.baseMapper.getList(cond);
    }

    @Override
    public Boolean save(FinInsMain finInsMain) {
        finInsMain.setModifyDate(new Date());
        finInsMain.setModifer(UserContext.getUserId());
        return this.updateById(finInsMain);
    }

    @Override
    public Boolean canSubmit(Long id) {
        FinInsMain finInsMain=this.selectById(id);
        String hsp=finInsMain.getHospSettlementPolicy();
        if(ObjectUtils.isEmpty(hsp)){
            throw new BizException(finInsMain.getPoolingArea()+"主表没有提交");
        }
        List<String> list = JSON.parseArray(hsp, String.class);

        for(String s:list){
            switch(s){
                case "1":
                    if (ObjectUtils.isEmpty(projectPayService.getByMainId(id))){
                        throw new BizException(finInsMain.getPoolingArea()+"按项目付费表没有提交！");
                    }
                    break;
                case "2":
                    if (ObjectUtils.isEmpty(totalService.getByMainId(id))){
                        throw new BizException(finInsMain.getPoolingArea()+"总额付费表没有提交！");
                    }
                    break;
                case "3":
                    if (ObjectUtils.isEmpty(singleDiseaseService.getByMainId(id))){
                        throw new BizException(finInsMain.getPoolingArea()+"单病种付费表没有提交！");
                    }
                    break;
                case "4":
                    if (ObjectUtils.isEmpty(dipAnalysisService.getByMainId(id))){
                        throw new BizException(finInsMain.getPoolingArea()+"dip表没有提交！");
                    }
                    break;
                case "5":
                    if (ObjectUtils.isEmpty(drgAnalysisService.getByMainId(id))){
                        throw new BizException(finInsMain.getPoolingArea()+"drg没有提交！");
                    }
                    break;
                case "6":
                    if (ObjectUtils.isEmpty(pertimePayService.getByMainId(id))){
                        throw new BizException(finInsMain.getPoolingArea()+"按人头付费没有提交！");
                    }
                    break;
            }
        }


        return true;
    }

    @Override
    public List<Map<String, Object>> queryListByCond(Page<Map<String, Object>> page, FinInsMainCondition cond) {
        List<Map<String, Object>> basicList = this.baseMapper.queryListByCond(page, cond);
        return basicList;
    }

    @Override
    public List<Map<String, Object>> queryListByCond(FinInsMainCondition cond) {
        return this.baseMapper.queryListByCond(cond);
    }

    @Override
    public List<Map<String, Object>> lastList() {
        return this.baseMapper.lastList(UserContext.getTenantId());
    }
}

