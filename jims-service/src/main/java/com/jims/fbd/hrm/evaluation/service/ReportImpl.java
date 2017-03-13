package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.ReportApi;
import com.jims.fbd.hrm.evaluation.bo.ReportBo;
import com.jims.fbd.hrm.evaluation.dao.ReportDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class ReportImpl extends CrudImplService<ReportDao, EvaluationReportData> implements ReportApi {

    @Autowired
    private ReportBo reportBo;
    /**
     * 查询科系得分
     * @return
     */
    @Override
    public List<EvaluationReportData> getReport() {
        return reportBo.getReport();
    }
    /**
     * 查询报表是否生成
     * @return
     */
    @Override
    public EvaluationReport checkReport() {
        return reportBo.checkReport();
    }
    /**
     * 根据科系ID查询该科系下科室得分
     * @return
     * @param pid
     */
    @Override
    public List<EvaluationReportData> getReportData(String pid) {
        return reportBo.getReportData(pid);
    }
    /**
     * 生成报表
     * @return
     */
    @Override
    public String createReport() {
        return reportBo.createReport();
    }
    /**
     *  修改科室平均达标率
     * @return
     */
    @Override
    public String editRateAvg(EvaluationReportData e) {
        return reportBo.editRateAvg(e);
    }
    /**
     *  发布报表
     * @return
     */
    @Override
    public String publishReport() {
        return reportBo.publishReport();
    }
    /**
     * 查询已发布报表
     * @return
     */
    @Override
    public List<EvaluationReportData> getPublishReport(String month) {
        return reportBo.getPublishReport(month);
    }
    /**
     * 获取报表标题
     * @return
     */
    @Override
    public EvaluationReport getReportTitle(String month) {
        return reportBo.getReportTitle(month);
    }
}
