package com.aier.cloud.biz.ui.biz.sys.controller;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aier.cloud.basic.api.domain.constant.CommSymbol;
import com.aier.cloud.basic.api.request.condition.sys.SysCommonCondition;
import com.aier.cloud.basic.api.response.domain.sys.Module;
import com.aier.cloud.basic.api.response.domain.sys.Permission;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.common.exception.BizException;
import com.aier.cloud.basic.common.properties.AierUiProperties;
import com.aier.cloud.basic.common.util.JsonUtil;
import com.aier.cloud.basic.log.annotion.AierLog;
import com.aier.cloud.basic.log.bean.LogMessage;
import com.aier.cloud.basic.log.utils.LogUtils;
import com.aier.cloud.basic.starter.ui.shiro.ShiroDbRealm;
import com.aier.cloud.basic.starter.ui.shiro.ShiroDbRealm.HashPassword;
import com.aier.cloud.basic.web.controller.BaseController;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.ShiroUtils;
import com.aier.cloud.biz.ui.biz.sys.feign.ModuleFavorService;
import com.aier.cloud.biz.ui.biz.sys.feign.ModuleService;
import com.aier.cloud.biz.ui.biz.sys.feign.ReportService;
import com.aier.cloud.biz.ui.biz.sys.feign.StaffService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

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
    private ModuleService moduleService;
	@Autowired
    private StaffService staffService;
	@Autowired
    private ModuleFavorService moduleFavorService;	
	@Autowired
	private ReportService reportService;
	@Autowired
    private AierUiProperties aierUiProperties;
	
	@Value("${spring.profiles.active}")
	private String profile;
	
	/**
     *  空地址请求
     * @param model
     * @param request
     * @return
     */
    @GetMapping(value = "")
    public String index(Model model, HttpServletRequest request) {
        Subject s = SecurityUtils.getSubject();
        return s.isRemembered() || s.isAuthenticated() ? redirectTo("home") : LOGIN_PAGE;
    }
	
    @AierLog(message="{0}登录了系统", module="系统登录")
	@RequiresUser 
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String home(HttpServletRequest request) {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		
		// 加入ipAddress 
		shiroUser.setIpAddress(request.getRemoteAddr());
		
		// 这个是放入user还是shiroUser呢？
		ShiroUtils.setSessionAttr(ShiroDbRealm.LOGIN_USER, shiroUser);
		
		List<Module> menus = getMenus(subject);
		List<Module> favorMenus = getFavorMenus(menus, shiroUser);
		String searchMeuns = getSearchMenus(menus);
		
		request.setAttribute("menus", menus);
		request.setAttribute("favorMenus", favorMenus);
		request.setAttribute("searchMenus", searchMeuns);
		request.setAttribute("isChangePassword", staffService.getChangePasswordById(shiroUser.getId()));
		request.setAttribute("profile", profile);
		LogUtils.putArgs(LogMessage.newWrite().setParams(ShiroUtils.getLoginName()));
		return INDEX;
	}
	

	/**
	 * 获取收藏列表和个人报表
	 * @return
	 */
	@RequestMapping(value = "/ui/sys/index/getFavor")
	@ResponseBody
	public Map<String, Object> getFavor() {
		Subject subject = SecurityUtils.getSubject();
		ShiroUser shiroUser = (ShiroUser)subject.getPrincipal();
		List<Module> menus = getMenus(subject);
		Map<String, Object> favors = Maps.newHashMap();
		favors.put("menu", getFavorMenus(menus, shiroUser));
		favors.put("report", getMyReport(shiroUser));
		return this.success(favors);
	}

	private Object getMyReport(ShiroUser shiroUser) {
		SysCommonCondition c = new SysCommonCondition();
		c.setId(shiroUser.getId());
		c.setStaffId(shiroUser.getId());
		return reportService.getStaffReport(c);
	}

	/**
	 * 从当前有权限的菜单里获取快捷菜单
	 * @param menus
	 * @param su
	 * @return
	 */
	private List<Module> getFavorMenus(List<Module> menus, ShiroUser su) {
		Set<Long> ids = moduleFavorService.getFavorModuleId(su.getId());
		return menus.stream()
					.filter((m)-> ids.contains(m.getId()))
					.collect(Collectors.toList());
	}

	/**
	 * 将三级菜单给前端去搜索
	 * @param menus
	 * @return
	 */
	private String getSearchMenus(List<Module> menus) {
		List<Module> searchMenus = menus.stream()
										.filter((m)-> Objects.equals(m.getGrade(),3))
										.collect(Collectors.toList()); 
		return JsonUtil.toJson(searchMenus);
	}

	/**
	 * 根据权限获取菜单
	 * @param subject
	 * @return
	 */
	private List<Module> getMenus(Subject subject) {
        // 查询所有菜单，可考虑缓存优化，提高加载速度
        List<Module> modules = moduleService.getAllList(aierUiProperties.getSiteCode());
        List<Module> hasPermission = Lists.newArrayList();
        hasPermission = modules.stream().filter(i -> subject.isPermitted(i.getModuleCode() + Permission.PERMISSION_COLON + Permission.PERMISSION_READ))
                                        .collect(Collectors.toList());
        
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
    
    
    @GetMapping(value="/updatePwd")
    public String preUpdatePassword() {
        return UPDATE_PASSWORD;
    }
    
    @AierLog(message="{0}修改了自己的密码", module="修改密码")
    @PostMapping(value="/updatePwd")
    public @ResponseBody Map<String,Object> updatePassword(HttpServletRequest request, String plainPassword, String newPassword, String rePassword) {
        
        Staff staff = staffService.getById(ShiroUtils.getId());
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
                        LogUtils.putArgs(LogMessage.newWrite().setParams(ShiroUtils.getLoginName()));
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
}
