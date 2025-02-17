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

/**
 * Rule
 * 人资绩效计分规则表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface RuleService{

    @PostMapping(value = "/api/amcs/aps/rule/getRule")
    @ResponseBody
    Map<String,Object> getRule(@RequestParam("id") Long id,@RequestParam(value="hospId",required =false) Long hospId);
    

    @PostMapping(value = "/api/amcs/aps/rule/updateRule")
    @ResponseBody
	void updateRule(@RequestBody Map m);

    @PostMapping(value = "/api/amcs/aps/rule/getRuleDetail")
    @ResponseBody
	Object getRuleDetail(@RequestBody Map m);


    @PostMapping(value = "/api/amcs/aps/rule/updateRuleDetail")
    @ResponseBody
	String updateRuleDetail(@RequestBody Map m);


    @PostMapping(value = "/api/amcs/aps/rule/getGroupRuleList")
    @ResponseBody
	Object getGroupRuleList(@RequestBody Map m);

    @PostMapping(value = "/api/amcs/aps/rule/refreshRuleFromGroup")
    @ResponseBody
    List<Map> refreshRuleFromGroup(@RequestBody Map m);

}
