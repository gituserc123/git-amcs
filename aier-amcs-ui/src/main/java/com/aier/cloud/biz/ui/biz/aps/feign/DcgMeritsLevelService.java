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

import java.util.Map;

/**
 * DcgMeritsLevel
 * 绩效手术分级配置
 * @author 爱尔眼科
 * @since 2022-02-07 14:18:00
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.amcs.MeritsLevelCondition;
import com.aier.cloud.basic.api.request.domain.amcs.DcgMeritsLevel;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-service-based")
public interface DcgMeritsLevelService{
    @PostMapping(value = "/api/aps/dcgMeritsLevel/getDcgMeritsLevel")
    DcgMeritsLevel getDcgMeritsLevelById(@RequestParam("id") Long id);
    
    @PostMapping(value = "/api/aps/dcgMeritsLevel/page")
    PageResponse<Map<String, Object>> getPage(@RequestBody MeritsLevelCondition moduleCondition);
    
    @PostMapping(value = "/api/aps/dcgMeritsLevel/create")
    Boolean create(@RequestBody DcgMeritsLevel module);
    
    @PostMapping(value = "/api/aps/dcgMeritsLevel/update")
    Boolean update(@RequestBody DcgMeritsLevel module);
    
    /**
     * 判断是否唯一
     * @param previousCode 之前
     * @param currentCode 当前
     * @return boolean
     */
    @RequestMapping(value = "/api/aps/dcgMeritsLevel/getNameUnique", method = RequestMethod.POST)
    Boolean getMeritsLevelUnique(@RequestParam("previousName") String previousName, @RequestParam("currentName") String currentName);

}
