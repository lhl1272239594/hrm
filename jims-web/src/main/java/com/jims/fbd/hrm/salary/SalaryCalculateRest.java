package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryCalculateApi;
import com.jims.fbd.hrm.salary.entity.SalaryCalculate;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 工资计算公式rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-cal")
public class SalaryCalculateRest {
    @Reference(version = "1.0.0")
    private SalaryCalculateApi salaryCalculateApi;

    /**
     * 查询公式信息
     *
     * @return
     */
    @Path("cal-list")
    @GET
    public PageData calList(@QueryParam("orgId")String orgId, @QueryParam("typeId")String typeId, @QueryParam("partId")String partId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryCalculate salaryCalculate = new SalaryCalculate();
        PageData pageData=new PageData();
        if(!orgId.equals("")&&orgId!=null) {
            salaryCalculate.setOrgId(orgId);
        }
        if(!typeId.equals("")&&typeId!=null){
            salaryCalculate.setTypeId(typeId);
        }
        if(!partId.equals("")&&partId!=null){
            salaryCalculate.setPartId(partId);
        }
        Page<SalaryCalculate> page= salaryCalculateApi.calList(new Page<SalaryCalculate>(request, response),salaryCalculate);
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 工资项目下拉框带回
     *
     * @return
     */
    @Path("find-project")
    @GET
    public List<SalaryProjectMoney> projectList(@QueryParam("orgId")String orgId, @QueryParam("typeId")String typeId) {
        return salaryCalculateApi.projectList(orgId,typeId);
    }
    /**
     * 人员类别下拉框带回
     *
     * @return
     */
    @Path("find-humanType")
    @GET
    public List<SalaryHumanType> htList(@QueryParam("orgId")String orgId) {
        return salaryCalculateApi.htList(orgId);
    }
    /**
     * 工资组成部分下拉框带回
     *
     * @return
     */
    @Path("find-salaryPart")
    @GET
    public List<SalaryPart> partList(@QueryParam("orgId")String orgId) {
        return salaryCalculateApi.partList(orgId);
    }
    /**
     * 绩效考评下拉框带回
     *
     * @return
     */
    @Path("find-performance-type")
    @GET
    public List<SalaryCalculate> performanceList(@QueryParam("orgId")String orgId) {
        return salaryCalculateApi.performanceList(orgId);
    }
    /**
     * 公式重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<SalaryCalculate> findCalsame(@QueryParam("orgId") String orgId, @QueryParam("content") String content, @QueryParam("typeName") String typeName, @QueryParam("partName") String partName) {
        return salaryCalculateApi.findCalsame(orgId,content,typeName,partName);
    }
    /**
     * 公式保存和修改
     * @param salaryCalculate
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryCalculate salaryCalculate, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String createDept = loginInfo.getDeptId();
        String userName=p.getId();
        String num = salaryCalculateApi.merge(salaryCalculate,userName,createDept);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }


    /**
     * 删除计算公式
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del-cal")
    @POST
    public StringData del_cal(List<SalaryCalculate> calculates) {
        String num = salaryCalculateApi.del_cal(calculates);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 启用
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("changeUp-enableFlag")
    @POST
    public StringData changeup(@QueryParam("contentId") String contentId) {
        salaryCalculateApi.changeup(contentId);
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
    public StringData changedown(@QueryParam("contentId") String contentId) {
        salaryCalculateApi.changedown(contentId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 启用停用试题
     * @param calculates
     * @return
     * @author 
     */
    @Path("enableFlag")
    @POST
    public StringData enableFlag(List<SalaryCalculate> calculates) {

        String num = salaryCalculateApi.enableFlag(calculates);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
