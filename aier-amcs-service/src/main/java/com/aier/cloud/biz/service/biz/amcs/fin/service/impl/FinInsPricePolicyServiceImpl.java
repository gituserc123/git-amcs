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

import com.aier.cloud.api.amcs.fin.condition.FinInsPricePolicyCondition;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsPricePolicyService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsPricePolicyMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsPricePolicy;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsPricePolicy
 * 价格政策表
 * @author 爱尔眼科
 * @since 2023-08-07 15:09:57
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsPricePolicyServiceImpl extends ServiceImpl<FinInsPricePolicyMapper, FinInsPricePolicy> implements FinInsPricePolicyService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, FinInsPricePolicyCondition cond) {
        return this.baseMapper.getAll(page,cond);
    }

    @Override
    public Map<String, Object> save(FinInsPricePolicy finInsPricePolicy) throws Exception {
        Map<String, Object> result = Maps.newHashMap();
        if(null == finInsPricePolicy.getId()){
            finInsPricePolicy.setCreateDate(new Date());
            finInsPricePolicy.setCreator(UserContext.getUserId());
        }
        finInsPricePolicy.setModifyDate(new Date());
        finInsPricePolicy.setModifer(UserContext.getUserId());
        this.insertOrUpdate(finInsPricePolicy);
        result.put("msg","成功");
        result.put("code","200");

        return result;
    }
}

