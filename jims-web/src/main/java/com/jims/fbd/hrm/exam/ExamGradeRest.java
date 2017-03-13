package com.jims.fbd.hrm.exam;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.exam.api.ExamGradeApi;
import com.jims.fbd.hrm.exam.api.ExamResultApi;
import com.jims.fbd.hrm.exam.entity.ExamDetail;
import com.jims.fbd.hrm.exam.entity.ExamScore;
import com.jims.fbd.hrm.exam.entity.Test;
import com.jims.fbd.hrm.exam.vo.ExamGradeVo;
import com.jims.fbd.hrm.exam.vo.ExamResultVo;
import com.jims.fbd.hrm.exam.vo.TestVo;
import com.jims.sys.entity.SysUser;
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
@Path("examGrade")
public class ExamGradeRest {

    @Reference(version = "1.0.0")
    private ExamGradeApi examGradeApi;

    /**
     * 查询评分列表
     *
     * @param request
     * @param response
     * @return
     */

    @Path("gradeList")
    @GET
    public PageData gradeList(@QueryParam("orgId")String orgId,@QueryParam("deptIds")String deptIds,@QueryParam("type")String type,@QueryParam("planName")String planName,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamGradeVo examGradeVo = new ExamGradeVo();
        if(!orgId.equals("")&&orgId!=null){
            examGradeVo.setOrgId(orgId);
        }
        if(!type.equals("")&&type!=null){
            examGradeVo.setTypeId(type);
        }
        if(!planName.equals("")&&planName!=null){
            examGradeVo.setPlanName(planName);
        }
        if(!deptIds.equals("")&&deptIds!=null){
            examGradeVo.setCreateDept(deptIds);
        }
        Page<ExamGradeVo> page= examGradeApi.gradeList(new Page<ExamGradeVo>(request, response), examGradeVo);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 查询试卷评分详细列表
     *
     * @param request
     * @param response
     * @return
     */

    @Path("gradeByEachList")
    @GET
    public PageData gradeByEachList(@QueryParam("orgId")String orgId,@QueryParam("planId")String planId ,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        ExamScore examScore = new ExamScore();
        if(!orgId.equals("")&&orgId!=null){
            examScore.setOrgId(orgId);
        }
        if(!planId.equals("")&&planId!=null){
            examScore.setPlanId(planId);
        }
        Page<ExamScore> page= examGradeApi.gradeByEachList(new Page<ExamScore>(request, response), examScore);
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }
    /**
     * 获取主观题
     *
     * @param request
     * @param response
     * @param TestVo
     * @return
     */

    @Path("getQuestion")
    @POST
    public Test getQuestion(TestVo TestVo, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        Test test=examGradeApi.getQuestion(TestVo.getScoreId());
        if (test != null) {
            test.setData("success");
            return test;
        }
        return null;
    }
    /**
     * 保存评分
     * @param Test
     * @return
     * @author wangzhiming
     */
    @Path("saveGrade")
    @POST
    public StringData saveGrade(Test Test, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String num = examGradeApi.saveGrade(Test);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
