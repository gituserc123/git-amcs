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
package com.aier.cloud.biz.service.biz.amcs.fin.service.impl;

import cn.hutool.json.JSONUtil;
import com.aier.cloud.api.amcs.adverse.enums.OperateEnum;
import com.aier.cloud.api.amcs.fin.condition.FinInsMainCondition;
import com.aier.cloud.api.amcs.fin.condition.FinInsMonthCondition;
import com.aier.cloud.api.amcs.fin.enums.FinStatusEnums;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.log.annotion.AierServiceLog;
import com.aier.cloud.basic.log.bean.LogMessage;
import com.aier.cloud.basic.log.utils.LogUtils;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinHospSetting;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMain;
import com.aier.cloud.biz.service.biz.amcs.fin.service.*;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinInsMonthMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinInsMonth;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.alibaba.fastjson.JSON;

import static com.aier.cloud.api.amcs.utils.TimePeriodUtil.getCurDatePeriod;

/**
 * FinInsMonth
 * 医院填报月度主表
 * @author 爱尔眼科
 * @since 2023-03-27 14:39:29
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinInsMonthServiceImpl extends ServiceImpl<FinInsMonthMapper, FinInsMonth> implements FinInsMonthService {

    @Override
    @AierServiceLog(module = "删除月记录",message="{0}=>删除{1}")
    public Boolean deleteByMonthId(String monthId) {
        FinInsMonth finInsMonth=this.selectById(monthId);
        this.baseMapper.deleteAdvance(monthId);
        this.baseMapper.deleteConsum(monthId);
        this.baseMapper.deleteDipA(monthId);
        this.baseMapper.deleteDipP(monthId);
        this.baseMapper.deleteDrgA(monthId);
        this.baseMapper.deleteDrgP(monthId);
        this.baseMapper.deleteMain(monthId);
        this.baseMapper.deletePerP(monthId);
        this.baseMapper.deleteProP(monthId);
        this.baseMapper.deleteSingle(monthId);
        this.baseMapper.deleteMonth(monthId);
        LogMessage message=LogMessage.newWrite().setParams(monthId).setParams(JSONUtil.toJsonStr(finInsMonth));
        LogUtils.putArgs(message);

        return true;
    }

    @Autowired
    private FinInsMainService finInsMainService;
    @Autowired
    private FinHospSettingService finHospSettingService;

    @Override
    public Boolean newLine(Long hospId) {
        FinInsMonth finInsMonth = new FinInsMonth();
        finInsMonth.setCreateDate(new Date());
        finInsMonth.setModifyDate(new Date());
        finInsMonth.setCreator(UserContext.getUserId());
        finInsMonth.setModifer(UserContext.getUserId());
        finInsMonth.setHospId(hospId);
        finInsMonth.setStatus(FinStatusEnums.REPORT.getValue());
        if(!UserContext.getIsHospUser()){
            throw new BizException("只有医院用户能进行上报！");
        }
        /**检查是否有统筹配置 */

        EntityWrapper<FinHospSetting> finHospSettingEntityWrapper=new EntityWrapper<>();
        finHospSettingEntityWrapper.eq("hosp_id", hospId).eq("using_sign",1);
        if(finHospSettingService.selectCount(finHospSettingEntityWrapper)==0){
            throw new BizException("没有有效的统筹区配置，请完成医院统筹区配置后再进行初始化！");
        }
        // 检查该医院本月是否已经插入过数据
        EntityWrapper<FinInsMonth> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("hosp_id", hospId)
//                .eq("status", FinStatusEnums.REPORT.getValue())
                .addFilter("to_char(create_date, 'YYYY-MM') = to_char(sysdate, 'YYYY-MM')");
        int count = this.selectCount(entityWrapper);
        if (count > 0) {
            throw new BizException("本月初始化过数据，不能生成！");
        }

        if (this.insert(finInsMonth)) {
            finInsMainService.newLines(hospId,finInsMonth.getId());
            return true;
        }
        return false;
    }

    @Override
    public List<Map<String, Object>> getList(FinInsMonthCondition cond) {
        return this.baseMapper.getList(cond);
    }

    @Override
    public Boolean canSubmit(Long id) {
        FinInsMainCondition cond=new FinInsMainCondition();
        cond.setMonthId(id);
       List<Map<String,Object>>  list=finInsMainService.getList(cond);

       for (Map m:list){
          Long mainId=  ((BigDecimal)m.get("id")).longValue();
          if(!finInsMainService.canSubmit(mainId)) {
              return false;
          }
       }
       return true;
    }

    @Override
    public Boolean submitToProvince(Long id) {
        if(canSubmit(id)){
            FinInsMonth finInsMonth=this.selectById(id);
            finInsMonth.setStatus(FinStatusEnums.PROVINCE.getValue());
            this.updateById(finInsMonth);
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Boolean submitToGroup(Long id) {

            FinInsMonth finInsMonth=this.selectById(id);
            finInsMonth.setStatus(FinStatusEnums.PROVINCE_AGREE.getValue());
            this.updateById(finInsMonth);
            return true;

    }

    @Override
    public Boolean returnToHos(Long id) {

            FinInsMonth finInsMonth=this.selectById(id);
            finInsMonth.setStatus(FinStatusEnums.RETURN.getValue());
            this.updateById(finInsMonth);
            return true;

    }

    @Override
    public List<Map<String,Object>> unReport(FinInsMonthCondition cond) {
        List<Map<String,Object>> l;
        switch (cond.getUnReportType()){
            case "unReport":
                l=this.baseMapper.unReport(cond);
                break;
            case "reporting":
                l=this.baseMapper.reporting(cond);
                break;
            case "return":
                l=this.baseMapper.returnBack(cond);
                break;
            case "noPrice":
                l=this.baseMapper.noPrice(cond);
                break;
            case "noDrgDip":
                l=this.baseMapper.noDrgDip(cond,getCurDatePeriod(LocalDateTime.now()));
                break;
            default:
                l=this.baseMapper.unReport(cond);
        }
        return l;
    }
}

