package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryProjectApi;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 工资项目rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-project")
public class SalaryProjectRest {
    @Reference(version = "1.0.0")
    private SalaryProjectApi salaryProjectApi;

    /**
     * 查询人员类别信息
     *
     * @return
     */
    @Path("project-list")
    @GET
    public PageData projectList(@QueryParam("orgId")String orgId, @QueryParam("projectName") String projectName, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryProject salaryProject=new SalaryProject();
        salaryProject.setOrgId(orgId);
        salaryProject.setProjectName(projectName);
        Page<SalaryProject> page= salaryProjectApi.projectList(new Page<SalaryProject>(request, response),salaryProject);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增类别重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<SalaryProject> findProjectsame(@QueryParam("orgId") String orgId, @QueryParam("projectName") String projectName, @QueryParam("projectId") String projectId) {
        return salaryProjectApi.findProjectsame(orgId,projectName,projectId);
    }

    /**
     * 删除占用判断
     *
     * @return
     */
    @POST
    @Path("if-occupy")
    public StringData findOccupy(List<SalaryProject> projects) {
        String num = salaryProjectApi.findOccupy(projects);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null&&num =="yes") {
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 级别保存和修改
     * @param salaryProject
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryProject salaryProject, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String createDept = loginInfo.getDeptId();
        String num = salaryProjectApi.merge(salaryProject,userName,createDept);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }


    /**
     * 批量删除
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del-project")
    @POST
    public StringData del_project(List<SalaryProject> projects) {
        String num = salaryProjectApi.del_project(projects);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 启用
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("changeUp-enableFlag")
    @POST
    public StringData changeup(@QueryParam("projectId") String projectId) {
        salaryProjectApi.changeup(projectId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 停用
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("changeDown-enableFlag")
    @POST
    public StringData changedown(@QueryParam("projectId") String projectId) {
        salaryProjectApi.changedown(projectId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 启用停用
     * @param projects
     * @return
     * @author 
     */
    @Path("enableFlag")
    @POST
    public StringData enableFlag(List<SalaryProject> projects) {

        String num = salaryProjectApi.enableFlag(projects);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
