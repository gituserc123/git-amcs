package com.aier.cloud.ui.biz.sys.service;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.aier.cloud.basic.api.request.condition.sys.SysCommonCondition;


/**
 * 报表权限
 * @author xiao_kek
 * @since 2018年5月24日 下午4:54:28
 */
@FeignClient(name="aier-service-system")
public interface ReportService {
    
	/**
	 * 更新权限
	 * @param m
	 */
    @RequestMapping(value = "/api/sys/report/updatePerm", method = RequestMethod.POST)
	void updatePerm(SysCommonCondition m);

    /**
     * 获取我的报表
     * @param m
     * @return
     */
    @RequestMapping(value = "/api/sys/report/getStaffReport", method = RequestMethod.POST)
    List<Map<String,String>> getStaffReport(SysCommonCondition m);

    /**
     * 更新报表
     * @param m
     */
    @RequestMapping(value = "/api/sys/report/updateReport", method = RequestMethod.POST)
	void updateReport(SysCommonCondition m);

    /**
     * 获取报表列表
     * @param m
     * @return
     */
    @RequestMapping(value = "/api/sys/report/getList", method = RequestMethod.POST)
    Object getList(SysCommonCondition m);

    /**
     * 获取报表员工
     * @param c
     * @return
     */
    @RequestMapping(value = "/api/sys/report/getReportStaff", method = RequestMethod.POST)
	Object getReportStaff(SysCommonCondition c);

    /**
     * 更新报表权限
     * @param m
     */
    @RequestMapping(value = "/api/sys/report/updateReportPerm", method = RequestMethod.POST)
	void updateReportPerm(SysCommonCondition m);
}
