package com.jims.common.utils;


import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * (常用工具类)
 * className: CommonUtil
 * User: zy
 * Date Time: 2015-03-13 13:38
 */
public class CommonUtil {

    /**
     * 是否是验证码登录
     *
     * @param useruame 用户名
     * @param isFail   计数加1
     * @param clean    计数清零
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean isValidateCodeLogin(String useruame, boolean isFail, boolean clean) {
       Map<String, String> loginFailMap;
        loginFailMap = JedisUtils.getMap("loginFailMap");
        if (loginFailMap == null) {
            loginFailMap = Maps.newHashMap();
            JedisUtils.setMap("loginFailMap", loginFailMap, 0);
        }
        Integer loginFailNum = Integer.valueOf(loginFailMap.get(useruame));
        if (loginFailNum == null) {
            loginFailNum = 0;
        }
        if (isFail) {
            // loginFailNum = 3;
           loginFailNum++;
           loginFailMap.put(useruame, loginFailNum.toString());
        }
        if (clean) {
            loginFailMap.remove(useruame);
        }
        boolean result = (loginFailNum.intValue() >= 3);
        return result;
    }

}
