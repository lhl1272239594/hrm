package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.evaluation.api.EvaluationApi;
import com.jims.fbd.hrm.evaluation.api.EvaluationQueryApi;
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
@Path("evaluationQuery")
public class EvaluationQueryRest {

    @Reference(version = "1.0.0")
    private EvaluationQueryApi evaluationQueryApi;
    /**
     * 查询评分列表
     *
     * @param request
     * @param response
     * @return
     */

    @Path("myEvaluation")
    @GET
    public PageData myEvaluation(@QueryParam("orgId")String orgId,@QueryParam("name")String name,@QueryParam("typeId")String typeId,@QueryParam("month")String month,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        EvaluationScoreVo evaluationScoreVo=new EvaluationScoreVo();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        evaluationScoreVo.setUserId(loginInfo.getPersionId());
        evaluationScoreVo.setOrgId(orgId);
        if(!month.equals("")&&month!=null){
            evaluationScoreVo.setMonth(month);
        }
        if(!name.equals("")&&name!=null){
            evaluationScoreVo.setPlanName(name);
        }
        if(!typeId.equals("")&&typeId!=null){
            evaluationScoreVo.setTypeId(typeId);
        }
        Page<EvaluationScoreVo> page= evaluationQueryApi.myEvaluation(new Page<EvaluationScoreVo>(request, response), evaluationScoreVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询评分列表
     *
     * @param request
     * @param response
     * @return
     */

    @Path("myGrade")
    @GET
    public PageData myGrade(@QueryParam("orgId")String orgId,@QueryParam("name")String name,@QueryParam("typeId")String typeId,@QueryParam("month")String month,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        EvaluationPlan evaluationPlan=new EvaluationPlan();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        evaluationPlan.setCreateBy(loginInfo.getPersionId());
        evaluationPlan.setOrgId(orgId);
        if(!month.equals("")&&month!=null){
            evaluationPlan.setExpiryDate(month);
        }
        if(!name.equals("")&&name!=null){
            evaluationPlan.setName(name);
        }
        if(!typeId.equals("")&&typeId!=null){
            evaluationPlan.setTypeId(typeId);
        }
        Page<EvaluationPlan> page= evaluationQueryApi.myGrade(new Page<EvaluationPlan>(request, response), evaluationPlan);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查看评分
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getScore")
    @POST
    public Evaluation getScore(EvaluationPlan evaluationPlan, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        Evaluation evaluation = evaluationQueryApi.getScore(evaluationPlan,loginInfo.getPersionId());
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
    public List<EvaluationScoreDetailVo> getDetail(@QueryParam("id") String id,@QueryParam("state") String state,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<EvaluationScoreDetailVo> es= evaluationQueryApi.getDetail(id,state);
        return es;
    }
}
