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
public class 收费项目Test {
	@Resource
	JdbcHelper db;
	@Resource
	DruidDataSource ds;

	@Resource
	RuleDetailService rs;

	@Test
	public void shuaId() throws Exception {
		String metricCode = "10108";
		String finance_code = "FW0805";
		String finance_name = "其他特殊检查";
		JdbcHelper av = ahisViewJdbcHelper(ds);
		
		List<Map<String, Object>> codes = av.queryMapList("select *\r\n" + 
				" * from base.t_dcg_medical_service w where w.finance_code = '"+finance_code+"' and\r\n" + 
				" * w.group_version = 1 and w.using_sign = 1");
		
		//db.update("update t_amcs_aps_metric w set w.rule_ext_type = 'D' where w.metric_code = '10085'");
		//db.update("delete from t_amcs_aps_rule_detail w where w.rule_id in (" + "select a.id from t_amcs_aps_rule a where a.metric_code = '10085')");

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

			for(Map<String, Object> rd : codes){
				RuleDetail hd = new RuleDetail();
				hd.setId(null);
				hd.setHospId(rule.getHospId());
				hd.setRuleId(rule.getId());
				hd.setItemScore(score);
				hd.setFinanceCodeValue(finance_name);
				hd.setRuleItemType("D");
				hd.setItemId(MapUtils.getLong(rd, "id"));
				hd.setItemCode(MapUtils.getString(rd, "groupServiceCode"));
				hd.setItemName(MapUtils.getString(rd, "groupServiceName"));
				hd.setItemLevel(MapUtils.getString(rd, ""));
				hd.setItemIcd(MapUtils.getString(rd, ""));
				hd.setItemScore(score);
				hd.setModifer(1L);
				hd.setFinanceCode(finance_code);
				news.add(hd);
			}
		}

		rs.insertBatch(news);

	}

	public JdbcHelper ahisViewJdbcHelper(DruidDataSource ds) {
		
		DruidDataSource view = new DruidDataSource();
		view = ds.cloneDruidDataSource();
		view.setUrl("jdbc:oracle:thin:@10.0.11.78:1521:ahisbak");
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