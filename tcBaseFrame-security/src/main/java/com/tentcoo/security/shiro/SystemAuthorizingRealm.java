package com.tentcoo.security.shiro;

import com.tentcoo.common.servlet.ValidateCodeServlet;
import com.tentcoo.data.api.EmployeeService;
import com.tentcoo.data.api.RoleService;
import com.tentcoo.data.pojo.Employee;
import com.tentcoo.data.pojo.Role;
import com.tentcoo.log.util.MyLog;
import com.tentcoo.security.utils.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by rover on 2018/1/20.
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private MyLog logger = MyLog.getLog(SystemAuthorizingRealm.class);
    @Resource
    private EmployeeService employeeService;
    @Resource
    private RoleService     roleService;
    public static final String HASH_ALGORITHM   = "SHA-1";
    public static final int    SALT_SIZE        = 8;
    public static final int    HASH_INTERATIONS = 1024;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // Principal               principal = (Principal) getAvailablePrincipal(principals);
        SimpleAuthorizationInfo info    = new SimpleAuthorizationInfo();
        Employee                current = (Employee) SecurityUtils.getSubject().getPrincipal();
        //用户拥有的角色
        List<Role> roleList = roleService.getRoleByEmpId(current.getId());
        //用户拥有的权限
        List<String>      permissionList = employeeService.getPermissionByEmpId(current.getId());
        ArrayList<String> roles          = new ArrayList<>();
        ArrayList<String> permissions          = new ArrayList<>();
        for (Role role : roleList) {
            roles.add(role.getEnName());
        }
        for (String permission : permissionList) {
            if (permission != null && !permission.equals("")) {
                permissions.add(permission);
            }
        }
        //添加用户对应的角色
        info.addRoles(roles);
        //添加用户拥有的权限
        info.addStringPermissions(permissions);
        return info;
    }

    /**
     * 登录校验
     *
     * @param authcToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token    = (UsernamePasswordToken) authcToken;
        String                username = (String) token.getPrincipal();
        // 校验登录验证码
        Integer failureNum = (Integer) UserUtil.getCache(username);
        if (failureNum != null && failureNum >= 3){
            Session session = UserUtil.getSession();
            String  code    = (String)session.getAttribute(ValidateCodeServlet.VALIDATE_CODE);
            if (token.getCaptcha() == null || !token.getCaptcha().toUpperCase().equals(code)){
                throw new AuthenticationException("验证码错误, 请重试.");
            }
        }
        Employee              employee = employeeService.getLoginInfoByUserName(username);
        if (employee == null) {
            return null;
        }

        return new SimpleAuthenticationInfo(employee, employee.getPassword(), this.getName());
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(HASH_ALGORITHM);
        matcher.setHashIterations(HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

    @Override
    protected void checkPermission(Permission permission, AuthorizationInfo info) {
        authorizationValidate(permission);
        super.checkPermission(permission, info);
    }

    @Override
    protected boolean[] isPermitted(List<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermitted(permissions, info);
    }

    @Override
    public boolean isPermitted(PrincipalCollection principals, Permission permission) {
        authorizationValidate(permission);
        return super.isPermitted(principals, permission);
    }

    @Override
    protected boolean isPermittedAll(Collection<Permission> permissions, AuthorizationInfo info) {
        if (permissions != null && !permissions.isEmpty()) {
            for (Permission permission : permissions) {
                authorizationValidate(permission);
            }
        }
        return super.isPermittedAll(permissions, info);
    }

    /**
     * 授权验证方法
     *
     * @param permission
     */
    private void authorizationValidate(Permission permission) {
        // 模块授权预留接口
    }

    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private String  id; // 编号
        private String  loginName; // 登录名
        private String  name; // 姓名
        private boolean mobileLogin; // 是否手机登录

//		private Map<String, Object> cacheMap;

        public Principal(String userId, String loginName, String userName, boolean mobileLogin) {
            this.id = userId;
            this.loginName = loginName;
            this.name = userName;
            this.mobileLogin = mobileLogin;
        }

        public String getId() {
            return id;
        }

        public String getLoginName() {
            return loginName;
        }

        public String getName() {
            return name;
        }

        public boolean isMobileLogin() {
            return mobileLogin;
        }

//		@JsonIgnore
//		public Map<String, Object> getCacheMaAp() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

        /**
         * 获取SESSIONID
         */
        public String getSessionid() {
            try {
                return (String) UserUtil.getSession().getId();
            } catch (Exception e) {
                return "";
            }
        }

        @Override
        public String toString() {
            return id;
        }

    }

}
