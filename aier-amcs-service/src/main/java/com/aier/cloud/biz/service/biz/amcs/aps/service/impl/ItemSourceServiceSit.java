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
package com.aier.cloud.biz.service.biz.amcs.aps.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.aier.cloud.api.amcs.enums.ScoreExtendTypeEnum;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.common.util.ToolUtils;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.basic.starter.service.config.log.SqlLogConfig;
import com.aier.cloud.basic.starter.service.util.DbUtil;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.sys.feign.BasedService;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.hutool.extra.spring.SpringUtil;

/**
 * RuleDetail
 * 人资绩效计分规则详情表
 * @author xiaokek
 * @since 2022-03-08 11:58:36
 */
@Service
public class ItemSourceServiceSit implements InitializingBean{

	@Resource JdbcHelper db;
	@Resource BasedService basedService;
	
	public List<Map> queryItems(Rule rule, Metric metric){
		
		List<Map<String,Object>> items = Lists.newArrayList();
		//D("D", "手术分类"), 
		if(ScoreExtendTypeEnum.D.is(metric.getRuleExtType())) {
			Map m = Maps.newHashMap();
			m.put("rows", 10000);
			items = basedService.getDcgMeritsLevel(m).getRows();
			
			//    A("A", "收费项目"), 
		}else if(ScoreExtendTypeEnum.A.is(metric.getRuleExtType())) {
			StringBuilder sql = new StringBuilder();
			sql.append("select id, group_drugs_code as item_code, drugs_desc as item_name,finance_code, ");
			sql.append("                (select b.finance_type_name ");
			sql.append("                   from base.t_dcg_finance_type b ");
			sql.append("                  where b.finance_type_code = w.finance_code ");
			sql.append("                    and rownum = 1) as finance_code_value ");
			sql.append("  from base.t_dcg_drugs w ");
			sql.append(" where using_sign = 1 ");
			sql.append("   and finance_code = :fcode ");
			sql.append("union all ");
			sql.append("select id, group_material_code as item_code, material_name as item_name,finance_code, ");
			sql.append("                (select b.finance_type_name ");
			sql.append("                   from base.t_dcg_finance_type b ");
			sql.append("                  where b.finance_type_code = w.finance_code ");
			sql.append("                    and rownum = 1) as finance_code_value ");
			sql.append("  from base.t_dcg_material w ");
			sql.append(" where using_sign = 1 ");
			sql.append("   and finance_code = :fcode ");
			sql.append("union all ");
			sql.append("select id, group_service_code as item_code, group_service_name as item_name,finance_code, ");
			sql.append("                (select b.finance_type_name ");
			sql.append("                   from base.t_dcg_finance_type b ");
			sql.append("                  where b.finance_type_code = w.finance_code ");
			sql.append("                    and rownum = 1) as finance_code_value ");
			sql.append("  from base.t_dcg_medical_service w ");
			sql.append(" where using_sign = 1 ");
			sql.append("   and group_version = 1 ");
			sql.append("   and finance_code = :fcode ");

			Map param = Maps.newHashMap();
			param.put("fcode", metric.getFinanceCode());
			items = prod.queryMapList(sql.toString(), param);
		}else if(ScoreExtendTypeEnum.I.is(metric.getRuleExtType())) {
			StringBuilder sql = new StringBuilder();
			sql.append("select distinct w.brand item_code, ");
			sql.append("                w.finance_code, ");
			sql.append("                (select b.finance_type_name ");
			sql.append("                   from base.t_dcg_finance_type b ");
			sql.append("                  where b.finance_type_code = w.finance_code ");
			sql.append("                    and rownum = 1) as finance_code_value, ");
			sql.append("                (select distinct a.material_name ");
			sql.append("                   from base.t_dcg_material a ");
			sql.append("                  where a.brand = w.brand ");
			sql.append("                    and rownum = 1) item_name ");
			sql.append("  from base.t_dcg_material w ");
			sql.append(" where w.finance_code like 'CL010101%' ");
			sql.append("   and w.using_sign = 1 ");
			items = prod.queryMapList(sql.toString());
		}
		return (List)items;
	}
	
	private static JdbcHelper prod = null;

	public List<Map> getHospItems(Set<String> itemCodes) {
		
		if(CollectionUtils.isEmpty(itemCodes)) {
			return Collections.EMPTY_LIST;
		}
		if(itemCodes.size() >999) {
			throw BizException.error("编码过多，请联系肖可修改程序！");
		}
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id, ");
		sql.append("       drugs_code as item_code, ");
		sql.append("       drugs_name as item_name, ");
		sql.append("       price, ");
		sql.append("       drugs_code as true_code ");
		sql.append("  from base.t_dch_drugs ");
		sql.append(" where group_drugs_code in (:item_codes) ");
		sql.append("   and hosp_id = :hosp_id ");
		sql.append("union all ");
		sql.append("select id, ");
		sql.append("       material_code as item_code, ");
		sql.append("       material_name as item_name, ");
		sql.append("       price, ");
		sql.append("       material_code as true_code ");
		sql.append("  from base.t_dch_material ");
		sql.append(" where group_material_code in (:item_codes) ");
		sql.append("   and hosp_id = :hosp_id ");
		sql.append("union all ");
		sql.append("select id, ");
		sql.append("       service_code as item_code, ");
		sql.append("       service_name as item_name, ");
		sql.append("       price, ");
		sql.append("       nvl(substr(group_service_code, ");
		sql.append("                  instr(group_service_code, '_', 1, 1) + 1, ");
		sql.append("                  instr(group_service_code, '_', -1, 1) - ");
		sql.append("                  instr(group_service_code, '_', 1, 1) - 1), ");
		sql.append("           substr(group_service_code, ");
		sql.append("                  instr(group_service_code, '_', 1, 1) + 1, ");
		sql.append("                  length(group_service_code) - ");
		sql.append("                  instr(group_service_code, '_', 1, 1))) as true_code ");
		sql.append("  from base.t_dch_medical_service ");
		sql.append(" where nvl(substr(group_service_code, ");
		sql.append("                  instr(group_service_code, '_', 1, 1) + 1, ");
		sql.append("                  instr(group_service_code, '_', -1, 1) - ");
		sql.append("                  instr(group_service_code, '_', 1, 1) - 1), ");
		sql.append("           substr(group_service_code, ");
		sql.append("                  instr(group_service_code, '_', 1, 1) + 1, ");
		sql.append("                  length(group_service_code) - ");
		sql.append("                  instr(group_service_code, '_', 1, 1))) ");
		sql.append("       in (:item_codes) ");
		sql.append("   and hosp_id = :hosp_id ");
		
		Map param = Maps.newHashMap();
		param.put("hosp_id", UserContext.getTenantId());
		param.put("item_codes", itemCodes);

		return prod.queryMapList(sql.toString(), param);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		try {
			DruidDataSource ds = SpringUtil.getBean(DruidDataSource.class);
			
			DruidDataSource view = new DruidDataSource();
			view = ds.cloneDruidDataSource();
			view.setUrl("jdbc:oracle:thin:@10.0.18.135:1521:ahisdm");
			view.setUsername("aier_view");
			view.setPassword("Ai_wi83v");
			view.setProxyFilters(Arrays.asList(SqlLogConfig.logFilter()));
			//view.setConnectionProperties("");
			JdbcTemplate jdbc = new JdbcTemplate(view) {
				@Override
				protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
					return new ColumnMapRowMapper() {
						@Override
						protected String getColumnKey(String columnName) {
	//						if(false) {
	//							return ToolUtils.camel(columnName);
	//						}
							return columnName;
						}
					};
					
				};
			};
			prod = new JdbcHelper(new NamedParameterJdbcTemplate(jdbc));
		}catch (Exception e) {
		}
	}

	public List<Map> getGroupItems(Rule rule, Metric metric) {
		return null;
	}

	
	/**
	 * 下拉搜索编码
	 * @param hospId
	 * @param string
	 * @return
	 */
	public List queryHospJ(Long hospId, String key) {
		
		StringBuilder sql = new StringBuilder();
		sql.append("select a.id as \"id\", ");
		sql.append("       a.price as \"price\", ");
		sql.append("       a.drugs_code as \"serviceCode\", ");
		sql.append("       a.drugs_name as \"serviceName\", ");
		sql.append("       a.finance_code as \"financeCode\", ");
		sql.append("       (select w.finance_type_name ");
		sql.append("          from base.t_dcg_finance_type w ");
		sql.append("         where w.finance_type_code = a.finance_code) as \"financeCodeValue\" ");
		sql.append("  from base.t_dch_drugs a ");
		sql.append(" where a.hosp_id = :hospId ");
		sql.append("   and a.drugs_name like :key ");
		sql.append("union all ");
		sql.append("select a.id as \"id\",  ");
		sql.append("       a.price as \"price\",  ");
		sql.append("       a.service_code as \"serviceCode\", ");
		sql.append("       a.service_name as \"serviceName\", ");
		sql.append("       a.finance_code as \"financeCode\",  ");
		sql.append("       (select w.finance_type_name ");
		sql.append("          from base.t_dcg_finance_type w ");
		sql.append("         where w.finance_type_code = a.finance_code) as \"financeCodeValue\" ");
		sql.append("  from base.t_dch_medical_service a ");
		sql.append(" where a.hosp_id = :hospId ");
		sql.append("   and a.service_name like :key ");
		sql.append("union all ");
		sql.append("select a.id as \"id\",  ");
		sql.append("       a.price as \"price\",  ");
		sql.append("       a.material_code as \"serviceCode\", ");
		sql.append("       a.material_name as \"serviceName\", ");
		sql.append("       a.finance_code as \"financeCode\", ");
		sql.append("       (select w.finance_type_name ");
		sql.append("          from base.t_dcg_finance_type w ");
		sql.append("         where w.finance_type_code = a.finance_code) as \"financeCodeValue\" ");
		sql.append("  from base.t_dch_material a ");
		sql.append(" where a.hosp_id = :hospId ");
		sql.append("   and a.material_name like :key ");
		
		Map param = Maps.newHashMap();
		param.put("hospId", hospId);
		param.put("key", "%"+key+"%");

		return prod.queryMapList(sql.toString(), param);
	}
}

