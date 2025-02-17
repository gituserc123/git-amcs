package com.aier.ccs;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aier.cloud.AierAmcsService;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleDetailService;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.collect.Lists;


/**
 * 把其他指标的拓展类型改为指标分类
 * @author xiaokek
 * @since 2022年2月24日 下午4:02:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AierAmcsService.class)
@WebAppConfiguration
public class 手术分类加未手术Test {
	@Resource
	JdbcHelper db;
	@Resource
	DruidDataSource ds;
	
	@Resource RuleDetailService rs;

	@Test
	public void shuaId() throws Exception {
		JdbcHelper av = 麻醉方式Test2.ahisViewJdbcHelper(ds);
		List<Map<String, Object>> icds = av.queryMapList("select * from base.t_dcg_code_dict w where w.type = 'performance_diseases'");

		String metricSql = "select  * from amcs.t_amcs_aps_metric w where w.metric_code >= '10039' and w.metric_code <= '10047'";
		List<Metric> metircs = db.queryBeanList(metricSql, Metric.class);
		String rulesSql = "select  * from amcs.t_amcs_aps_rule w where w.metric_code >= '10039' and w.metric_code <= '10047'";
		List<Rule> rules = db.queryBeanList(rulesSql, Rule.class);
		
		String sql = "select  * from amcs.t_amcs_aps_rule_detail w where w.rule_id = 1508612236509610039";
		List<RuleDetail> baseRds = db.queryBeanList(sql, RuleDetail.class);
		
		List<RuleDetail> news = Lists.newArrayList();
		for(Rule rule : rules){
			BigDecimal score = BigDecimal.valueOf(0);
			for(Metric m : metircs){
				if(m.getMetricCode().equalsIgnoreCase(rule.getMetricCode())) {
					score = m.getDefaultPoint();
					break;
				}
			}
			
			for(Map icd : icds){
				//10        眼肌屈光不要
				if(!"10".equals(MapUtils.getString(icd, "valueCode"))){
					RuleDetail hd = new RuleDetail();
					hd.setId(null);
					hd.setHospId(rule.getHospId());
					hd.setRuleId(rule.getId());
					hd.setItemScore(score);
					hd.setFinanceCodeValue(null);
					hd.setRuleItemType("D");
					hd.setItemId(null);
					hd.setItemCode("_" + MapUtils.getString(icd, "valueCode"));
					hd.setItemName("未手术_" + MapUtils.getString(icd, "valueDesc"));
					hd.setItemLevel("0");
					hd.setItemIcd(MapUtils.getString(icd, "valueDesc"));
					hd.setItemScore(score);
					hd.setModifer(1L);
					hd.setFinanceCode(null);
					hd.setUsingSign(1);
					news.add(hd);
				}
			}
		}
		
		rs.insertBatch(news);
		
		long min = news.get(0).getId(),max = min;
		for(RuleDetail n : news){
			if(n.getId() < min) {
				min = n.getId();
			}
			
			if(n.getId() > max) {
				max = n.getId();
			}
		}
		System.out.println(min + "-" + max);
		
	}

}