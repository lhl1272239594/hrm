package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryTmpApi;
import com.jims.fbd.hrm.salary.bo.SalaryTmpBo;
import com.jims.fbd.hrm.salary.dao.SalaryTmpDao;
import com.jims.fbd.hrm.salary.entity.SalaryTmp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 创建工资imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class SalaryTmpImpl extends CrudImplService<SalaryTmpDao,SalaryTmp> implements SalaryTmpApi {
    @Autowired
    private SalaryTmpBo salaryTmpBo;

    /**
     * 查询创建工资列表
     * @return
     * @author 
     */
    @Override
    public Page<SalaryTmp> salaryList(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        return salaryTmpBo.salaryList(page,salaryTmp);
    }
    /**
     * 查询待发放工资列表
     * @return
     * @author 
     */
    @Override
    public Page<SalaryTmp> salarytodoList(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        return salaryTmpBo.salarytodoList(page,salaryTmp);
    }
    /**
     * 查询待发放工资列表
     * @return
     * @author 
     */
    @Override
    public Page<SalaryTmp> salarytodoList1(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        return salaryTmpBo.salarytodoList1(page,salaryTmp);
    }
    /**
     * 手工调整
     * @param tmps
     * @return
     * @author 
     */
    @Override
    public String change(List<SalaryTmp> tmps, String userName) {
        return salaryTmpBo.change(tmps,userName);
    }

    /**
     * 创建工资
     * @param salaryTmp
     * @return
     * @author 
     */
    @Override
    public String create(SalaryTmp salaryTmp) {
        return salaryTmpBo.create(salaryTmp);
    }

    /**
     * 重新计算
     * @param tmps
     * @return
     * @author 
     */
    @Override
    public String recal(List<SalaryTmp> tmps) {
        return salaryTmpBo.recal(tmps);
    }

    /**
     * 下发工资(全部)
     * @param salaryTmp
     * @return
     * @author 
     */
    @Override
    public String deal(SalaryTmp salaryTmp) {
        return salaryTmpBo.deal(salaryTmp);
    }
    /**
     * 下发工资(按部门)
     * @param salaryTmp
     * @return
     * @author 
     */
    @Override
    public String dealbyDept(SalaryTmp salaryTmp) {
        return salaryTmpBo.dealbyDept(salaryTmp);
    }
    /**
     * 查询工资历史列表
     * @return
     * @author 
     */
    @Override
    public Page<SalaryTmp> salaryHistorylist(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        return salaryTmpBo.salaryHistorylist(page,salaryTmp);
    }
    /**
     * 查询历史信息
     * @return
     * @author 
     */
    @Override
    public Page<SalaryTmp> salaryHistoryall(Page<SalaryTmp> page, SalaryTmp salaryTmp) {
        return salaryTmpBo.salaryHistoryall(page,salaryTmp);
    }

    /**
     * 查询工资历史列表
     * 我的工资
     * @return
     * @author 
     */
    @Override
    public Page<SalaryTmp> getmypay(Page<SalaryTmp> page, SalaryTmp salaryTmp)
    {
        return salaryTmpBo.getmypay(page,salaryTmp);
    }
    /**
     * 查询是否已下发
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryTmp> findSalarysame(String orgId) {
        return salaryTmpBo.findSalarysame(orgId);
    }
    /**
     * 工资详单
     * @return
     */
    @Override
    public List<SalaryTmp> salaryInfo(SalaryTmp salaryTmp) {
        return salaryTmpBo.salaryInfo(salaryTmp);
    }
    /**
     * 工资详单
     * @return
     */
    @Override
    public List<SalaryTmp> salaryDetail(SalaryTmp salaryTmp) {
        return salaryTmpBo.salaryDetail(salaryTmp);
    }

}
