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
package com.aier.cloud.biz.service.biz.amcs.adverse.service;

import java.util.Map;

import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeUnplReoperation;
import com.baomidou.mybatisplus.service.IService;
import org.apache.ibatis.annotations.Param;

/**
 * AeUnplReoperation
 * 非计划再手术上报表单
 * @author 爱尔眼科
 * @since 2024-10-29 16:36:56
 */
public interface AeUnplReoperationService extends IService<AeUnplReoperation>{

    Integer setIolTypeNull(@Param("id") Long id);

    Map<String, Object> getStaffInfo(@Param("inpNumber") String inpNumber);
}