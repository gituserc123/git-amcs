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

import com.aier.cloud.api.amcs.fin.condition.FinMaDictInfoCondition;
import com.aier.cloud.api.amcs.fin.domain.FinMaDictInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
public interface FinMaDictInfoFeignService{

    @RequestMapping(value ="/api/service/biz/amcs/fin/finMaDictInfo/getMaDictList")
    @ResponseBody
    List<FinMaDictInfo> getMaDictList(@RequestBody FinMaDictInfoCondition finMaDictInfoCondition);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finMaDictInfo/save")
    @ResponseBody
    Map<String, Object> save(@RequestBody FinMaDictInfo finMaDictInfo);
}