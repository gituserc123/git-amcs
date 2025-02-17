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
 * DcgOperGrade
 * 手术项目对应手术分级配置
 * @author 爱尔眼科
 * @since 2022-02-11 15:44:54
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.aier.cloud.basic.api.request.condition.amcs.OperGradeCondition;
import com.aier.cloud.basic.api.request.domain.amcs.DcgOperGrade;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-service-based")
public interface DcgOperGradeService{

    @PostMapping(value = "/api/aps/dcgOperGrade/getDcgOperGrade")
    DcgOperGrade getDcgOperGrade(@RequestParam("id") Long id);
    
    @PostMapping(value = "/api/aps/dcgOperGrade/page")
    PageResponse<Map<String, Object>> getPage(@RequestBody OperGradeCondition moduleCondition);
    
    @PostMapping(value = "/api/aps/dcgOperGrade/create")
    Boolean create(@RequestBody DcgOperGrade module);
    
    @PostMapping(value = "/api/aps/dcgOperGrade/update")
    Boolean update(@RequestBody DcgOperGrade module);
    
    @PostMapping(value = "/api/based/dcgMedicalService/apsQuery")
    List<Map<String,Object>> apsQuery(@RequestParam("keyword") String keyword);

}
