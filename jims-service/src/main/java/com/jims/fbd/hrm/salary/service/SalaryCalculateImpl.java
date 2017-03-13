package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryCalculateApi;
import com.jims.fbd.hrm.salary.bo.SalaryCalculateBo;
import com.jims.fbd.hrm.salary.dao.SalaryCalculateDao;
import com.jims.fbd.hrm.salary.entity.SalaryCalculate;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资计算公式imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class SalaryCalculateImpl extends CrudImplService<SalaryCalculateDao,SalaryCalculate> implements SalaryCalculateApi {
    @Autowired
    private SalaryCalculateBo salaryCalculateBo;
    @Autowired
    private SalaryCalculateDao salaryCalculateDao;

    /**
     * 查询公式是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryCalculate> findCalsame(String orgId, String content, String typeName, String partName) {
        return salaryCalculateBo.findCalsame(orgId,content, typeName,partName);
    }
    /**
     * 工资项目下拉框带回
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryProjectMoney> projectList(String orgId, String typeId) {
        return salaryCalculateBo.projectList(orgId,typeId);
    }
    /**
     * 工资级别下拉框带回
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryHumanType> htList(String orgId) {
        return salaryCalculateBo.htList(orgId);
    }
    /**
     * 工资组成部分下拉框带回
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryPart> partList(String orgId) {
        return salaryCalculateBo.partList(orgId);
    }
    /**
     * 绩效考评下拉框带回
     * @return
     * @author 
     * @version 2016-09-23
     */
    @Override
    public List<SalaryCalculate> performanceList(String orgId) {
        return salaryCalculateBo.performanceList(orgId);
    }
    /**
     * 新增修改数据
     * @param
     * @return
     * @author 
     */
    @Override
    public String merge(SalaryCalculate salaryCalculate, String userName, String createDept) {
        return salaryCalculateBo.merge(salaryCalculate,userName,createDept);
    }
    /**
     * 启用
     * @param contentId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String contentId) {
        salaryCalculateBo.changeup(contentId);
    }
    /**
     * 停用
     * @param contentId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String contentId) {
        salaryCalculateBo.changedown(contentId);
    }
    /**
     * 启用停用
     * @param calculates
     * @return
     * @author 
     */
    @Override
    public String enableFlag(List<SalaryCalculate> calculates) {
        return salaryCalculateBo.enableFlag(calculates);
    }
    /**
     * 删除工资级别
     * @param calculates
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_cal(List<SalaryCalculate> calculates) {
        return salaryCalculateBo.delete(calculates);
    }

    /**
     * 查询公式数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryCalculate> calList(Page< SalaryCalculate > page, SalaryCalculate salaryCalculate) {
        return salaryCalculateBo.calList(page,salaryCalculate);
    }


}
