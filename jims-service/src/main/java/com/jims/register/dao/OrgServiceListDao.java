package com.jims.register.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.OrgServiceList;

import java.util.List;

/**
 * 机构选择服务DAO接口
 * @author lgx
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgServiceListDao extends CrudDao<OrgServiceList> {

    /**
     * 根据机构ID获取机构服务列表
     * @param orgId 机构ID
     * @return 机构服务列表
     * @author fengyuguang
     */
    public List<OrgServiceList> findByOrgId(String orgId);
	
}