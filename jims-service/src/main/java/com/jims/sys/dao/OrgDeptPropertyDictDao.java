/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.entity.SysCompany;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 组织结构DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface OrgDeptPropertyDictDao extends CrudDao<OrgDeptPropertyDict> {

    /**
     * 查询所有的属性类型
     * @return
     */
    public List<OrgDeptPropertyDict> findProperty(@Param("orgId") String orgId);

    /**
     * 根据条件查询所有的属性信息
     * @return
     */
    public List<OrgDeptPropertyDict> findByCondition(OrgDeptPropertyDict orgDeptPropertyDict);



    /**
     * 查询最大的排序值
     * @return
     */
    public OrgDeptPropertyDict findSort(@Param("orgId") String orgId);


}