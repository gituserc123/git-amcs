package com.aier.cloud.ui.shiro;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.aier.cloud.basic.api.domain.enums.InstEnum;
import com.aier.cloud.basic.api.request.domain.sys.Platform;
import com.aier.cloud.basic.api.request.domain.sys.SysPlatformInst;
import com.aier.cloud.basic.api.response.domain.sys.Institution;
import com.aier.cloud.basic.api.response.domain.sys.Permission;
import com.aier.cloud.basic.api.response.domain.sys.Staff;
import com.aier.cloud.basic.cache.redis.RedisService;
import com.aier.cloud.basic.common.properties.AierUiProperties;
import com.aier.cloud.basic.common.util.DateUtils;
import com.aier.cloud.basic.common.util.DigestUtils;
import com.aier.cloud.basic.common.util.EncodeUtils;
import com.aier.cloud.basic.starter.ui.feign.RoleService;
import com.aier.cloud.basic.starter.ui.shiro.CloudShiroDbRealm;
import com.aier.cloud.basic.web.service.RSAService;
import com.aier.cloud.basic.web.shiro.InstitutionUserCodePasswordToken;
import com.aier.cloud.basic.web.shiro.ShiroUser;
import com.aier.cloud.basic.web.shiro.exception.IncorrectCaptchaException;
import com.aier.cloud.basic.web.shiro.exception.IncorrectInstException;
import com.aier.cloud.basic.web.shiro.exception.LoginTimeOutException;
import com.aier.cloud.basic.web.shiro.interfaces.IShiroDbRealmService;
import com.aier.cloud.basic.web.shiro.interfaces.IShiroUser;
import com.aier.cloud.ui.biz.sys.service.InstitutionService;
import com.aier.cloud.ui.biz.sys.service.ModuleService;
import com.aier.cloud.ui.biz.sys.service.PermissionService;
import com.aier.cloud.ui.biz.sys.service.PlatformService;
import com.aier.cloud.ui.biz.sys.service.StaffService;
import com.google.common.collect.Sets;


/**
 * 
 * 
 * <b>类名称：</b>ShiroDbRealm<br/>
 * <b>类描述：</b>授权认证管理<br/>
 *               处理用户登录，用户权限刷新，授权<br/>
 * <b>创建人：</b>rain_deng<br/>
 * <b>修改人：</b>rain_deng<br/>
 * <b>修改时间：</b>2017年11月15日 下午2:30:40<br/>
 * <b>修改备注：</b><br/>
 * @version 1.0.0<br/>
 *@author rain_deng
 */
public class ShiroDbRealm extends CloudShiroDbRealm implements IShiroDbRealmService{
	
	private final static Logger log = LoggerFactory.getLogger(ShiroDbRealm.class);
	
	/** 用户组织层级分隔符 */
	public static final String USER_ORG_GRADE_SEPARATOR = ",";
	
	/** 最大失败尝试次数 */
	public static final int MAX_TRY_TIMES = 5;
	
	/** 默认密码 */
	public static final String DEFAULT_PASSWORD = "Aier123456";
	
	/** 登录用户 */
	public final static String LOGIN_USER = "login_user";
	
	@Resource
    private InstitutionService instService;
	
	@Resource
	private StaffService staffService;
	
	@Resource
	private ModuleService moduleService;
	
	@Resource
	private PermissionService permissionService;
	
	@Resource
	private PlatformService platformService;
	
	@Resource
	private RoleService roleService;
	
	@Autowired
	private RedisService redisService;
	
	@Autowired
	private AierUiProperties properties;
	
	@Value("${spring.profiles.active}")
    private String profile;
	
	/**
	 * 给ShiroDbRealm提供编码信息，用于密码比对
	 */
	public ShiroDbRealm() {
		super();
	}
	
	/**
	 * 认证回调函数, 登录时调用.</br>
	 * 1. 获取登录用户的所属医院/集团 的 机构编码</br>
     * 2. 登录验证，失败，返回信息</br>
     * 3. 验证通过，记录最后登录机构等信息</br>
     * 4. 保存用户会话信息，如租户信息等</br>
	 */
	@Override
	public AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
	    InstitutionUserCodePasswordToken token = (InstitutionUserCodePasswordToken) authcToken; 
		String  username         = token.getUsername();
		String  password         = new String(token.getPassword());
		Long    institution      = token.getInstitution();
		Long    dept             = token.getDept();
		String  ip               = token.getHost();
		String  loginToken       = token.getLoginToken();
		if (StringUtils.isNotBlank(username)) 
		{
		    Staff staff = staffService.getByName(username);
            if (staff == null) {
                throw new IncorrectCredentialsException("未知账号错误");
            }
            
            if(properties.getOpenCloud() && !staff.getAdmin()) {
            	List<String> roles = roleService.selectRolesByStaff(staff.getId(), institution);
        		if (CollectionUtils.isEmpty(roles)) {
            		throw new IncorrectCaptchaException("该账号不是管理员，并且没有权限，无法登录");
            	}
            }
            
            if (properties.getJasypt() && RSAService.EXPIRE_TIMEOUT_RETURN.equals(password) && StringUtils.isBlank(loginToken)){
            	log.info("-----登录页停留时间过长，请再次登录------");
            	throw new LoginTimeOutException("登录页停留时间过长，请再次登录");
            }
            
            if (StringUtils.isNotBlank(password) && !RSAService.EXPIRE_TIMEOUT_RETURN.equals(password) && !Objects.isNull(institution) && !Objects.isNull(dept) && StringUtils.isBlank(loginToken))
            {
                return validationLogin(staff, password, institution, dept, ip, staff.getChangePassword() != null ? staff.getChangePassword() : false);
            } 
            // ajax 异步登录
            else if (StringUtils.isNotBlank(password) && !RSAService.EXPIRE_TIMEOUT_RETURN.equals(password) && Objects.isNull(institution) && Objects.isNull(dept) && StringUtils.isBlank(loginToken)) 
            {
                return validationLogin(staff, password, staff.getLastLoginInst(), staff.getLastLoginDept(), ip, staff.getChangePassword() != null ? staff.getChangePassword() : false);
            }
            else 
            {
                throw new IncorrectCredentialsException("未知账户错误");
            }
		}
		else 
		{
		    throw new UnknownAccountException("未知账户错误");
		}
	}

	/**
     * 登录时，查询平台机构启停状态 和 构建所有当前用户权限资源
	 * @param staff
	 * @param password
	 * @param institution
	 * @param dept
	 * @param ip
	 * @param ischangePassword
	 * @return
	 */
	private AuthenticationInfo validationLogin (Staff staff, String password, Long institution, Long dept, String ip, Boolean ischangePassword) {
	    
	    if (!staff.getStatus()) {
            throw new DisabledAccountException("对不起，账号已经暂停使用");
        }
	    
	    /**
	     * 防暴力登录限制，10秒内不能重复登录同一个账号，暂未处理
	     */
        Institution loginInst = instService.getById(institution);
        if (Objects.isNull(loginInst)) {
            throw new IncorrectInstException("未授权或不存在的机构");
        }
        
        Institution loginDept = instService.getById(dept);
        if (Objects.isNull(loginDept)) {
            throw new IncorrectInstException("未授权或不存在的科室/部门");
        }
        
        /** 获取用户关联的所有医院信息 */
        List<Institution> institutions = instService.getListByStaffCode(staff.getCode());
        if (!institutions.contains(loginInst)) {
            throw new IncorrectInstException("未授权或不存在的机构");
        }
        
        /** 获取带dept详细信息的dept集合 */
        List<Institution> depts = instService.getDeptDetailListByStaffCodeAndInst(staff.getCode(), institution);
        if (!(depts.stream().anyMatch(item -> item.getId().equals(loginDept.getId())))) {
            throw new IncorrectInstException("未授权或不存在的科室/部门");
        }
        
        /** 如果用户被锁定，查询自动解锁时间，到时间自动解锁 */
        if (staff.getLocked()) {
            // 2分钟后自动解锁，会在系统配置动态配置
            int loginFailureLockTime = 2; 
            Date lockedDate = staff.getLockedDate() != null ? staff.getLockedDate() : new Date();
            Date unlockDate = org.apache.commons.lang3.time.DateUtils.addMinutes(lockedDate, loginFailureLockTime);
            if (new Date().after(unlockDate)) {
                staff.setLoginFailureCount(0);
                staff.setLocked(false);
                staff.setLockedDate(null);
                staff.setModifer(staff.getId());
                staffService.edit(staff);
            } else {
                throw new LockedAccountException();
            }
        }
        
        /** 如果用户登录密码错误，会记录登录失败次数，超过系统设置的失败登录次数，将会被自动锁定 */
        if (!validatePassword(password, staff.getPassword(), staff.getSalt())) {
            int loginFailureCount = staff.getLoginFailureCount() + 1;
            
            if (loginFailureCount >= MAX_TRY_TIMES ) {
                staff.setLocked(true);
                staff.setLockedDate(new Date());
            }
            staff.setLoginFailureCount(loginFailureCount);
            staff.setModifer(staff.getId());
            staffService.edit(staff);
            loginFailureCount(loginFailureCount);
        }
        String lastIp = StringUtils.isNotBlank(staff.getLoginIp()) ? staff.getLoginIp() : StringUtils.EMPTY;
        String lastLoginTime = Objects.isNull(staff.getLastLoginTime()) ? StringUtils.EMPTY : DateUtils.getTime(staff.getLastLoginTime());
        staff.setLoginIp(ip);
        staff.setLastLoginTime(new Date());
        staff.setLoginFailureCount(0);
        staff.setModifer(staff.getId());
        staff.setLastLoginInst(institution);
        staff.setLastLoginDept(dept);
        staffService.edit(staff);
        
        Long tenantId = !Objects.isNull(loginInst.getAhisHosp()) ? Long.valueOf(loginInst.getAhisHosp()) : 0L;
        /**
         * id 用户id：当前登录的用户id
         * loginName 登录名：当前登录的用户名
         * instId 机构id：当前用户工作执行机构（如，部门，科室等） 暂无
         * instName 机构名称 ：当前用户工作执行机构（如，部门，科室等）暂无
         * tenantId 租户id：当前用户工作执行医院，可为空，空表示是非医院用户,默认非医院是 0
         * isChangePassword 是否更改密码
         * isHosp 是否医院用户
         * isAdmin 是否超级管理员
         * depts 当前关联的科室集合
         * deptId 当前登录的科室id
         */
        ShiroUser shiroUser = new ShiroUser(staff.getId(), staff.getCode(), staff.getName(), institution, loginInst.getName(), tenantId, ischangePassword, staff.getAdmin(), loginInst.getInstType().equals(InstEnum.HOSP.getInstType()), depts, dept, loginInst.getInstSchema());
        shiroUser.setDeptName(loginDept.getName());
        setlastIpAndloginTime(shiroUser, lastIp, lastLoginTime);
        shiroUser.setClouds(getClouds(shiroUser, properties));
        setDateScopeInstIds(shiroUser, staff, loginInst);
        setEhrInstCodes(shiroUser, staff, loginInst);
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
		matcher.setHashIterations(INTERATIONS);
		setCredentialsMatcher(matcher);
        
        byte[] salt = EncodeUtils.decodeHex(staff.getSalt());
        return new SimpleAuthenticationInfo(shiroUser, staff.getPassword(),ByteSource.Util.bytes(salt), getName());
	}
	
	/**
	 * 授权查询回调函数, 进行鉴权认证，但缓存中无用户的授权信息时调用.
	 */
	@Override
	public AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
	    log.info("##################执行Shiro授权##################");
		Collection<?> collection = principals.fromRealm(principals.getRealmNames().stream().findFirst().get());
		if (CollectionUtils.isEmpty(collection)) {
			return null;
		}
        ShiroUser shiroUser = (ShiroUser) collection.iterator().next();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		// 角色  + 站点权限 门户中心 
		info.addStringPermissions(makePermissions(shiroUser));
		info.addRoles(roleService.selectRolesByStaff(shiroUser.getId(), shiroUser.getInstId()));
		if (log.isInfoEnabled()) {
            log.info("{}拥有的角色:{}", shiroUser.getLoginName(), info.getRoles());
		}
		return info;
	}
	
	/**
	 * 设置上一次登录ip和登录时间
	 * @param shiroUser
	 * @param lastIp
	 * @param lastLoginTime
	 */
	private void setlastIpAndloginTime(ShiroUser shiroUser, String lastIp, String lastLoginTime) {
		shiroUser.setIpAddress(lastIp);
        shiroUser.setLastLoginTime(lastLoginTime);
        log.info("-----上次登录ip:{}----上次登录时:{}--",lastIp,lastLoginTime);
	}
	
	/**
	 *  构建权限资源
	 *  只要用户账户是开通状态，即可登录门户中心
     *  登录门户中心，可看到当前用户有权限（非通用权限）的多个应用（废弃）
     *  SysPlatformInst 表中初始化基础数据后，并关联到某医院的多个应用
     *  ********************************************************
        modify by deng_lei since 2019.04.17
                      登录逻辑调整：
                      登录到首页后，加载当前用户所有系统的所有权限菜单；
                      登录时，查询平台机构启停状态 和 构建所有当前用户权限资源
                      对于所有原有的三级菜单，需要根据platformCode动态对所有三级菜单加上绝对地址。
	 */
	private Collection<String> makePermissions(IShiroUser<Long> shiroUser) {
        Collection<String> stringPermissions = Sets.newHashSet();
        // 集团或者超级管理员 默认可以看到所有平台
        if (shiroUser.getIsAdmin() || shiroUser.getTenantId() == 0L) {
        	//stringPermissions.add("*:*");
        	List<Platform> platforms = platformService.list();
            platforms.forEach( i -> {
                stringPermissions.add(i.getCode() + Permission.PERMISSION_COLON + Permission.PERMISSION_READ);
            });
        } else {
            // 当前用户登录的医院 所开启的应用
            List<SysPlatformInst> sysPlatformInsts = platformService.getSysPlatformInstList(shiroUser.getTenantId());
            if (CollectionUtils.isNotEmpty(sysPlatformInsts)) {
                sysPlatformInsts.forEach(i -> {
                    if (i.getInitSign().equals(1)){
                        stringPermissions.add(i.getPlatformCode() + Permission.PERMISSION_COLON + Permission.PERMISSION_READ);
                    }
                }); 
            }
        } 
        
        // 只有超级管理员有所有权限
        if (shiroUser.getIsAdmin()) {
        	stringPermissions.add("*:*:*");
            return stringPermissions;
        }
        
        List<Permission> permissions = permissionService.selectAllListByPortal(shiroUser.getId(), shiroUser.getInstId());
        permissions.forEach( permission -> {
            stringPermissions.add(permission.getModuleCode() + Permission.PERMISSION_COLON + permission.getPermCode());
        });
        if (log.isInfoEnabled()) {
            log.info("{} 登录了系统。At {}",shiroUser.getLoginName(),DateUtils.getTime());
        }
       
        return stringPermissions;
	}
	
	
	/**
     * 
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @param salt 盐值
     * @return
     */
    public static boolean validatePassword(String plainPassword, String password, String salt) {
        byte[] hashPassword = DigestUtils.sha1(plainPassword.getBytes(), EncodeUtils.decodeHex(salt), INTERATIONS);
        String oldPassword = EncodeUtils.encodeHex(hashPassword);
        return password.equals(oldPassword);
    }
}
