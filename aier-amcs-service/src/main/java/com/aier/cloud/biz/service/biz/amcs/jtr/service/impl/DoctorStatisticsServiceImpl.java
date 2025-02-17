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

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aier.basic.weixin.api.AwxService;
import com.aier.basic.weixin.api.CpMesg;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.log.annotion.AierServiceLog;
import com.aier.cloud.basic.log.bean.LogMessage;
import com.aier.cloud.basic.log.utils.LogUtils;
import com.aier.cloud.biz.service.biz.amcs.jtr.dao.DoctorStatisticsMapper;
import com.aier.cloud.biz.service.biz.amcs.jtr.dto.JobStatisticsDto;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorStatistics;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.DoctorTitle;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorStatisticsService;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorTitleService;
import com.aier.cloud.biz.service.config.WxProperties;
import com.aier.cloud.center.common.context.UserContext;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * DoctorStatistics
 * 职改医生统计表
 *
 * @author 爱尔眼科
 * @since 2021-09-26 14:29:33
 */
@Service
@Transactional(rollbackFor = Throwable.class)
public class DoctorStatisticsServiceImpl extends ServiceImpl<DoctorStatisticsMapper, DoctorStatistics> implements DoctorStatisticsService {
    static final String TYPE_WEEK="周";
    static final String TYPE_MONTH="月";
    static final int SHOW_DAYS=65;
    @Resource
    private DoctorStatisticsMapper doctorStatisticsMapper;

    @Autowired
    private WxProperties wxProperties;
    @Autowired
    private DoctorTitleService doctorTitleService;



    @Override
    @AierServiceLog(message="企业微信推送消息{1}：工号：{0}  {2}",module = "企微推送")
    public Boolean sendMsg(String userCode, String content) {

        LogMessage logMessage=LogMessage.newWrite().setParams(userCode);
        AwxService.initCp(wxProperties.getCorpId(), wxProperties.getFuCorpSecret(), wxProperties.getFuAgentId());
        CpMesg cpMesg = new CpMesg();
        cpMesg.setUser(userCode);
        cpMesg.setContent(content);

        try {
            AwxService.sendCpMesg(cpMesg);
            logMessage.setParams("成功");
        } catch (Exception e) {
            logMessage.setParams("失败");
            logMessage.setParams(e.getLocalizedMessage());
            e.printStackTrace();
        }finally{
            LogUtils.putArgs(logMessage);
        }
        return true;
    }

    @Override
    public String sendToAll(String dateType) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar calWeek = Calendar.getInstance();
        calWeek.setFirstDayOfWeek(Calendar.MONDAY);
        calWeek.add(Calendar.DATE, calWeek.getFirstDayOfWeek() - calWeek.get(Calendar.DAY_OF_WEEK)-7);
        Date firstDay=calWeek.getTime();
        String first=sdf.format(firstDay);
        calWeek.add(Calendar.DATE,6);
        Date endDay=calWeek.getTime();
        String end=sdf.format(endDay);
        Calendar calMonth = Calendar.getInstance();
        int thisMonth=calMonth.get(Calendar.MONTH)+1;
        calMonth.add(Calendar.MONTH,-1);
        int lastMonth=calMonth.get(Calendar.MONTH)+1;
        calMonth.add(Calendar.MONTH,2);
        int nextMonth=calMonth.get(Calendar.MONTH)+1;
        String content="";
        if (TYPE_WEEK.equals(dateType)){
            content="【职称填报提醒】请及时填写/核对上周（"+first+"到"+end+"）门诊工作量、出院工作量、手术工作量（AHIS系统提取出院数据略有延迟）";
        }else if (TYPE_MONTH.equals(dateType)){
            content="【职称填报提醒】请及时填写/核对"+lastMonth+"、"+thisMonth+"月门诊工作量、出院工作量、手术工作量（系统进入"+nextMonth+"月后将不能修改"+lastMonth+"月工作量）";
        }
        String lambdaContent=content;
        Wrapper<DoctorTitle> doctorTitleWrapper = new EntityWrapper<>();
        doctorTitleWrapper.eq("TITLE_TYPE", 1);
        Set<String> set = new HashSet();

        List<DoctorTitle> list = doctorTitleService.selectList(doctorTitleWrapper);
        list.forEach(l -> {
            set.add(l.getStaffCode());
        });
        int[] count={0};
        set.forEach(s -> {
            Wrapper<DoctorStatistics> doctorStatisticsWrapper = new EntityWrapper<>();
            doctorStatisticsWrapper.eq("USER_CODE", s)
                    .ge("STATISTICS_DATE", firstDay.toInstant().minus(1, ChronoUnit.DAYS))
                    .le("STATISTICS_DATE", endDay);
            if (this.selectList(doctorStatisticsWrapper).size()==0 && TYPE_WEEK.equals(dateType)){
//                System.out.println(s+"|"+lambdaContent);
                sendMsg(s, lambdaContent);
                count[0]++;
            }
            if (TYPE_MONTH.equals(dateType)){
                sendMsg(s, lambdaContent);
                count[0]++;
            }

        });
        return "成功发送" + Integer.toString(count[0]) + "条信息";
    }

    @Override
    public Boolean saveDoctorStatistics(DoctorStatistics doctorstatistics) {

        doctorstatistics.setUserCode(UserContext.getUserCode());
        doctorstatistics.setUserName(UserContext.getUserName());
        doctorstatistics.setStatisticsType(2);
        doctorstatistics.setCreator(UserContext.getUserId());
        doctorstatistics.setCreateDate(new Date());
        doctorstatistics.setModifer(UserContext.getUserId());
        doctorstatistics.setModifyDate(new Date());


        return this.insertOrUpdate(doctorstatistics);


    }

    @Override
    public List<DoctorStatistics> getDoctorStatistics(String statisticsDate, String userCode, Long hospId) {
        Wrapper<DoctorStatistics> doctorStatisticsWrapper = new EntityWrapper<>();
        doctorStatisticsWrapper.eq("to_char(STATISTICS_DATE,'yyyy-MM-dd')", statisticsDate)
                .eq("USER_CODE", userCode)
                .eq("HOSP_ID", hospId)
                .orderBy("STATISTICS_TYPE", false)
        ;

        return this.selectList(doctorStatisticsWrapper);
    }

    @Override
    public List<Map<String, Object>> last60Days(String userCode, Long hospId) {

        List<Map<String, Object>> list = new ArrayList<>();
        Calendar c = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        c.getTime();
        Date date;
        for (int i = 1; i < SHOW_DAYS; i++) {

            Map<String, Object> map = new HashMap<>(SHOW_DAYS);
            c.add(Calendar.DATE, -1);
            date = c.getTime();
            List<DoctorStatistics> doctorStatisticsList = getDoctorStatistics(dateFormat.format(date), userCode, hospId);

            if (doctorStatisticsList.size() > 0) {
                map.put("statisticsDate", dateFormat.format(date));
                map.put("doctorStatisticsList", doctorStatisticsList);
                list.add(map);
            }


        }
        return list;
    }

    @Override
    public void saveJobStatistics(JobStatisticsDto dto) throws ParseException {
        List<DoctorStatistics> ds = dto.getDs();
        if (CollectionUtils.isEmpty(ds)) {
            return;
        }

        if (dto.getBeginDate() == null || dto.getEndDate() == null) {
            throw BizException.error("统计时间范围参数为空！");
        }

        if (dto.getBeginDate().getTime() > dto.getEndDate().getTime()) {
            throw BizException.error("统计时间范围异常！");
        }

        deleteAll(dto);
        this.insertBatch(ds, 2000);
    }

    private void deleteAll(JobStatisticsDto dto) throws ParseException {
        Date begin = DateUtils.parseDate(DateFormatUtils.format(DateUtils.addDays(dto.getBeginDate(), -1), "yyyy-MM-dd") + " 23:59:59", "yyyy-MM-dd HH:mm:ss");
        EntityWrapper w = new EntityWrapper<>();
        w.between("STATISTICS_DATE", begin, dto.getEndDate());
        w.eq("STATISTICS_TYPE", 1);
        if (StringUtils.isNotBlank(dto.getStaffCode())) {
            w.eq("user_code", dto.getStaffCode());
        }
        if(CollectionUtils.isNotEmpty(dto.getHospIds())){
        	w.in("hosp_id", dto.getHospIds());
        }
        this.delete(w);
    }

    @Override
    public List<Map<String, Object>> getByDateRange(String startDate, String endDate, String userCode) {
        return this.baseMapper.getByDateRange(startDate, endDate, userCode);
    }

}

