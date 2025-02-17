package com.aier.cloud.biz.ui.biz.amcs.jtr.feign;

import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.domain.DoctorStatistics;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 参数服务
 * @author xiaokek
 * @since 2018年1月29日 上午10:11:40
 */
@FeignClient(name="aier-amcs-service")
public interface DoctorStatisticsService {
	@RequestMapping(value="/api/amcs/jtr/doctorStatistics/last60Days", method=RequestMethod.POST)
	@ResponseBody  List<Map<String, Object>> last60Days(@RequestParam("userCode")String userCode, @RequestParam("hospId")Long hospId);

	@RequestMapping(value="/api/amcs/jtr/doctorStatistics/getDoctorStatistics", method=RequestMethod.POST)
	@ResponseBody List<?> getDoctorStatistics(@RequestParam("statisticsDate")String statisticsDate, @RequestParam("userCode")String userCode, @RequestParam("hospId")Long hospId);

	
	@RequestMapping(value="/api/amcs/jtr/doctorStatistics/saveDoctorStatistics", method=RequestMethod.POST)
	@ResponseBody
	boolean saveDoctorStatistics(DoctorStatistics doctorstatistics);

	@RequestMapping(value="/api/amcs/jtr/doctorStatistics/selectById", method=RequestMethod.POST)
	@ResponseBody
	Map selectById(@RequestParam("id")Long id);

	@RequestMapping(value="/api/amcs/jtr/doctorTitle/getJobDoctorTitle", method=RequestMethod.POST)
	@ResponseBody
	String getJobDoctorTitle(@RequestParam(value = "statisticsDate") String userCode);



	
}
