package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryPartApi;
import com.jims.fbd.hrm.salary.bo.SalaryPartBo;
import com.jims.fbd.hrm.salary.dao.SalaryPartDao;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资组成部分imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class SalaryPartImpl extends CrudImplService<SalaryPartDao,SalaryPart> implements SalaryPartApi {
    @Autowired
    private SalaryPartBo salaryPartBo;

    /**
     * 查询是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryPart> findPartsame(String orgId, String partName, String partId) {
        return salaryPartBo.findPartsame(orgId,partName,partId);
    }
    /**
     * 判断社保是否已存在
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryPart> findSbsame(String orgId) {
        return salaryPartBo.findSbsame(orgId);
    }

    /**
     * 保存修改
     * @return
     * @author 
     * @version 2016-05-31
     */
    @Override
    public String merge(SalaryPart salaryPart, String userName, String createDept ) {
        return salaryPartBo.merge(salaryPart,userName,createDept);
    }
    /**
     * 启用
     * @param partId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String partId) {
        salaryPartBo.changeup(partId);
    }
    /**
     * 停用
     * @param partId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String partId) {
        salaryPartBo.changedown(partId);
    }
    /**
     * 启用停用
     * @param parts
     * @return
     * @author 
     */
    @Override
    public String enableFlag(List<SalaryPart> parts) {
        return salaryPartBo.enableFlag(parts);
    }
    /**
     * 恢复普通工资
     * @param parts
     * @return
     * @author 
     */
    @Override
    public String back(List<SalaryPart> parts, String userName) {
        return salaryPartBo.back(parts,userName);
    }
    /**
     * 匹配社保基数
     * @param parts
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String match_part(List<SalaryPart> parts, String userName) {
        return salaryPartBo.match_part(parts,userName);
    }
    /**
     * 批量删除
     * @param parts
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_part(List<SalaryPart> parts) {
        return salaryPartBo.delete(parts);
    }
    /**
     * 删除占用判断
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(List<SalaryPart> parts) {
        return salaryPartBo.findOccupy(parts);
    }
    /**
     * 查询人员类别数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryPart> partList(Page<SalaryPart> page, SalaryPart salaryPart) {
        return salaryPartBo.partList(page,salaryPart);
    }



}
