package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.MouldDao;
import com.jims.fbd.hrm.evaluation.dao.ReportDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class ReportBo extends CrudImplService<ReportDao,EvaluationReportData> {
    /**
     * 查询科系得分
     * @return
     */
    public List<EvaluationReportData> getReport() {
        return dao.getReport();
    }
    /**
     * 查询报表是否生成
     * @return
     */
    public EvaluationReport checkReport() {
        return dao.checkReport();
    }
    /**
     * 根据科系ID查询该科系下科室得分
     * @return
     * @param pid
     */
    public List<EvaluationReportData> getReportData(String pid) {
        return dao.getReportData(pid);
    }
    /**
     * 生成报表
     * @return
     */
    public String createReport() {
        //重新生成报表，删除之前的报表
        dao.delReport();
        //删除报表数据
        dao.delReportData();
        EvaluationReport er=new EvaluationReport();
        er.preInsert();
        er.setAvg(dao.getAvg("all"));//整体考评均值
        er.setSelfAvg(dao.getSelfAvg("all"));//整体自评均值
        //插入报表
        dao.insertReport(er);
        //获取所有科系
        List<DeptConfig> system=dao.getSystem();
        int sort=1;
        for(DeptConfig s:system){
            String name=s.getName();//科系名称
            //根据科系ID获取该科系下的考评科室
            List<DeptConfig> dept=dao.getDept(s.getId());
            String avg=null;
            String selfAvg=null;
            String pid=null;
            if(dept.size()>0){
                //获取科系考评平均达标率
                avg=dao.getAvg(s.getId());
                //获取科系自评平均达标率
                selfAvg=dao.getSelfAvg(s.getId());
                EvaluationReportData erd=new EvaluationReportData();
                erd.preInsert();
                erd.setReportId(er.getId());
                erd.setSystemName(name);
                erd.setAvg(avg);
                erd.setSelfAvg(selfAvg);
                erd.setType("1");
                erd.setSort(String.valueOf(sort));
                //插入报表科系头表
                dao.insertReportSystem(erd);
                sort++;
                pid=erd.getId();
            }
            for(DeptConfig d:dept){
                if(avg!=null){
                    //获取科室评分数据
                    EvaluationReportData erd=dao.getDeptData(d.getId(),avg);
                    String deptName=d.getDeptName();
                    erd.preInsert();
                    erd.setReportId(er.getId());
                    erd.setSystemName(name);
                    erd.setDeptName(deptName);
                    String obj=erd.getObj();
                    erd.setState("1");
                    erd.setPid(pid);
                    erd.setType("2");
                    //科室没有自评
                    if(obj.equals("4")){
                        erd.setState("0");
                        erd.setScore("无考评");
                        erd.setTotalScore("");
                        erd.setRate("0");
                        erd.setRateAvg(String.valueOf(Float.valueOf(avg)-10));
                    }
                    //插入报表科系行表
                    dao.insertReportDept(erd);
                }
            }
        }
        //获取没有设置科系的科室
        List<DeptConfig> dept=dao.getDept("");
        if(dept.size()>0){
            String name="其它";
            String avg=null;
            String selfAvg=null;
            String pid=null;
            if(dept.size()>0){
                //获取科系考评平均达标率
                avg=dao.getAvg("other");
                //获取科系自评平均达标率
                selfAvg=dao.getSelfAvg("other");
                EvaluationReportData erd=new EvaluationReportData();
                erd.preInsert();
                erd.setReportId(er.getId());
                erd.setSystemName(name);
                erd.setAvg(avg);
                erd.setSelfAvg(selfAvg);
                erd.setType("1");
                erd.setSort(String.valueOf(sort));
                //插入报表科系头表
                dao.insertReportSystem(erd);
                sort++;
                pid=erd.getId();
            }
            for(DeptConfig d:dept){
                if(avg!=null){
                    //获取科室评分数据
                    EvaluationReportData erd=dao.getDeptData(d.getId(),avg);
                    String deptName=d.getDeptName();
                    erd.preInsert();
                    erd.setReportId(er.getId());
                    erd.setSystemName(name);
                    erd.setDeptName(deptName);
                    String obj=erd.getObj();
                    erd.setState("1");
                    erd.setPid(pid);
                    erd.setType("2");
                    //科室没有自评
                    if(obj.equals("4")){
                        erd.setState("0");
                        erd.setScore("无考评");
                        erd.setTotalScore("");
                        erd.setRate("0");
                        erd.setRateAvg(String.valueOf(Float.valueOf(avg)-10));
                    }
                    //插入报表科系行表
                    dao.insertReportDept(erd);
                }
            }
        }
        return "success";
    }
    /**
     *  修改科室平均达标率
     * @return
     */
    public String editRateAvg(EvaluationReportData e) {
        dao.editRateAvg(e);
        return "success";
    }
    /**
     *  发布报表
     * @return
     */
    public String publishReport() {
        dao.publishReport();
        return "success";
    }
    /**
     * 查询已发布报表
     * @return
     */
    public List<EvaluationReportData> getPublishReport(String month) {
        return dao.getPublishReport(month);
    }
    /**
     * 获取报表标题
     * @return
     */
    public EvaluationReport getReportTitle(String month) {
        return dao.getReportTitle(month);
    }
}
