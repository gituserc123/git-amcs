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

import cn.hutool.extra.pinyin.PinyinUtil;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.starter.service.util.DbUtil;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.aier.cloud.biz.service.biz.amcs.fin.service.FinDictTypeService;
import com.aier.cloud.biz.service.biz.amcs.fin.dao.FinDictTypeMapper;
import com.aier.cloud.biz.service.biz.amcs.fin.entity.FinDictType;

import java.util.List;
import java.util.Map;

/**
 * FinDictType
 * 财务字典表
 * @author 爱尔眼科
 * @since 2023-03-13 11:28:02
 */
@Service
@Transactional(rollbackFor=Throwable.class)
public class FinDictTypeServiceImpl extends ServiceImpl<FinDictTypeMapper, FinDictType> implements FinDictTypeService {


    @Override
    public List<Map> getList(String typeCode, String filter) {
        EntityWrapper ew = new EntityWrapper<>();
        ew.setSqlSelect(DbUtil.camel("id", "value_code", "value_desc","type_desc","using_sign","type_code",
                "first_spell", "orders"));
        ew.eq("type_code", typeCode);


        ew.orderBy("orders,value_code");
        return this.selectMaps(ew);
    }

    @Override
    public Map<String, List> getMoreList(List<String> paraTypes) {
        return null;
    }

    @Override
    public Boolean save(FinDictType finDictType) {
        finDictType.setModifer(UserContext.getUserId());
        if(StringUtil.isNullOrEmpty(finDictType.getFirstSpell())){
            String py= PinyinUtil.getFirstLetter(finDictType.getValueDesc(),"").toUpperCase();
            finDictType.setFirstSpell(StringUtil.length(py)>40?py.substring(0,40):py);
        }


        // 校验个字段组合的唯一性
        Wrapper<FinDictType> wrapper = new EntityWrapper<FinDictType>();
        wrapper
                .eq("type_code", finDictType.getTypeCode())
                .eq("value_code", finDictType.getValueCode());
                if(!ObjectUtils.isEmpty(finDictType.getHospId())){
                    wrapper.eq("hosp_id", finDictType.getHospId());
                }

        FinDictType existDictType = this.selectOne(wrapper);
        if (existDictType != null && !existDictType.getId().equals(finDictType.getId())) {
            // 如果存在相同的记录，且不是当前记录，则说明不满足唯一性要求，直接返回false
            throw new BizException("存在相同的类型和编码");
        }

        return this.insertOrUpdate(finDictType);

    }

    @Override
    public Boolean del(Long id) {
        return null;
    }

    @Override
    public List getType(Long hospId){
        return this.baseMapper.getType(hospId);
    }
}

