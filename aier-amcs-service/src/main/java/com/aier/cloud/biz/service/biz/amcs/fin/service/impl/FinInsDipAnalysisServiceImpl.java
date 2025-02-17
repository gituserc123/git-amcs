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

import cn.hutool.core.date.DateUtil;
import com.aier.cloud.api.amcs.fin.condition.FinInsDipAnalysisCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsDipInfo;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsMainMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMain;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.plugins.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsDipAnalysisService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsDipAnalysisMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsDipAnalysis;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsDipAnalysis
 * DIP盈亏分析表
 * @author 爱尔眼科
 * @since 2023-04-05 09:27:54
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsDipAnalysisServiceImpl extends ServiceImpl<FinInsDipAnalysisMapper, FinInsDipAnalysis> implements FinInsDipAnalysisService {

    @Resource
    private FinInsMainMapper finInsMainMapper;

    @Override
    public Boolean save(FinInsDipAnalysis finInsDipAnalysis) {
        if (finInsDipAnalysis.getId() == null) {
            finInsDipAnalysis.setCreator(UserContext.getUserId());
            finInsDipAnalysis.setCreateDate(new Date());
            finInsDipAnalysis.setModifer(UserContext.getUserId());
        } else {
            finInsDipAnalysis.setModifer(UserContext.getUserId());
        }
        // 解决页面传不过来monthId的bug
        if(finInsDipAnalysis.getMonthId()==null){
            FinInsMain finInsMain = finInsMainMapper.selectById(finInsDipAnalysis.getMainId());
            finInsDipAnalysis.setMonthId(finInsMain.getMonthId());
        }
        return this.insertOrUpdate(finInsDipAnalysis);
    }

    @Override
    public FinInsDipAnalysis getByMainId(Long mainId) {
        return this.baseMapper.getByMainId(mainId);
    }

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, FinInsDipAnalysisCondition finInsDipAnalysisCondition) {
        return this.baseMapper.getAll(page, finInsDipAnalysisCondition);
    }

    @Override
    public List<Map<String, Object>> lastList() {
        // 获取当前日期
        long currentTimeMillis = System.currentTimeMillis();
        Date currentDateStr = DateUtil.date(currentTimeMillis);
        // 获取前2个月的月份历史数据
        String twoMonthsAgoMonth = DateUtil.offsetMonth(currentDateStr, -2).toString("yyyy-MM");
        String paramMonth = twoMonthsAgoMonth + "-01";
        String paramCurEndTime = DateUtil.format(currentDateStr,"yyyy-MM-dd");
        return this.baseMapper.lastList(UserContext.getTenantId(),paramMonth,paramCurEndTime);
    }

    @Override
    public Integer del(FinInsDipAnalysis finInsDipAnalysis) {
        return this.baseMapper.deleteById(finInsDipAnalysis.getId());
    }

    @Override
    public List<FinInsDipAnalysis> lastCommitData() {
        return this.baseMapper.lastCommitData(UserContext.getTenantId());
    }
}

