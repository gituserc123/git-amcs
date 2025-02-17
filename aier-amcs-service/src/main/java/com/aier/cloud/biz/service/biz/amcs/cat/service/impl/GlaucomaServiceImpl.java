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
package com.aier.cloud.biz.service.biz.amcs.cat.service.impl;

import com.aier.cloud.api.amcs.condition.GlaucomaCondition;
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
import com.aier.cloud.biz.service.biz.amcs.cat.service.GlaucomaService;
import com.aier.cloud.biz.service.biz.amcs.cat.dao.GlaucomaMapper;
import com.aier.cloud.biz.service.biz.amcs.cat.entity.Glaucoma;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Glaucoma
 * 青光眼筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class GlaucomaServiceImpl extends ServiceImpl<GlaucomaMapper, Glaucoma> implements GlaucomaService {

    @Override
    public List<Map<String, Object>> getAll(Page<Map<String, Object>> page, GlaucomaCondition cond) {
        cond.setHospId(UserContext.getUser().getTenantId());
        List<Map<String, Object>> list = this.baseMapper.getAll(page,cond);
        return list;
    }

    @Override
    public boolean save(Glaucoma glaucoma) {
        Boolean b = null;
        if(!Optional.ofNullable(glaucoma.getId()).isPresent()){
            glaucoma.setHospId(UserContext.getUser().getTenantId());
        }
        glaucoma.setModifer(UserContext.getUserId());
        glaucoma.setModifyDate(new Date());
        b=this.insertOrUpdate(glaucoma);
        return b;
    }

    @Override
    public List<Glaucoma> getAll(GlaucomaCondition cond) {
        cond.setHospId(UserContext.getUser().getTenantId());
        return this.baseMapper.getAll(cond);
    }

    @Override
    public Integer delGlaucoma(GlaucomaCondition cond) {
        return this.baseMapper.deleteById(cond.getId());
    }
}

