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

import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.adverse.condition.EventConfigCondition;
import com.aier.cloud.api.amcs.utils.NodeUtil;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import io.undertow.server.handlers.proxy.mod_cluster.NodeConfig;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.NodeConfigService;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.NodeConfigMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.EventConfig;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * NodeConfig
 * 不良事件节点配置表
 * @author 爱尔眼科
 * @since 2022-07-08 09:35:14
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class NodeConfigServiceImpl extends ServiceImpl<NodeConfigMapper, EventConfig> implements NodeConfigService {
    @Override
    public Boolean save(EventConfig eventConfig) {
        if(eventConfig.getId()==null){
            eventConfig.setCreator(UserContext.getUserId());
            eventConfig.setCreateDate(new Date());
            eventConfig.setModifer(UserContext.getUserId());
        }else{
            eventConfig.setModifer(UserContext.getUserId());
        }
        return this.insertOrUpdate(eventConfig);
    }

    @Override
    public NodeEnum NextNode(NodeEnum nodeEnum, EventConfig eventConfig){
        return NodeUtil.NextNode(nodeEnum, eventConfig.getNodeValue().intValue());
    }

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, EventConfigCondition cond)  {
        return this.baseMapper.getAll(cond);

    }

    @Override
    public EventConfig getNodeConfigByCode(String eventCode) {
        Wrapper<EventConfig> eventConfigWrapper=new EntityWrapper<>();
        eventConfigWrapper.eq("event_code",eventCode);
        List<EventConfig> list=this.selectList(eventConfigWrapper);
        if (list.size()>0){
            return list.get(0);
        }
        return null;
    }
}

