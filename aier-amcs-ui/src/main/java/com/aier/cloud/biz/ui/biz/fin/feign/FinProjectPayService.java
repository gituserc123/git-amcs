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
 * FinInsProjectPay
 * 项目付费表
 * @author 爱尔眼科
 * @since 2023-04-03 10:12:19
 */

import com.aier.cloud.api.amcs.fin.domain.FinInsProjectPay;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@FeignClient(name = "aier-amcs-service")
public interface FinProjectPayService{

    @PostMapping(value = "/api/service/biz/amcs/fin/finInsProjectPay/getFinInsProjectPay")
    @ResponseBody
    Map<String,Object> getFinInsProjectPay(@RequestParam("id") Long id);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finInsProjectPay/save")
    Boolean save(FinInsProjectPay finInsProjectPay);

    @RequestMapping(value = "/api/service/biz/amcs/fin/finInsProjectPay/getByMainId")
    @ResponseBody
    FinInsProjectPay getByMainId(@RequestParam("mainId") Long mainId);

    @RequestMapping(value ="/api/service/biz/amcs/fin/finInsProjectPay/lastList")
    @ResponseBody
    List<Map<String,Object>> lastList();
}
