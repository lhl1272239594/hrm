package com.jims.register.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.register.entity.OrgSelfServiceList;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 机构自定义服务DAO接口
 * @author lgx
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgSelfServiceListDao extends CrudDao<OrgSelfServiceList> {

    /**
     * 检索自定义服务
     * @param personId 人员ID
     * @param orgId    机构ID
     * @return 自定义服务
     * @author fengyuguang
     */
    public List<OrgSelfServiceList> findSelfServiceByOrgIdPersonId(@Param("personId")String personId, @Param("orgId")String orgId);
}