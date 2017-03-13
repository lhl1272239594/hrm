package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryHumanTypeApi;
import com.jims.fbd.hrm.salary.bo.SalaryHumanTypeBo;
import com.jims.fbd.hrm.salary.dao.SalaryHumanTypeDao;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资级别imp层
 *
 * @author 
 * @version 2016-05-31
 */
@Service(version = "1.0.0")
public class SalaryHumanTypeImpl extends CrudImplService<SalaryHumanTypeDao,SalaryHumanType> implements SalaryHumanTypeApi {
    @Autowired
    private SalaryHumanTypeBo salaryHumanTypeBo;
    @Autowired
    private SalaryHumanTypeDao salaryHumanTypeDao;

    /**
     * 查询工资级别是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryHumanType> findHtsame(String orgId, String typeName, String typeId) {
        return salaryHumanTypeBo.findHtsame(orgId,typeName,typeId);
    }

    /**
     * 保存修改
     * @return
     * @author 
     * @version 2016-05-31
     */
    @Override
    public String merge(SalaryHumanType salaryHumanType, String userName, String createDept) {
        return salaryHumanTypeBo.merge(salaryHumanType,userName,createDept);
    }
    /**
     * 启用
     * @param typeId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String typeId) {
        salaryHumanTypeBo.changeup(typeId);
    }
    /**
     * 停用
     * @param typeId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String typeId) {
        salaryHumanTypeBo.changedown(typeId);
    }
    /**
     * 启用停用
     * @param humanTypes
     * @return
     * @author 
     */
    @Override
    public String enableFlag(List<SalaryHumanType> humanTypes) {
        return salaryHumanTypeBo.enableFlag(humanTypes);
    }
    /**
     * 删除员工类别
     * @param humanTypes
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_ht(List<SalaryHumanType> humanTypes) {
        return salaryHumanTypeBo.delete(humanTypes);
    }
    /**
     * 删除占用判断
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(List<SalaryHumanType> humanTypes) {
        return salaryHumanTypeBo.findOccupy(humanTypes);
    }
    /**
     * 查询人员类别数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryHumanType> htList(Page<SalaryHumanType> page, SalaryHumanType salaryHumanType) {
        return salaryHumanTypeBo.htList(page,salaryHumanType);
    }

    /**
     * 查询人员类别数据
     * @return
     * @author 
     */
    @Override
    public List<SalaryHumanType> htList1(String orgId) {
        return salaryHumanTypeBo.htList1(orgId);
    }

}
