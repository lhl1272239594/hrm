package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryProjectMoneyApi;
import com.jims.fbd.hrm.salary.bo.SalaryProjectMoneyBo;
import com.jims.fbd.hrm.salary.dao.SalaryProjectMoneyDao;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import com.jims.fbd.hrm.salary.vo.SalaryProjectMoneyVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资项目金额imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class SalaryProjectMoneyImpl extends CrudImplService<SalaryProjectMoneyDao,SalaryProjectMoney> implements SalaryProjectMoneyApi {
    @Autowired
    private SalaryProjectMoneyBo salaryProjectMoneyBo;
    @Autowired
    private SalaryProjectMoneyDao salaryProjectMoneyDao;

    /**
     * 工资项目金额
     *
     * @return
     */
    @Override
    public List<SalaryProjectMoney> moneyList(SalaryProjectMoney salaryProjectMoney) {

        return salaryProjectMoneyBo.moneyList(salaryProjectMoney);
    }

    /**
     * 工资项目列表
     *
     * @return
     */
    @Override
    public List<SalaryProject> projectList(String orgId) {

        return salaryProjectMoneyBo.projectList(orgId);
    }

    /**
     * 新增修改工资项目金额
     *
     * @param SalaryProjectMoneyVo
     * @return
     * @author 
     */
    @Override
    public List<SalaryProjectMoney> merge(SalaryProjectMoneyVo<SalaryProjectMoney> SalaryProjectMoneyVo, String personId, String createDept) {

        return salaryProjectMoneyBo.merge(SalaryProjectMoneyVo, personId, createDept);
    }

    /**
     * 删除工资项目金额
     *
     * @param ids
     * @return
     * @author 
     */
    @Override
    public String del_spm(String ids) {
        try {
            salaryProjectMoneyBo.del_spm(ids);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
}
