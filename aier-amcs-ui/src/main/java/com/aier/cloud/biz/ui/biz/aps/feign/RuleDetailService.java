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
 * RuleDetail
 * 人资绩效计分规则详情表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.domain.RefreshResult;

import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface RuleDetailService{

    @PostMapping(value = "/api/amcs/aps/ruleDetail/getRuleDetail")
    @ResponseBody
    Map<String,Object> getRuleDetail(@RequestParam("id") Long id);

    @PostMapping(value = "/api/amcs/aps/ruleDetail/getRuleDetail")
    @ResponseBody
	Map<String, Object> selectById(Long id);

    @PostMapping(value = "/api/amcs/aps/ruleDetail/refreshGroupRule")
    @ResponseBody
	RefreshResult refreshGroupRule(@RequestParam("id") Long id);
    @PostMapping(value = "/api/amcs/aps/ruleDetail/refreshHospRule")
    @ResponseBody
	RefreshResult refreshHospRule(@RequestParam("id") Long id);

}
