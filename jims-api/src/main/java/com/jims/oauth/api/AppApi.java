package com.jims.oauth.api;

import com.jims.oauth.entity.App;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface AppApi {

    public App selectByPrimaryKey(String appKey);

}
