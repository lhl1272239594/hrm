package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.api.SalaryLpsApi;
import com.jims.fbd.hrm.salary.bo.SalaryLpsBo;
import com.jims.fbd.hrm.salary.dao.SalaryLpsDao;
import com.jims.fbd.hrm.salary.entity.SalaryLps;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资级别项目金额imp层
 *
 * @author 
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class SalaryLpsImpl implements SalaryLpsApi {
    @Autowired
    private SalaryLpsBo salaryLpsBo;
    @Autowired
    private SalaryLpsDao salaryLpsDao;

    /**
     * 查询工资级别是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryLps> findLpssame(String orgId, String levelId, String projectId) {
        return salaryLpsBo.findLpssame(orgId,levelId,projectId);
    }

    /**
     * 删除工资级别
     * @param ids
     * @return
     * @author 
     * @version 2016-05-31
     */
    @Override
    public String del_lps(String ids) {
        return salaryLpsBo.delete(ids);
    }

    /**
     * 查询工资级别数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryLps> lpsList(Page<SalaryLps> page, SalaryLps salaryLps) {
        return salaryLpsBo.lpsList(page,salaryLps);
    }

    /**
     * 增加数据
     * @param salaryLps
     * @return
     * @author 
     */
    @Override
    public String save(SalaryLps salaryLps) {
        System.out.println(salaryLps.getLevelId());
        return salaryLpsBo.save(salaryLps);
    }

    /**
     * 启用
     * @param lpsId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String lpsId) {
        salaryLpsBo.changeup(lpsId);
    }
    /**
     * 停用
     * @param lpsId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String lpsId) {
        salaryLpsBo.changedown(lpsId);
    }

    /**
     * 修改数据
     * @param salaryLps
     * @return
     * @author 
     */
    public String update(SalaryLps salaryLps) {
        return salaryLpsBo.update(salaryLps);
    }
}
