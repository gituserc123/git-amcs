package com.aier.cloud;

import org.springframework.boot.SpringApplication;

import com.aier.cloud.center.common.annotion.AierCloudApplication;

/**
 * AierHisUi
 * @author rain_deng
 * @since 2018年1月29日 上午10:08:59
 */
@AierCloudApplication
public class AierMcsPortal {
    
  /**
    * 
    * @Title: main  
    * @param @param args    设定文件  
    * @return void    返回类型  
    * @throws
    */
	public static void main(String[] args) {
		SpringApplication.run(AierMcsPortal.class, args);
	}
}

