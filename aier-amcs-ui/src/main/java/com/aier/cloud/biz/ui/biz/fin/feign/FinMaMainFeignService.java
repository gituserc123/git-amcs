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
package com.aier.cloud.biz.ui.biz.fin.feign;

import com.aier.cloud.api.amcs.fin.condition.FinMaMainCondition;
import com.aier.cloud.api.amcs.fin.domain.FinMaMain;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * FinMaDictInfo
 * 医保管理分析字典表
 * @author 爱尔眼科
 * @since 2024-02-22 14:32:33
 */
@FeignClient(name = "aier-amcs-service")
public interface FinMaMainFeignService {

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/save")
    @ResponseBody
    Map<String, String> save(@RequestBody FinMaMain finMaMain);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/getAllRecord")
    @ResponseBody
    List<FinMaMain> getAllRecord(@RequestBody FinMaMainCondition cond);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/saveDipStr")
    @ResponseBody
    Map<String, Object> saveDipStr(@RequestBody String strData);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/saveDrgStr")
    @ResponseBody
    Map<String, Object> saveDrgStr(@RequestBody String strData);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/getAll")
    @ResponseBody
    PageResponse<Map<String, Object>> getAll(@RequestBody FinMaMainCondition cond);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/getStatDipRecord")
    @ResponseBody
    List<Map<String, Object>> getStatDipRecord(@RequestBody FinMaMainCondition cond);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/getStatDrgRecord")
    @ResponseBody
    List<Map<String, Object>> getStatDrgRecord(@RequestBody FinMaMainCondition cond);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaMain/getStatCount")
    @ResponseBody
    Integer getStatCount(@RequestBody FinMaMainCondition cond);
}