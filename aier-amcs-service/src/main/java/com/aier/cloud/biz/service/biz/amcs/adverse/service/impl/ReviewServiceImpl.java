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

import com.aier.cloud.api.amcs.adverse.condition.ReviewCondition;
import com.aier.cloud.center.common.context.UserContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.ReviewService;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.ReviewMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.Review;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Review
 * 
 * @author 爱尔眼科
 * @since 2022-08-01 09:45:25
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class ReviewServiceImpl extends ServiceImpl<ReviewMapper, Review> implements ReviewService {
    @Override
    public List<Map<String, Object>> getAll(ReviewCondition cond) {
        return this.baseMapper.getAll(cond);
    }

    @Override
    public Boolean save(Review review) {
        if(review.getId()==null){
            review.setCreator(UserContext.getUserId());
            review.setCreateDate(new Date());
            review.setModifer(UserContext.getUserId());
            review.setHospId(UserContext.getTenantId());
        }else{
            review.setModifer(UserContext.getUserId());
        }
        return this.insertOrUpdate(review);
    }
}

