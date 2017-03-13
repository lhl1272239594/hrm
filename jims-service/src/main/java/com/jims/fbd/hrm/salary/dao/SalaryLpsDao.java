package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryLps;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 薪资管理DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryLpsDao extends CrudDao<SalaryLps> {



    /**
     * 查询级别是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryLps> findLpssame(@Param("orgId") String orgId, @Param("levelId") String levelId, @Param("projectId") String projectId);
    /**
     * 查询工资级别

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryLps> lpsList(@Param("salaryLps") SalaryLps salaryLps);

    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("lpsId") String lpsId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("lpsId") String lpsId);
}