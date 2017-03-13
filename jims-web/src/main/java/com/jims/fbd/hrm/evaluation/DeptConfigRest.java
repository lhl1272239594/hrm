package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.api.DeptConfigApi;
import com.jims.fbd.hrm.evaluation.api.EvaluationProjectApi;
import com.jims.fbd.hrm.evaluation.vo.DeptConfig;
import com.jims.fbd.hrm.evaluation.vo.EvaluationType;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.sys.entity.OrgStaff;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("deptConfig")
public class DeptConfigRest {

    @Reference(version = "1.0.0")
    private DeptConfigApi deptConfigApi;

    /**
     * 查询科系
     *
     * @param request
     * @param response
     * @return
     */
    @Path("getParent")
    @GET
    public List<DeptConfig> getParent(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return deptConfigApi.getParent();
    }
    /**
     * 查询已选科室
     *
     * @param request
     * @param response
     * @return
     */
    @Path("getDeptById")
    @GET
    public List<DeptConfig> getDeptById(@QueryParam("id")String id,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return deptConfigApi.getDeptById(id);
    }
    /**
     * 新增修改科系名称
     *
     * @param deptConfig
     * @return
     */
    @Path("deptMerge")
    @POST
    public StringData deptMerge(DeptConfig deptConfig,  @Context HttpServletRequest request) {
        String num = deptConfigApi.deptMerge(deptConfig);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 删除科系名称
     * @param deptConfigs
     * @return
     */
    @Path("delDept")
    @POST
    public StringData delDept(List<DeptConfig> deptConfigs) {

        String num = deptConfigApi.delDept(deptConfigs);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查询科室
     *
     * @param request
     * @param response
     * @return
     */
    @Path("findListByPid")
    @GET
    public List<DeptConfig> findListByPid(@QueryParam("id")String id,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return deptConfigApi.findListByPid(id);
    }
    /**
     * 新增科室
     *
     * @param deptConfig
     * @return
     */
    @Path("Merge")
    @POST
    public StringData Merge(DeptConfig deptConfig,  @Context HttpServletRequest request) {
        String num = deptConfigApi.Merge(deptConfig);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 删除科室
     * @param deptConfig
     * @return
     */
    @Path("remove")
    @POST
    public StringData remove(DeptConfig deptConfig) {

        String num = deptConfigApi.remove(deptConfig);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

}
