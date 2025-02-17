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
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsProjectPayService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsProjectPayMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsProjectPay;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsProjectPay
 * 项目付费表
 * @author 爱尔眼科
 * @since 2023-04-03 10:12:19
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsProjectPayServiceImpl extends ServiceImpl<FinInsProjectPayMapper, FinInsProjectPay> implements FinInsProjectPayService {

    @Resource
    private FinInsMainMapper finInsMainMapper;

    @Override
    public Boolean save(FinInsProjectPay finInsProjectPay) {
        if (finInsProjectPay.getId() == null) {
            FinInsProjectPay finInsProjectPayQuery = this.getByMainId(finInsProjectPay.getMainId());
            if(finInsProjectPayQuery != null){
                finInsProjectPay.setId(finInsProjectPayQuery.getId());
                finInsProjectPay.setModifer(UserContext.getUserId());
            }else{
                finInsProjectPay.setCreator(UserContext.getUserId());
                finInsProjectPay.setCreateDate(new Date());
                finInsProjectPay.setModifer(UserContext.getUserId());
            }
        }else{
            finInsProjectPay.setModifer(UserContext.getUserId());
        }
        // 解决页面传不过来monthId的bug
        if(finInsProjectPay.getMonthId()==null){
            FinInsMain finInsMain = finInsMainMapper.selectById(finInsProjectPay.getMainId());
            finInsProjectPay.setMonthId(finInsMain.getMonthId());
        }
        return this.insertOrUpdate(finInsProjectPay);
    }

    @Override
    public FinInsProjectPay getByMainId(Long mainId) {
        return this.baseMapper.getByMainId(mainId);
    }

    @Override
    public List<Map<String, Object>> lastList() {
        return this.baseMapper.lastList(UserContext.getTenantId());
    }
}

