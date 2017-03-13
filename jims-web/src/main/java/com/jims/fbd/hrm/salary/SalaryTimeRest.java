package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.salary.api.SalaryTimeApi;
import com.jims.fbd.hrm.salary.entity.SalaryTime;
import com.jims.sys.entity.PersionInfo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 工资结算日期rest层
 *
 * @author 
 * @version 2016-09-31
 */
@Component
@Produces("application/json")
@Path("salary-time")
public class SalaryTimeRest {
    @Reference(version = "1.0.0")
    private SalaryTimeApi salaryTimeApi;

    /**
     * 查询结算日期
     *
     * @return
     */
    @Path("time-list")
    @GET
    public List<SalaryTime> timeList(@QueryParam("orgId")String orgId, @QueryParam("deptId")String deptId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryTime salaryTime = new SalaryTime();
        salaryTime.setOrgId(orgId);
        salaryTime.setDeptId(deptId);
        List<SalaryTime> timeList = salaryTimeApi.timeList(salaryTime);
        return timeList;
    }
    /**
     * 新增存在判断
     *
     * @return
     */
    @GET
    @Path("if-time-exist")
    public List<SalaryTime> findTimesame(@QueryParam("orgId") String orgId) {

        return salaryTimeApi.findTimesame(orgId);
    }
    /**
     * 保存和修改
     * @param salaryTime
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryTime salaryTime, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userName=p.getId();
        String createDept = loginInfo.getDeptId();
        String num = salaryTimeApi.merge(salaryTime,userName,createDept);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }


}
