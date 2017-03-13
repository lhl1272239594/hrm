package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryHumanTypeApi;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 工资级别rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-ht")
public class SalaryHumanTypeRest {
    @Reference(version = "1.0.0")
    private SalaryHumanTypeApi salaryHumanTypeApi;

    /**
     * 查询人员类别信息
     *
     * @return
     */
    @Path("ht-list")
    @GET
    public PageData htList(@QueryParam("orgId")String orgId, @QueryParam("typeName") String typeName, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryHumanType salaryHumanType=new SalaryHumanType();
        salaryHumanType.setOrgId(orgId);
        if(!typeName.equals("")&&typeName!=null){
            salaryHumanType.setTypeName(typeName);
        }
        Page<SalaryHumanType> page= salaryHumanTypeApi.htList(new Page<SalaryHumanType>(request, response),salaryHumanType);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询人员类别信息
     *
     * @return
     */
    @Path("ht-list1")
    @GET
    public List<SalaryHumanType> htList1(@QueryParam("orgId")String orgId) {
        SalaryHumanType salaryHumanType=new SalaryHumanType();
        List<SalaryHumanType> list= salaryHumanTypeApi.htList1(orgId);
        return list;
    }
    /**
     * 新增类别重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<SalaryHumanType> findHtsame(@QueryParam("orgId") String orgId, @QueryParam("typeName") String typeName, @QueryParam("typeId") String typeId) {
        return salaryHumanTypeApi.findHtsame(orgId,typeName,typeId);
    }
    /**
     * 人员类别保存和修改
     * @param salaryHumanType
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryHumanType salaryHumanType, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String createDept = loginInfo.getDeptId();
        String num = salaryHumanTypeApi.merge(salaryHumanType,userName,createDept);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 删除人员类别信息
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del-ht")
    @POST
    public StringData del_ht(List<SalaryHumanType> humanTypes) {
        String num = salaryHumanTypeApi.del_ht(humanTypes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 删除占用判断
     *
     * @return
     */
    @POST
    @Path("if-occupy")
    public StringData findOccupy(List<SalaryHumanType> humanTypes) {
        String num = salaryHumanTypeApi.findOccupy(humanTypes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null&&num =="yes") {
            stringData.setData("success");
        }
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
    public StringData changeup(@QueryParam("typeId") String typeId) {
        salaryHumanTypeApi.changeup(typeId);
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
    public StringData changedown(@QueryParam("typeId") String typeId) {
        salaryHumanTypeApi.changedown(typeId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 启用停用
     * @param humanTypes
     * @return
     * @author 
     */
    @Path("enableFlag")
    @POST
    public StringData enableFlag(List<SalaryHumanType> humanTypes) {

        String num = salaryHumanTypeApi.enableFlag(humanTypes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
