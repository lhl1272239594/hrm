package com.jims.fbd.hrm.evaluation.api;

import com.jims.fbd.hrm.evaluation.vo.*;

import java.util.List;

public interface ReportApi {
    /**
     * 查询科系得分
     * @return
     */
    public List<EvaluationReportData> getReport();
    /**
     * 查询报表是否生成
     * @return
     */
    public EvaluationReport checkReport();
    /**
     * 根据科系ID查询该科系下科室得分
     * @return
     * @param pid
     */
    public List<EvaluationReportData> getReportData(String pid);
    /**
     * 生成报表
     * @return
     */
    public String createReport();
    /**
     *  修改科室平均达标率
     * @return
     */
    public String editRateAvg(EvaluationReportData e);
    /**
     *  发布报表
     * @return
     */
    public String publishReport();
    /**
     * 查询已发布报表
     * @return
     */
    public List<EvaluationReportData> getPublishReport(String month);
    /**
     * 获取报表标题
     * @return
     */
    public EvaluationReport getReportTitle(String month);
}
