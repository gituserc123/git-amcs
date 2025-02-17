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
package com.aier.cloud.biz.service.biz.amcs.cat.service.impl;

import com.aier.cloud.api.amcs.condition.LacrimalCondition;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.cat.service.LacrimalService;
import com.aier.cloud.biz.service.biz.amcs.cat.dao.LacrimalMapper;
import com.aier.cloud.biz.service.biz.amcs.cat.entity.Lacrimal;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Lacrimal
 * 泪道筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class LacrimalServiceImpl extends ServiceImpl<LacrimalMapper, Lacrimal> implements LacrimalService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, LacrimalCondition cond) {
        cond.setHospId(UserContext.getUser().getTenantId());
        List<Map<String, Object>> list = this.baseMapper.getAll(page,cond);
        return list;
    }

    @Override
    public boolean save(Lacrimal lacrimal) {
        Boolean b = null;
        if(!Optional.ofNullable(lacrimal.getId()).isPresent()){
            lacrimal.setHospId(UserContext.getUser().getTenantId());
        }
        lacrimal.setModifer(UserContext.getUserId());
        lacrimal.setModifyDate(new Date());
        b=this.insertOrUpdate(lacrimal);
        return b;
    }

    @Override
    public Integer delLacrimal(LacrimalCondition cond) {
        return this.baseMapper.deleteById(cond.getId());
    }

    @Override
    public List<Lacrimal> getAll(LacrimalCondition cond) {
        cond.setHospId(UserContext.getUser().getTenantId());
        return this.baseMapper.getAll(cond);
    }
}

