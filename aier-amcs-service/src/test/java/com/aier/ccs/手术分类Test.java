package com.aier.ccs;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

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
import com.google.common.collect.Lists;


/**
 * 把其他指标的拓展类型改为指标分类
 * @author xiaokek
 * @since 2022年2月24日 下午4:02:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AierAmcsService.class)
@WebAppConfiguration
public class 手术分类Test {
	@Resource
	JdbcHelper db;
	
	@Resource RuleDetailService rs;

	@Test
	public void shuaId() throws Exception {
		db.update("update t_amcs_aps_metric w set w.rule_ext_type = 'D' where w.metric_code = '10085'");
		db.update("delete from t_amcs_aps_rule_detail w where w.rule_id in (" + 
				"select a.id from t_amcs_aps_rule a where a.metric_code = '10085')");
		
		String metricSql = "select  * from amcs.t_amcs_aps_metric w where w.metric_code >= '10085' and w.metric_code <= '10085'";
		List<Metric> metircs = db.queryBeanList(metricSql, Metric.class);
		String rulesSql = "select  * from amcs.t_amcs_aps_rule w where w.metric_code >= '10085' and w.metric_code <= '10085'";
		List<Rule> rules = db.queryBeanList(rulesSql, Rule.class);
		
		String sql = "select  * from amcs.t_amcs_aps_rule_detail w where w.rule_id = 1508612236509610085";
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
			
			for(RuleDetail ruleDetail : baseRds){
				RuleDetail target = new RuleDetail();
				BeanUtils.copyProperties(ruleDetail, target);
				target.setId(null);
				target.setHospId(rule.getHospId());
				target.setRuleId(rule.getId());
				target.setItemScore(score);
				news.add(target);
			}
		}
		
		rs.insertBatch(news);
		
	}

}