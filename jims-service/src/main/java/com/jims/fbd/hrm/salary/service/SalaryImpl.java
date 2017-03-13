package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryApi;
import com.jims.fbd.hrm.salary.bo.SalaryBo;
import com.jims.fbd.hrm.salary.dao.SalaryDao;
import com.jims.fbd.hrm.salary.entity.Salary;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 薪资管理imp层
 *
 * @author 
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class SalaryImpl extends CrudImplService<SalaryDao,Salary> implements SalaryApi {
    @Autowired
    private SalaryBo salaryBo;
    @Autowired
    private SalaryDao salaryDao;

    /**
     * 查询工资级别是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<Salary> findLevelsame(String orgId, String typeId, String levelName) {
        return salaryBo.findLevelsame(orgId,typeId,levelName);
    }

    /**
     * 工资级别下拉框带回
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<Salary> levelDownlist(String orgId) {
        return salaryBo.levelDownlist(orgId);
    }
    /**
     * 保存修改
     * @param salary
     * @return
     * @author 
     * @version 2016-05-31
     */
    @Override
    public String save_level(Salary salary) {

        return salaryBo.save(salary);
    }
    /**
     * 启用
     * @param levelId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String levelId) {
        salaryBo.changeup(levelId);
    }
    /**
     * 停用
     * @param levelId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String levelId) {
        salaryBo.changedown(levelId);
    }
    /**
     * 删除工资级别
     * @param ids
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_level(String ids) {
        return salaryBo.delete(ids);
    }

    /**
     * 查询工资级别数据
     * @return
     * @author 
     */
    @Override
    public Page<Salary> levelLists(Page<Salary> page, Salary salary) {
        return salaryBo.levelLists(page,salary);
    }

    /**
     * 增加数据
     * @param salary
     * @return
     * @author 
     */
    @Override
    public String save(Salary salary) {
        System.out.println(salary.getLevelId());
        return salaryBo.save(salary);
    }

    /**
     * 修改数据
     * @param salary
     * @return
     * @author 
     */
    public String update(Salary salary) {
        return salaryBo.update(salary);
    }

}
