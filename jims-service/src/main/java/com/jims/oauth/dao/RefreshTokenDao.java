package com.jims.oauth.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.oauth.entity.RefreshToken;

/**
 * refresh_tokenDAO接口
 * @author luohk
 * @version 2016-05-30
 */
@MyBatisDao
public interface RefreshTokenDao extends CrudDao<RefreshToken> {
    public RefreshToken findUnique(String appKey, String userId);
}