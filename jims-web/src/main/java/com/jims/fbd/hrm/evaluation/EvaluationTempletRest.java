package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.evaluation.api.EvaluationTempletApi;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
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
@Path("templet")
public class EvaluationTempletRest {

    @Reference(version = "1.0.0")
    private EvaluationTempletApi evaluationTempletApi;
    /**
     * 查询考评模板
     *
     * @param request
     * @param response
     * @return
     */

    @Path("templetList")
    @GET
    public PageData templetList(@QueryParam("name")String name,@QueryParam("orgId")String orgId,@QueryParam("typeId")String typeId,@QueryParam("startType")String startType,@QueryParam("deptIds") String deptIds,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        TempletVo TempletVo = new TempletVo();
        if(!name.equals("")&&name!=null){
            TempletVo.setName(name);
        }
        if(!orgId.equals("")&&orgId!=null){
            TempletVo.setOrgId(orgId);
        }
        if(!typeId.equals("")&&typeId!=null){
            TempletVo.setTypeId(typeId);
        }
        if(!startType.equals("")&&startType!=null){
            TempletVo.setStartType(startType);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            TempletVo.setCreateDept(deptIds);
        }
        Page<TempletVo> page= evaluationTempletApi.templetList(new Page<TempletVo>(request, response), TempletVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询考评类型
     *
     * @return
     */
    @GET
    @Path("evaluationType")
    public List<EvaluationType> questionType(@QueryParam("orgId")String orgId) {
        return evaluationTempletApi.evaluationType(orgId);
    }
    /**
     * 查询考评项目
     *
     * @param request
     * @param response
     * @return
     */

    @Path("projectList")
    @GET
    public List<ProjectVo> projectList(@QueryParam("orgId") String orgId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        ProjectVo projectVo = new ProjectVo();
        if(!orgId.equals("")&&orgId!=null){
            projectVo.setOrgId(orgId);
        }
        List<ProjectVo> projectVos= evaluationTempletApi.projectList(projectVo);
        return projectVos;
    }
    /**
     * 新增修改模板
     *
     * @param templetVo
     * @return
     */
    @Path("templetMerge")
    @POST
    public StringData templetMerge(TempletVo templetVo, @Context HttpServletRequest request) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        templetVo.setCreateDept(orgStaff.getDeptId());
        String num = evaluationTempletApi.templetMerge(templetVo);
        if(num!=null&&num.equals("hasName")){
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        String self=templetVo.getSelf();
        String obj=templetVo.getObj();
        String type=templetVo.getType();
        evaluationTempletApi.removePerson(num);
        if(obj.equals("1")&&self.equals("0")){
            List<PersonVo> dept=templetVo.getDept();
            List<PersonVo> user=templetVo.getUser();
            if(dept.size()>0){
                TempletVo t=new TempletVo();
                t.setType("1");
                t.setId(num);
                t.setSelf(self);
                if(self.equals("1")){
                    t.setPersonVos(user);
                }
                else {
                    t.setPersonVos(dept);
                }
                evaluationTempletApi.savePerson(t);
            }
        }
        if(obj.equals("1")&&self.equals("1")){
            List<PersonVo> user=templetVo.getUser();
            if(user.size()>0){
                TempletVo t=new TempletVo();
                t.setType("1");
                t.setSelf(self);
                t.setId(num);
                t.setPersonVos(user);
                evaluationTempletApi.savePerson(t);
            }
        }
        if(obj.equals("2")){
            List<PersonVo> user=templetVo.getUser();
            if(user.size()>0){
                TempletVo t=new TempletVo();
                t.setType("2");
                t.setId(num);
                t.setPersonVos(user);
                evaluationTempletApi.savePerson(t);
            }
        }
        if(self.equals("0")){
            List<PersonVo> grade=templetVo.getGrade();
            if(grade.size()>0){
                TempletVo t=new TempletVo();
                t.setType("2");
                t.setId(num);
                t.setPersonVos(grade);
                evaluationTempletApi.savePerson(t);
            }
        }
        if(type.equals("3")){
            List<StandardVo> standardVos=templetVo.getStandard();
            if(standardVos.size()>0){
                evaluationTempletApi.clearScore(num);
                TempletStandardVo t=new TempletStandardVo();
                t.setId(num);
                t.setStandardVos(templetVo.getStandard());
                evaluationTempletApi.saveStandard(t);
            }
        }
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 根据项目查询标准
     *
     * @param request
     * @param response
     * @return
     */

    @Path("standardByProject")
    @POST
    public List<StandardVo> standardByProject(StandardVo standardVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        standardVo.setCreateBy(orgStaff.getPersionId());
        List<StandardVo> standardVos= evaluationTempletApi.standardByProject(standardVo);
        return standardVos;
    }
    /**
     * 修改考评模板状态
     *
     * @param templetVo
     * @return
     */

    @Path("templetState")
    @POST
    public StringData templetState(TempletVo templetVo) {
        String num = evaluationTempletApi.templetState(templetVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 保存考评标准
     * @param templetStandardVo
     * @return
     */
    @Path("saveStandard")
    @POST
    public StringData saveStandard(TempletStandardVo templetStandardVo) {

        String num = evaluationTempletApi.saveStandard(templetStandardVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查看已选考评标准
     *
     * @param request
     * @param response
     * @return
     */

    @Path("templetStandard")
    @GET
    public List<StandardVo> templetStandard(@QueryParam("orgId") String orgId, @QueryParam("templetId") String templetId ,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<StandardVo> standardVos= evaluationTempletApi.templetStandard(orgId,templetId);
        return standardVos;
    }
    /**
     * 我的考评标准
     *
     * @param request
     * @param response
     * @return
     */

    @Path("myStandard")
    @GET
    public List<StandardVo> myStandard(@QueryParam("name") String name,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        List<StandardVo> standardVos= evaluationTempletApi.myStandard(orgStaff.getPersionId(),name);
        return standardVos;
    }
    /**
     * 查看模板授权
     *
     * @param templetVo
     * @return
     */

    @Path("checkAuthorize")
    @POST
    public StringData checkAuthorize(TempletVo templetVo) {
        String num = evaluationTempletApi.checkAuthorize(templetVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
   /* *//**
     * 删除考评标准
     *
     * @param templetStandardVo
     * @return
     *//*

    @Path("delStandard")
    @POST
    public StringData delStandard(TempletStandardVo templetStandardVo) {
        String num = evaluationTempletApi.delStandard(templetStandardVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }*/
    /**
     * 查看授权人员
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getPersonById")
    @GET
    public List<PersonVo> getPersonById(@QueryParam("templetId") String templetId,@QueryParam("type") String type, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<PersonVo> standardPersonVos= evaluationTempletApi.getPersonById(templetId,type);
        return standardPersonVos;
    }
    /**
     * 保存考评对象
     *
     * @param request
     * @param response
     * @return
     */

    @Path("savePerson")
    @POST
    public StringData savePerson(TempletVo templetVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String num = evaluationTempletApi.savePerson(templetVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
   /* *//**
     * 删除考评对象
     *
     * @param request
     * @param response
     * @return
     *//*

    @Path("removePerson")
    @POST
    public StringData removePerson(TempletVo templetVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String num = evaluationTempletApi.removePerson(templetVo);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }*/
    /**
     * 启动考评
     *
     * @param request
     * @param response
     * @return
     */

    @Path("templetPublish")
    @POST
    public StringData templetPublish(TempletVo templetVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String num = evaluationTempletApi.templetPublish(templetVo,loginInfo.getPersionId());
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }

}
