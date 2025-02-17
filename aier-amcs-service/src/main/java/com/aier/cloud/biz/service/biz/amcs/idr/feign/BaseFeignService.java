package com.aier.cloud.biz.service.biz.amcs.idr.feign;

import com.aier.cloud.basic.api.request.condition.based.BasedCommonCondition;
import com.aier.cloud.basic.api.request.condition.based.CostTypeCondition;
import com.aier.cloud.basic.api.request.condition.based.DcgDiagCondition;
import com.aier.cloud.basic.api.request.condition.based.DchDrugsCondition;
import com.aier.cloud.basic.api.request.condition.based.DchItemInsureCondition;
import com.aier.cloud.basic.api.request.condition.based.DchMaterialCondition;
import com.aier.cloud.basic.api.request.condition.based.DchMedicalServiceCondition;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.request.domain.based.*;
import com.aier.cloud.basic.api.response.domain.base.DchPayItem;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 *
 * <p>Title: BaseFeignService</p>
 * <p>Description: 医保调用外部服务接口</p>
 * <p>Company: 爱尔眼科</p>
 * @author xxc
 * @date 2018年5月4日
 */

@FeignClient(name="aier-service-based")
public interface BaseFeignService {
    /**
     * 根据id获取收费项目信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/based/dchPayItem/getById", method = RequestMethod.POST)
    DchPayItem getPayItemById(@RequestParam(value = "id") Long id);

    /**
     * 根据id获取药品信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/based/dchDrugs/getById", method = RequestMethod.POST)
    DchDrugs getDrugById(@RequestParam(value = "id") Long id);


    /**
     * 根据id获取材料信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/based/dchMaterial/getById", method = RequestMethod.POST)
    DchMaterial getMaterialById(@RequestParam(value = "id") Long id);
    
        
    
    /**
     * 获取参数值
     * @param code
     * @return
     */
    @RequestMapping(value="/api/based/config/getValue", method=RequestMethod.POST)
    String getValue(@RequestParam(value="code")String code);


	/**
	 * 取费别
	 * @param cond
	 * @return PageResponse<Map>
	 */
	@RequestMapping(value = "/api/based/costTypeAdjust/findByParamsForPage", method = RequestMethod.POST, consumes = "application/json")
	PageResponse<Map<String, Object>> findByParamsForPage(@RequestBody CostTypeCondition cond);
	
	/**
	 * 查询三大目录对照的医保等级信息(参数必须包含：项目编码、医保项目编码)
	 * @param condition
	 * @return List<DchItemInsureInfo>
	 */
	@RequestMapping(value = "/api/based/dchItemInsureInfo/selectByCondition", method=RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<DchItemInsureInfo> selectByCondition(@RequestBody DchItemInsureCondition condition);

	/**
	 * 查询三大目录对照的医保等级信息(参数必须包含：项目编码、医保项目编码)
	 * @param condition
	 * @return List<DchItemInsureInfo>
	 */
	@RequestMapping(value = "/api/based/dchItemInsureInfo/selectByConditionNew", method=RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<DchItemInsureInfo> selectByConditionNew(@RequestBody DchItemInsureCondition condition);

	/**
	 * 查询三大目录对照的医保等级信息
	 * @param  itemId
	 * @return List<DchItemInsureInfo>
	 */
	@RequestMapping(value = "/api/based/dchItemInsureInfo/selectByItemId", method=RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<DchItemInsureInfo> selectByItemId(@RequestParam(value="itemId") Long itemId);

	/**
	 * 批量新增三大目录对照的医保等级信息
	 * @param entityList
	 * @return Boolean
	 */
	@RequestMapping(value = "/api/based/dchItemInsureInfo/saveItemInsureByList", method=RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Boolean saveItemInsure(@RequestBody List<DchItemInsureInfo> entityList);
	
	/**
	 * 根据ID删除三大目录对照的医保等级信息
	 * @param id
	 * @return Boolean
	 */
	@RequestMapping(value = "/api/based/dchItemInsureInfo/deleteItemInsureInfoById", method=RequestMethod.POST, consumes = "application/json")
	public @ResponseBody Boolean deleteItemInsureInfoById(@RequestParam("id") Long id);
	
	/**
	 * 根据费别值获取相关信息
	 * @param cond
	 * @return
	 */
	@RequestMapping(value = "/api/based/costTypeAdjust/findByParams", method=RequestMethod.POST, consumes = "application/json")
	public @ResponseBody List<Map> findByParams(@RequestBody CostTypeCondition cond);
	
	
	/**
     * 根据id获取集团诊疗信息
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/based/dcgMedicalService/getById", method = RequestMethod.POST)
    DcgMedicalService getMedicalServiceById(@RequestParam(value = "id") Long id);

	/**
	 * 根据id获取医院诊疗信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/api/based/dchMedicalService/getById", method = RequestMethod.POST)
	DchMedicalService getDchMedicalServiceById(@RequestParam(value = "id") Long id);
    /**
	 * 批量删除三大目录对照的医保等级信息
	 * @param districtCode
	 * @param insureCode
	 * @return 
	 */
	@RequestMapping(value = "/api/based/dchItemInsureInfo/deleteByInsureCode", method=RequestMethod.POST, consumes = "application/json")
	public @ResponseBody void deleteByInsureCode(@RequestParam("insureCode") String insureCode,@RequestParam("districtCode") String districtCode);

	/**
	 * 根据条件获取    药品
	 * @return
	 */
	@RequestMapping(value = "/api/based/dchDrugs/query", method = RequestMethod.POST)
	PageResponse getDchDrugByCondition(@RequestBody DchDrugsCondition dchDrugsCondition);

	/**
	 * 根据条件获取   诊疗
	 * @return
	 */
	@RequestMapping(value = "/api/based/dchMedicalService/query", method = RequestMethod.POST)
	List<Map<String, Object>> getDchMedicalServiceByCondition(@RequestBody DchMedicalServiceCondition dchMedicalServiceCondition);

	/**
	 * 根据条件获取   耗材服务设施
	 * @return
	 */
	@RequestMapping(value = "/api/based/dchMaterial/query", method = RequestMethod.POST)
	List<Map<String, Object>> getDchMaterialByCondition(@RequestBody DchMaterialCondition dchMaterialCondition);

	
	/**
	 * "根据主表Id查询医嘱收费对照表列表
	 * */
	@RequestMapping(value = "/api/based/dchOrder/getDetailByMainIdForList", method = RequestMethod.POST)
	public List<Map<String, Object>> getDetailByMainIdForList(@RequestParam("itemId") Long itemId,@RequestParam("matchingSign") Long matchingSign);

	/**
	 * 根据Codes查询药品信息
	 * */
	@RequestMapping(value = "/api/based/dchDrugs/getDrugsByCodes", method = RequestMethod.POST)
	public Map<String, DchDrugs> getDrugsByCodes(@RequestBody List<String> codes);

	/**
	 * 根据Codes查询药品信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/api/based/dchMaterial/getMaterialByCodes", method = RequestMethod.POST)
	public Map<String, DchMaterial> getMaterialByCodes(@RequestBody List<String> codes) ;

	/**
	 * 根据Codes查询药品信息
	 *
	 * @return
	 */
	@RequestMapping(value = "/api/based/dchMedicalService/getMedicalByCodes", method = RequestMethod.POST)
	public Map<String, DchMedicalService> getMedicalByCodes(@RequestBody List<String> codes) ;

	/**
	 * 查询列表
	 *
	 * @param cond
	 * @return List<Map>
	 */
	@RequestMapping(value = "/api/based/costType/findByParams", method = RequestMethod.POST, consumes = "application/json")
	List<Map<String, Object>> findByParamsCostType(@RequestBody CostTypeCondition cond);




	@RequestMapping(value = "/api/based/dchItemInsureInfo/batchUpdateNoDefaultForItemId", method = RequestMethod.POST)
	public @ResponseBody Boolean batchUpdateNoDefaultForItemId(@RequestBody DchItemInsureInfo insureInfo);

	/**
	 * 根据code查询字典中文
	 *
	 * @return
	 */
	@RequestMapping(value = "/api/based/dcgCodeDict/getCodeDict", method = RequestMethod.POST)
	public DcgCodeDict getCodeDict(@RequestParam("type")String type, @RequestParam("valueCode")String valueCode) ;

	/**
	 * 获取省市区
	 * @param d
	 * @return
	 */
    @RequestMapping(value="/api/based/dcgRegion/getList", method=RequestMethod.POST)
    public PageResponse<Map> getList(@RequestBody BasedCommonCondition d);
	/**
	 * 根据组合条件查询诊断信息带版本号
	 *
	 * @param cond
	 * @return
	 */
	@RequestMapping(value = "/api/based/diag/getDcgDiagList", method = RequestMethod.POST)
	@ResponseBody
	public List<DcgDiag> getDcgDiagList(@RequestBody DcgDiagCondition cond);

	/**
	 * 根据医院hosp_id查询医院所属的诊断信息
	 *
	 * @param cond
	 * @return
	 */
	@RequestMapping(value = "/api/based/diag/findByHospId", method = RequestMethod.POST)
	public List<DcgDiag> findByHospId(@RequestBody DcgDiagCondition cond);
	
	  /**根据条件该机构下所有医院用户
     * @Title: getSysStaffHospById  
     * @param @param staffId
     * @param @return    设定文件  
     * @return SysStaffHosp    返回类型  
     * @throws
     */
    @RequestMapping(value = "/api/sys/staff/getHospStaff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Map> getHospStaff(@RequestBody StaffCondition sc);
    

}
