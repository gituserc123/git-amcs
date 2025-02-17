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
package com.aier.cloud.biz.service.biz.amcs.fin.service.impl;

import cn.hutool.core.util.StrUtil;
import com.aier.cloud.api.amcs.fin.condition.FinMaDictInfoCondition;
import com.aier.cloud.api.amcs.utils.TimePeriodUtil;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.google.common.collect.Maps;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinMaDictInfoService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinMaDictInfoMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinMaDictInfo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * FinMaDictInfo
 * 医保管理分析字典表
 * @author 爱尔眼科
 * @since 2024-02-22 14:32:33
 */
@Service
public class FinMaDictInfoServiceImpl extends ServiceImpl<FinMaDictInfoMapper, FinMaDictInfo> implements FinMaDictInfoService {

    @Override
    public List<FinMaDictInfo> getMaDictList(FinMaDictInfoCondition finMaDictInfoCondition) {
        Wrapper<FinMaDictInfo> wrapperFinDict = new EntityWrapper<>();
        if(StrUtil.isNotEmpty(finMaDictInfoCondition.getQueryFstLevelVal())){
            wrapperFinDict.eq("fst_level",finMaDictInfoCondition.getQueryFstLevelVal());
        }
        if(StrUtil.isNotEmpty(finMaDictInfoCondition.getQuerySndLevelVal())){
            wrapperFinDict.eq("snd_level",finMaDictInfoCondition.getQuerySndLevelVal());
        }
        if(StrUtil.isNotEmpty(finMaDictInfoCondition.getQueryThdLevelVal())){
            wrapperFinDict.eq("thd_level",finMaDictInfoCondition.getQueryThdLevelVal());
        }
        if(StrUtil.isNotEmpty(finMaDictInfoCondition.getQueryValueDesc())){
            wrapperFinDict.like("value_desc",finMaDictInfoCondition.getQueryValueDesc());
        }
        if(finMaDictInfoCondition.getUsingSign()!=null && finMaDictInfoCondition.getUsingSign().intValue()==1){
            wrapperFinDict.eq("using_sign",finMaDictInfoCondition.getUsingSign());
        }
        List<FinMaDictInfo> list = this.selectList(wrapperFinDict);
        return list;
    }

    @Override
    public Map<String, Object> save(FinMaDictInfo finMaDictInfo) {
        Map<String, Object> result = Maps.newHashMap();
        try{
            if(finMaDictInfo.getId()==null){
                Wrapper<FinMaDictInfo> wrapperFinDict = new EntityWrapper<>();
                wrapperFinDict.eq("fst_level",finMaDictInfo.getFstLevel());
                wrapperFinDict.eq("snd_level",finMaDictInfo.getSndLevel());
                wrapperFinDict.eq("thd_level",finMaDictInfo.getThdLevel());
                wrapperFinDict.like("value_code",finMaDictInfo.getFstLevel()+finMaDictInfo.getSndLevel()+finMaDictInfo.getThdLevel()+"%");
                List<FinMaDictInfo> list = this.selectList(wrapperFinDict);
                if(Objects.nonNull(list) && list.size()>0){
                    finMaDictInfo.setValueCode(finMaDictInfo.getFstLevel()+finMaDictInfo.getSndLevel()+finMaDictInfo.getThdLevel()+StrUtil.padPre(String.valueOf(list.size()+1), 3, "0"));
                }else{
                    finMaDictInfo.setValueCode(finMaDictInfo.getFstLevel()+finMaDictInfo.getSndLevel()+finMaDictInfo.getThdLevel()+"001");
                }
            }
            finMaDictInfo.setModifer(UserContext.getUserId());
            finMaDictInfo.setModifyDate(new Date());
            this.insertOrUpdate(finMaDictInfo);
        }catch (Exception e){
            e.printStackTrace();
            result.put("code","500");
            result.put("msg","保存失败:" + e.getMessage());
            return result;
        }
        result.put("code","200");
        result.put("msg","保存成功！");
        return result;
    }
}

