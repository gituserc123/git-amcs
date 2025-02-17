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

import com.aier.cloud.api.amcs.fin.condition.FinInsCommentCondition;
import com.aier.cloud.center.common.context.UserContext;
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
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinInsCommentService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsCommentMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsComment;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * FinInsComment
 * 沟通意见表
 * @author 爱尔眼科
 * @since 2023-04-27 17:00:17
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsCommentServiceImpl extends ServiceImpl<FinInsCommentMapper, FinInsComment> implements FinInsCommentService {
    @Override
    public List<Map<String, Object>> getCommentByAssociatedId(Long associatedId) {
        return this.baseMapper.getCommentByAssociatedId(associatedId);
    }

    @Override
    public Boolean save(FinInsComment finInsComment) {
        if (finInsComment.getId() == null) {
            finInsComment.setCreator(UserContext.getUserId());
            finInsComment.setCreateDate(new Date());
            finInsComment.setModifer(UserContext.getUserId());
        } else {
            finInsComment.setModifer(UserContext.getUserId());
        }
        return this.insertOrUpdate(finInsComment);
    }
}

