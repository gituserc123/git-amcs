package com.aier.cloud.biz.ui.biz.fin.controller;


import cn.hutool.json.JSONUtil;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.fin.condition.FinMaMainCondition;
import com.aier.cloud.api.amcs.fin.domain.FinMaMain;
import com.aier.cloud.api.amcs.utils.TimePeriodUtil;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.fin.feign.FinMaMainFeignService;
import com.aier.cloud.biz.ui.biz.fin.utils.MergeStrategy;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Controller
@RequestMapping("/ui/amcs/fin/ma/tab")
public class FinManageAnalysisTabController extends BaseController {

    private static final String MA_TAB_PAGE = "amcs/fin/business/manageAnalysis/finMaTabPage";
    private static final String MA_TAB_PAGE1 = "amcs/fin/business/manageAnalysis/finMaTabPage1";
    private static final String MA_STAT_TAB_PAGE = "amcs/fin/business/manageAnalysis/finMaStatTabPage";

    private static final String MA_SUM_STAT_TAB_PAGE = "amcs/fin/business/manageAnalysis/finSumStatMaPage";

    @Autowired
    private FinMaMainFeignService finMaMainFeignService;

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private FinDipManageAnalysisUiController finDipManageAnalysisUiController;

    @Autowired
    private FinDrgManageAnalysisUiController finDrgManageAnalysisUiController;

    @RequestMapping(value = "/tabPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String tabPage(Map<String, Object> map) {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();

        LocalDateTime now = LocalDateTime.now();
        if(shiroUser.getIsHosp()){
            // 医院人员登录
            String curQuarter = TimePeriodUtil.getCurDatePeriod(now);
            FinMaMainCondition cond = new FinMaMainCondition();
            cond.setHospId(shiroUser.getTenantId());
            cond.setMaYear(now.getYear());
            cond.setMaQuarter(Integer.parseInt(curQuarter.substring(curQuarter.length() - 1)));
            cond.setMaIdentifier(curQuarter+shiroUser.getTenantId());
            List<FinMaMain> finMaMains = finMaMainFeignService.getAllRecord(cond);
            if(CollectionUtils.isNotEmpty(finMaMains) && finMaMains.size()>0){
                if(finMaMains.get(0).getSettlementMethod().equals("DIP")){
                    map.put("chooseVal","1");
                }else{
                    map.put("chooseVal","2");
                }
                map.put("curPeriod","1");
            }else{
                // 如果当前时间主表内没有记录，查看上个季度是否有记录；
                String preQuarter = TimePeriodUtil.getPreviousDatePeriod(now);
                FinMaMainCondition condPre = new FinMaMainCondition();
                condPre.setHospId(shiroUser.getTenantId());
                condPre.setMaYear(Integer.parseInt(preQuarter.substring(0,4)));
                condPre.setMaQuarter(Integer.parseInt(preQuarter.substring(preQuarter.length() - 1)));
                condPre.setMaIdentifier(preQuarter+shiroUser.getTenantId());
                List<FinMaMain> finPreMaMains = finMaMainFeignService.getAllRecord(condPre);
                if(CollectionUtils.isNotEmpty(finPreMaMains) && finPreMaMains.size()>0){
                    if(finPreMaMains.get(0).getSettlementMethod().equals("DIP")){
                        map.put("chooseVal","1");
                        map.put("prePeriod","2");
                    }else{
                        map.put("chooseVal","2");
                        map.put("prePeriod","1");
                    }
                }
            }
        }else{
            if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 集团登录
            } else {
                // 省区登录
            }
            map.put("chooseVal","3");
        }
        return MA_TAB_PAGE;
    }

    @RequestMapping(value = "/statTabPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String statTabPage(Map<String, Object> map) {
        return MA_STAT_TAB_PAGE;
    }

    @RequestMapping(value = "/sumStatPage", method = { RequestMethod.GET, RequestMethod.POST })
    public String sumStatPage(Map<String, Object> map) {
        Integer empType = this.getEmpType();
        map.put("empType", empType);
        return MA_SUM_STAT_TAB_PAGE;
    }

    @RequestMapping(value = "/tabPage1", method = { RequestMethod.GET, RequestMethod.POST })
    public String tabPage1(Map<String, Object> map) {
        return MA_TAB_PAGE1;
    }

    @RequestMapping(value = "/tree", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> tree(){
        List<Map<String, Object>> modules = new ArrayList<>();
        if(this.getEmpType() == Constants.Group){
            List<Institution> list = institutionService.getListByInstType(InstEnum.PROVINCE);
            if (CollectionUtils.isEmpty(list)) {
                return modules;
            }

            list.forEach(inst -> {
                Map<String, Object> map = new HashMap<>();
                map.put("name", inst.getName());

                map.put("treepath", inst.getTreePaths());
                map.put("id", inst.getId());
                map.put("pid", inst.getParentId());
                modules.add(map);
            });

            Map<String, Object> allMap = Maps.newHashMap();
            allMap.put("name", "全部");
            allMap.put("treepath", JSONUtil.createArray());
            allMap.put("id", 99);
            allMap.put("pid", 0);
            modules.add(0,allMap);
        }
        return modules;
    }

    private int getEmpType() {
        if (!ShiroUtils.getIsHosp()) {
            // 集团或省区登录
            if (Constants.GroupInstId.equals(ShiroUtils.getInstId())) {
                // 集团登录
                return Constants.Group;
            } else {
                // 省区登录
                return Constants.Province;
            }
        }
        return Constants.Hosp;
    }

    @RequestMapping(value = "/downLoadList", method = { RequestMethod.GET, RequestMethod.POST })
    public void downLoadList(HttpServletResponse response, String paramJson) throws IOException {
        FinMaMainCondition cond = JSON.parseObject(paramJson, FinMaMainCondition.class);
        // 设置响应类型
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码
        String fileName = java.net.URLEncoder.encode("汇总统计" + this.getDateYMD(), "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");

        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).build();

        // 写入第一个sheet
        List<List<String>> dipStatExcel = finDipManageAnalysisUiController.getExcelStatDataList(cond);
        WriteSheet sheet1 = EasyExcel.writerSheet("DIP统计").head(finDipManageAnalysisUiController.getExcelStatHeadList()).registerWriteHandler(new MergeStrategy(dipStatExcel.size(), 0, 1, 2, 3, 4)).build();
        excelWriter.write(dipStatExcel, sheet1);

        // 写入第二个sheet，假设有不同的头部和数据
        List<List<String>> drgStatExcel = finDrgManageAnalysisUiController.getExcelStatDataList(cond);
        WriteSheet sheet2 = EasyExcel.writerSheet("DRG统计").head(finDrgManageAnalysisUiController.getExcelStatHeadList()).registerWriteHandler(new MergeStrategy(drgStatExcel.size(), 0, 1, 2, 3, 4)).build();
        excelWriter.write(drgStatExcel, sheet2);

        // 记得关闭ExcelWriter
        excelWriter.finish();
    }

    public String getDateYMD(){
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());                    //放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }

}
