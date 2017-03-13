package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryProjectMoneyApi;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import com.jims.fbd.hrm.salary.vo.SalaryProjectMoneyVo;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;


/**
 * 工资项目金额rest层
 *
 * @author
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("spm")
public class SalaryProjectMoneyRest {
    @Reference(version = "1.0.0")
    private SalaryProjectMoneyApi salaryProjectMoneyApi;

    /**
     * 工资项目金额
     *
     * @return
     */
    @Path("money-list")
    @GET
    public List<SalaryProjectMoney> moneyList(@QueryParam("orgId")String orgId, @QueryParam("typeId") String typeId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryProjectMoney salaryProjectMoney = new SalaryProjectMoney();
        salaryProjectMoney.setOrgId(orgId);
        salaryProjectMoney.setTypeId(typeId);
        List<SalaryProjectMoney> moneyList = salaryProjectMoneyApi.moneyList(salaryProjectMoney);

        return moneyList;
    }
    /**
     * 工资项目列表
     *
     * @return
     */
    @Path("project-list")
    @GET
    public List<SalaryProject> projectList(@QueryParam("orgId")String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<SalaryProject> projectList = salaryProjectMoneyApi.projectList(orgId);

        return projectList;
    }

    /**
     * 新增修改项目金额
     *
     * @param SalaryProjectMoneyVo
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData merge(SalaryProjectMoneyVo<SalaryProjectMoney> SalaryProjectMoneyVo, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String personId=p.getId();
        String createDept = loginInfo.getDeptId();
        List<SalaryProjectMoney> merge = new ArrayList<SalaryProjectMoney>();
        merge = salaryProjectMoneyApi.merge(SalaryProjectMoneyVo,personId,createDept);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 删除工资项目金额
     *
     * @param ids
     * @return
     * @author 
     */
    @Path("del-spm")
    @POST
    public StringData del_spm(String ids) {
        String num = salaryProjectMoneyApi.del_spm(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
