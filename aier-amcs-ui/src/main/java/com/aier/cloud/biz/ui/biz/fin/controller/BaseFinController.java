package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.web.controller.BaseController;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.fill.FillConfig;
import org.assertj.core.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class BaseFinController  extends BaseController {

    protected static final Logger logger = LoggerFactory.getLogger(BaseFinController.class);

    public void returnResult(PageResponse<Map<String, Object>> retVal, String tmpPath, String docPreSuffix, String docName) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        String templateAbsolutePath = Strings.concat(tmpPath, docPreSuffix, ".xlsx");
        response.reset();
        ClassPathResource config = new ClassPathResource(templateAbsolutePath);
        if(!config.exists()) {
            throw new BizException("没有找到模版");
        }
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding("utf-8");
        String fileRealName = URLEncoder.encode(docName, "UTF-8");
        response.setHeader("Content-disposition", "attachment;filename=" + fileRealName + ".xlsx");
        ExcelWriter excelWriter = EasyExcel.write(response.getOutputStream()).withTemplate(config.getInputStream()).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        FillConfig fillConfig = FillConfig.builder().forceNewRow(false).build();
        //System.out.println(paramJson);
        excelWriter.fill(retVal.getRows(),fillConfig,writeSheet);

        excelWriter.finish();
    }

    public static String getDateYMD(){
        StringBuffer sDate = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());					//放入Date类型数据
        return sDate.append(calendar.get(Calendar.YEAR)).append(calendar.get(Calendar.MONTH)+1).append(calendar.get(Calendar.DATE)).append(calendar.get(Calendar.HOUR_OF_DAY)).append(calendar.get(Calendar.MINUTE)).append(calendar.get(Calendar.SECOND)).toString();
    }

}
