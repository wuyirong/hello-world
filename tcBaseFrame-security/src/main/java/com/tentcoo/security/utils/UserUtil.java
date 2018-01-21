package com.tentcoo.security.utils;

import com.tentcoo.security.shiro.SystemAuthorizingRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * Created by rover on 2018/1/20.
 */
public class UserUtil {

    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "ln";
    public static final String USER_CACHE_LIST_BY_OFFICE_ID_ = "oid_";

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static SystemAuthorizingRealm.Principal getPrincipal(){
        try{
            Subject subject = SecurityUtils.getSubject();
            SystemAuthorizingRealm.Principal principal = (SystemAuthorizingRealm.Principal)subject.getPrincipal();
            if (principal != null){
                return principal;
            }
//			subject.logout();
        }catch (UnavailableSecurityManagerException e) {

        }catch (InvalidSessionException e){

        }
        return null;
    }

    public static Session getSession(){
        try{
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null){
                session = subject.getSession();
            }
            if (session != null){
                return session;
            }
//			subject.logout();
        }catch (InvalidSessionException e){

        }
        return null;
    }


    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
        Object obj = getSession().getAttribute(key);
        return obj==null?defaultValue:obj;
    }

    public static void putCache(String key, Object value) {
//		getCacheMap().put(key, value);
        getSession().setAttribute(key, value);
    }

    public static void removeCache(String key) {
//		getCacheMap().remove(key);
        getSession().removeAttribute(key);
    }

}
