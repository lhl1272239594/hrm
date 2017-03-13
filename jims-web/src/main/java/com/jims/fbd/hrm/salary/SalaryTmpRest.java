package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.api.SalaryTmpApi;
import com.jims.fbd.hrm.salary.entity.SalaryTmp;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 创建工资REST层
 *
 * @author 
 * @version 2016-09-22
 */
@Component
@Produces("application/json")
@Path("salary-tmp")
public class SalaryTmpRest {
    @Reference(version = "1.0.0")
    private SalaryTmpApi salaryTmpApi;

    /**
     * 查询工资创建列表
     *
     * @return
     */
    @Path("salary-list")
    @GET
    public PageData salaryList(@QueryParam("orgId")String orgId, @QueryParam("personName") String personName, @QueryParam("deptId") String deptId, @QueryParam("typeId") String typeId, @QueryParam("deptIds") String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp=new SalaryTmp();
        salaryTmp.setOrgId(orgId);
        salaryTmp.setPersonName(personName);
        salaryTmp.setDeptId(deptId);
        salaryTmp.setTypeId(typeId);
        salaryTmp.setDeptIds(deptIds);
        Page<SalaryTmp> page= salaryTmpApi.salaryList(new Page<SalaryTmp>(request, response),salaryTmp);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询工资待发放列表
     *
     * @return
     */
    @Path("salary-list-todo")
    @GET
    public PageData salarytodoList(@QueryParam("orgId")String orgId, @QueryParam("personName") String personName, @QueryParam("deptId") String deptId, @QueryParam("typeId") String typeId, @QueryParam("deptIds") String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp=new SalaryTmp();
        salaryTmp.setOrgId(orgId);
        salaryTmp.setPersonName(personName);
        salaryTmp.setDeptId(deptId);
        salaryTmp.setTypeId(typeId);
        salaryTmp.setDeptIds(deptIds);
        Page<SalaryTmp> page= salaryTmpApi.salarytodoList(new Page<SalaryTmp>(request, response),salaryTmp);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询工资待发放列表
     *
     * @return
     */
    @Path("salary-list-todo1")
    @GET
    public PageData salarytodoList1(@QueryParam("orgId")String orgId, @QueryParam("personName") String personName, @QueryParam("deptId") String deptId, @QueryParam("typeId") String typeId, @QueryParam("deptIds") String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp=new SalaryTmp();
        salaryTmp.setOrgId(orgId);
        salaryTmp.setPersonName(personName);
        salaryTmp.setDeptId(deptId);
        salaryTmp.setTypeId(typeId);
        salaryTmp.setDeptIds(deptIds);
        Page<SalaryTmp> page= salaryTmpApi.salarytodoList1(new Page<SalaryTmp>(request, response),salaryTmp);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 手工调整
     * @param tmps
     * @return
     * @author 
     */
    @Path("change")
    @POST
    public StringData change(List<SalaryTmp> tmps, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = salaryTmpApi.change(tmps,userName);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 创建工资
     * @param salaryTmp
     * @return
     * @author 
     */
    @Path("create")
    @POST
    public StringData create(SalaryTmp salaryTmp, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        salaryTmp.setCreateBy(userName);
        String num = salaryTmpApi.create(salaryTmp);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 重新计算
     * @param tmps
     * @return
     * @author 
     */
    @Path("recal")
    @POST
    public StringData recal(List<SalaryTmp> tmps) {

        String num = salaryTmpApi.recal(tmps);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 下发工资(全部)
     * @param salaryTmp
     * @return
     * @author 
     */
    @Path("deal")
    @POST
    public StringData deal(SalaryTmp salaryTmp, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        salaryTmp.setConfirmMan(userName);
        String num = salaryTmpApi.deal(salaryTmp);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 下发工资(按部门)
     * @param salaryTmp
     * @return
     * @author 
     */
    @Path("dealbyDept")
    @POST
    public StringData dealbyDept(SalaryTmp salaryTmp, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        salaryTmp.setConfirmMan(userName);
        String num = salaryTmpApi.dealbyDept(salaryTmp);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查询工资历史表
     *
     * @return
     */
    @Path("salary-history-list")
    @GET
    public PageData salaryHistorylist(@QueryParam("orgId")String orgId, @QueryParam("personName") String personName, @QueryParam("deptId") String deptId, @QueryParam("typeId") String typeId, @QueryParam("salaryMonth") String salaryMonth, @QueryParam("deptIds") String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp=new SalaryTmp();
        salaryTmp.setOrgId(orgId);
        salaryTmp.setPersonName(personName);
        salaryTmp.setDeptId(deptId);
        salaryTmp.setTypeId(typeId);
        salaryTmp.setSalaryMonth(salaryMonth);
        salaryTmp.setDeptIds(deptIds);
        Page<SalaryTmp> page= salaryTmpApi.salaryHistorylist(new Page<SalaryTmp>(request, response),salaryTmp);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询历史信息
     *
     * @return
     */
    @Path("salary-history-all")
    @GET
    public PageData salaryHistoryall(@QueryParam("personId")String personId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp=new SalaryTmp();
        salaryTmp.setPersonId(personId);
        Page<SalaryTmp> page= salaryTmpApi.salaryHistoryall(new Page<SalaryTmp>(request, response),salaryTmp);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 查询工资历史表
     *我的工资使用 2016-9-27
     * @return
     */
    @Path("getmypay")
    @GET
    public PageData getmypay(@QueryParam("salaryMonth") String salaryMonth, @QueryParam("orgId")String orgId, @QueryParam("pid")String pid, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp=new SalaryTmp();
        salaryTmp.setOrgId(orgId);
        salaryTmp.setTmpId(pid);
        salaryTmp.setSalaryMonth(salaryMonth);
        Page<SalaryTmp> page= salaryTmpApi.getmypay(new Page<SalaryTmp>(request, response),salaryTmp);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 判断当月是否已下发
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<SalaryTmp> findSalarysame(@QueryParam("orgId") String orgId) {
        return salaryTmpApi.findSalarysame(orgId);
    }
    /**
     * 工资详单
     *
     * @return
     */
    @Path("salary-history-info")
    @GET
    public List<SalaryTmp> salaryInfo(@QueryParam("orgId")String orgId, @QueryParam("personId")String personId, @QueryParam("typeId") String typeId, @QueryParam("salaryMonth") String salaryMonth, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp = new SalaryTmp();
        salaryTmp.setOrgId(orgId);
        salaryTmp.setPersonId(personId);
        salaryTmp.setTypeId(typeId);
        salaryTmp.setSalaryMonth(salaryMonth);
        List<SalaryTmp> salaryInfo = salaryTmpApi.salaryInfo(salaryTmp);

        return salaryInfo;
    }
    /**
     * 工资详单
     *
     * @return
     */
    @Path("salary-history-detail")
    @GET
    public List<SalaryTmp> salaryDetail(@QueryParam("orgId")String orgId, @QueryParam("personId")String personId, @QueryParam("typeId") String typeId, @QueryParam("salaryMonth") String salaryMonth, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTmp salaryTmp = new SalaryTmp();
        salaryTmp.setOrgId(orgId);
        salaryTmp.setPersonId(personId);
        salaryTmp.setTypeId(typeId);
        salaryTmp.setSalaryMonth(salaryMonth);
        List<SalaryTmp> salaryInfo = salaryTmpApi.salaryDetail(salaryTmp);

        return salaryInfo;
    }
}
