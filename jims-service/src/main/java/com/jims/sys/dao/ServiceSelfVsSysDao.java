package com.jims.sys.dao;

import com.jims.sys.entity.ServiceSelfVsSys;
import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;

/**
* 机构服务自定义对应系统服务Dao
* @author lgx
* @version 2016-07-11
*/
@MyBatisDao
public interface ServiceSelfVsSysDao extends CrudDao<ServiceSelfVsSys> {

    /**
     * 根据自定义服务ID删除
     * @param selfServiceId
     * @return
     */
    public int deleteBySelfServiceId(String selfServiceId);

}