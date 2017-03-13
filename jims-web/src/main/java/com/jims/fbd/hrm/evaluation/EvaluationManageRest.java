package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.evaluation.api.EvaluationApi;
import com.jims.fbd.hrm.evaluation.api.EvaluationManageApi;
import com.jims.fbd.hrm.evaluation.vo.Evaluation;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreDetailVo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;
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
@Path("manage")
public class EvaluationManageRest {

    @Reference(version = "1.0.0")
    private EvaluationManageApi evaluationManageApi;
    /**
     * 查询考评计划
     *
     * @param request
     * @param response
     * @return
     */

    @Path("planList")
    @GET
    public PageData gradeList(@QueryParam("orgId")String orgId,@QueryParam("name")String name,@QueryParam("typeId")String typeId,@QueryParam("state")String state,@QueryParam("deptIds")String deptIds,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        EvaluationPlan evaluationPlan=new EvaluationPlan();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        if(!orgId.equals("")&&orgId!=null){
            evaluationPlan.setOrgId(orgId);
        }
        if(!loginInfo.getPersionId().equals("")&&loginInfo.getPersionId()!=null){
            evaluationPlan.setCreateBy(loginInfo.getPersionId());
        }
        if(!name.equals("")&&name!=null){
            evaluationPlan.setName(name);
        }
        if(!typeId.equals("")&&typeId!=null){
            evaluationPlan.setTypeId(typeId);
        }
        if(!state.equals("")&&state!=null){
            evaluationPlan.setState(state);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            evaluationPlan.setCreateDept(deptIds);
        }
        Page<EvaluationPlan> page= evaluationManageApi.planList(new Page<EvaluationPlan>(request, response), evaluationPlan);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询已结束考评
     *
     * @param request
     * @param response
     * @return
     */

    @Path("resultList")
    @GET
    public PageData resultList(@QueryParam("orgId")String orgId,@QueryParam("name")String name,@QueryParam("typeId")String typeId,@QueryParam("state")String state,@QueryParam("deptIds")String deptIds,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        EvaluationPlan evaluationPlan=new EvaluationPlan();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        if(!orgId.equals("")&&orgId!=null){
            evaluationPlan.setOrgId(orgId);
        }
        if(!loginInfo.getPersionId().equals("")&&loginInfo.getPersionId()!=null){
            evaluationPlan.setCreateBy(loginInfo.getPersionId());
        }
        if(!name.equals("")&&name!=null){
            evaluationPlan.setName(name);
        }
        if(!typeId.equals("")&&typeId!=null){
            evaluationPlan.setTypeId(typeId);
        }
        if(!state.equals("")&&state!=null){
            evaluationPlan.setState(state);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            evaluationPlan.setCreateDept(deptIds);
        }
        Page<EvaluationPlan> page= evaluationManageApi.resultList(new Page<EvaluationPlan>(request, response), evaluationPlan);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 评分汇总
     * @param evaluationPlan
     * @return
     */
    @Path("summaryData")
    @POST
    public StringData summaryData(EvaluationPlan evaluationPlan) {

        String num = evaluationManageApi.summaryData(evaluationPlan);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 发布成绩
     * @param evaluationPlan
     * @return
     */
    @Path("publish")
    @POST
    public StringData publish(EvaluationPlan evaluationPlan) {
        String num = evaluationManageApi.publish(evaluationPlan);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查询成绩
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getScore")
    @GET
    public List<EvaluationScoreVo> getScore(@QueryParam("planId")String planId, @QueryParam("obj")String obj,@QueryParam("state")String state, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        return evaluationManageApi.getScore(planId,obj,state);
    }
    /**
     * 按权限获取成绩头表
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getResultScore")
    @GET
    public List<EvaluationScoreVo> getResultScore(@QueryParam("planId")String planId, @QueryParam("obj")String obj,@QueryParam("state")String state,@QueryParam("deptIds")String deptIds, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        return evaluationManageApi.getResultScore(planId,obj,state,deptIds);
    }
    /**
     * 查看评分详情
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getScoreList")
    @POST
    public Evaluation getScoreList(EvaluationScoreVo evaluationScoreVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Evaluation evaluation = evaluationManageApi.getScoreList(evaluationScoreVo);
        if (evaluation != null) {
            evaluation.setData("success");
            return evaluation;
        }
        return null;
    }
    /**
     * 获取考评标准
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getDetail")
    @GET
    public PageData getDetail(@QueryParam("id") String id,@QueryParam("state")  String state,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        EvaluationScoreDetailVo es=new EvaluationScoreDetailVo();
        es.setType(state);
        es.setId(id);
        Page<EvaluationScoreDetailVo> page = evaluationManageApi.getDetail(new Page<EvaluationScoreDetailVo>(request, response),es);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 删除考评计划
     * @param request
     * @param response
     * @return
     */

    @Path("del")
    @POST
    public StringData del(EvaluationPlan evaluationPlan, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String num = evaluationManageApi.del(evaluationPlan);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
