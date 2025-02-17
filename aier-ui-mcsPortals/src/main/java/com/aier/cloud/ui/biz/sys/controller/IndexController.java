package com.aier.cloud.ui.biz.sys.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.domain.constant.CommSymbol;
import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.feign.MyWorkSpaceService;
import com.aier.cloud.basic.api.request.domain.sys.Platform;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.api.response.domain.sys.Module;
import com.aier.cloud.basic.api.response.domain.sys.Permission;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.api.response.domain.sys.SysMyworkspace;
import com.aier.cloud.basic.api.response.domain.sys.SysStaffHosp;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.common.properties.AierUiProperties;
import com.aier.cloud.basic.common.properties.CloudProperties;
import com.aier.cloud.basic.common.util.HanyuPinyinUtils;
import com.aier.cloud.basic.common.util.HttpUtils;
import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.basic.log.annotion.AierLog;
import com.aier.cloud.basic.log.bean.LogMessage;
import com.aier.cloud.basic.log.utils.LogUtils;
import com.aier.cloud.basic.starter.ui.shiro.ShiroDbRealm;
import com.aier.cloud.basic.starter.ui.shiro.ShiroDbRealm.HashPassword;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.basic.web.shiro.interfaces.IShiroUser;
import com.aier.cloud.ui.biz.sys.service.InstitutionService;
import com.aier.cloud.ui.biz.sys.service.ModuleFavorService;
import com.aier.cloud.ui.biz.sys.service.ModuleService;
import com.aier.cloud.ui.biz.sys.service.PlatformService;
import com.aier.cloud.ui.biz.sys.service.StaffHospService;
import com.aier.cloud.ui.biz.sys.service.StaffService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.hutool.core.collection.CollUtil;

/**
 * 
 * 首页加载
 *
 * @author rain_deng
 * @since 2018年3月14日 上午10:10:25
 */
@Controller
public class IndexController extends BaseController implements CommSymbol {
	
	private static final String INDEX = "sys/index";
	
	private static final String LOGIN_PAGE = "sys/login";
	
	private static final String UPDATE_PASSWORD = "sys/staff/password";
	
	@Autowired
    private StaffService staffService;
	
	@Autowired
	private ModuleService moduleService;
	
	@Autowired
    private PlatformService platformService;
	
	@Autowired
	private InstitutionService instService;
	
	@Value("${spring.profiles.active}")
    private String profile;
	
	@Autowired
	private AierUiProperties aierUiProperties;
	
	@Autowired
	private StaffHospService staffHospService;
	
	@Autowired
	private MyWorkSpaceService s;
	
	/**
     *  空地址请求
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index(Model model, HttpServletRequest request) {
        Subject s = SecurityUtils.getSubject();
		if (aierUiProperties.getOpenCloud()) {
			ShiroUser shiroUser = (ShiroUser)s.getPrincipal();
			RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
	 		com.aier.cloud.ui.shiro.ShiroDbRealm sr = (com.aier.cloud.ui.shiro.ShiroDbRealm)rsm.getRealms().iterator().next();
	 		sr.clearCachedAuthorizationInfo(shiroUser);
		}
		
        return s.isRemembered() || s.isAuthenticated() ? redirectTo("home") : LOGIN_PAGE;
    }
	
    /**
     * 对于所有原有的三级菜单，需要根据platformCode动态对所有三级菜单加上绝对地址。
     * 新增菜单时，最好保证权限code唯一，新增菜单模块 验证唯一性
     * 门户只需要加载0-3级菜单的view权限0
     * @param request
     * @return
     */
	@RequiresUser 
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		
		if (aierUiProperties.getOpenCloud()) {
			RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
	 		com.aier.cloud.ui.shiro.ShiroDbRealm sr = (com.aier.cloud.ui.shiro.ShiroDbRealm)rsm.getRealms().iterator().next();
	 		sr.clearCachedAuthorizationInfo(shiroUser);
		}
		
		// 这个是放入user还是shiroUser呢？
		ShiroUtils.setSessionAttr(ShiroDbRealm.LOGIN_USER, shiroUser);	
		
		//获取应用列表(默认是通过最后修改时间 升序 排序的)
		List<Platform> platforms = getPlatforms().stream()
                .filter(i -> subject.isPermitted(i.getCode() + Permission.PERMISSION_COLON + Permission.PERMISSION_READ))
                .sorted(Comparator.comparing(Platform::getOrders))
                .collect(Collectors.toList());
		
		List<Module> menus = getAbsPathModules(getMenus(subject), platforms);
		
        String searchMeuns = getSearchMenus(menus,null);
        
		request.setAttribute("platforms", platforms);
		if (aierUiProperties.getOpenCloud()) {
			request.setAttribute("clouds", getClouds(shiroUser, aierUiProperties));
		}
		request.setAttribute("openCloud", aierUiProperties.getOpenCloud());
		request.setAttribute("searchMenus", searchMeuns);
		request.setAttribute("menus", menus);
		request.setAttribute("isChangePassword", staffService.getChangePasswordById(shiroUser.getId()));
		request.setAttribute("profile", profile);
		request.setAttribute("depts", shiroUser.getDepts());
		request.setAttribute("nowDept", shiroUser.getDeptName());
		return INDEX;
	}
	
	@RequestMapping(value="/switch", method = RequestMethod.POST)
	public String switchOrganization(HttpServletRequest request, 
			@RequestParam("instId") Long instId, @RequestParam("deptId") Long deptId) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		// 加入ipAddress
		Institution inst = instService.getById(instId);
		Institution dept = instService.getById(deptId);
		if (inst != null) {
			shiroUser.setInstId(instId);
			shiroUser.setTenantId(Objects.isNull(inst.getAhisHosp()) ? 0L : Long.valueOf(inst.getAhisHosp()));
			shiroUser.setInstName(inst.getName());
			List<Institution> depts = instService.getDeptDetailListByStaffCodeAndInst(shiroUser.getLoginCode(), instId);
			shiroUser.setDepts(depts);
			shiroUser.setIsHosp(inst.getInstType().equals(InstEnum.HOSP.getInstType()));
			shiroUser.setClouds(getPortalClouds(shiroUser, aierUiProperties));
		}
		if (dept != null) {
			shiroUser.setDeptId(deptId);
			shiroUser.setDeptName(dept.getName());
		}
		
		// 动态切换
		RealmSecurityManager rsm = (RealmSecurityManager)SecurityUtils.getSecurityManager();
		com.aier.cloud.ui.shiro.ShiroDbRealm sr = (com.aier.cloud.ui.shiro.ShiroDbRealm)rsm.getRealms().iterator().next();
		sr.clearCachedAuthorizationInfo(shiroUser);
		sr.doGetAuthorizationInfo(new SimplePrincipalCollection(shiroUser,""));
		//切换用户账户
		//subject.runAs(new SimplePrincipalCollection(shiroUser,""));
	
		return home(request);
	}
	
    /**
     * 获取我的工作台
     * @return
     */
    @RequestMapping(value = "/ui/sys/index/getMyWorkspace")
    @ResponseBody
    public Map<String, Object> getMyWorkspace() {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        List<Platform> platforms = getPlatforms().stream()
                .filter(i -> subject.isPermitted(i.getCode() + Permission.PERMISSION_COLON + Permission.PERMISSION_READ))
                .collect(Collectors.toList());
        List<Module> menus = getAbsPathModules(getMenus(subject), platforms);
        List<SysMyworkspace> myworkspaces = s.getSysMyworkspace(shiroUser.getId());
        List<Long> ids = myworkspaces.stream().map(SysMyworkspace::getModuleId).distinct().collect(Collectors.toList());
        List<Module> filters = menus.stream().filter(m-> ids.contains(m.getId())).collect(Collectors.toList());
        filters.forEach(i->{
        	myworkspaces.forEach(j->{
        		if (i.getId().equals(j.getModuleId())) {
        			i.setModuleCategorize(j.getModuleCategorize());
        		}
        	});
        });
        Map<String, Object> workspaces = Maps.newHashMap();
        workspaces.put("menu", filters.stream().filter(i->StringUtils.isNotBlank(i.getModuleCategorize()))
        										.collect(Collectors.groupingBy(Module::getModuleCategorize)));
        //workspaces.put("report", getMyReport(shiroUser));
        return this.success(workspaces);
    }
    
    /**
     *  根据平台编码获取菜单
     * @param platformCode
     * @return
     */
    @RequestMapping(value = "/ui/sys/index/getModuleByPlatform")
    @ResponseBody
    public Map<String, Object> getModuleByPlatform(@RequestParam("platformCode") String platformCode) {
        List<Module> modules = moduleService.getAllList(platformCode);
        modules=  modules.stream().filter(i ->i.getGrade()>2).collect(Collectors.toList());
        return this.success(modules);
    }
    
    /**
     * 登录逻辑调整：
                   登录到首页后，加载当前用户所有系统的所有权限菜单；
                   登录时，查询平台机构启停状态 和 构建所有当前用户权限资源
                   对于所有原有的三级菜单，需要根据platformCode动态对所有三级菜单加上绝对地址。
     * @return
     */
    private List<Platform> getPlatforms() {
        Subject subject = SecurityUtils.getSubject();
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        List<Platform> platforms = Lists.newArrayList();
        if (shiroUser.getIsAdmin() || shiroUser.getTenantId() == 0L) {
            platforms = platformService.listByTypeAndCloud(Platform.PLATFORM_PLATTYPE_AHIS, Platform.PLATFORM_CLOUDTYPE_AMCSCLOUD);
        } else {
            platforms = platformService.getPlatformsByTenantIdAndCloudType(shiroUser.getTenantId(), Platform.PLATFORM_CLOUDTYPE_AMCSCLOUD);
        }
        if(CollUtil.isNotEmpty(platforms)) {
        	platforms.forEach(i -> {
            	if (CollectionUtils.isNotEmpty(aierUiProperties.getPlatforms())) {
            		aierUiProperties.getPlatforms().forEach(p -> {
                		if(i.getCode().equals(p.getCode())) {
                			i.setUrl(aierUiProperties.getOpenIntranet()?(HttpUtils.getPath()+HttpUtils.HTTP_SLASH+p.getCode()+HttpUtils.HTTP_SLASH):p.getUrl());
                		}
                	});
            	}
            });
        }
        return platforms;
    }
    
    private List<Module> getAbsPathModules(List<Module> menus, List<Platform> platforms) {
        platforms.forEach(p -> {
            menus.forEach(m -> {
                if (m.getPlatformCode().equals(p.getCode())) {
                    if (!p.getHasModules()) {
                        p.setHasModules(true);
                    }
                    if (!m.getOuturl() && m.getDisplay()) {
                        m.setUrl(p.getUrl() + m.getUrl());
                    }
                    if (null != m.getOuturl() && m.getOuturl() && m.getDisplay() && aierUiProperties.getOpenIntranet()) {
                    	m.setUrl(HttpUtils.getOutUrlPath(m.getUrl(), aierUiProperties));
                    	//m.setUrl(HttpUtils.HTTP_DOUBLE_SLASH + HttpUtils.getServerName() + HttpUtils.HTTP_COLON + urls[1]);
                    }
                }
            });
        });
        
        return menus;
    }
	
	/**
     * 将三级菜单给前端去搜索
     * @param menus
	 * @param reports 
     * @return
     */
    private String getSearchMenus(List<Module> menus, List<Map<String, String>> reports) {
        List<Module> temp = menus.stream()
                .filter((m)-> Objects.equals(m.getGrade(),3))
                .collect(Collectors.toList());
        List<Module> searchMenus;
        if(CollectionUtils.isNotEmpty(temp)) {
        	searchMenus = Lists.newArrayList(temp.toArray(new Module[] {}));
        }else {
        	searchMenus = Lists.newArrayList();
        }
        return JsonUtil.toJson(searchMenus);
    }
	
	/**
     * 根据权限获取菜单，这里获取所有菜单，然后在过滤
     * @param subject
     * @param platforms 已授权平台应用
     * @return
     */
    private List<Module> getMenus(Subject subject) {
        ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
        List<Module> hasPermission = null;
        if (shiroUser.getIsAdmin()) {
            hasPermission = moduleService.getAllListByPortal();
        } else {
            hasPermission = moduleService.getListByPortal(shiroUser.getId(), shiroUser.getInstId());
        }
        buildModule(hasPermission);
        return hasPermission;
    }
    
    /**
     * 构建菜单列表
     * @param list
     * @return
     */
    private List<Module> buildModule(List<Module> list) {
        if (CollectionUtils.isNotEmpty(list)) {
            List<Module> levelOne = Lists.newArrayList();
            List<Module> levelTwo = Lists.newArrayList();
            for (Module module : list) {
                if (Module.GRADE_LEVEL_ONE.equals(module.getGrade())) {
                    // 一级菜单
                    levelOne.add(module);
                } else if (Module.GRADE_LEVEL_TWO.equals(module.getGrade())) {
                    // 二级菜单
                    levelTwo.add(module);
                }
            }
            //一级菜单排序
            Collections.sort(levelOne);

            Set<Module> result = Sets.newHashSet();
            for (Module module : levelOne) {
                // 将一级菜单和二级菜单关联
                sortAndSetChildren(list, module, Module.GRADE_LEVEL_TWO);
                result.add(module);
            }

            for (Module module : levelTwo) {
                // 将二级菜单和三级菜单关联
                sortAndSetChildren(list, module, Module.GRADE_LEVEL_THREE);
                result.add(module);
            }
            return Lists.newArrayList(result);
        }
        return Collections.emptyList();
    }

    /**
     * 根据id 获取其子节点，子节点再排序，然后赋值到setChildren(List<Module> children)中
     * @param list 所有节点
     * @param module 上一级菜单
     * @param grade 要汇聚的层级
     */
    private void sortAndSetChildren (List<Module> list, Module module, Integer grade) {
        List<Module> sortList = Lists.newArrayList();
        sortList = list.stream().filter(i -> (i.getGrade().equals(grade) && null != i.getParent() && null != i.getParent().getId() && i.getParent().getId().equals(module.getId())))
                .collect(Collectors.toList());
        Collections.sort(sortList);
        module.setChildren(sortList);
    }
    
    
    private List<CloudProperties> getPortalClouds(IShiroUser<Long> shiroUser, AierUiProperties properties){
		if (properties.getOpenCloud()) {
			List<CloudProperties> list = properties.getClouds();
			// 集团或者超级管理员 默认可以看到所有平台
	        if (shiroUser.getIsAdmin() || shiroUser.getTenantId() == 0L) {
	           return list;
	        } else {
	        	List<CloudProperties> clouds = Lists.newArrayList();
	            // 当前用户登录的医院 所开启的应用
	            List<String> platforms = platformService.getPlatformsByTenantId(shiroUser.getTenantId()).stream().map(Platform::getCloudType).distinct().collect(Collectors.toList());
	            if (CollectionUtils.isNotEmpty(platforms)) {
	            	platforms.forEach(p -> {
	            		clouds.add(list.stream().filter(cloud -> cloud.getCode().equals(p)).findFirst().get());
	            	});
	            }
	            return clouds;
	        } 
		}
		return Collections.emptyList();
	}
	
    @GetMapping(value="/updatePwd")
    public String preUpdatePassword() {
        return UPDATE_PASSWORD;
    }
    
    @PostMapping(value="/updatePwd")
    public @ResponseBody Map<String,Object> updatePassword(HttpServletRequest request, String plainPassword, String newPassword, String rePassword) {
        Staff staff = staffService.getById(ShiroUtils.getId());
        
        if (StringUtils.isNotBlank(plainPassword) && StringUtils.isNotBlank(newPassword)
        		& plainPassword.equals(newPassword)) {
        	throw new BizException("原密码不能与新密码一样，请重新设置！");
        }
        
        if (newPassword != null && newPassword.equals(rePassword)) {
            staff.setPlainPassword(plainPassword);
            try {
                boolean isMatch = ShiroDbRealm.validatePassword(staff.getPlainPassword(), staff.getPassword(), staff.getSalt());
                if (isMatch) {
                    HashPassword hashPassword = ShiroDbRealm.encryptPassword(newPassword);
                    staff.setSalt(hashPassword.salt);
                    staff.setPassword(hashPassword.password);
                    staff.setChangePassword(true);
                    if(staffService.updatePwd(staff)){
                        return success("修改密码成功！");
                    }
                }
                throw new BizException("用户密码错误！");
            } catch (BizException e) {
                return fail(e.getMessage());
            }
        }
        
        return fail("修改密码失败！");
    }
    
    private static final String STAFF_PROFILE = "sys/staff/staffProfile";
    
    @GetMapping(value = "/ui/sys/staff/staffProfile")
    public String preStaffEdit(Map<String, Object> map) {
        Long instId = ShiroUtils.getInstId();
        SysStaffHosp sysStaffHosp = staffService.getStaffHospByIdAndHosp(ShiroUtils.getId(), instId, ShiroUtils.getDeptId());
        sysStaffHosp.getStaff().setFirstSpell(HanyuPinyinUtils.getFirstLettersUp(sysStaffHosp.getStaff().getName()));
        List<Institution> deptList = instService.getDeptListByStaffCodeAndInst(sysStaffHosp.getStaff().getCode(), instId);
        map.put("staffHosp", sysStaffHosp);
        map.put("deptList", deptList);
        return STAFF_PROFILE;
    }
    
    @PostMapping(value = "/ui/sys/staff/getStaffByType")
	public @ResponseBody List<Map<String,Object>> getStaffByType(Long staffId) {
		return staffHospService.getStaffByType(staffId, ShiroUtils.getInstId());
	}
    
    @PostMapping(value = "/ui/sys/staff/staffHospEdit")
    public @ResponseBody Map<String,Object> staffHospEdit(Staff staff) {
        Long operator = ShiroUtils.getId();
        staff.setId(operator);
        staff.setModifer(operator);
        boolean result = staffService.staffEdit(staff);
        return result ? success("人员信息编辑成功") : fail("人员信息编辑失败");
    }
    
    @AierLog(message="{0}注销了系统", module="系统注销")
    @RequestMapping("/ssoLogout")
    public String logout(HttpServletRequest request) {
        Optional<String> loginName =  Optional.ofNullable(ShiroUtils.getLoginName()); 
        if (loginName.isPresent()) {
            LogUtils.putArgs(LogMessage.newWrite().setParams(ShiroUtils.getLoginName()));
            SecurityUtils.getSubject().logout();
        }
        String http = request.getHeader("X-Forwarded-Proto");
        if(StringUtils.isNotBlank(http)){
        	return redirectTo(Optional.ofNullable(http+":"+aierUiProperties.getPortalUri()).orElse(StringUtils.EMPTY) + "/login");
        }else{
        	return redirectTo(Optional.ofNullable(aierUiProperties.getPortalUri()).orElse(StringUtils.EMPTY) + "/login");
        }
    }
}
