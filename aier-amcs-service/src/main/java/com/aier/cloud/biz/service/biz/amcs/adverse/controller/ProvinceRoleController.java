package com.aier.cloud.biz.service.biz.amcs.adverse.controller;

import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.domain.ProvinceRole;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.starter.service.controller.BaseController;
import com.aier.cloud.biz.service.biz.amcs.adverse.entity.ProvinceRoleConfig;
import com.aier.cloud.biz.service.biz.amcs.adverse.service.ProvinceRoleService;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 不良事件省区角色配置接口
 *
 * @author chendongdong
 * @date 2022/5/11
 */
@Api("不良事件省区角色配置接口")
@RestController
@RequestMapping("/api/amcs/adverse/province/role/config")
public class ProvinceRoleController extends BaseController {

    @Autowired
    private ProvinceRoleService provinceRoleService;

    @RequestMapping(value = "/getList")
    public @ResponseBody
    PageResponse getList(@RequestBody ProvinceRoleCondition m) {
        Page p = this.tranToPage(m);
        add("creator", "t_sys_staff|id|name", "creator");
        List<ProvinceRoleConfig> list = provinceRoleService.queryListPage(m, p);
        return this.returnPageResponse(p, list);
    }

    @RequestMapping(value = "/getListAll")
    public @ResponseBody
    List<ProvinceRoleConfig> getListAll(@RequestBody ProvinceRoleCondition m) {
        List<ProvinceRoleConfig> list = provinceRoleService.queryListPage(m);
        return list;
    }

    @PostMapping("/save")
    @ResponseBody
    public boolean save(@RequestBody ProvinceRole provinceRole){
        return provinceRoleService.save(provinceRole);
    }


    @RequestMapping("/remove")
    @ResponseBody
    public boolean removeById(@RequestParam("id")Long id){
        return provinceRoleService.remove(id);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public ProvinceRoleConfig getById(@RequestParam("id")Long id){
        return provinceRoleService.getById(id);
    }

    /**
     * 查询省区角色关联的用户
     * @param provinceId
     * @param roleId
     * @param staffId
     * @return
     */
    @RequestMapping("/queryByProvinceAndRole")
    @ResponseBody
    public ProvinceRoleConfig queryByProvinceAndRole(@RequestParam("provinceId")Long provinceId,
                                                     @RequestParam("roleId")Long roleId,
                                                     @RequestParam("staffId")Long staffId){
        return provinceRoleService.getByRoleAndStaff(provinceId,roleId,staffId);
    }

    /**
     * 获取用户所有省区角色
     * @param staffId
     * @return
     */
    @RequestMapping("/queryProvinceRoleByStaffId")
    @ResponseBody
    public List<ProvinceRoleConfig> queryProvinceRoleByStaffId(@RequestParam("staffId")Long staffId){
        return provinceRoleService.findByStaff(staffId);
    }

    /**
     * 检查省区是否存在改角色
     * @param provinceId
     * @param roleId
     * @return
     */
    @RequestMapping("/checkProvinceRole")
    @ResponseBody
    public boolean checkProvinceRole(@RequestParam("provinceId")Long provinceId,@RequestParam("roleId") Long roleId){
        return provinceRoleService.checkProvinceRole(provinceId,roleId);
    }
}
