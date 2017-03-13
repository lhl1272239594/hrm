package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.api.SalaryLpsApi;
import com.jims.fbd.hrm.salary.entity.SalaryLps;
import com.jims.fbd.hrm.salary.vo.SalaryLpsVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 工资管理rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-lps")
public class SalaryLpsRest {
    @Reference(version = "1.0.0")
    private SalaryLpsApi salaryLpsApi;

    /**
     * 查询级别项目金额关系信息
     *
     * @return
     */
    @Path("lps-list")
    @GET
    public PageData lpsList(@QueryParam("orgId")String orgId, @QueryParam("levelId") String levelId, @QueryParam("projectId") String projectId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryLps salaryLps = new SalaryLps();
        salaryLps.setOrgId(orgId);
        salaryLps.setLevelId(levelId);
        salaryLps.setProjectId(projectId);
        Page<SalaryLps> page= salaryLpsApi.lpsList(new Page<SalaryLps>(request, response),salaryLps);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增重复判断
     *
     * @return
     */
    @GET
    @Path("if-lps-exist")
    public List<SalaryLps> findLpssame(@QueryParam("orgId") String orgId, @QueryParam("levelId") String levelId, @QueryParam("projectId") String projectId) {



        return salaryLpsApi.findLpssame(orgId,levelId,projectId);
    }
    /**
     * 关系保存和修改
     * @param salarylpsVo
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryLpsVo<SalaryLps> salarylpsVo) {
        int num = 0;
        int count = 0;
        SalaryLps salaryLps = new SalaryLps();
        salaryLps.setLpsId(salarylpsVo.getLpsId());
        salaryLps.setEnableFlag(salarylpsVo.getEnableFlag());
        salaryLps.setLevelName(salarylpsVo.getLevelName());
        salaryLps.setLevelId(salarylpsVo.getLevelId());
        salaryLps.setProjectId(salarylpsVo.getProjectId());
        salaryLps.setProjectName(salarylpsVo.getProjectName());
        salaryLps.setOrgId(salarylpsVo.getOrgId());

        salaryLps.setMoney(salarylpsVo.getMoney());
        salaryLps.setMultiple(salarylpsVo.getMultiple());

        if(salarylpsVo.getLpsId().equals("")){
            salaryLps.preInsert();
            salaryLpsApi.save(salaryLps);
        }
        else{
            salaryLps.preUpdate();
            salaryLpsApi.update(salaryLps);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }

    /**
     * 删除工资级别信息
     *
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
   @Path("del-lps")
    @POST
    public StringData del_lps(String ids) {
        String num = salaryLpsApi.del_lps(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
