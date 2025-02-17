package com.aier.cloud.api.amcs.condition;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections.MapUtils;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * @program: amcs
 * @description: 调用肖可单医生刷新脚本
 * @author: hsw
 * @create: 2022-08-15 10:55
 **/

public class JobParam {
    public JobParam(Long hospId, Date beginDate, Date endDate) {
        super();
        this.hospId = hospId;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public JobParam() {

    }
    private Long hospId;
    private Set<Long> hospIds = Sets.newHashSet();
    private Date beginDate;
    private Date endDate;
    private Map<String, Object> ext = Maps.newHashMap();
    public Long getHospId() {
        return hospId;
    }
    public Date getBeginDate() {
        return beginDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setHospId(Long hospId) {
        this.hospId = hospId;
    }
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    public void addExt(String k, Object v) {
        ext.put(k, v);
    }
    public String myExt(String k) {
        return MapUtils.getString(ext, k);
    }

    public Set<Long> getHospIds() {
        return hospIds;
    }

    public void setHospIds(Set<Long> hospIds) {
        this.hospIds = hospIds;
    }

    public Map<String, Object> getExt() {
        return ext;
    }

    public void setExt(Map<String, Object> ext) {
        this.ext = ext;
    }
}
