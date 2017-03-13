package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工资项目DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryProjectDao extends CrudDao<SalaryProject> {



    /**
     * 查询是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryProject> findProjectsame(@Param("orgId") String orgId, @Param("projectName") String projectName, @Param("projectId") String projectId);
    /**
     * 删除占用判断
     *
     * @param salaryPart
     * @return
     */
    public int findOccupy(@Param("SalaryProject") SalaryProject salaryPart);

    /**
     * 查询工资项目

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryProject> projectList(SalaryProject salaryProject);
    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("projectId") String projectId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("projectId") String projectId);
    /**
     * 停用启用
     *
     * @param salaryProject
     * @return
     */
    public void enableFlag(@Param("SalaryProject") SalaryProject salaryProject);
    /**
     * 批量删除
     *
     * @param salaryProject
     * @return
     */
    public void deleteProject(@Param("SalaryProject") SalaryProject salaryProject);

    /**
     * 修改
     *
     * @param salaryProject
     * @return
     */
    public void updateProject(@Param("salaryProject") SalaryProject salaryProject);
    /**
     * 新增
     *
     * @param salaryProject
     * @return
     */
    public void insertProject(@Param("salaryProject") SalaryProject salaryProject);

}