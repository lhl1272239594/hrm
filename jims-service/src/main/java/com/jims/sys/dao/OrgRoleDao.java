/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.OrgRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 角色表DAO接口
 * @author yangruidong
 * @version 2016-05-31
 */
@MyBatisDao
public interface OrgRoleDao extends CrudDao<OrgRole> {


    /**
     * 根据orgId获取所有的角色
     * @return
     */
    public List<OrgRole> findAllList(String orgId);
    public List<OrgRole> findAllList1(String orgId);
    /**
     * 根据角色名称模糊查询角色信息
     * @param roleName 角色名称
     * @param orgId  组织机构ID
     * @return 角色信息列表
     * @author fengyuguang
     */
    public List<OrgRole> findByRoleName(String roleName,String orgId);


    /**
     * 导入模板数据
     * @param orgRole
     * @return
     */
    public Integer insertByTemplate(OrgRole orgRole);

    /**
     *根据角色名称查询角色的id
     * @param roleName
     * @return
     */
    public List<OrgRole> findByName(@Param("roleName") String roleName,@Param("orgId") String orgId);
	
}