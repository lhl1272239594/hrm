package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryPartApi;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 工资组成部分rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-part")
public class SalaryPartRest {
    @Reference(version = "1.0.0")
    private SalaryPartApi salaryPartApi;

    /**
     * 查询
     *
     * @return
     */
    @Path("part-list")
    @GET
    public PageData partList(@QueryParam("orgId")String orgId, @QueryParam("partName") String partName, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryPart salaryPart=new SalaryPart();
        salaryPart.setOrgId(orgId);
        salaryPart.setPartName(partName);
        Page<SalaryPart> page= salaryPartApi.partList(new Page<SalaryPart>(request, response),salaryPart);
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
    public List<SalaryPart> findPartsame(@QueryParam("orgId") String orgId, @QueryParam("partName") String partName, @QueryParam("partId") String partId) {
        return salaryPartApi.findPartsame(orgId,partName,partId);
    }
    /**
     * 判断社保基数是否已存在
     *
     * @return
     */
    @GET
    @Path("if-sb-exist")
    public List<SalaryPart> findSbsame(@QueryParam("orgId") String orgId) {
        return salaryPartApi.findSbsame(orgId);
    }
    /**
     * 保存和修改
     * @param salaryPart
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryPart salaryPart, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userName=p.getId();
        String createDept = loginInfo.getDeptId();
        String num = salaryPartApi.merge(salaryPart,userName,createDept);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 删除
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del-part")
    @POST
    public StringData del_part(List<SalaryPart> parts) {
        String num = salaryPartApi.del_part(parts);
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
    public StringData findOccupy(List<SalaryPart> parts) {
        String num = salaryPartApi.findOccupy(parts);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (num != null&&num =="yes") {
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 匹配社保基数
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("match-part")
    @POST
    public StringData match_part(List<SalaryPart> parts, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = salaryPartApi.match_part(parts,userName);
        StringData stringData = new StringData();
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
    public StringData changeup(@QueryParam("partId") String partId) {
        salaryPartApi.changeup(partId);
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
    public StringData changedown(@QueryParam("partId") String partId) {
        salaryPartApi.changedown(partId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 启用停用
     * @param parts
     * @return
     * @author 
     */
    @Path("enableFlag")
    @POST
    public StringData enableFlag(List<SalaryPart> parts) {

        String num = salaryPartApi.enableFlag(parts);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 恢复普通工资
     * @param parts
     * @return
     * @author 
     */
    @Path("back-normal")
    @POST
    public StringData back(List<SalaryPart> parts, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userName=p.getId();
        String num = salaryPartApi.back(parts,userName);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
