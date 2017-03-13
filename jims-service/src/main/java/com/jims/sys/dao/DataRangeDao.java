/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.DeptDict;

import java.util.List;

/**
 * 部门信息DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface DataRangeDao extends CrudDao<DeptDict> {
  /*  *//**
     * 根据部门名称查询部门对象
     * @return 部门实体类对象
     * @author fengyuguang
     *//*
    public List<DeptDict> getByName(String deptName);

    *//**
     * 根据部门名称和组织机构ID查询指定组织机构的部门信息
     * @param deptName 部门名称
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     *//*
    public List<DeptDict> getByNameOrgId(String deptName, String orgId);*/

    /**
     * 查询所有的组织机构信息
     * @return
     */
    public List<DeptDict> findList(String orgId, String serviceId, String menuId);

   /* *//**
     * 查询科室属性信息
     * @return
     *//*
    public List<DeptDict> findProperty();

    *//**
     * 查询上级科室信息
     * @return
     *//*
    public List<DeptDict> findParent();
    *//**
     * 查询某个机构的上级科室
     * @return
     *//*
    public List<DeptDict> findListParent(@Param("orgId") String orgId);

    *//**
     * 查询科室代码下的所以科室
     * @return
     *//*
    public List<DeptDict> findListByCode(@Param("code") String code);

    *//**
     * 查询所有科室
     * @return
     *//*
    public List<DeptDict> getList();

    public List<DeptDict> getOperation(@Param("orgId") String orgId);

    *//**
     * 获取医生的科室
     * @param orgId
     * @param persionId
     * @return
     *//*
    public List<DeptDict> getDoctorDept(@Param("orgId") String orgId, @Param("persionId") String persionId, @Param("doctorGroup") String doctorGroup);
	*/
}