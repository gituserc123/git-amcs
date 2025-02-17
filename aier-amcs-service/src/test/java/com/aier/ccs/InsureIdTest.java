package com.aier.ccs;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.collections.MapUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.aier.cloud.AierAmcsService;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.alibaba.druid.pool.DruidDataSource;


/**
 * 刷id脚本
 * 前提：将临时表在dev环境建好，后面的步骤按顺序来，缺一不可
 * 
 * 第1步，手工对所有临时表创建id索引，例如
 * create index IDX_t_insure_drugs_ls on t_insure_drugs_ls (ID);
 * 
 * 第2步，手工对所有表执行刷id语句，例如
 * update t_insure_drugs_ls set id = rownum;
 * 
 * 第3步，将shuaId()方法的变量String[] tables赋值为此次要刷id的表名。
 * 
 * 第4步，也就是最后一步，运行junit执行shuaId()方法。 eclipse 中是 选择方法右键 run as junit
 * @author xiaokek
 * @since 2022年2月24日 下午4:02:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = AierAmcsService.class)
@WebAppConfiguration
public class InsureIdTest {
	@Resource
	DruidDataSource ds;


	@Test
	public void 创建虚拟医院() throws Exception {
		JdbcHelper db = 麻醉方式Test2.ahisViewJdbcHelper(ds);
		List<Map<String,Object>> staffs = db.queryMapList("select * from syss.t_sys_staff a where "
				+ "a.id in ( select w.staff_id from syss.t_sys_staff_inst w where w.institution_id = 100642 and w.using_sign = 1) "
				+ "and a.code not like 'T%' ");
	}
	
	@Test
	public void shuaId() throws Exception {
		JdbcHelper db = 麻醉方式Test2.ahisViewJdbcHelper(ds);
		
		int max = 0;
		long maxId = 0L;
		boolean has = true;
		int page = 0, size = 900000;
		while(has) {
			String sql = "select id,rownum as rowss from his.t_outp_reg_resource w ";
			sql = "select id from (" + sql;
			sql = sql + ") t where t.rowss > ? and t.rowss <= ? ";
			List<Map> ens = (List)db.queryMapList(sql, page*size, (page+1)*size);
			page++;
			has = ens.size() != 0;
			for(Map en : ens){
				Long id = MapUtils.getLong(en, "ID");
				String _2stString = Long.toBinaryString(id);
				_2stString = _2stString.substring(_2stString.length()-12, _2stString.length());
				int temp = Integer.parseInt(_2stString, 2);
				if(max < temp) {
					maxId = id;
					max = temp;
				}
			}
			System.out.println(max + "-" + maxId);
		}
	}
}