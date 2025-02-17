package com.aier.cloud.biz.service.biz.amcs.job;


import com.aier.basic.weixin.api.AwxService;
import com.aier.basic.weixin.api.CpMesg;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.basic.api.request.condition.sys.AuthorizeCondition;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.AeBasicInfoService;
import com.aier.cloud.biz.service.biz.sys.feign.AuthorizeService;
import com.aier.cloud.biz.service.biz.sys.feign.InstitutionService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.List;

@JobHandler(value="com.aier.cloud.amcs.SendUnfinishMsgJobHandler")
@Component
public class SendUnfinishMsgJobHandler extends IJobHandler {

    private final static int DELAY_DAYS = 90;

    @Value("${amcs.adverse.medicalRole}")
    private Long medicalRoleId; //医务管理角色

    @Autowired
    private AeBasicInfoService aeBasicInfoService;

    @Autowired
    private AuthorizeService authorizeService;

    @Autowired
    private InstitutionService institutionService;

    @Override
    public ReturnT<String> execute(String param) throws Exception {

        try {
            List<Map<String, Object>> unfinishInfo = aeBasicInfoService.getUnfinishedEvent(DELAY_DAYS);
            if(unfinishInfo == null || unfinishInfo.size() == 0) {
                return new ReturnT<String>(ReturnT.SUCCESS_CODE, "无未完结消息");
            }

            for(Map<String, Object> curInfo: unfinishInfo) {
                Object hospId = curInfo.get("hospId");
                List<Map<String, Object>> users = getHospitalStaff(ObjectUtils.isEmpty(hospId) ? null : Long.parseLong(hospId.toString()));
                if(users == null || users.size() == 0) {
                    continue;
                }
                for(Map<String, Object> curUser: users) {
                    CpMesg mCpMesg = new CpMesg();
                    mCpMesg.setUser(curUser.get("code").toString());
                    List<Map<String, String>> events = (List<Map<String, String>>) curInfo.get("events");
                    for (Map<String, String> event : events) {
                        String eventName = event.get("eventName");
                        String patientName = event.get("patientName");
                        String msg = Constants.DelayFinishMsg;
                        msg = String.format(msg, curInfo.get("hospName"), patientName, eventName);
                        mCpMesg.setContent(msg);
                        AwxService.sendCpMesg(mCpMesg);
                    }
                }
            }
            return new ReturnT<String>(ReturnT.SUCCESS_CODE, "推送未完结消息成功");
        }catch(Exception ex) {
            return new ReturnT<String>(ReturnT.FAIL_CODE, ex.getMessage());
        }

    }

    private List<Map<String, Object>> getHospitalStaff(Long instId){
        if(ObjectUtils.isEmpty(instId)) {
            return null;
        }
        AuthorizeCondition authorizeCondition = new AuthorizeCondition();
        authorizeCondition.setInstitution(instId);
        authorizeCondition.setRoleId(medicalRoleId);
        return authorizeService.getListByRoleAuthorize(authorizeCondition).getRows();
    }

}
