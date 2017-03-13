
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryTaxDao;
import com.jims.fbd.hrm.salary.entity.SalaryTax;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 个人所得税税率BO层
 * @author
 * @version 2016-08-22
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryTaxBo extends CrudImplService<SalaryTaxDao, SalaryTax> {
    /**
     * 查询税率数据
     * @return
     * @author
     * @version 2016-08-22
     */
    public Page<SalaryTax> taxList(Page<SalaryTax> page, SalaryTax salaryTax ) {
        salaryTax.setPage(page);
        page.setList(dao.taxList(salaryTax));
        return page;
    }

    // 查询税率是否重复
    public List<SalaryTax> findTaxsame(String orgId, String baseNum, String rate, String lowLimit, String highLimit, String decuteNum, String taxId){

        return dao.findTaxsame(orgId,baseNum,rate,lowLimit,highLimit,decuteNum,taxId);
    }
    /**
     *增加方法
     * @param salaryTax
     * @return
     * @author
     */
    public String save(SalaryTax salaryTax) {
        return dao.insert(salaryTax)+"";
    }

    /**
     *修改
     * @param salaryTax
     * @return
     * @author
     */
    public String update(SalaryTax salaryTax) {
        return dao.update(salaryTax)+"";
    }
    /**
     *统一变更起征基数
     * @param salaryTax
     * @return
     * @author
     */
    public String formateBase(SalaryTax salaryTax) {
        return dao.formateBase(salaryTax)+"";
    }
    /**
     *启用
     * @param taxId
     * @return
     * @author
     */
    public void changeup(String taxId) {
        dao.changeup(taxId);
    }
    /**
     *停用
     * @param taxId
     * @return
     * @author
     */
    public void changedown(String taxId) {
        dao.changedown(taxId);
    }
    /**
     * 停用启用
     * @param  taxes
     * @return
     */
    public String enableFlag(List<SalaryTax> taxes) {
        for (SalaryTax q : taxes) {
            q.preUpdate();
            dao.enableFlag(q);
        }
        return "sucsess";
    }

    /**
     * 删除数据
     * @param taxes
     * @return
     * @author
     */
    public String delete(List<SalaryTax> taxes) {
        for (SalaryTax q : taxes) {
            q.preUpdate();
            dao.deleteTax(q);
        }
        return "sucsess";
    }


}