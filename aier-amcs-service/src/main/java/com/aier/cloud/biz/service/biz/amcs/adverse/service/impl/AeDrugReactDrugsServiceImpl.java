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

import com.aier.cloud.api.amcs.condition.DrugReactDrugsCondition;
import com.aier.cloud.center.common.context.UserContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeDrugReactDrugsService;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeDrugReactDrugsMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDrugReactDrugs;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * AeDrugReactDrugs
 * 药品不良反应事件药物信息表
 * @author 爱尔眼科
 * @since 2023-07-25 08:33:47
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class AeDrugReactDrugsServiceImpl extends ServiceImpl<AeDrugReactDrugsMapper, AeDrugReactDrugs> implements AeDrugReactDrugsService {

    @Override
    public List<Map<String,Object>> getAllEntity(DrugReactDrugsCondition cond) {
        return this.baseMapper.getAllEntity(cond);
    }

    @Override
    public boolean save(AeDrugReactDrugs aeDrugReactDrugs){
        try{
            if(null == aeDrugReactDrugs.getId()){
                aeDrugReactDrugs.setCreateDate(new Date());
                aeDrugReactDrugs.setCreator(UserContext.getUserId());
            }
            aeDrugReactDrugs.setModifyDate(new Date());
            aeDrugReactDrugs.setModifer(UserContext.getUserId());
            this.insertOrUpdate(aeDrugReactDrugs);
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Integer delete(Long id) {
        return this.baseMapper.deleteById(id);
    }

}

