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

import com.aier.cloud.api.amcs.fin.condition.PersonCondition;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
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
import com.aier.cloud.biz.service.biz.amcs.fin.service.PersonService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.PersonMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.Person;

import java.util.List;
import java.util.Map;

/**
 * Person
 * 医保系统相关人员表
 * @author 爱尔眼科
 * @since 2023-08-29 15:14:28
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    @Override
    public Boolean save(Person person) {
        if(person.getId()==null){
            Wrapper<Person> wrapper = new EntityWrapper<>();
            wrapper.eq("hosp_id",person.getHospId());
            if(this.selectList(wrapper).size()>0){
                throw new BizException("该医院已经存在人员信息，不能重复添加");
            };
        }
        person.setModifer(UserContext.getUserId());
        return this.insertOrUpdate(person);
    }

    @Override
    public Person getPersonByHospId(Long hospId) {
        Wrapper<Person> wrapper = new EntityWrapper<>();
        wrapper.eq("hosp_id",hospId);
        return this.selectOne(wrapper);
    }

    @Override
    public List<Map<String,Object>> getPersonList(PersonCondition personCondition) {
        return this.baseMapper.getPersonList(personCondition);
    }
}

