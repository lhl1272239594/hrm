package com.jims.oauth.api;

import com.jims.oauth.entity.RefreshToken;

/**
 * Created by Administrator on 2016/5/30.
 */
public interface RefreshTokenApi {

    public RefreshToken findUnique(String appKey, String userId);

    public String save(RefreshToken refreshToken);

    public String update(RefreshToken refreshToken);
}
