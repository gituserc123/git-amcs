package com.aier.cloud.api.amcs.adverse.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2023-01-28 14:53
 **/

@Data
public class AeOaPayVO {
    /**
     * 付款单位
     **/
    private String yymc;
    /**
     * 患者姓名
     **/
    private String hzxm;
    /**
     * 性别
     **/
    private String xb;
    /**
     * 住院号
     **/
    private String zyh;
    /**
     * 年龄
     **/
    private String nl;
    /**
     * 诊断
     **/
    private String zd;
    /**
     * 摘要
     **/
    private String scomment;
    /**
     * 赔付金额
     **/
    private String pfje;
    /**
     * 员工工号
     **/
    private String workcode;
    /**
     * 第一次上报日期
     */
    private String frd;
    /**
     * 主事件id
     */
    private String zsjid;
}
