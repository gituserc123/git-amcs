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
package com.aier.cloud.biz.service.biz.amcs.fin.service;

import com.aier.cloud.api.amcs.fin.condition.PersonCondition;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsSingleDisease;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.Person;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

/**
 * Person
 * 医保系统相关人员表
 * @author 爱尔眼科
 * @since 2023-08-29 15:14:28
 */
public interface PersonService extends IService<Person>{
    Boolean save(Person person);

    Person getPersonByHospId(Long hospId);


    List<Map<String,Object>> getPersonList(PersonCondition personCondition);
}