package com.aier.cloud.biz.ui.biz.fin.controller;

import com.aier.cloud.api.amcs.adverse.domain.ProvinceRoleConfig;
import com.aier.cloud.api.amcs.condition.ProvinceRoleCondition;
import com.aier.cloud.api.amcs.constant.Constants;
import com.aier.cloud.api.amcs.domain.ProvinceRole;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.condition.sys.StaffCondition;
import com.aier.cloud.basic.api.response.domain.base.PageResponse;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.api.response.domain.sys.Role;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.biz.ui.biz.adverse.feign.AdverseEventService;
import com.aier.cloud.biz.ui.biz.sys.feign.AuthorizeService;
import com.aier.cloud.biz.ui.biz.sys.feign.InstitutionService;
import com.aier.cloud.biz.ui.biz.sys.feign.RoleService;
import com.aier.cloud.biz.ui.biz.sys.feign.StaffService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 不良事件管理
 *
 * @author chendongdong
 * @date 2022/4/25
 */
@Controller
@RequestMapping("/ui/amcs/fin/config/")
public class FinEventController extends BaseController {

    @Autowired
    private InstitutionService institutionService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private StaffService staffService;

    @Autowired
    private AuthorizeService authorizeService;

    @Autowired
    private AdverseEventService adverseEventService;

    @Value("${amcs.fin.roles:}")
    private String roleIds;

    /***
     * 不良事件省区角色 配置关联
     * @return
     */
    @GetMapping("index")
    public String index() {

        return "amcs/fin/config/index";
    }

    @GetMapping(value = "/createView")
    public String createView(@RequestParam(value = "roleId") Long roleId,
                             @RequestParam(value = "roleName") String roleName,
                             @RequestParam(value = "provinceId") Long provinceId,
                             @RequestParam(value = "provinceName") String provinceName,
                             HttpServletRequest request) {
        request.setAttribute("provinceId", provinceId);
        request.setAttribute("provinceName", provinceName);
        request.setAttribute("roleName", roleName);
        request.setAttribute("roleId", roleId);
        return "amcs/fin/config/create";
    }

    //@RequiresPermissions("Module:view")
    @RequestMapping(value = "/tree", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<Map<String, Object>> tree() {
        List<Map<String, Object>> modules = new ArrayList<>();
        List<Institution> list = institutionService.getListByInstType(InstEnum.PROVINCE);
        List<Institution> list2 = institutionService.getListByInstType(InstEnum.PROVINCE_DEPT);

//        list.addAll(list2.stream().filter(inst->inst.getName().endsWith("办公室")).collect(Collectors.toList()));
        if (CollectionUtils.isEmpty(list)) {
            return modules;
        }
        List<Long> idList = Arrays.stream(roleIds.split(",")).map(Long::parseLong).collect(Collectors.toList());

        List<Role> roles = roleService.getRolesByIds(idList);

        list.forEach(inst -> {
            Map<String, Object> map = new HashMap<>();
            map.put("name", inst.getName());

            map.put("treepath", inst.getTreePaths());
//            String[] o=inst.getTreepath().split(",");
            map.put("id", inst.getId());
            map.put("pid", inst.getParentId());
//            if (o.length>0&&inst.getName().endsWith("办公室")){
//                map.put("pid", o[o.length-1]);
//            }else {
//                map.put("pid", inst.getParentId());
//            }
            modules.add(map);
            for (Role role : roles) {
                Map<String, Object> subMap = new HashMap<>();
                subMap.put("name", role.getRoleName());
                subMap.put("pid", inst.getId());
                subMap.put("treepath", "0," + inst.getId());
                subMap.put("id", role.getId());
                subMap.put("isRoleNode",1);
                modules.add(subMap);
            }

        });
        return modules;
    }

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object queryList(ProvinceRoleCondition condition) {
        return adverseEventService.getList(condition);
    }


    @RequestMapping(value = "/remove/{id}", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean remove(@PathVariable("id") Long id) {
        Map<String, Object> map = adverseEventService.getById(id);
        if (Objects.isNull(map)) {
            logger.warn("记录不存在，请确认id={}是否有误", id);
            return false;
        }
        Long roleId = Long.valueOf(map.get("roleId").toString());
        Long staffId = Long.valueOf(map.get("staffId").toString());
        boolean ret2 = adverseEventService.removeById(id);
        try {
            boolean ret = authorizeService.cancelStaffProvinceRole(roleId, staffId);
        }catch (Exception e){
            logger.error("apd删除用户角色失败:{}",e.getMessage());
        }
        return true;
    }

    @RequestMapping(value = "/staffList", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public PageResponse<Staff> queryStaffList(StaffCondition condition) {
        PageResponse<Staff> page = staffService.page(condition);
        ProvinceRoleCondition provinceRoleCondition=new ProvinceRoleCondition();
//        provinceRoleCondition.setProvinceId(condition.getInstId());
        List<ProvinceRoleConfig> provinceRoleConfigList=adverseEventService.getListAll(provinceRoleCondition);
        page.getRows().forEach(staff -> {
            staff.setDeleteFlag(0);
            for (ProvinceRoleConfig provinceRoleConfig:provinceRoleConfigList){
                if(provinceRoleConfig.getStaffId().equals(staff.getId())){
                    staff.setDeleteFlag(1);
                }
            }

        });
        return page;
    }

    @PostMapping(value = "/staffBindRole")
    @ResponseBody
    public Map staffBindRole(ProvinceRole provinceRole) {
        if (Objects.isNull(provinceRole)) {
            fail("参数为空，用户授权失败！");
        }

        List<Staff> staffList = staffService.getListByIds(provinceRole.getStaffIds());
        if (CollectionUtils.isNotEmpty(staffList)) {
            List<Institution> list=institutionService.getListByStaffCode(staffList.get(0).getCode());
            provinceRole.setStaffList(staffList);
//            Boolean ret = authorizeService.createProvinceRole(provinceRole.getRoleId(), provinceRole.getStaffIds());
            /**
             * 将该用户所有登录机构分配省区角色
             */
            list.forEach(institution -> {
                authorizeService.create(provinceRole.getRoleId(),institution.getId(),provinceRole.getStaffIds(), Constants.PlatformCode);
            });
//            Boolean ret = authorizeService.create(provinceRole.getRoleId(),provinceRole.getProvinceId(),provinceRole.getStaffIds(),"amcs");
            if (adverseEventService.save(provinceRole)) {
                return success("绑定成功");
            }
        }
        return fail("人员信息有误");
    }
}
