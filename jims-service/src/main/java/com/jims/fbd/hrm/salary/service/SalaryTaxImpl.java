package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.api.SalaryTaxApi;
import com.jims.fbd.hrm.salary.bo.SalaryTaxBo;
import com.jims.fbd.hrm.salary.dao.SalaryTaxDao;
import com.jims.fbd.hrm.salary.entity.SalaryTax;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 个人所得税税率imp层
 *
 * @author 
 * @version 2016-08-22
 */
@Service(version = "1.0.0")
public class SalaryTaxImpl implements SalaryTaxApi {
    @Autowired
    private SalaryTaxBo salaryTaxBo;
    @Autowired
    private SalaryTaxDao salaryTaxDao;

    /**
     * 查询个人所得税是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryTax> findTaxsame(String orgId, String baseNum, String rate, String lowLimit, String highLimit, String decuteNum, String taxId) {
        return salaryTaxBo.findTaxsame(orgId,baseNum,rate,lowLimit,highLimit,decuteNum,taxId);
    }

    /**
     * 启用
     * @param taxId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String taxId) {
        salaryTaxBo.changeup(taxId);
    }
    /**
     * 停用
     * @param taxId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String taxId) {
        salaryTaxBo.changedown(taxId);
    }
    /**
     * 启用停用
     * @param taxes
     * @return
     * @author 
     */
    @Override
    public String enableFlag(List<SalaryTax> taxes) {
        return salaryTaxBo.enableFlag(taxes);
    }
    /**
     * 删除税率
     * @param taxes
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_tax(List<SalaryTax> taxes) {
        return salaryTaxBo.delete(taxes);
    }

    /**
     * 查询税率数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryTax> taxList(Page<SalaryTax> page, SalaryTax salaryTax) {
        return salaryTaxBo.taxList(page,salaryTax);
    }

    /**
     * 增加数据
     * @param salaryTax
     * @return
     * @author 
     */
    @Override
    public String save(SalaryTax salaryTax) {
        return salaryTaxBo.save(salaryTax);
    }

    /**
     * 修改数据
     * @param salaryTax
     * @return
     * @author 
     */
    @Override
    public String update(SalaryTax salaryTax) {
        return salaryTaxBo.update(salaryTax);
    }
    /**
     * 修改数据
     * @param salaryTax
     * @return
     * @author 
     */
    public String formateBase(SalaryTax salaryTax) {
        return salaryTaxBo.formateBase(salaryTax);
    }

}
