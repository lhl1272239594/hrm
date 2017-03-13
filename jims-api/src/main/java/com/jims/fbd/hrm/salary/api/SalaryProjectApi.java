package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryProject;

import java.util.List;

/**
 * 工资项目api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryProjectApi {


    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryProject> findProjectsame(String orgId, String projectName, String projectId);
    /**
     * 删除占用判断
     * @author 
     * @version 2016-08-22
     * @return
     */
    String findOccupy(List<SalaryProject> projects);
    /**
     * 保存或修改
     * @author 
     * @version 2016-08-31
     * @return
     */
    String merge(SalaryProject salaryProject, String userName, String createDept);
    /**
     * 启用
     * @param projectId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changeup(String projectId);
    /**
     * 停用
     * @param projectId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changedown(String projectId);
    /**
     * 启用停用
     * @param projects
     * @return
     * @author 
     */
    String enableFlag(List<SalaryProject> projects);
    /**
     * 删除
     * @param projects
     * @return
     * @author 
     * @version 2016-08-31
     */
    String del_project(List<SalaryProject> projects);
    /**
     * 查询工资项目列表
     * @return
     * @author 
     * @version 2016-08-20
     */
    Page<SalaryProject> projectList(Page<SalaryProject> page, SalaryProject salaryProject);


}
