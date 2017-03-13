package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryTime;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工资结算日期DAO接口
 * @author 
 * @version 2016-09-22
 */
@MyBatisHrmDao
public interface SalaryTimeDao extends CrudDao<SalaryTime> {



    /**
     * 查询是否已存在

     * @return
     * @author 
     * @version 2016-09-22
     */
    List<SalaryTime> findTimesame(@Param("orgId") String orgId);
    /**
     * 查询

     * @return
     * @author 
     * @version 2016-08-22
     */
    List<SalaryTime> timeList(SalaryTime salaryTime);
    /**
     * 修改
     *
     * @param salaryTime
     * @return
     */
    void updateTime(@Param("SalaryTime") SalaryTime salaryTime);
    /**
     * 新增
     *
     * @param salaryTime
     * @return
     */
    void insertTime(@Param("SalaryTime") SalaryTime salaryTime);

}