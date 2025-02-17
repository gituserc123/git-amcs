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
package com.aier.cloud.biz.ui.biz.amcs.cat.service;

/**
 * Lacrimal
 * 泪道筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */

import com.aier.cloud.api.amcs.condition.LacrimalCondition;
import com.aier.cloud.api.amcs.domain.Lacrimal;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface LacrimalService{

    @PostMapping(value = "/api/amcs/cat/lacrimal/getLacrimal")
    @ResponseBody
    Map<String,Object> getLacrimal(@RequestParam("id") Long id);


    @RequestMapping(value = "/api/amcs/cat/lacrimal/getAll", method = RequestMethod.POST)
    PageResponse<Map<String, Object>> getAll(@RequestBody LacrimalCondition lacrimalCondition);

    @RequestMapping(value = "/api/amcs/cat/lacrimal/save", method = RequestMethod.POST)
    boolean saveLacrimal(@RequestBody Lacrimal lacrimal);

    @RequestMapping(value = "/api/amcs/cat/lacrimal/getAllEntityList")
    @ResponseBody
    List<Lacrimal> getAllEntityList(@RequestBody LacrimalCondition lacrimalCondition);

    @RequestMapping(value = "/api/amcs/cat/lacrimal/delLacrimal")
    @ResponseBody
    Integer delLacrimal(@RequestBody LacrimalCondition lacrimalCondition);

}
