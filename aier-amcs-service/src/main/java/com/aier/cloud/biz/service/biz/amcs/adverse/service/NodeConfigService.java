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
package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import com.aier.cloud.api.amcs.adverse.enums.NodeEnum;
import com.aier.cloud.api.amcs.adverse.condition.EventConfigCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.EventConfig;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import io.undertow.server.handlers.proxy.mod_cluster.NodeConfig;

import java.util.List;
import java.util.Map;

/**
 * NodeConfig
 * 不良事件节点配置表
 * @author 爱尔眼科
 * @since 2022-07-08 09:35:14
 */
public interface NodeConfigService extends IService<EventConfig>{
	Boolean save(EventConfig eventConfig);
	NodeEnum NextNode(NodeEnum nodeEnum, EventConfig eventConfig);
	List<Map<String, Object>> getAll(Page<Map<String, Object>> page, EventConfigCondition cond);
	EventConfig getNodeConfigByCode(String eventCode);
}