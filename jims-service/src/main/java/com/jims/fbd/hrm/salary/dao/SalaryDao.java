package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.Salary;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 薪资管理DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryDao extends CrudDao<Salary> {



    /**
     * 查询级别是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<Salary> findLevelsame(@Param("orgId") String orgId, @Param("typeId") String typeId, @Param("levelName") String levelName);

    /**
     * 工资级别名称下拉框带回

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<Salary> levelDownlist(@Param("orgId") String orgId);
    /**
     * 查询工资级别

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<Salary> levelList(Salary salary);
    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("levelId") String levelId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("levelId") String levelId);

}