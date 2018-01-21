package com.tentcoo.security.shiro;

import com.tentcoo.log.util.MyLog;
import com.tentcoo.security.utils.UserUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by rover on 2018/1/20.
 */
public class SystemAuthorizingRealm extends AuthorizingRealm {

    private MyLog logger = MyLog.getLog(SystemAuthorizingRealm.class);

    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int SALT_SIZE = 8;
    public static final int HASH_INTERATIONS = 1024;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Principal principal = (Principal) getAvailablePrincipal(principals);
        //todo
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        //todo
        return null;
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
     * @param permission
     */
    private void authorizationValidate(Permission permission){
        // 模块授权预留接口
    }

    public static class Principal implements Serializable {

        private static final long serialVersionUID = 1L;

        private String id; // 编号
        private String loginName; // 登录名
        private String name; // 姓名
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
//		public Map<String, Object> getCacheMap() {
//			if (cacheMap==null){
//				cacheMap = new HashMap<String, Object>();
//			}
//			return cacheMap;
//		}

        /**
         * 获取SESSIONID
         */
        public String getSessionid() {
            try{
                return (String) UserUtil.getSession().getId();
            }catch (Exception e) {
                return "";
            }
        }

        @Override
        public String toString() {
            return id;
        }

    }

}
