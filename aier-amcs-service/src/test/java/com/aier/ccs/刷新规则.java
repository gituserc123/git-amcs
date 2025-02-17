package com.aier.ccs;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aier.cloud.AierAmcsService;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.ItemSourceServiceSit;
import com.aier.cloud.biz.service.biz.amcs.aps.service.impl.RuleDetailServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AierAmcsService.class)
public class 刷新规则{
    @Autowired
    private RuleDetailServiceImpl rs;
    @Resource JdbcHelper db;
    @Test
    public void test() throws Exception {
    	//生成清理缓存脚本
//    	JdbcHelper prod = ItemSourceServiceSit.prod;
//    	List<Map<String, Object>> results = prod.queryMapList("select distinct w.ahis_hosp from syss.t_sys_institution w where w.ahis_hosp is not null ORDER BY w.ahis_hosp asc");
//    	results.forEach(r ->{
//    		System.out.println("del based:configbased:config:"+r.get("AHIS_HOSP")+":opr007");
//    	});
//    	Rule rule = db.queryBean("select * from amcs.t_amcs_aps_rule w where w.id = 1678660377589604354", Rule.class);
//    	rs.refreshHospRuleWithOutGroup(rule);
    	
    }
}