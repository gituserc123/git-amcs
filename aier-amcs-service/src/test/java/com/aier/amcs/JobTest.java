package com.aier.amcs;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.aier.cloud.AierAmcsService;
import com.aier.cloud.biz.service.biz.amcs.job.SendMsgToProvinceJobHandler;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= AierAmcsService.class)
public class JobTest {

	
	@Resource
	SendMsgToProvinceJobHandler sendMsgToProvinceJobHandler;
	
	@Test
    public void test() throws ClassNotFoundException {
		try {
			sendMsgToProvinceJobHandler.execute(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
