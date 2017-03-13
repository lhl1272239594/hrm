/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.entity.DeptDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 部门信息DAO接口
 * @author yangruidong
 * @version 2016-04-13
 */
@MyBatisDao
public interface DeptDictDao extends CrudDao<DeptDict> {
    /**
     * 根据部门名称查询部门对象
     * @return 部门实体类对象
     * @author fengyuguang
     */
    public List<DeptDict> getByName(String deptName);

    /**
     * 根据部门名称和组织机构ID查询指定组织机构的部门信息
     * @param deptName 部门名称
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<DeptDict> getByNameOrgId(@Param("deptName")String deptName,@Param("orgId")String orgId);

    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAll(String orgId);

    /**
     *查询某个科室
     * @param orgId
     * @return
     */
    public List<DeptDict>  getByOrgId(@Param("orgId") String orgId) ;

    /**
     * 查询科室属性信息
     * @return
     */
    public List<DeptDict> findProperty();

    /**
     * 查询上级科室信息
     * @return
     */
    public List<DeptDict> findParent();
    /**
     * 查询某个机构的上级科室
     * @return
     */
    public List<DeptDict> findListParent(@Param("orgId") String orgId,@Param("q") String q,@Param("deptCode") String deptCode);

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(@Param("code")String code);

    /**
     * 查询所有科室
     * @return
     */
    public List<DeptDict> getList();

    public List<DeptDict> getOperation(@Param("orgId")String orgId);

    /**
     * 获取医生的科室
     * @param orgId
     * @param persionId
     * @return
     */
    public List<DeptDict> getDoctorDept(@Param("orgId")String orgId,@Param("persionId")String persionId,@Param("doctorGroup")String doctorGroup);

    /**
     * 获取科室中的医生
     * @param orgId
     * @param doctorGroup
     * @param deptId
     * @return
     */
    public List<BaseDto> getDoctor(@Param("orgId")String orgId,@Param("doctorGroup")String doctorGroup,@Param("deptId")String deptId);



    /**
     * 查询病房
     * @param orgId
     * @param q
     * @return
     */
    public List<DeptDict> findListOfInpatients(@Param("orgId")String orgId,@Param("q") String q);

    /**
     * 查询当前登陆人所属科室
     * @param orgId
     * @param personId
     * @return
     */
    public List<DeptDict> findByPersonId(String orgId, String personId);
}