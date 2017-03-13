package com.jims.fbd.hrm.evaluation;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.evaluation.vo.EvaluationReport;
import com.jims.fbd.hrm.evaluation.vo.EvaluationReportData;
import org.springframework.stereotype.Component;
import com.jims.fbd.hrm.evaluation.api.ReportApi;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;


@Component
@Produces("application/json")
@Path("report")
public class ReportRest {

    @Reference(version = "1.0.0")
    private ReportApi ReportApi;
    /**
     * 根据科系ID查询该科系下科室得分
     * @return
     */
    @Path("getReportData")
    @GET
    public List<EvaluationReportData> getReportData(@QueryParam("pid")String pid,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return ReportApi.getReportData(pid);
    }
    /**
     * 查询科系得分
     * @return
     */
    @Path("getReport")
    @GET
    public List<EvaluationReportData> getReport(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return ReportApi.getReport();
    }
    /**
     * 查询已发布报表
     * @return
     */
    @Path("getPublishReport")
    @GET
    public List<EvaluationReportData> getPublishReport(@QueryParam("month")String month ,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return ReportApi.getPublishReport(month);
    }
    /**
     * 查询报表是否生成
     * @return
     */
    @Path("checkReport")
    @GET
    public EvaluationReport checkReport(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return ReportApi.checkReport();
    }
    /**
     * 获取报表标题
     * @return
     */
    @Path("getReportTitle")
    @GET
    public EvaluationReport getReportTitle(@QueryParam("month")String month ,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        return ReportApi.getReportTitle(month);
    }
    /**
     * 生成报表
     * @return
     */
    @Path("createReport")
    @GET
    public StringData createReport(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        String num = ReportApi.createReport();
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     *  修改科室平均达标率
     * @return
     */
    @Path("editRateAvg")
    @POST
    public StringData editRateAvg(EvaluationReportData e,@Context HttpServletRequest request, @Context HttpServletResponse response) {
        HttpSession session= request.getSession() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        e.setUpdateBy(loginInfo.getPersionId());
        String num = ReportApi.editRateAvg(e);
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
    /**
     *  发布报表
     * @return
     */
    @Path("publishReport")
    @GET
    public StringData publishReport(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        String num = ReportApi.publishReport();
        if (num != null) {
            StringData stringData = new StringData();
            stringData.setCode(num);
            stringData.setData("success");
            return stringData;
        }
        return null;
    }
}
