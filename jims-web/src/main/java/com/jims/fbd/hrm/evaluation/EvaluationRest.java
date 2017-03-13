package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.evaluation.api.EvaluationApi;
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
import java.util.ArrayList;
import java.util.List;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("evaluation")
public class EvaluationRest {

    @Reference(version = "1.0.0")
    private EvaluationApi evaluationApi;
    /**
     * 查询评分列表
     *
     * @param request
     * @param response
     * @return
     */

    @Path("gradeList")
    @GET
    public PageData gradeList(@QueryParam("orgId")String orgId,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        EvaluationPlan evaluationPlan=new EvaluationPlan();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        evaluationPlan.setCreateBy(loginInfo.getPersionId());
        evaluationPlan.setOrgId(orgId);
        if(!orgId.equals("")&&orgId!=null){
            evaluationPlan.setOrgId(orgId);
        }
        Page<EvaluationPlan> page= evaluationApi.gradeList(new Page<EvaluationPlan>(request, response), evaluationPlan);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 开始评分
     *
     * @param request
     * @param response
     * @return
     */

    @Path("startGrade")
    @POST
    public Evaluation startGrade(EvaluationPlan evaluationPlan, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        Evaluation evaluation = evaluationApi.startGrade(evaluationPlan,loginInfo.getPersionId());
        if (evaluation != null) {
            evaluation.setData("success");
            return evaluation;
        }
        return null;
    }
    /**
     * 获取考评对象主表
     *
     * @param request
     * @param response
     * @return
     */

    @Path("getObj")
    @GET
    public List<EvaluationScoreVo> getObj(@QueryParam("templetId") String templetId,@QueryParam("planId") String planId,@QueryParam("type") String type,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        List<EvaluationScoreVo> es= evaluationApi.getObj(planId,templetId,loginInfo.getPersionId(),type);
        return es;
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
    public PageData getDetail(@QueryParam("id") String id,@QueryParam("type") String type,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        EvaluationScoreDetailVo es=new EvaluationScoreDetailVo();
        es.setType(type);
        es.setId(id);
        es.setCreateBy(loginInfo.getPersionId());
        Page<EvaluationScoreDetailVo> page = evaluationApi.getDetail(new Page<EvaluationScoreDetailVo>(request, response),es);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 保存评分
     * @param evaluationScoreDetailVo
     * @return
     */
    @Path("saveScore")
    @POST
    public StringData saveScore(EvaluationScoreDetailVo evaluationScoreDetailVo,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        evaluationScoreDetailVo.setUpdateBy(loginInfo.getPersionId());
        String num = evaluationApi.saveScore(evaluationScoreDetailVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 查看是否有未评分项
     *
     * @param request
     * @param response
     * @return
     */

    @Path("checkBeforeSubmit")
    @GET
    public Evaluation checkBeforeSubmit(@QueryParam("tid") String tid,@QueryParam("planId") String planId,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        Evaluation evaluation = evaluationApi.checkBeforeSubmit(tid,planId,loginInfo.getPersionId());
        if (evaluation != null) {
            evaluation.setData("success");
            return evaluation;
        }
        return null;
    }
    /**
     * 提交考评
     *
     * @param request
     * @param response
     * @return
     */

    @Path("submitGrade")
    @GET
    public Evaluation submitGrade(@QueryParam("tid") String tid,@QueryParam("planId") String planId,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        Evaluation evaluation = evaluationApi.submitGrade(tid,planId,loginInfo.getPersionId());
        if (evaluation != null) {
            evaluation.setData("success");
            return evaluation;
        }
        return null;
    }
    /**
     * 获取成绩头表的总分和当前分值
     * @param request
     * @param response
     * @return
     */
    @Path("getScoreById")
    @GET
    public EvaluationScoreVo getScoreById(@QueryParam("tid") String tid,@QueryParam("state") String state,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        EvaluationScoreVo es=evaluationApi.getScoreById(tid,state);
        if (es != null) {
            return es;
        }
        return null;
    }
}
