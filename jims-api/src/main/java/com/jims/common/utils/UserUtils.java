/**
 * Copyright &copy; 2012-2014 <a href="https://github.com.jims.emr">JeeSite</a> All rights reserved.
 */
package com.jims.common.utils;


import com.jims.sys.entity.User;

/**
 * 用户工具类
 * @author zhangyao
 * @version 2016-04-06
 */
public class UserUtils {

    /**
     * 根据ID获取用户
     * @param id
     * @return 取不到返回null
     */
    public static User get(String id){

        return new User();
    }
    /**
     * 获取当前登录用户
     */
    public static User getUser(){
        return new User();
    }



}
