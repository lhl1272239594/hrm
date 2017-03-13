package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryTax;

import java.util.List;

/**
 * 个人所得税税率api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryTaxApi {

    //根据参数查询新增数据是否重复
    public List<SalaryTax> findTaxsame(String orgId, String baseNum, String rate, String lowLimit, String highLimit, String decuteNum, String taxId);


   /**
     * 保存修改数据
     * @param salaryTax
     * @return
     */
    public String save(SalaryTax salaryTax);
    public String update(SalaryTax salaryTax);
 /**
  * 统一变更起征基数
  * @param salaryTax
  * @return
  */
    public String formateBase(SalaryTax salaryTax);

    /**
     * 删除
     * @param taxes
     * @return
     * @author 
     * @version 2016-08-31
     */
    public String del_tax(List<SalaryTax> taxes);
    /**
     * 启用
     * @param taxId
     * @return
     * @author 
     * @version 2016-08-31
     */
    public void changeup(String taxId);
    /**
     * 停用
     * @param taxId
     * @return
     * @author 
     * @version 2016-08-31
     */
    public void changedown(String taxId);
    /**
     * 启用停用
     * @param taxes
     * @return
     * @author 
     */
    public String enableFlag(List<SalaryTax> taxes);
    /**
     * 查询税率全部列表
     * @return
     * @author 
     * @version 2016-08-20
     */

    public Page<SalaryTax> taxList(Page<SalaryTax> page, SalaryTax salaryTax);


}
