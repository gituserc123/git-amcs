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

import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import  com.aier.cloud.biz.service.biz.amcs.fin.service.FinHospSettingService;
import  com.aier.cloud.biz.service.biz.amcs.fin.dao.FinHospSettingMapper;
import  com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinHospSetting
 * 财务医院设置
 * @author 爱尔眼科
 * @since 2023-03-22 14:23:52
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinHospSettingServiceImpl extends ServiceImpl<FinHospSettingMapper, FinHospSetting> implements FinHospSettingService {
    @Override
    public List<Map<String,Object>> getList( FinHospSettingCondition cond) {
        return this.baseMapper.getList(cond);
    }


    @Override
    public Boolean save(FinHospSetting finHospSetting) {
        if(finHospSetting.getId()==null){
            finHospSetting.setModifer(UserContext.getUserId());
            finHospSetting.setHospId(UserContext.getTenantId());
        }else{
            finHospSetting.setModifer(UserContext.getUserId());
        }
        return this.insertOrUpdate(finHospSetting);
    }
}

