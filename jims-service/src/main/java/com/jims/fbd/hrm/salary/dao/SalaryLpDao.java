package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryLp;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 薪资管理DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryLpDao extends CrudDao<SalaryLp> {



    /**
     * 查询是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryLp> findLpsame(@Param("orgId") String orgId, @Param("levelId") String levelId, @Param("projectId") String projectId);
    /**
     * 查询工资级别

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryLp> lpList(@Param("salaryLp") SalaryLp salaryLp);

    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("lpId") String lpId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("lpId") String lpId);
}