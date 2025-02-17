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

import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsMainMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMain;
import com.aier.cloud.center.common.context.UserContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsSingleDiseaseService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsSingleDiseaseMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsSingleDisease;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsSingleDisease
 * 单病种付费表
 * @author 爱尔眼科
 * @since 2023-04-04 16:59:21
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsSingleDiseaseServiceImpl extends ServiceImpl<FinInsSingleDiseaseMapper, FinInsSingleDisease> implements FinInsSingleDiseaseService {

    @Resource
    private FinInsMainMapper finInsMainMapper;

    @Override
    public Boolean save(FinInsSingleDisease finInsSingleDisease) {
        if (finInsSingleDisease.getId() == null) {
            FinInsSingleDisease finInsSingleDiseaseQuery = this.getByMainId(finInsSingleDisease.getMainId());
            if(finInsSingleDiseaseQuery != null){
                finInsSingleDisease.setId(finInsSingleDiseaseQuery.getId());
                finInsSingleDisease.setModifer(UserContext.getUserId());
            }else{
                finInsSingleDisease.setCreator(UserContext.getUserId());
                finInsSingleDisease.setCreateDate(new Date());
                finInsSingleDisease.setModifer(UserContext.getUserId());
            }
        }else{
            finInsSingleDisease.setModifer(UserContext.getUserId());
        }
        // 解决页面传不过来monthId的bug
        if(finInsSingleDisease.getMonthId()==null){
            FinInsMain finInsMain = finInsMainMapper.selectById(finInsSingleDisease.getMainId());
            finInsSingleDisease.setMonthId(finInsMain.getMonthId());
        }
        return this.insertOrUpdate(finInsSingleDisease);
    }

    @Override
    public FinInsSingleDisease getByMainId(Long mainId) {
        return this.baseMapper.getByMainId(mainId);
    }

    @Override
    public List<Map<String, Object>> lastList() {
        return this.baseMapper.lastList(UserContext.getTenantId());
    }
}

