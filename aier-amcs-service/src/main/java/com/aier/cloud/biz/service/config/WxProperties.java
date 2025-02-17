package com.aier.cloud.biz.service.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @program: amcs
 * @description: 企业微信配置
 * @author: hsw
 * @create: 2021-12-09 08:54
 **/
@Component
@ConfigurationProperties(prefix = "wechat.cp")
@Data
public class WxProperties {

    private String corpId ;
    private Integer fuAgentId;
    private String fuCorpSecret;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public Integer getFuAgentId() {
        return fuAgentId;
    }

    public void setFuAgentId(Integer fuAgentId) {
        this.fuAgentId = fuAgentId;
    }

    public String getFuCorpSecret() {
        return fuCorpSecret;
    }

    public void setFuCorpSecret(String fuCorpSecret) {
        this.fuCorpSecret = fuCorpSecret;
    }
}
