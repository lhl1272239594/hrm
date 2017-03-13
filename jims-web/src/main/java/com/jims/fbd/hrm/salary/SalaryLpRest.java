package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.api.SalaryLpApi;
import com.jims.fbd.hrm.salary.entity.SalaryLp;
import com.jims.fbd.hrm.salary.vo.SalaryLpVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * 工资级别项目rest层
 *
 * @author 
 * @version 2016-08-22
 */
@Component
@Produces("application/json")
@Path("salary-lp")
public class SalaryLpRest {
    @Reference(version = "1.0.0")
    private SalaryLpApi salaryLpApi;

    /**
     * 查询级别项目金额关系信息
     *
     * @return
     */
    @Path("lp-list")
    @GET
    public PageData lpList(@QueryParam("orgId")String orgId, @QueryParam("levelId") String levelId, @QueryParam("projectId") String projectId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        SalaryLp salaryLp = new SalaryLp();
        salaryLp.setOrgId(orgId);
        salaryLp.setLevelId(levelId);
        salaryLp.setProjectId(projectId);
        Page<SalaryLp> page= salaryLpApi.lpList(new Page<SalaryLp>(request, response),salaryLp);

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
    @Path("if-lp-exist")
    public List<SalaryLp> findLpssame(@QueryParam("orgId") String orgId, @QueryParam("levelId") String levelId, @QueryParam("projectId") String projectId) {



        return salaryLpApi.findLpsame(orgId,levelId,projectId);
    }
    /**
     * 关系保存和修改
     * @param salaryLpVo
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryLpVo<SalaryLp> salaryLpVo) {
        int num = 0;
        int count = 0;
        SalaryLp salaryLp = new SalaryLp();
        salaryLp.setLpId(salaryLpVo.getLpId());
        //salaryLp.setLpid(salaryLpVo.getLpId());
        //SalaryLp.setEnableFlag(salarylpsVo.getEnableFlag());
        salaryLp.setLevelName(salaryLpVo.getLevelName());
        salaryLp.setLevelId(salaryLpVo.getLevelId());
        salaryLp.setProjectId(salaryLpVo.getProjectId());
        salaryLp.setProjectName(salaryLpVo.getProjectName());
        salaryLp.setOrgId(salaryLpVo.getOrgId());



        if(salaryLpVo.getLpId().equals("")){
            salaryLp.preInsert();
            salaryLpApi.save(salaryLp);
        }
        else{
            salaryLp.preUpdate();
            salaryLpApi.update(salaryLp);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(num));
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
    public StringData changeup(@QueryParam("lpId") String lpId) {
        salaryLpApi.changeup(lpId);
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
    public StringData changedown(@QueryParam("lpId") String lpId) {
        salaryLpApi.changedown(lpId);
        StringData stringData = new StringData();
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
    @Path("del-lp")
    @POST
    public StringData del_lp(String ids) {
        String num = salaryLpApi.del_lp(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }


}
