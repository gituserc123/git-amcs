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
package com.aier.cloud.biz.ui.biz.adverse.feign;

/**
 * Lacrimal
 * 泪道筛查与转诊登记表
 * @author 爱尔眼科
 * @since 2022-04-07 09:56:38
 */

import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.domain.ProvinceRole;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface AdverseEventService {

    @PostMapping(value = "/api/amcs/adverse/province/role/config/getList")
    @ResponseBody
    PageResponse getList(@RequestBody ProvinceRoleCondition m);

    @PostMapping(value = "/api/amcs/adverse/province/role/config/getListAll")
    @ResponseBody
    List<ProvinceRoleConfig> getListAll(@RequestBody ProvinceRoleCondition m);

    @PostMapping("/api/amcs/adverse/province/role/config/save")
    @ResponseBody
    public boolean save(@RequestBody ProvinceRole provinceRole);

    @RequestMapping("/api/amcs/adverse/province/role/config/remove")
    @ResponseBody
    public boolean removeById(@RequestParam("id")Long id);

    @RequestMapping("/api/amcs/adverse/province/role/config/getById")
    @ResponseBody
    public Map<String,Object> getById(@RequestParam("id")Long id);

    @RequestMapping("/api/amcs/adverse/province/role/config/queryByProvinceAndRole")
    @ResponseBody
    public Map<String,Object> queryByProvinceAndRole(@RequestParam("provinceId")Long provinceId,
                                                     @RequestParam("roleId")Long roleId,
                                                     @RequestParam("staffId")Long staffId);

    @RequestMapping("/api/amcs/adverse/province/role/config/queryProvinceRoleByStaffId")
    @ResponseBody
    public List<Map<String,Object>> queryProvinceRoleByStaffId(@RequestParam("staffId")Long staffId);

    @RequestMapping("/api/amcs/adverse/province/role/config/checkProvinceRole")
    @ResponseBody
    public boolean checkProvinceRole(@RequestParam("provinceId")Long provinceId,@RequestParam("roleId") Long roleId);
}
