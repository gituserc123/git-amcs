package com.aier.cloud.biz.service.biz.amcs.jtr.controller;

import com.aier.cloud.api.amcs.condition.PositionCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.jtr.entity.Position;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.DoctorFeign;
import com.aier.cloud.biz.service.biz.amcs.jtr.service.Jobs;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;

/**
 * @program: amcs
 * @description:
 * @author: hsw
 * @create: 2021-11-04 08:55
 **/

@Controller
@RequestMapping("/api/amcs/jtr/position")
public class PositionController extends BaseController {

    @Autowired
    private DoctorFeign doctorFeign;



    @RequestMapping("/getAll")
    public @ResponseBody
    List<Position> getAll(@RequestBody PositionCondition positionCondition){
        StaffCondition staffCondition=new StaffCondition();
        staffCondition.setStaffKey(positionCondition.getUserCode());
        Map map = doctorFeign.getStaffForAmcs(staffCondition);
        List<Position> list=new ArrayList<>();
        Set<Integer> set=new HashSet<>();
        JSONArray jsonArray=(JSONArray)map.get("jobs");
        jsonArray.forEach(ja->{

            JSONObject aa=(JSONObject)ja;
            Jobs jobsItem= JSONObject.toJavaObject(aa, Jobs.class);
            Position position=new Position();
            position.setAhisSign(jobsItem.getAhisSign());
            position.setCurrentUserCode(jobsItem.getCode());
            position.setUserName(map.get("name").toString());
            position.setUserCode(map.get("code").toString());
            position.setMainSign(jobsItem.getIsMainCode().equals("1")?1:0);
            position.setAhisSign(jobsItem.getAhisSign());
            position.setUsingSign(jobsItem.getUsingSign());
            position.setHospId(jobsItem.getHospId());

            position.setHospName(jobsItem.getHospName());
            if (!set.contains(jobsItem.getHospId())){
                list.add(position);
            }

            set.add(jobsItem.getHospId());
        });
        return list;

    }
}
