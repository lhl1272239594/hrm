
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryProjectMoneyDao;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import com.jims.fbd.hrm.salary.vo.SalaryProjectMoneyVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 工资项目金额BO层
 * @author
 * @version 2016-08-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryProjectMoneyBo extends CrudImplService<SalaryProjectMoneyDao, SalaryProjectMoney> {
    /**
     * 项目金额查询
     * @param salaryProjectMoney
     * @return
     */
    public List<SalaryProjectMoney> moneyList(SalaryProjectMoney salaryProjectMoney) {

        return dao.moneyList(salaryProjectMoney);

    }

    /**
     * 工资项目列表
     * @param orgId
     * @return
     */
    public List<SalaryProject> projectList(String orgId) {

        return dao.projectList(orgId);

    }

    /**
     * 新增修改工资项目金额
     *
     * @return
     * @author
     */
    public List<SalaryProjectMoney> merge(SalaryProjectMoneyVo<SalaryProjectMoney> SalaryProjectMoneyVo, String personId, String createDept) {
        List<SalaryProjectMoney> merge = new ArrayList<SalaryProjectMoney>();
        List<SalaryProjectMoney> inserted = SalaryProjectMoneyVo.getInserted();
        List<SalaryProjectMoney> updated = SalaryProjectMoneyVo.getUpdated();
        String typeId = SalaryProjectMoneyVo.getTypeId();
        //新增
        for (com.jims.fbd.hrm.salary.entity.SalaryProjectMoney SalaryProjectMoney : inserted) {
            SalaryProjectMoney.preInsert();
            int num1=dao.insertSpm(SalaryProjectMoney,typeId,personId,createDept);
        }
        //更新
        for (com.jims.fbd.hrm.salary.entity.SalaryProjectMoney SalaryProjectMoney : updated) {
            SalaryProjectMoney.setUpdateBy(personId);
            SalaryProjectMoney.preUpdate();
            int num = dao.update(SalaryProjectMoney);
        }
        return merge;
    }

    /**
     * 删除工资项目金额
     * @param ids
     * @return
     * @author
     */
    public void del_spm(String ids) {
        dao.del_spm(ids);
    }
}