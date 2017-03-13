package com.jims.fbd.hrm.salary;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.api.SalaryApi;
import com.jims.fbd.hrm.salary.entity.Salary;
import com.jims.fbd.hrm.salary.vo.SalaryVo;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
@Path("salary")
public class SalaryRest {
    @Reference(version = "1.0.0")
    private SalaryApi salaryApi;

    /**
     * 查询工资级别信息
     *
     * @return
     */
    @Path("level-list")
    @GET
    public PageData levelLists(@QueryParam("orgId")String orgId, @QueryParam("levelName") String levelName, @QueryParam("typeId") String typeId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Salary salary=new Salary();
        salary.setOrgId(orgId);
        salary.setLevelName(levelName);
        salary.setTypeId(typeId);
        Page<Salary> page= salaryApi.levelLists(new Page<Salary>(request, response),salary);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 工资级别名称下拉框带回
     *
     * @return
     */
    @Path("level-downlist")
    @GET
    public List<Salary> levelDownlist(@QueryParam("orgId")String orgId) {
        return salaryApi.levelDownlist(orgId);
    }
    /**
     * 新增级别重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<Salary> findLevelsame(@QueryParam("orgId") String orgId, @QueryParam("typeId") String typeId, @QueryParam("levelName") String levelName) {
        return salaryApi.findLevelsame(orgId,typeId,levelName);
    }
    /**
     * 级别保存和修改
     * @param salaryVo
     * @return
     * @author 
     */
    @Path("merge")
    @POST
    public StringData save(SalaryVo<Salary> salaryVo, @Context HttpServletRequest request) {
        int num = 0;
        int count = 0;
        Salary salary = new Salary();
        HttpSession session = request.getSession();
        SysUser sysUser= (SysUser) session.getAttribute("SysUser");
        PersionInfo p = (PersionInfo) session.getAttribute("PersionInfo");
        String userId=sysUser.getLoginName();
        String userName=p.getName();
        salary.setCreateBy(userName);
        salary.setUpdateBy(userName);
        salary.setLevelName(salaryVo.getLevelName());
        salary.setTypeId(salaryVo.getTypeId());
        salary.setLevelId(salaryVo.getLevelId());
        salary.setOrgId(salaryVo.getOrgId());
        if(salaryVo.getLevelId().equals("")){
            salary.preInsert();
            salaryApi.save(salary);
        }
        else{

            salaryApi.update(salary);
        }
        StringData stringData = new StringData();
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }


    /**
     * 删除工资级别信息
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("del_level")
    @POST
    public StringData del_level(String ids) {
        String num = salaryApi.del_level(ids);
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
    public StringData changeup(@QueryParam("levelId") String levelId) {
        salaryApi.changeup(levelId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }
    /**
     * 停用
     * @param
     * @author 
     * @version 2016-08-20
     * @return
     */
    @Path("changeDown-enableFlag")
    @POST
    public StringData changedown(@QueryParam("levelId") String levelId) {
        salaryApi.changedown(levelId);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }


}
