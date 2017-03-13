package com.jims.oauth.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.oauth.entity.Authority;

/**
 * authorityDAO接口
 * @author luohk
 * @version 2016-05-30
 */
@MyBatisDao
public interface AuthorityDao extends CrudDao<Authority> {

    public Authority findUnique(String appKey, String userId);

    public Authority findAppKey(String appKey);
}