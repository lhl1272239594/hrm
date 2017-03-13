/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.common.utils;

import com.jims.sys.entity.SysUser;
import com.jims.sys.security.SystemAuthorizingRealm.Principal;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.UnavailableSecurityManagerException;
import org.apache.shiro.session.InvalidSessionException;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

/**
 * 用户工具类
 *
 * @author ThinkGem
 * @version 2013-12-05
 */
public class UserUtils {
    public static final String CACHE_AREA_LIST = "areaList";
    public static final String USER_CACHE = "userCache";
    public static final String USER_CACHE_ID_ = "id_";
    public static final String USER_CACHE_LOGIN_NAME_ = "ln";
    public static final String CACHE_ROLE_LIST = "roleList";


    /**
     * 根据ID获取用户
     *
     * @param id
     * @return 取不到返回null
     */
    public static SysUser get(String id) {
        SysUser user = (SysUser) JedisUtils.getObject( USER_CACHE_ID_ + id);
        if (user == null) {
            //user = sysUserApi.selectLoginName(name);
            user = new SysUser();
            if (user == null) {
                return null;
            }

            JedisUtils.setObject(USER_CACHE_ID_ + user.getId(), user,0);
        }
        return user;
    }

    /**
     * 根据登录名获取用户
     *
     * @param idCard
     * @return 取不到返回null
     */
    public static SysUser getByLoginName(String idCard) {
        // 先从缓存中获取，缓存中没有从数据库中查到用户信息，放入缓存
        SysUser user = (SysUser) JedisUtils.getObject( USER_CACHE_LOGIN_NAME_ + idCard);

        return user;
    }



    /**
     * 获取当前用户
     *
     * @return 取不到返回 new User()
     */
    public static SysUser getUser() {
        Principal principal = getPrincipal();
        if (principal != null) {
            SysUser user = get(principal.getId());
            if (user != null) {
                return user;
            }
            return new SysUser();
        }
        // 如果没有登录，则返回实例化空的User对象。
        return new SysUser();
    }

    /**
     * 修改用户缓存
     *
     * @param user
     */
    public static void setUser(SysUser user) {
        JedisUtils.setObject(USER_CACHE,  user,0);
    }

    /**
     * 获取授权主要对象
     */
    public static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登录者对象
     */
    public static Principal getPrincipal() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Principal principal = (Principal) subject.getPrincipal();
            if (principal != null) {
                return principal;
            }
//			subject.logout();
        } catch (UnavailableSecurityManagerException e) {

        } catch (InvalidSessionException e) {

        }
        return null;
    }

    public static Session getSession() {
        try {
            Subject subject = SecurityUtils.getSubject();
            Session session = subject.getSession(false);
            if (session == null) {
                session = subject.getSession();
            }
            if (session != null) {
                return session;
            }
//			subject.logout();
        } catch (InvalidSessionException e) {

        }
        return null;
    }

    // ============== User Cache ==============

    public static Object getCache(String key) {
        return getCache(key, null);
    }

    public static Object getCache(String key, Object defaultValue) {
//		Object obj = getCacheMap().get(key);
        Object obj = getSession().getAttribute(key);
        return obj == null ? defaultValue : obj;
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
