package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.api.SalaryLpApi;
import com.jims.fbd.hrm.salary.bo.SalaryLpBo;
import com.jims.fbd.hrm.salary.dao.SalaryLpDao;
import com.jims.fbd.hrm.salary.entity.SalaryLp;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资级别项目imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class SalaryLpImpl implements SalaryLpApi {
    @Autowired
    private SalaryLpBo salaryLpBo;
    @Autowired
    private SalaryLpDao salaryLpDao;

    /**
     * 查询是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryLp> findLpsame(String orgId, String levelId, String projectId) {
        return salaryLpBo.findLpsame(orgId,levelId,projectId);
    }

    /**
     * 删除
     * @param ids
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public String del_lp(String ids) {
        return salaryLpBo.delete(ids);
    }

    /**
     * 查询
     * @return
     * @author 
     */
    @Override
    public Page<SalaryLp> lpList(Page<SalaryLp> page, SalaryLp salaryLp) {
        return salaryLpBo.lpList(page,salaryLp);
    }

    /**
     * 增加数据
     * @param salaryLp
     * @return
     * @author 
     */
    @Override
    public String save(SalaryLp salaryLp) {

        return salaryLpBo.save(salaryLp);
    }

    /**
     * 启用
     * @param lpId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String lpId) {
        salaryLpBo.changeup(lpId);
    }
    /**
     * 停用
     * @param lpId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String lpId) {
        salaryLpBo.changedown(lpId);
    }

    /**
     * 修改数据
     * @param salaryLp
     * @return
     * @author 
     */
    public String update(SalaryLp salaryLp) {
        return salaryLpBo.update(salaryLp);
    }
}
