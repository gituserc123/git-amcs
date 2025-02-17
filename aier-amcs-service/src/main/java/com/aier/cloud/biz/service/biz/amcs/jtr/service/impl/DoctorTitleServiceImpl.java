/*
 * Copyright © 2004-2020 Aier EYE Hospital Group.
 * 爱尔眼科医院集团 信息管理中心 开发部 版权所有
 *
 * Licensed under the Aier EYE Hospital Group License;
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.aierchina.com/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.aier.cloud.biz.service.biz.amcs.jtr.service.impl;

import java.text.ParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.basic.starter.service.config.JdbcHelper;
import com.aier.cloud.biz.service.biz.amcs.jtr.dao.DoctorTitleMapper;
import com.aier.cloud.biz.service.biz.amcs.jtr.dto.JobStatisticsDto;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorTitle;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorTitleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.common.collect.Maps;

/**
 * DoctorTitle 员工职称（执业证）信息
 *
 * @author 爱尔眼科
 * @since 2021-11-12 17:28:03
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class DoctorTitleServiceImpl extends ServiceImpl<DoctorTitleMapper, DoctorTitle> implements DoctorTitleService {

    @Resource
    RestTemplate rt;
    @Resource
    JdbcHelper db;

    @Override
    public String getJobDoctorTitle(String userCode) {
        Wrapper<DoctorTitle> doctorTitleWrapper = new EntityWrapper<>();
        doctorTitleWrapper.eq("STAFF_CODE", userCode)
                .eq("TITLE_TYPE", 1)
                .orderBy("TITLE_CODE", false)
                .orderBy("START_DATE", false);
        if (this.selectList(doctorTitleWrapper).size() > 0) {
            return this.selectList(doctorTitleWrapper).get(0).getTitleName();
        } else {
            return "待取得职称";
        }
    }

    @Override
    public void saveJobDoctorTitle(JobStatisticsDto d) {
        HttpHeaders hs = new HttpHeaders();
        hs.setContentType(MediaType.APPLICATION_JSON_UTF8);

        int currentPageNumber = 1;
        int totalPageNumber = 1;
        while (currentPageNumber <= totalPageNumber) {
            Map param = Maps.newHashMap();
            param.put("systemCd", "AHIS");
            param.put("ecode", "1ce3ef985fec11e88773e69e6c980052");
            param.put("pageNumber", currentPageNumber);
            param.put("outDt", DateFormatUtils.format(d.getBeginDate(), "yyyy-MM-dd"));
            param.put("scopeFlag", "M");
            String body = JsonUtil.toJson(param);
            HttpEntity<String> request = new HttpEntity<String>(body, hs);
            ResponseEntity<TitlePage> responseEntity = rt.postForEntity("https://ehr.aierchina.com/PSIGW/RESTListeningConnector/PSFT_HR/AI_EMPL_DOCT_TITLE.v1/", request, TitlePage.class);
            TitlePage tp = responseEntity.getBody();

            if (CollectionUtils.isNotEmpty(tp.getEmplDoctTitleList())) {
                tp.getEmplDoctTitleList().forEach(t -> {
                    DoctorTitle dt = new DoctorTitle();
                    dt.setModifer(1L);
                    dt.setModifyDate(new Date());
                    dt.setStaffCode(MapUtils.getString(t, "emplid"));
                    dt.setTitleType(1);
                    dt.setTitleCode(MapUtils.getString(t, "titleId"));
                    dt.setTitleName(MapUtils.getString(t, "titleName"));
                    dt.setTitleExtName(MapUtils.getString(t, "titleRatingName"));
                    try {
                        dt.setStartDate(DateUtils.parseDate(MapUtils.getString(t, "startDt"), "yyyy-MM-dd"));
                    } catch (ParseException e) {
                        dt.setStartDate(new Date());
                    }
                    modify(t, dt);
                });
            }
            totalPageNumber = tp.getTotalPageNumber();
            currentPageNumber++;
        }

        currentPageNumber = 1;
        totalPageNumber = 1;
        while (currentPageNumber <= totalPageNumber) {
            Map param = Maps.newHashMap();
            param.put("systemCd", "AHIS");
            param.put("ecode", "1ce3ef985fec11e88773e69e6c980052");
            param.put("pageNumber", currentPageNumber);
            param.put("outDt", DateFormatUtils.format(d.getBeginDate(), "yyyy-MM-dd"));
            param.put("scopeFlag", "M");
            String body = JsonUtil.toJson(param);
            HttpEntity<String> request = new HttpEntity<String>(body, hs);
            ResponseEntity<TitlePage> responseEntity = rt.postForEntity("https://ehr.aierchina.com/PSIGW/RESTListeningConnector/PSFT_HR/AI_EMPL_DOCT_LICENSE.v1/", request, TitlePage.class);
            TitlePage tp = responseEntity.getBody();

            if (CollectionUtils.isNotEmpty(tp.getEmplDoctLicenseList())) {
                tp.getEmplDoctLicenseList().forEach(t -> {
                    DoctorTitle dt = new DoctorTitle();
                    dt.setModifer(1L);
                    dt.setModifyDate(new Date());
                    dt.setStaffCode(MapUtils.getString(t, "emplid"));
                    dt.setTitleType(2);
                    dt.setTitleCode(MapUtils.getString(t, "seqNo"));
                    dt.setTitleName(MapUtils.getString(t, "licenseScopeName"));
                    dt.setTitleExtName(MapUtils.getString(t, "licenseCataName"));
                    try {
                        dt.setStartDate(DateUtils.parseDate(MapUtils.getString(t, "startDt"), "yyyy-MM-dd"));
                    } catch (ParseException e) {
                        dt.setStartDate(new Date());
                    }
                    modify(t, dt);
                });
            }
            totalPageNumber = tp.getTotalPageNumber();
            currentPageNumber++;
        }
    }

    private void modify(Map t, DoctorTitle dt) {
        Long id = exists(dt);
        String outFlag = MapUtils.getString(t, "outFlag", "A");
        if (Arrays.asList("A", "U", "N").contains(outFlag.toUpperCase())) {
            if (id == null) {
                this.insert(dt);
            } else {
                dt.setId(id);
                this.updateById(dt);
            }
        } else if (Arrays.asList("D").contains(outFlag.toUpperCase())) {
            if (id != null) {
                dt.setId(id);
                this.deleteById(id);
            }
        }
    }

    private Long exists(DoctorTitle dt) {
        return db.queryLong("select w.id from t_jtr_doctor_title w where w.staff_code = ? and w.title_type = ? and w.title_code = ?", dt.getStaffCode(), dt.getTitleType(), dt.getTitleCode());
    }

    public static class TitlePage {
        String errcode;
        String errmsg;
        int totalPageNumber;
        int currentPageNumber;
        List<Map> emplDoctTitleList;
        List<Map> emplDoctLicenseList;

        public String getErrcode() {
            return errcode;
        }

        public void setErrcode(String errcode) {
            this.errcode = errcode;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }

        public int getTotalPageNumber() {
            if (totalPageNumber > 100) {
                return 100;
            }
            return totalPageNumber;
        }

        public void setTotalPageNumber(int totalPageNumber) {
            this.totalPageNumber = totalPageNumber;
        }

        public int getCurrentPageNumber() {
            return currentPageNumber;
        }

        public void setCurrentPageNumber(int currentPageNumber) {
            this.currentPageNumber = currentPageNumber;
        }

        public List<Map> getEmplDoctTitleList() {
            return emplDoctTitleList;
        }

        public void setEmplDoctTitleList(List<Map> emplDoctTitleList) {
            this.emplDoctTitleList = emplDoctTitleList;
        }

        public List<Map> getEmplDoctLicenseList() {
            return emplDoctLicenseList;
        }

        public void setEmplDoctLicenseList(List<Map> emplDoctLicenseList) {
            this.emplDoctLicenseList = emplDoctLicenseList;
        }
    }

}
