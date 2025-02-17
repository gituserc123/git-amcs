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
 * FinHospSetting
 * 财务医院设置
 * @author 爱尔眼科
 * @since 2023-03-22 14:23:52
 */


import com.aier.cloud.api.amcs.fin.condition.FinHospSettingCondition;
import com.aier.cloud.api.amcs.fin.domain.FinHospSetting;
import com.aier.cloud.api.amcs.fin.domain.FinInsMain;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface FinHospSettingService{

    @PostMapping(value = "/api/service/biz/amcs/fin/finHospSetting/getFinHospSetting")
    @ResponseBody
    Map<String,Object> getFinHospSetting(@RequestParam("id") Long id);

    @PostMapping(value="/api/service/biz/amcs/fin/finHospSetting/getList")
    @ResponseBody
    List<Map<String,Object>> getFinSettingList(FinHospSettingCondition cond);

    @PostMapping(value="/api/service/biz/amcs/fin/finHospSetting/save")
    @ResponseBody
    Boolean save(@RequestBody FinHospSetting finHospSetting);
}
