package com.jims.oauth.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.oauth.entity.App;

/**
 * appDAO接口
 * @author luohk
 * @version 2016-05-30
 */
@MyBatisDao
public interface AppDao extends CrudDao<App> {
    public App selectByPrimaryKey(String appKey);
}