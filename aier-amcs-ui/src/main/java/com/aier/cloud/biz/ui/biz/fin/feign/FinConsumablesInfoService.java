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
package com.aier.cloud.biz.ui.biz.fin.feign;

/**
 * FinInsConsumablesInfo
 * 耗材附属信息表
 * @author 爱尔眼科
 * @since 2023-04-20 08:36:26
 */

import com.aier.cloud.api.amcs.fin.condition.FinInsConsumablesInfoCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsDipAnalysisCondition;
import com.aier.cloud.api.amcs.fin.domain.FinInsConsumablesInfo;
import com.aier.cloud.api.amcs.fin.domain.FinInsDipAnalysis;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface FinConsumablesInfoService{

    @PostMapping(value = "/api/service/biz/amcs/fin/finInsConsumablesInfo/getFinInsConsumablesInfo")
    @ResponseBody
    Map<String,Object> getFinInsConsumablesInfo(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finInsConsumablesInfo/save")
    Boolean save(FinInsConsumablesInfo finInsConsumablesInfo);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finInsConsumablesInfo/getAll", method = RequestMethod.POST)
    PageResponse<Map<String, Object>> getAll(@RequestBody FinInsConsumablesInfoCondition finInsConsumablesInfoCondition);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsConsumablesInfo/lastList")
    @ResponseBody
    List<Map<String,Object>> lastList(@RequestParam("consumablesType") Integer consumablesType);

}
