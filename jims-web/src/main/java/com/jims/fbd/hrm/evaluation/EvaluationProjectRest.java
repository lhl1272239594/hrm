package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.api.EvaluationProjectApi;
import com.jims.fbd.hrm.evaluation.api.EvaluationStandardApi;
import com.jims.fbd.hrm.evaluation.vo.EvaluationType;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import com.jims.fbd.hrm.exam.api.ExamResultApi;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
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
@Path("project")
public class EvaluationProjectRest {

    @Reference(version = "1.0.0")
    private EvaluationProjectApi evaluationProjectApi;

    /**
     * 查询一级项目
     *
     * @param request
     * @param response
     * @return
     */

    @Path("firstLevelList")
    @GET
    public PageData firstLevelList(@QueryParam("orgId")String orgId,@QueryParam("methodId")String methodId,@QueryParam("name")String name,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        ProjectVo projectVo = new ProjectVo();
        if(!orgId.equals("")&&orgId!=null){
            projectVo.setOrgId(orgId);
        }
        if(!methodId.equals("")&&methodId!=null){
            projectVo.setMethodId(methodId);
        }
        if(!name.equals("")&&name!=null){
            projectVo.setName(name);
        }
        Page<ProjectVo> page= evaluationProjectApi.firstLevelList(new Page<ProjectVo>(request, response), projectVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询二级项目
     *
     * @param request
     * @param response
     * @return
     */

    @Path("secondLevelList")
    @GET
    public PageData secondLevelList(@QueryParam("orgId")String orgId,@QueryParam("id")String id,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        ProjectVo projectVo = new ProjectVo();
        if(!orgId.equals("")&&orgId!=null){
            projectVo.setOrgId(orgId);
        }
        if(!id.equals("")&&id!=null){
            projectVo.setParentId(id);
        }
        Page<ProjectVo> page= evaluationProjectApi.secondLevelList(new Page<ProjectVo>(request, response), projectVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 新增考评项目
     *
     * @param projectVo
     * @return
     */

    @Path("saveProject")
    @POST
    public StringData saveProject(ProjectVo projectVo,  @Context HttpServletRequest request) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        projectVo.setCreateDept(orgStaff.getDeptId());
        String num = evaluationProjectApi.saveProject(projectVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

    /**
     * 修改考评项目状态
     *
     * @param projectVo
     * @return
     */

    @Path("editProject")
    @POST
    public StringData editProject(ProjectVo projectVo) {
        String num = evaluationProjectApi.editProject(projectVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 查询考评类型
     *
     * @return
     */
    @Path("evaluationType")
    @GET
    public PageData projectList(@QueryParam("orgId")String orgId ,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        EvaluationType evaluationType=new EvaluationType();
        if(!orgId.equals("")&&orgId!=null){
            evaluationType.setOrgId(orgId);
        }
        Page<EvaluationType> page= evaluationProjectApi.evaluationType(new Page<EvaluationType>(request, response),evaluationType);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 新增修改考评类型
     *
     * @param evaluationType
     * @return
     */
    @Path("typeMerge")
    @POST
    public StringData typeMerge(EvaluationType evaluationType,  @Context HttpServletRequest request) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        evaluationType.setCreateDept(orgStaff.getDeptId());
        String num = evaluationProjectApi.typeMerge(evaluationType);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 启用停用考评类型
     * @param evaluationTypes
     * @return
     */
    @Path("typeStatus")
    @POST
    public StringData typeStatus(List<EvaluationType> evaluationTypes) {

        String num = evaluationProjectApi.typeStatus(evaluationTypes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 删除考评类型
     * @param evaluationTypes
     * @return
     */
    @Path("delType")
    @POST
    public StringData delType(List<EvaluationType> evaluationTypes) {

        String num = evaluationProjectApi.delType(evaluationTypes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查看考评类型是否被占用
     * @param evaluationTypes
     * @return
     */
    @Path("checkTypeIsUsed")
    @POST
    public StringData checkTypeIsUsed(List<EvaluationType> evaluationTypes) {

        String num = evaluationProjectApi.checkTypeIsUsed(evaluationTypes);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 修改考评类型分类
     * @param evaluationType
     * @return
     */
    @Path("changeType")
    @POST
    public StringData changeType(EvaluationType evaluationType) {

        String num = evaluationProjectApi.changeType(evaluationType);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查看考评项目是否被占用
     * @return
     */
    @Path("checkProjectIsUsed")
    @GET
    public StringData checkProjectIsUsed(@QueryParam("id")String id,@QueryParam("lx")String lx) {

        String num = evaluationProjectApi.checkProjectIsUsed(id,lx);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
