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
package com.aier.cloud.biz.service.biz.amcs.adverse.service.impl;


import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


import cn.hutool.extra.pinyin.PinyinUtil;
import com.aier.cloud.center.common.context.UserContext;
import io.netty.util.internal.StringUtil;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeDictTypeService;
import com.alibaba.nacos.common.utils.CollectionUtils;
import com.aier.cloud.basic.starter.service.util.DbUtil;
//import com.aier.cloud.service.util.DbUtil;
//import com.aier.cloud.basic.core.base.util.DbUtil;
import com.aier.cloud.biz.service.biz.amcs.adverse.dao.AeDictTypeMapper;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.AeDictType;

/**
 * AeDictType
 *
 * @author 爱尔眼科
 * @since 2022-07-14 17:37:37
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class AeDictTypeServiceImpl extends ServiceImpl<AeDictTypeMapper, AeDictType> implements AeDictTypeService {

    @Resource
    AeDictTypeMapper dictTypeMapper;

    private final static String SPLIT_SYMBOL = ",";

    @Override
    public List<Map> getList(String typeCode, String filter) {
        EntityWrapper ew = new EntityWrapper<>();
        ew.setSqlSelect(DbUtil.camel("id", "value_code", "value_desc","type_desc","using_sign","type_code",
                "first_spell", "filter", "orders", "orders", "combobox_filter"));

        ew.eq("type_code", typeCode);
        if (!ObjectUtils.isEmpty(filter)) {
            //需要满足不管理亚专科的二级字典
            Integer splitIndex = filter.indexOf(SPLIT_SYMBOL);
            if (splitIndex > 0) {
                StringBuffer filterSB = new StringBuffer(filter);
                filterSB.replace(0, splitIndex, "0");
                ew.andNew().eq("filter", filter).or().eq("filter", filterSB.toString());
            } else {
                ew.andNew().eq("filter", filter);
            }

            //ew.eq("filter", filter).or().eq("filter", "999");
        }

        ew.orderBy("orders,value_code");
        return dictTypeMapper.selectMaps(ew);
    }

    @Override
    public List<Map> getListByFilters(String typeCode, String filters) {
        EntityWrapper ew = new EntityWrapper<>();
        ew.setSqlSelect(DbUtil.camel("id", "value_code", "value_desc","type_desc","using_sign","type_code",
                "first_spell", "filter", "orders", "orders", "combobox_filter"));

        ew.eq("type_code", typeCode);
        if (!ObjectUtils.isEmpty(filters)) {
            //需要满足不管理亚专科的二级字典
            Integer splitIndex = filters.indexOf(SPLIT_SYMBOL);
            if (splitIndex > 0) {
                String[] filterArr = filters.split(SPLIT_SYMBOL);
                //此处拼接后效果如(filter=1 or filter = 2)
                ew.andNew();
                boolean first = true;
                for (String filter : filterArr) {
                    if (first) {
                        ew.eq("filter", filter);
                        first = false;
                    } else {
                        ew.or().eq("filter", filter);
                    }
                }
            } else {
                ew.andNew().eq("filter", filters);
            }
        }

        ew.orderBy("orders,value_code");
        return dictTypeMapper.selectMaps(ew);
    }


    @Override
    public Map<String, List> getMoreList(List<String> paraTypes) {
        if (CollectionUtils.isNotEmpty(paraTypes)) {
            Map<String, List> ls = Maps.newHashMap();
            for (String p : paraTypes) {
                ls.put(p, this.getList(p, ""));
            }
            return ls;
        } else {
            return Collections.EMPTY_MAP;
        }
    }

    @Override
    public Boolean save(AeDictType dictType) {


        dictType.setModifer(UserContext.getUserId());
        if(StringUtil.isNullOrEmpty(dictType.getFirstSpell())){
            String py=PinyinUtil.getFirstLetter(dictType.getValueDesc(),"").toUpperCase();
            dictType.setFirstSpell(StringUtil.length(py)>40?py.substring(0,40):py);
        }

        return this.insertOrUpdate(dictType);
    }

    @Override
    public Boolean del(Long id) {
        return this.deleteById(id);
    }
}

