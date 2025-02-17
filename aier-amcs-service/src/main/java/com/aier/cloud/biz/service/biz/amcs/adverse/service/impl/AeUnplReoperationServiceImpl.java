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
package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;

import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.common.exception.BizAssert;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeBasicInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeBasicInfo;
import com.aier.cloud.biz.service.biz.sys.feign.StaffService;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeUnplReoperationService;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeUnplReoperationMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeUnplReoperation;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * AeUnplReoperation
 * 非计划再手术上报表单
 * @author 爱尔眼科
 * @since 2024-10-29 16:36:56
 */
@Service
public class AeUnplReoperationServiceImpl extends ServiceImpl<AeUnplReoperationMapper, AeUnplReoperation> implements AeUnplReoperationService {

    @Autowired
    AeBasicInfoMapper aeBasicInfoMapper;

    @Autowired
    StaffService staffService;

    @Override
    public Integer setIolTypeNull(Long id) {
        AeUnplReoperation aeUnplReoperation = new AeUnplReoperation();
        aeUnplReoperation.setIolTypeCode(null);
        aeUnplReoperation.setIolTypeName(null);
        return this.baseMapper.updateById(aeUnplReoperation);
    }

    @Override
    public Map<String, Object> getStaffInfo(String inpNumber) {
        Map<String, Object> rs = Maps.newHashMap();
        try{
            BizAssert.notNull(UserContext.getTenantId(), BizException.WARN, "租户信息为空！");
            Long hospId = UserContext.getTenantId();
            //通过医院ID、住院号获取患者基本信息
            EntityWrapper<AeBasicInfo> ew = new EntityWrapper<>();
            ew.eq("hosp_id", hospId);
            ew.eq("patient_no", inpNumber);
            ew.eq("event_code", "109");
            ew.orderBy("create_date", false);
            List<AeBasicInfo> basicInfoList = this.aeBasicInfoMapper.selectList(ew);

            if (CollectionUtils.isEmpty(basicInfoList)) {
                return null;
            }
            AeBasicInfo basicInfo = basicInfoList.get(0);
            Long staffId = basicInfo.getStaffId();
            if(!ObjectUtils.isEmpty(staffId)){
                Staff staff = Optional.ofNullable(staffService.getById(staffId))
                        .orElseThrow(() -> new RuntimeException("Staff not found"));
                rs.put("staff_id", staffId);
                rs.put("staff_code", staff.getCode());
                rs.put("event_name", basicInfo.getEventName());
                rs.put("patient_name", basicInfo.getPatientName());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return rs;
    }
}

