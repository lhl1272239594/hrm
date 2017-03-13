
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryProjectDao;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资项目BO层
 *
 * @author
 * @version 2016-08-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryProjectBo extends CrudImplService<SalaryProjectDao, SalaryProject> {
    /**
     * 查询工资项目数据
     * @return
     * @author
     * @version 2016-08-22
     */

    public Page<SalaryProject> projectList(Page<SalaryProject> page, SalaryProject salaryProject) {
        salaryProject.setPage(page);
        page.setList(dao.projectList(salaryProject));
        return page;
    }
    /**
     *查询工资项目是否重复
     * @return
     * @author 
     */
    public List<SalaryProject> findProjectsame(String orgId, String projectName, String projectId){
        return dao.findProjectsame(orgId,projectName,projectId);
    }
    /**
     * 删除占用判断
     * @return
     * @author
     * @version 2016-08-23
     */
    public String findOccupy(List<SalaryProject> projects) {
        String result = "no";
        for (SalaryProject q : projects) {

            int sum = dao.findOccupy(q);
            if (sum>1||sum==1){
                result = "yes";
                break;
            }
        }
        return result;
    }

    /**
     * 新增修改保存
     * @return
     * @author
     */
    public String merge(SalaryProject salaryProject, String userName, String createDept) {
        //判断主键id是否为空，不为空则更新
        if (org.apache.commons.lang3.StringUtils.isNotBlank(salaryProject.getProjectId())){
            salaryProject.preUpdate();
            salaryProject.setUpdateBy(userName);
            dao.updateProject(salaryProject);
            return "edit";
        }
        else{
            salaryProject.preInsert();
            salaryProject.setProjectId(salaryProject.getProjectId());
            salaryProject.setCreateBy(userName);
            salaryProject.setUpdateBy(userName);
            salaryProject.setCreateDept(createDept);
            dao.insertProject(salaryProject);
            return "add";
        }
    }
    /**
     * 启用
     * @param projectId
     * @return
     * @author
     */
    public void changeup(String projectId) {
         dao.changeup(projectId);
    }
    /**
     * 停用
     * @param projectId
     * @return
     * @author
     */
    public void changedown(String projectId) {
        dao.changedown(projectId);
    }
    /**
     * 停用启用
     * @param  projects
     * @return
     */
    public String enableFlag(List<SalaryProject> projects) {
        for (SalaryProject q : projects) {
            q.preUpdate();
            dao.enableFlag(q);
        }
        return "sucsess";
    }
    /**
     * 删除数据
     * @param projects
     * @return
     * @author 
     */
    public String delete(List<SalaryProject> projects) {
        for (SalaryProject q : projects) {
            q.preUpdate();
            dao.deleteProject(q);
        }
        return "sucsess";
    }

}