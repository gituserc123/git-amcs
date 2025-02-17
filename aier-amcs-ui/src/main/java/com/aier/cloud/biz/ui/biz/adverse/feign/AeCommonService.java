package com.aier.cloud.biz.ui.biz.adverse.feign;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.aier.cloud.api.amcs.adverse.domain.AeFocusDomain;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.api.amcs.adverse.condition.AeInfoCondition;
import com.aier.cloud.api.amcs.adverse.domain.AeBasicInfo;
import com.aier.cloud.api.amcs.adverse.enums.EventEnum;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;

@FeignClient(name = "aier-amcs-service")
public interface AeCommonService {
	
	@PostMapping("/api/service/biz/amcs/adverse/common/save")
    @ResponseBody
    public Map<String, Object> save(@RequestBody Map<String, Object> mData, @RequestParam("eEnum") EventEnum eEnum);

	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/update")
	@ResponseBody
	public Boolean update(List<AeBasicInfo> basicList);

	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/updateOaRequestById")
	@ResponseBody
	public Integer updateOaRequestById(@RequestParam("id")Long id,@RequestParam("requestId")Integer requestId);
	
	@PostMapping("/api/service/biz/amcs/adverse/common/merge")
    @ResponseBody
    public boolean merge(@RequestParam("masterId") Long masterId, @RequestParam("mergeIds") List<Long> mergeIds);
	
	@PostMapping("/api/service/biz/amcs/adverse/common/reback")
    @ResponseBody
    public boolean reback(@RequestParam("basicId") Long basicId, @RequestParam("eEnum") EventEnum eEnum, @RequestParam("node") Integer node);
	
	@PostMapping("/api/service/biz/amcs/adverse/common/findById")
    @ResponseBody
    public Map<String, Object> findById(@RequestParam("id") Long id, @RequestParam("eEnum") EventEnum eEnum);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/getLastReportTimes")
    @ResponseBody
    public Integer getLastReportTimes(@RequestParam("prevId") Long prevId);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/getLast")
    @ResponseBody
	public Map<String, Object> getLast(@RequestParam("basicId") Long basicId);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/getLastEventInfo")
	@ResponseBody
	List<Map<String, Object>> getLastEventInfo(@RequestParam("basicIds") List<Long> basicIds);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/getLastEvents")
    @ResponseBody
	public Map<String, Object> getLastEvents(@RequestParam("basicId") Long basicId);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/getLastEvent")
    @ResponseBody
	public Map<String, Object> getLastEvent(@RequestParam("prevId") Long prevId, @RequestParam("eEnum") EventEnum eEnum, @RequestParam("isLast") Boolean isLast);

	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/sumAmount")
	@ResponseBody
	public AeBasicInfo sumAmount(@RequestBody AeInfoCondition cond);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/common/listNode")
	@ResponseBody
	public PageResponse <Map<String, Object>> listNode(@RequestBody AeInfoCondition cond);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/findListByCond")
	@ResponseBody
	public PageResponse <Map<String, Object>> findListByCond(@RequestBody AeInfoCondition m);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/findReturnList")
	@ResponseBody
	public PageResponse <Map<String, Object>> findReturnList(@RequestBody AeInfoCondition m);
	
	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/findReviewList")
	@ResponseBody
	public PageResponse <Map<String, Object>> findReviewList(@RequestBody AeInfoCondition m);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/getAeBasicInfo")
    @ResponseBody
    public AeBasicInfo getBasicById(@RequestParam("id") Long id);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/updateNode")
    @ResponseBody
    public boolean updateNode(@RequestParam("id") Long id, @RequestParam("node") Integer node);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/updateGroupReview")
    @ResponseBody
    public boolean updateGroupReview(@RequestParam("id") Long id);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/findOpinionList")
    @ResponseBody
	public PageResponse<Map<String, Object>> findOpinionList(@RequestBody AeInfoCondition m);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/findOpinionForReview")
    @ResponseBody
	public Map<String, Object> findOpinionForReview(@RequestBody AeInfoCondition m);

	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/findOperator")
	@ResponseBody
	Long findOperator(@RequestParam("id")Long id, @RequestParam("node")Integer node, @RequestParam("type")Integer type);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/hasGroupLook")
	@ResponseBody
	Boolean hasGroupLook(@RequestParam("basicId")Long basicId);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/findLookList")
	@ResponseBody
	List<Map<String, Object>> findLookList(@RequestParam("basicIds") List<Long> basicIds);
	
	
	@PostMapping("/api/service/biz/amcs/adverse/aeOperationRecord/findReviewList")
	@ResponseBody
	List<Map<String, Object>> findReviewList(@RequestParam("basicIds") List<Long> basicIds, @RequestParam("node") Integer node);

	@PostMapping("/api/service/biz/amcs/adverse/aeFocusEvent/add")
	@ResponseBody
	Long addFouce(@RequestBody AeFocusDomain focus);

	@PostMapping("/api/service/biz/amcs/adverse/aeFocusEvent/del")
	@ResponseBody
	Boolean delFouce(@RequestParam("id") Long focusId);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/queryListByCond")
	@ResponseBody
	PageResponse <Map<String, Object>> queryListByCond(@RequestBody AeInfoCondition m);

	@PostMapping(value = "/api/service/biz/amcs/adverse/aeBasicInfo/queryByQueryMapper")
	@ResponseBody
	List<Map<String, Object>> queryByQueryMapper(@RequestParam("table") String table,@RequestParam("queryFeild")String queryFeild,@RequestParam("cond")String cond);

	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/archivedById")
	@ResponseBody
	Integer archivedById(@RequestParam("id")Long id);
	
	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/delById")
	@ResponseBody
	Boolean delById(@RequestParam("id")Long id);

	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/delOa")
	@ResponseBody
	Boolean delOa(@RequestParam("id") Long id);

	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/delAmount")
	@ResponseBody
	Boolean delAmount(@RequestParam("id") Long id, @RequestParam("type") String type);

	@PostMapping("/api/service/biz/amcs/adverse/aeBasicInfo/updateEhrDeptReview")
	@ResponseBody
	boolean updateEhrDeptReview(@RequestParam("id") Long id);

}
