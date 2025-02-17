package com.aier.ccs;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aier.cloud.AierAmcsService;
import com.aier.cloud.basic.common.util.ToolUtils;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.basic.starter.service.config.log.SqlLogConfig;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleDetailService;
import com.aier.cloud.center.common.context.UserContext;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;

import cn.hutool.json.JSONUtil;

/**
 * 
 * select * from base.t_dcg_material w where w.finance_code = 'FW0803'; select *
 * from base.t_dcg_medical_service w where w.finance_code = 'FW0803' and
 * w.group_version = 1 and w.using_sign = 1; select * from base.t_dcg_drugs w
 * where w.finance_code = 'FW0803'; select * from base.t_dcg_finance_type w
 * where w.finance_type_name like '%常规检查%'
 * 
 * @author xiaokek
 * @since 2022年2月24日 下午4:02:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AierAmcsService.class)
@WebAppConfiguration
public class 麻醉方式Test2 {
	@Resource
	JdbcHelper db;
	@Resource
	DruidDataSource ds;

	@Resource
	RuleDetailService rs;

	@Test
	public void shuaId() throws Exception {
		String metricCode = "10051";
		JdbcHelper av = ahisViewJdbcHelper(ds);

		List<Map<String, Object>> mazuis = av.queryMapList("select * from base.t_dcg_code_dict w where w.type = 'anesthesia_mode'");
		List<Map<String, Object>> icds = av.queryMapList("select * from base.t_dcg_code_dict w where w.type = 'performance_diseases'");
		
		
		//db.update("update t_amcs_aps_metric w set w.rule_ext_type = 'D' where w.metric_code = '10085'");
		db.update("delete from t_amcs_aps_rule_detail w where w.rule_id in (" + "select a.id from t_amcs_aps_rule a where a.metric_code = '"+metricCode+"')");

		String metricSql = "select  * from amcs.t_amcs_aps_metric w where w.metric_code = ?";
		List<Metric> metircs = db.queryBeanList(metricSql, Metric.class,metricCode);
		String rulesSql = "select  * from amcs.t_amcs_aps_rule w where w.metric_code = ?";
		List<Rule> rules = db.queryBeanList(rulesSql, Rule.class,metricCode);

		String sql = "select  * from amcs.t_amcs_aps_rule_detail w where w.rule_id = 1508612236509610031";
		List<RuleDetail> baseRds = db.queryBeanList(sql, RuleDetail.class);

		List<RuleDetail> news = Lists.newArrayList();
		for(Rule rule : rules){
			BigDecimal score = BigDecimal.valueOf(0);
			for(Metric m : metircs){
				if(m.getMetricCode().equalsIgnoreCase(rule.getMetricCode())){
					score = m.getDefaultPoint();
					break;
				}
			}

			for(Map<String, Object> rd : mazuis){
				String mazuiValueCode = MapUtils.getString(rd, "valueCode");
				String mazuiValueDesc = MapUtils.getString(rd, "valueDesc");
				if("1".equals(mazuiValueCode) || "13".equals(mazuiValueCode)) {
					score = BigDecimal.valueOf(10);
				}else {
					score = BigDecimal.valueOf(2);
				}
				
				if("1".equals(mazuiValueCode) || "13".equals(mazuiValueCode) 
						|| "3".equals(mazuiValueCode) || "35".equals(mazuiValueCode)
						 || "36".equals(mazuiValueCode)) {
					
					for(Map<String, Object> icd : icds){
						String icdValueCode = MapUtils.getString(icd, "valueCode");
						if("10".equals(icdValueCode)) {
							continue;
						}
						RuleDetail hd = new RuleDetail();
						hd.setId(null);
						hd.setHospId(rule.getHospId());
						hd.setRuleId(rule.getId());
						hd.setItemScore(score);
						hd.setFinanceCodeValue(null);
						hd.setRuleItemType("D");
						hd.setItemId(null);
						hd.setItemCode(mazuiValueCode + "_" + icdValueCode);
						hd.setItemName(mazuiValueDesc + "_" + MapUtils.getString(icd, "valueDesc"));
						hd.setItemLevel(null);
						hd.setItemIcd(icdValueCode);
						hd.setItemScore(score);
						hd.setModifer(1L);
						hd.setFinanceCode(null);
						hd.setUsingSign(1);
						news.add(hd);
					}
				}else {
					RuleDetail hd = new RuleDetail();
					hd.setId(null);
					hd.setHospId(rule.getHospId());
					hd.setRuleId(rule.getId());
					hd.setItemScore(score);
					hd.setFinanceCodeValue(null);
					hd.setRuleItemType("D");
					hd.setItemId(null);
					hd.setItemCode(mazuiValueCode + "_all");
					hd.setItemName(mazuiValueDesc + "_全病种");
					hd.setItemLevel(null);
					hd.setItemIcd("all");
					hd.setItemScore(score);
					hd.setModifer(1L);
					hd.setFinanceCode(null);
					hd.setUsingSign(1);
					news.add(hd);
				}
				
				
			}
		}

//		System.out.println(news.size());
		rs.insertBatch(news);

	}

	public static JdbcHelper ahisViewJdbcHelper(DruidDataSource ds) {
		
		DruidDataSource view = new DruidDataSource();
		view = ds.cloneDruidDataSource();
		view.setUrl("jdbc:oracle:thin:@10.0.18.135:1521:ahisdm");
		view.setUsername("ahis_view");
		view.setPassword("Ahe80#Ic1q");
		view.setProxyFilters(Arrays.asList(SqlLogConfig.logFilter()));
		//view.setConnectionProperties("");
		JdbcTemplate jdbc = new JdbcTemplate(view) {
			@Override
			protected RowMapper<Map<String, Object>> getColumnMapRowMapper() {
				return new ColumnMapRowMapper() {
					@Override
					protected String getColumnKey(String columnName) {
						return ToolUtils.camel(columnName);
					}
				};
				
			};
		};
		return new JdbcHelper(new NamedParameterJdbcTemplate(jdbc));
	}

}