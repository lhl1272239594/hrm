package com.jims.oauth.api;

import com.jims.oauth.entity.Authority;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface AuthorityApi {

    public Authority findUnique(String appKey, String userId);

    public String save(Authority authority);

    public Authority findAppKey(String appKey);
}
