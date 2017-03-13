package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryTax;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 个人所得税DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryTaxDao extends CrudDao<SalaryTax> {



    /**
     * 查询税率是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryTax> findTaxsame(@Param("orgId") String orgId, @Param("baseNum") String baseNum, @Param("rate") String rate, @Param("lowLimit") String lowLimit, @Param("highLimit") String highLimit, @Param("decuteNum") String decuteNum, @Param("taxId") String taxId);
    /**
     * 查询个税

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryTax> taxList(SalaryTax salaryTax);
    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("taxId") String taxId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("taxId") String taxId);
    /**
     * 停用启用
     *
     * @param salaryTax
     * @return
     */
    public void enableFlag(@Param("SalaryTax") SalaryTax salaryTax);
    /**
     * 批量删除
     *
     * @param salaryTax
     * @return
     */
    public void deleteTax(@Param("SalaryTax") SalaryTax salaryTax);
    /**
     * 统一变更起征基数
     * @return
     * @author 
     * @version 2016-08-22
     */
    public int formateBase(SalaryTax salaryTax);

}