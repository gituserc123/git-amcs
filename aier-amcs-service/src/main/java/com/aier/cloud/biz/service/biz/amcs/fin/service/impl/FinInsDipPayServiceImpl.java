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
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsDipPayService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsDipPayMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsDipPay;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsDipPay
 * DIP付费表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:54
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsDipPayServiceImpl extends ServiceImpl<FinInsDipPayMapper, FinInsDipPay> implements FinInsDipPayService {

    @Resource
    private FinInsMainMapper finInsMainMapper;

    @Override
    public Boolean save(FinInsDipPay finInsDipPay) {
        if (finInsDipPay.getId() == null) {
            FinInsDipPay finInsDipPayQuery = this.getByMainId(finInsDipPay.getMainId());
            if(finInsDipPayQuery != null){
                finInsDipPay.setId(finInsDipPayQuery.getId());
                finInsDipPay.setModifer(UserContext.getUserId());
            }else{
                finInsDipPay.setCreator(UserContext.getUserId());
                finInsDipPay.setCreateDate(new Date());
                finInsDipPay.setModifer(UserContext.getUserId());
            }
        } else {
            finInsDipPay.setModifer(UserContext.getUserId());
        }
        // 解决页面传不过来monthId的bug
        if(finInsDipPay.getMonthId()==null){
            FinInsMain finInsMain = finInsMainMapper.selectById(finInsDipPay.getMainId());
            finInsDipPay.setMonthId(finInsMain.getMonthId());
        }
        return this.insertOrUpdate(finInsDipPay);
    }

    @Override
    public FinInsDipPay getByMainId(Long mainId) {
        return this.baseMapper.getByMainId(mainId);
    }

    @Override
    public List<Map<String, Object>> lastList() {
        return this.baseMapper.lastList(UserContext.getTenantId());
    }
}

