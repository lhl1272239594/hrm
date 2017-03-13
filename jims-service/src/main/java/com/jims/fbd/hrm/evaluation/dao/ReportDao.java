package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface ReportDao extends CrudDao<EvaluationReportData> {
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
    public List<EvaluationReportData> getReportData(@Param("pid") String pid);
    /**
     * 获取所有科系
     * @return
     */
    public  List<DeptConfig> getSystem();
    /**
     * 根据科系ID获取该科系下的考评科室
     * @return
     * @param id
     */
    public List<DeptConfig> getDept(@Param("id") String id);
    /**
     * 获取科系考评平均达标率
     * @return
     * @param id
     */
    public String getAvg(@Param("id") String id);
    /**
     * 获取科系自评平均达标率
     * @return
     * @param id
     */
    public String getSelfAvg(@Param("id") String id);
    /**
     * 获取科室评分数据
     * @return
     * @param id
     * @param avg
     */
    public EvaluationReportData getDeptData(@Param("id")String id,@Param("avg") String avg);
    /**
     * 重新生成报表，删除之前的报表
     * @return
     */
    public void delReport();
    /**
     * 删除报表数据
     * @return
     */
    public void delReportData();
    /**
     * 插入报表
     * @return
     */
    public void insertReport(EvaluationReport er);
    /**
     * 插入报表科系头表
     * @return
     */
    public void insertReportSystem(EvaluationReportData er);
    /**
     * 插入报表科系行表
     * @return
     */
    public void insertReportDept(EvaluationReportData er);
    /**
     *  修改科室平均达标率
     * @return
     */
    public void editRateAvg(EvaluationReportData e);
    /**
     *  发布报表
     * @return
     */
    public void publishReport();
    /**
     * 查询已发布报表
     * @return
     */
    public List<EvaluationReportData> getPublishReport(@Param("month") String month);
    /**
     * 获取报表标题
     * @return
     */
    public EvaluationReport getReportTitle(@Param("month") String month);
}
