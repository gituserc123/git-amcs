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
package com.aier.cloud.biz.service.biz.amcs.adverse.dao;

import com.aier.cloud.basic.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotations.SqlParser;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * 
 * 分项表
 * @author 爱尔眼科
 * @since 2021-04-08 16:06:13
 */


@Mapper
public interface QueryMapper extends BaseMapper<BaseEntity>{

    @SqlParser(filter = true)
    @Select("select ${queryFeild}  from ${table} where  ${cond} ")
    List<Map<String, Object>> queryByCond(@Param("table") String table, @Param("queryFeild") String queryFeild,@Param("cond") String cond);

}
