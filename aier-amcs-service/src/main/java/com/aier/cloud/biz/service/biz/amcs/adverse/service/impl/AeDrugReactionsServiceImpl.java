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
package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;

import com.aier.cloud.api.amcs.condition.DrugReactionsCondition;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeDrugReactionsService;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeDrugReactionsMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDrugReactions;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * AeDrugReactions
 * 药品不良反应事件报告
 * @author 爱尔眼科
 * @since 2023-07-25 08:33:47
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AeDrugReactionsServiceImpl extends ServiceImpl<AeDrugReactionsMapper, AeDrugReactions> implements AeDrugReactionsService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, DrugReactionsCondition cond) {
        return this.baseMapper.getAll(page,cond);
    }

    @Override
    public Map<String, Object> save(AeDrugReactions aeDrugReactions)  throws Exception{
        Map<String, Object> result = Maps.newHashMap();

        if(null == aeDrugReactions.getId()){
            aeDrugReactions.setCreateDate(new Date());
            aeDrugReactions.setCreator(UserContext.getUserId());
            aeDrugReactions.setCreatorName(UserContext.getUserName());
        }
        aeDrugReactions.setModifyDate(new Date());
        aeDrugReactions.setModifer(UserContext.getUserId());
        this.insertOrUpdate(aeDrugReactions);
        result.put("msg","成功");
        result.put("code","200");
        result.put("drugReactionId",aeDrugReactions.getId());

        return result;
    }

    @Override
    public List<Map<String, Object>> getAllEntity(DrugReactionsCondition cond) {
        return this.baseMapper.getAllEntity(cond);
    }
}

