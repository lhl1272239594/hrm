package com.jims.fbd.hrm.salary.api;


import com.jims.fbd.hrm.salary.entity.SalaryProject;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import com.jims.fbd.hrm.salary.vo.SalaryProjectMoneyVo;

import java.util.List;

/**
 * 工资项目金额api层
 * @author
 * @version 2016-08-22
 */
public interface SalaryProjectMoneyApi {


    /**
     * 查询工资项目金额
     * @return
     */
    List<SalaryProjectMoney> moneyList(SalaryProjectMoney salaryProjectMoney);

    /**
     * 工资项目下拉框查询
     * @return
     */
    List<SalaryProject> projectList(String orgId);

    /**新增保存
     * @param SalaryProjectMoneyVo
     * @return
     */
    List<SalaryProjectMoney> merge(SalaryProjectMoneyVo<SalaryProjectMoney> SalaryProjectMoneyVo, String personId, String createDept);

    /**
     * 删除方法
     * @param ids
     */
    String del_spm(String ids);
}
