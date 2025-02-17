package com.aier.cloud.biz.service.biz.sys.feign;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.request.domain.sys.StaffInst;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.api.response.domain.sys.SysStaffHosp;


/**
 * 用户服务
 * @author xiaokek
 * @since 2018年1月29日 上午10:11:40
 */
@FeignClient(name="aier-service-system")
public interface StaffService {
    /**
     * 根据ids查询包含id：name的键值对，主要用于翻译
     * @param ids
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getNamesByIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Map<Long,String> getNamesByIds(@RequestBody List<Long> ids);
    
    /**
     * 根据ids查询包含id：name的键值对，主要用于翻译
     * @param sc 
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getStaffByCondition", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Object getStaffByCondition(@RequestBody StaffCondition sc);
    
    /**
     * 根据条件查询所有非冻结去重复的用户
     * @param sc
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getAllStaffByCondition", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Object getAllStaffByCondition(@RequestBody StaffCondition sc);
    
    /**
     * 根据ids查询包含id：name的键值对，主要用于翻译
     * @param sc 
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getStaffByConditionForSelect", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map> getStaffByConditionForSelect(@RequestBody StaffCondition sc);

    /**
     * 根据用户名，用户所属部门查询用户列表
     * @param sc 
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getStaffByConditionForSuggest", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Object getStaffByConditionForSuggest(@RequestBody StaffCondition sc);
    
    
    /**
     * 根据sc查询医院用户
     * @param sc 
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getHospStaff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	List getHospStaff(StaffCondition sc);
    /**
     * getUserByUsername
     * @param name
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getByName/{name}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Staff getByName(@PathVariable("name") String name);
    
    /**
     * getById
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Staff getById(@RequestParam("id") Long id);

    /**
     * getByCode
     * @param staff_code
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getByCode", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Staff getByCode(@RequestParam("code") String code);

    
    /**
     * getById
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getChangePasswordById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean getChangePasswordById(@RequestParam("id") Long id);
    
    /**
     * getListByIds
     * @param ids
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getListByIds", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Staff> getListByIds(@RequestParam("ids") String ids);
    
    /**
     * getStaffHospByIdAndHosp
     * @param staffId
     * @param hospId
     * @param deptId
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getStaffHospByIdAndHosp", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SysStaffHosp getStaffHospByIdAndHosp(@RequestParam("staffId") Long staffId, @RequestParam("hospId") Long hospId, @RequestParam("deptId") Long deptId);
    
    /**
     * getSysStaffHospById
     * @param staffId
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getSysStaffHospById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    SysStaffHosp getSysStaffHospById(@RequestParam("staffId") Long staffId);

    /**
     * getDetailById
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getDetailById", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Staff getDetailById(@RequestParam("id") Long id);
    
    /**
     * create
     * @param staff
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean create(@RequestBody Staff staff);
    
    /**
     * edit
     * @param staff
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/edit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean edit(@RequestBody Staff staff);
    
    
    /**
     * 编辑用户附属医院详细信息
     * @param sysStaffHosp
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/staffHospEdit", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean staffHospEdit(@RequestBody SysStaffHosp sysStaffHosp);
    
    
    /**
     * 创建外院专家账户，医院详细信息，医院科室关联
     * @param sysStaffHosp
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/staffHospCreate", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean staffHospCreate(@RequestBody SysStaffHosp sysStaffHosp);
    
    /**
     * edit
     * @param staff
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/updatePwd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean updatePwd(@RequestBody Staff staff);
    
    /**
     * 批量修改用户信息
     * resetBatchStaff
     * @param list
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/resetBatchStaff", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean resetBatchStaff(@RequestBody List<Staff> list);
    
    /**
     * 用户默认分页
     * @param staffCondition
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/page", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Staff> getPage(@RequestBody StaffCondition staffCondition);
    
    
    /**
     * 根据医院id获取关联的所有用户
     * @param staffCondition
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/pageByInst", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<Staff> getPageByInst(@RequestBody StaffCondition staffCondition);

    
    /**
     * 根据ids查询包含id：name的键值对，主要用于翻译
     * @param staffCondition
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/getStaffByInstAndDept", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Object getStaffByInstAndDept(@RequestBody StaffCondition staffCondition);
    
    /**
     * 更改人员科室启停状态
     * @param staffInst
     * @return
     */
    @RequestMapping(value = "/api/sys/staff/changeStaffDeptUsingSign", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    Boolean changeStaffDeptUsingSign(@RequestBody StaffInst staffInst);


    /**
     * 获取视光科开单医生列表
     * @Param [id]
     * @return java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     **/
    @RequestMapping(value = "/api/sys/staff/getStaffForAvis", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    List<Map<String, Object>> getStaffForAvis(@RequestParam("hospId") Long hospId,@RequestParam("id") Long id);

    /**
     * 分页查询员工
     * @param staffCondition
     * @return
     */
    @PostMapping(value = "/api/sys/staff/page")
    PageResponse<Staff> page(@RequestBody StaffCondition staffCondition);
}
