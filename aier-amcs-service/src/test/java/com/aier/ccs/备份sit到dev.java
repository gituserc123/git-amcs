package com.aier.ccs;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aier.cloud.AierAmcsService;
import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.basic.common.util.ToolUtils;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.basic.starter.service.config.log.SqlLogConfig;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.DcgDictType;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Metric;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.Rule;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetail;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetailCalc;
import com.aier.cloud.biz.service.biz.amcs.aps.entity.RuleDetailRange;
import com.aier.cloud.biz.service.biz.amcs.aps.service.RuleDetailService;
import com.alibaba.druid.pool.DruidDataSource;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * 把其他指标的拓展类型改为指标分类
 * @author xiaokek
 * @since 2022年2月24日 下午4:02:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AierAmcsService.class)
@WebAppConfiguration
public class 备份sit到dev {
	private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	@Resource
	JdbcHelper db;
	@Resource
	DruidDataSource ds;
	
	@Resource RuleDetailService rs;

	@Test
	public void shuaId() throws Exception {
//		JdbcHelper av = 麻醉方式Test2.ahisViewJdbcHelper(ds);
		JdbcHelper sit = sit(ds);
		JdbcHelper dev = dev(ds);
		JdbcHelper devBase = devBase(ds);
		export(DcgDictType.class, "select * from base.t_dcg_dict_type w where w.type_code like '%aps_config%'",sit);
		export(Metric.class, "select * from amcs.t_amcs_aps_metric",db);
		export(Rule.class, "select * from amcs.t_amcs_aps_rule",db);
		export(RuleDetail.class, "select * from amcs.t_amcs_aps_rule_detail w where exists (select 1 from amcs.t_amcs_aps_rule a where a.id = w.rule_id)",db);
		export(RuleDetailRange.class, "select * from amcs.t_amcs_aps_rule_detail_range w where exists (select 1 from amcs.t_amcs_aps_rule_detail a where a.id = w.detail_id)",db);
		export(RuleDetailCalc.class, "select * from amcs.t_amcs_aps_rule_detail_calc w where exists (select 1 from amcs.t_amcs_aps_rule_detail a where a.id = w.rule_detail_id)",db);

		//dev.insert(gson.fromJson(select(DcgDictType.class), new TypeToken<List<DcgDictType>>() {}.getType()));

		devBase.update("delete from base.t_dcg_dict_type w where w.type_code like '%aps_config%'");
		dev.update("truncate table t_amcs_aps_metric");
		dev.update("truncate table t_amcs_aps_rule");
		dev.update("truncate table t_amcs_aps_rule_detail");
		dev.update("truncate table t_amcs_aps_rule_detail_range");
		dev.update("truncate table t_amcs_aps_rule_detail_calc");
		
//		dev.insertWithId(((List)gson.fromJson(select(Metric.class), new TypeToken<List<Metric>>() {}.getType())).toArray());
//		dev.insertWithId(((List)gson.fromJson(select(Rule.class), new TypeToken<List<Rule>>() {}.getType())).toArray());
//		dev.insertWithId(((List)gson.fromJson(select(RuleDetail.class), new TypeToken<List<RuleDetail>>() {}.getType())).toArray());
//		dev.insertWithId(((List)gson.fromJson(select(RuleDetailRange.class), new TypeToken<List<RuleDetailRange>>() {}.getType())).toArray());

		Object[] result = ((List)gson.fromJson(select(Metric.class), new TypeToken<List<Metric>>() {}.getType())).toArray();
		for(Object object : result){
			dev.insertWithId(object);
		}

		result = ((List)gson.fromJson(select(DcgDictType.class), new TypeToken<List<DcgDictType>>() {}.getType())).toArray();
		for(Object object : result){
			devBase.insertWithId(object);
		}
		
		result = ((List)gson.fromJson(select(Rule.class), new TypeToken<List<Rule>>() {}.getType())).toArray();
		for(Object object : result){
			dev.insertWithId(object);
		}
		result = ((List)gson.fromJson(select(RuleDetail.class), new TypeToken<List<RuleDetail>>() {}.getType())).toArray();
		for(Object object : result){
			dev.insertWithId(object);
		}
		result = ((List)gson.fromJson(select(RuleDetailRange.class), new TypeToken<List<RuleDetailRange>>() {}.getType())).toArray();
		for(Object object : result){
			dev.insertWithId(object);
		}
		
		result = ((List)gson.fromJson(select(RuleDetailCalc.class), new TypeToken<List<RuleDetailCalc>>() {}.getType())).toArray();
		for(Object object : result){
			dev.insertWithId(object);
		}
	}
	

	private String select(Class table) throws IOException {
		return FileUtils.readFileToString(new File("D:\\xiaokek\\rubbish\\"+table.getSimpleName()+".json"));
	}


	private void export(Class table, String sql,JdbcHelper av) throws IOException {
		List result = av.queryBeanList(sql, table);
		FileUtils.write(new File("D:\\xiaokek\\rubbish\\"+table.getSimpleName()+".json"), JsonUtil.toJson(result), false);
	}

	private JdbcHelper dev(DruidDataSource ds) {
		DruidDataSource view = new DruidDataSource();
		view = ds.cloneDruidDataSource();
		view.setUrl("jdbc:oracle:thin:@do-ora.acloud.com:1521:ahis");
		view.setUsername("amcs");
		view.setPassword("amcs_dev");
//		view.setProxyFilters(Arrays.asList(SqlLogConfig.logFilter()));
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

	private JdbcHelper devBase(DruidDataSource ds) {
		DruidDataSource view = new DruidDataSource();
		view = ds.cloneDruidDataSource();
		view.setUrl("jdbc:oracle:thin:@do-ora.acloud.com:1521:ahis");
		view.setUsername("base");
		view.setPassword("base_dev");
//		view.setProxyFilters(Arrays.asList(SqlLogConfig.logFilter()));
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


	public static JdbcHelper sit(DruidDataSource ds) {
		
		DruidDataSource view = new DruidDataSource();
		view = ds.cloneDruidDataSource();
		view.setUrl("jdbc:oracle:thin:@so-ora.acloud.com:1521:ahis");
		view.setUsername("syss");
		view.setPassword("syss_sit");
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