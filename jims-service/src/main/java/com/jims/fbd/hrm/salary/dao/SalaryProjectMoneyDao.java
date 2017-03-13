package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工资项目DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryProjectMoneyDao extends CrudDao<SalaryProjectMoney> {



    /**
     * 查询工资项目金额
     * @return
     */
    List<SalaryProjectMoney> moneyList(SalaryProjectMoney salaryProjectMoney);

    /**
     * 工资项目列表
     * @return
     */
    List<SalaryProject> projectList(String orgId);
    /**
     * 新增工资项目金额
     * @return
     */
    int insertSpm(@Param("SalaryProjectMoney") SalaryProjectMoney SalaryProjectMoney, @Param("typeId") String typeId, @Param("personId") String personId, @Param("createDept") String createDept) ;

    /* *
     * 删除
     * @param ids
     * @return
     **/
    void del_spm(@Param("ids") String ids) ;
}