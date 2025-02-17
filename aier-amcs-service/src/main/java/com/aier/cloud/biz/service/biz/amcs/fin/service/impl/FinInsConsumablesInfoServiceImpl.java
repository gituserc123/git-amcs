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

import com.aier.cloud.api.amcs.fin.condition.FinInsConsumablesInfoCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsMainMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMain;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsConsumablesInfoService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsConsumablesInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsConsumablesInfo;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsConsumablesInfo
 * 耗材附属信息表
 * @author 爱尔眼科
 * @since 2023-04-20 08:36:26
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsConsumablesInfoServiceImpl extends ServiceImpl<FinInsConsumablesInfoMapper, FinInsConsumablesInfo> implements FinInsConsumablesInfoService {

    @Resource
    private FinInsMainMapper finInsMainMapper;

    @Override
    public Boolean save(FinInsConsumablesInfo finInsConsumablesInfo) {
        if (finInsConsumablesInfo.getId() == null) {
            finInsConsumablesInfo.setCreator(UserContext.getUserId());
            finInsConsumablesInfo.setCreateDate(new Date());
            finInsConsumablesInfo.setModifer(UserContext.getUserId());
        } else {
            finInsConsumablesInfo.setModifer(UserContext.getUserId());
        }
        // 解决页面传不过来monthId的bug
        if(finInsConsumablesInfo.getMonthId()==null){
            FinInsMain finInsMain = finInsMainMapper.selectById(finInsConsumablesInfo.getMainId());
            finInsConsumablesInfo.setMonthId(finInsMain.getMonthId());
        }
        return this.insertOrUpdate(finInsConsumablesInfo);
    }

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, FinInsConsumablesInfoCondition finInsConsumablesInfoCondition) {
        return this.baseMapper.getAll(page, finInsConsumablesInfoCondition);
    }

    @Override
    public List<Map<String, Object>> lastList(Integer consumablesType) {
        return this.baseMapper.lastList(UserContext.getTenantId(),consumablesType);
    }
}

