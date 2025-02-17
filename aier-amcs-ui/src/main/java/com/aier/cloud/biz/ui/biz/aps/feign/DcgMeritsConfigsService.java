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
package com.aier.cloud.biz.ui.biz.aps.feign;

import java.util.List;
import java.util.Map;

/**
 * DcgMeritsConfigs
 * 绩效联合术式配置
 * @author 爱尔眼科
 * @since 2022-02-11 15:44:54
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.amcs.MeritsConfigsCondition;
import com.aier.cloud.basic.api.request.condition.sys.AutoCompleteCondition;
import com.aier.cloud.basic.api.request.domain.amcs.DcgMeritsConfigs;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-service-based")
public interface DcgMeritsConfigsService{

    @PostMapping(value = "/api/aps/dcgMeritsConfigs/getDcgMeritsConfigs")
    DcgMeritsConfigs getDcgMeritsConfigs(@RequestParam("id") Long id);
    
    @PostMapping(value = "/api/aps/dcgMeritsConfigs/page")
    PageResponse<Map<String, Object>> getPage(@RequestBody MeritsConfigsCondition moduleCondition);
    
    @PostMapping(value = "/api/aps/dcgMeritsConfigs/create")
    Boolean create(@RequestBody DcgMeritsConfigs module);
    
    @PostMapping(value = "/api/aps/dcgMeritsConfigs/update")
    Boolean update(@RequestBody DcgMeritsConfigs module);
    
    @PostMapping(value = "/api/aps/dcgMeritsConfigs/meritsLevelQuery")
    List<Map<String,Object>> meritsLevelQuery(@RequestParam("keyword") String keyword);

}
