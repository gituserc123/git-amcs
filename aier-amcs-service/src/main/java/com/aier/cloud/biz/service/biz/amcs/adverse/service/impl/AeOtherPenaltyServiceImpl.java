/*
 * Copyright © 2017-2025 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 研发部 版权所有
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
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeOtherPenaltyService;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeOtherPenaltyMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeOtherPenalty;

/**
 * AeOtherPenalty
 * 行政处罚事件
 * @author 爱尔眼科
 * @since 2023-11-08 16:19:13
 */
@Service
public class AeOtherPenaltyServiceImpl extends ServiceImpl<AeOtherPenaltyMapper, AeOtherPenalty> implements AeOtherPenaltyService {
	
}

