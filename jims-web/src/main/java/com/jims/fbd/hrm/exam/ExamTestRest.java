package com.jims.fbd.hrm.exam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.exam.api.ExamResultApi;
import com.jims.fbd.hrm.exam.api.ExamTestApi;
import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import com.jims.fbd.hrm.exam.vo.TestVo;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.SysUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("test")
public class ExamTestRest {

    @Reference(version = "1.0.0")
    private ExamTestApi examTestApi;

    /**
     * 开始考试
     *
     * @param request
     * @param response
     * @return
     */

    @Path("startTest")
    @POST
    public Test startTest(TestVo TestVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        OrgStaff orgStaff= (OrgStaff) session.getAttribute("OrgStaff");
        TestVo.setCreateDept(orgStaff.getDeptId());
        Test test = examTestApi.startTest(TestVo);
        if (test != null) {
            test.setData("success");
            return test;
        }
        return null;
    }
    /**
     * 查看成绩行表
     *
     * @param request
     * @param response
     * @param sort
     * @param scoreId
     * @return
     */

    @Path("getQuestion")
    @GET
    public Test getQuestion(@QueryParam("sort") String sort, @QueryParam("scoreId") String scoreId, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Test test=examTestApi.getQuestion(sort,scoreId);
        if (test != null) {
            test.setData("success");
            return test;
        }
        return null;
    }
    /**
     * 保存考生答案
     *
     * @param examDetail
     * @return
     */

    @Path("saveAnswer")
    @POST
    public StringData saveAnswer(ExamDetail examDetail) {
        String num = examTestApi.saveAnswer(examDetail);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     * 交卷验证
     *
     * @param request
     * @param response
     * @param scoreId
     * @return
     */

    @Path("submitValidate")
    @GET
    public Test submitValidate(@QueryParam("scoreId") String scoreId,@QueryParam("limitSubmit")String limitSubmit,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Test test=examTestApi.submitValidate(scoreId,limitSubmit);
        if (test != null) {
            test.setData("success");
            return test;
        }
        return null;
    }
    /**
     * 提交试卷
     *
     * @param TestVo
     * @param request
     * @param response
     * @return
     */

    @Path("submitExam")
    @POST
    public Test submitExam(TestVo TestVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Test test = examTestApi.submitExam(TestVo);
        if (test != null) {
            test.setData("success");
            return test;
        }
        return null;
    }

}
