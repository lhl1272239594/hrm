package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.api.SalaryProjectApi;
import com.jims.fbd.hrm.salary.bo.SalaryProjectBo;
import com.jims.fbd.hrm.salary.dao.SalaryProjectDao;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资项目imp层
 *
 * @author 
 * @version 2016-08-31
 */
@Service(version = "1.0.0")
public class SalaryProjectImpl extends CrudImplService<SalaryProjectDao,SalaryProject> implements SalaryProjectApi {
    @Autowired
    private SalaryProjectBo salaryProjectBo;
    @Autowired
    private SalaryProjectDao salaryProjectDao;

    /**
     * 查询是否重复
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public List<SalaryProject> findProjectsame(String orgId, String projectName, String projectId) {
        return salaryProjectBo.findProjectsame(orgId,projectName,projectId);
    }
    /**
     * 删除占用判断
     * @return
     * @author 
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(List<SalaryProject> projects) {
        return salaryProjectBo.findOccupy(projects);
    }

    /**
     * 保存修改
     * @return
     * @author 
     * @version 2016-05-31
     */
    @Override
    public String merge(SalaryProject salaryProject, String userName, String createDept ) {
        return salaryProjectBo.merge(salaryProject,userName,createDept);
    }
    /**
     * 启用
     * @param projectId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changeup(String projectId) {
        salaryProjectBo.changeup(projectId);
    }
    /**
     * 停用
     * @param projectId
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public void changedown(String projectId) {
        salaryProjectBo.changedown(projectId);
    }
    /**
     * 启用停用
     * @param projects
     * @return
     * @author 
     */
    @Override
    public String enableFlag(List<SalaryProject> projects) {
        return salaryProjectBo.enableFlag(projects);
    }
    /**
     * 删除员工类别
     * @param projects
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String del_project(List<SalaryProject> projects) {
        return salaryProjectBo.delete(projects);
    }

    /**
     * 查询人员类别数据
     * @return
     * @author 
     */
    @Override
    public Page<SalaryProject> projectList(Page<SalaryProject> page, SalaryProject salaryProject) {
        return salaryProjectBo.projectList(page,salaryProject);
    }



}
