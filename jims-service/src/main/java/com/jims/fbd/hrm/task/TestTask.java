package com.jims.fbd.hrm.task;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.vo.EvaluationPlan;
import com.jims.fbd.hrm.evaluation.vo.EvaluationScoreVo;
import com.jims.fbd.hrm.evaluation.vo.TempletVo;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by wzm123 on 2016/10/12.
 */
@Service
@Component
@Transactional(readOnly = false)
public class TestTask extends CrudImplService<TaskDao,TempletVo> {

    public void plan(){
        Thread t=new Thread(new Runnable() {
            @Override
            public void run() {
                dao.finishPlan();
                dao.startPlan();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
                Date now = dao.getNow();
                String date=sdf.format(now);
                String date1=sdf1.format(now);
                Calendar calendar = Calendar.getInstance();
                try {
                    calendar.setTime(sdf.parse(date));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int year=calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH)+1;
                int day=calendar.get(Calendar.DAY_OF_MONTH);
                int maxDay=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                String time = DateFormat.getDateTimeInstance().format(now);

                //获取启动类型为自动、状态为启用没有删除的考评模板
                List<TempletVo> templetVoList=dao.getTemplet();
                for(TempletVo t:templetVoList){
                    //获取启动周期类型
                    String periodType=t.getPeriodType();//
                    //获取启动日期
                    int startDay=Integer.valueOf(t.getStartDay());
                    //周期为月
                    if(periodType.equals("0")){
                        //如果启动日期大于本月最大天数，那么本月的启动日期为本月最大天数
                        if(startDay>maxDay){
                            startDay=maxDay;
                        }
                        //当天等于启动日期则启动该模板
                        if(day==startDay){
                            try {
                                insert(t,date1,now);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    //周期为季度
                    if(periodType.equals("1")){
                        //获取该季度第几个月
                        String period=t.getPeriod();
                        int p=Integer.valueOf(period);
                        //判断本月是否与设置相等
                        if(month%3==p){
                            //如果启动日期大于本月最大天数，那么本月的启动日期为本月最大天数
                            if(startDay>maxDay){
                                startDay=maxDay;
                            }
                            //当天等于启动日期则启动该模板
                            if(day==startDay){
                                try {
                                    insert(t,year+"-"+(month/3+1),now);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                    //周期为年
                    if(periodType.equals("2")){
                        //获取该年第几个月
                        String period=t.getPeriod();
                        int p=Integer.valueOf(period);
                        if(month==p){
                            //如果启动日期大于本月最大天数，那么本月的启动日期为本月最大天数
                            if(startDay>maxDay){
                                startDay=maxDay;
                            }
                            //当天等于启动日期则启动该模板
                            if(day==startDay){
                                try {
                                    insert(t,String.valueOf(year),now);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }
                }

            }
        });
        t.start();
    }
    public void insert(TempletVo t,String date,Date now){
        String lx=dao.getType(t.getId());
        EvaluationPlan e=new EvaluationPlan();
        e.preInsert();
        e.setCreateBy("admin");
        e.setTempletId(t.getId());
        e.setName(t.getName()+"-"+date);
        e.setTypeId(t.getTypeId());
        e.setScore(t.getScore());
        e.setSelf(t.getSelf());
        e.setCreateDept(t.getCreateDept());
        e.setStartDate(now);
        e.setExpiryDate(t.getExpiryDate());
        e.setObj(t.getObj());
        e.setOrgId(t.getOrgId());
        e.setType(lx);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date Date = null;
        Calendar cl = Calendar.getInstance();
        cl.setTime(now);
        if(lx.equals("1")){
            cl.set(Calendar.DATE, cl.getActualMaximum(Calendar.DATE));
        }
        else {
            cl.add(Calendar.DAY_OF_YEAR, Integer.valueOf(t.getExpiryDate()));
        }
        Date = cl.getTime();
        e.setEndDate(Date);
        dao.insertEvaluationPlan(e);
        //如果考评计划类型为普通考评，添加考评标准
        if (lx.equals("3")) {
            dao.insertPlanStandard(t.getId(), e.getId());
        }
        //更新启动时间
        dao.lastPublish(t.getId());
        String obj=t.getObj();
        String self=t.getSelf();
        String id=t.getId();
        //考评计划类型为科室自评(特殊业务)
        if (lx.equals("1")) {
            //自评
            if (self.equals("1")) {
                //考评对象为科室，按照选择的人员所有科室循环生成考评头表
                if (obj.equals("1")) {
                    //获取所有考评科室
                    List<PersonVo> dept = dao.getDept(id, "1", "");
                    for (PersonVo d : dept) {
                        if (d.getDepId() != null) {
                            String totalScore = dao.getTotalScore(d.getDepId());
                            if (Float.valueOf(totalScore) > 0) {
                                EvaluationScoreVo es = new EvaluationScoreVo();
                                es.preInsert();
                                es.setObj(obj);
                                es.setSelf(self);
                                es.setDeptId(d.getDepId());
                                es.setPlanId(e.getId());
                                es.setOrgId(e.getOrgId());
                                es.setCreateDept(t.getCreateDept());
                                es.setTempletId(id);
                                es.setCreateBy("admin");
                                es.setTypeId(t.getTypeId());
                                es.setTotalScore(totalScore);
                                dao.insertEvaluationScore(es);
                            }
                        }
                    }
                }
            }
        }
        //考评计划类型为考评科室(特殊业务)
        if (lx.equals("2")) {
            //考评
            if (self.equals("0")) {
                //考评对象为科室，按照选择的人员所有科室循环生成考评头表
                if (obj.equals("1")) {
                    //从已选科室中查询出上月参加自评的科室
                    List<PersonVo> dept = dao.getDept(id, "1", lx);
                    for (PersonVo d : dept) {
                        if (d.getDepId() != null) {
                            String totalScore = dao.getTotalScore(d.getDepId());
                            if (Float.valueOf(totalScore) > 0) {
                                EvaluationScoreVo es = new EvaluationScoreVo();
                                es.preInsert();
                                es.setObj(obj);
                                es.setSelf(self);
                                es.setDeptId(d.getDepId());
                                es.setPlanId(e.getId());
                                es.setOrgId(e.getOrgId());
                                es.setCreateDept(t.getCreateDept());
                                es.setTempletId(id);
                                es.setCreateBy("admin");
                                es.setTypeId(t.getTypeId());
                                es.setTotalScore(totalScore);
                                dao.insertEvaluationScore(es);
                            }
                        }
                    }
                    //从已选科室中查询出上月未参加自评的科室
                    dept = dao.getDeptNotGrade(id, "1");
                    for (PersonVo d : dept) {
                        if (d.getDepId() != null) {
                            String totalScore = dao.getTotalScore(d.getDepId());
                            if (Float.valueOf(totalScore) > 0) {
                                EvaluationScoreVo es = new EvaluationScoreVo();
                                es.preInsert();
                                es.setObj("4");
                                es.setSelf(self);
                                es.setDeptId(d.getDepId());
                                es.setPlanId(e.getId());
                                es.setOrgId(e.getOrgId());
                                es.setCreateDept(t.getCreateDept());
                                es.setTempletId(id);
                                es.setCreateBy("admin");
                                es.setTypeId(t.getTypeId());
                                es.setTotalScore(totalScore);
                                dao.insertEvaluationScore(es);
                            }
                        }
                    }
                }
            }
        }
        //考评计划类型为普通考评
        if (lx.equals("3")) {
            if (obj.equals("1")) {
                //获取所有考评科室
                List<PersonVo> dept = dao.getDept(id, "1", "");
                //生成科室成绩主表
                for (PersonVo d : dept) {
                    //获取科室的所有考评人员
                    if (d.getDepId() != null) {
                        EvaluationScoreVo es = new EvaluationScoreVo();
                        es.preInsert();
                        es.setObj(obj);
                        es.setSelf(self);
                        es.setDeptId(d.getDepId());
                        es.setPlanId(e.getId());
                        es.setOrgId(e.getOrgId());
                        es.setCreateDept(t.getCreateDept());
                        es.setTempletId(id);
                        es.setCreateBy("admin");
                        es.setTypeId(t.getTypeId());
                        es.setTotalScore(t.getScore());
                        dao.insertEvaluationScore(es);
                    }
                }
                //如果为考评科室，插入考评成绩主表
                if (self.equals("1")) {
                    for (PersonVo d : dept) {
                        //获取所有考评人员
                        List<PersonVo> personVo = dao.getPerson(id, obj, d.getDepId());
                        for (PersonVo p : personVo) {
                            if (p.getUserId() != null) {
                                EvaluationScoreVo es = new EvaluationScoreVo();
                                es.preInsert();
                                es.setObj("3");
                                es.setSelf(self);
                                es.setDeptId(d.getDepId());
                                es.setPlanId(e.getId());
                                es.setOrgId(e.getOrgId());
                                es.setCreateDept(t.getCreateDept());
                                es.setTempletId(id);
                                es.setCreateBy("admin");
                                es.setTypeId(t.getTypeId());
                                es.setTotalScore(t.getScore());
                                es.setGradeBy(p.getUserId());
                                dao.insertEvaluationScore(es);
                            }
                        }
                    }
                }
            }
            //考评对象为人员
            if (obj.equals("2")) {
                //获取所有考评人员
                List<PersonVo> personVo = dao.getPerson(id, obj, "");
                for (PersonVo p : personVo) {
                    if (p.getUserId() != null) {
                        EvaluationScoreVo es = new EvaluationScoreVo();
                        es.preInsert();
                        es.setObj(obj);
                        es.setSelf(self);
                        es.setUserId(p.getUserId());
                        es.setPlanId(e.getId());
                        es.setOrgId(e.getOrgId());
                        es.setCreateDept(t.getCreateDept());
                        es.setTempletId(id);
                        es.setTypeId(t.getTypeId());
                        es.setCreateBy("admin");
                        es.setTotalScore(t.getScore());
                        if (self.equals("1")) {
                            es.setGradeBy(p.getUserId());
                        }
                        dao.insertEvaluationScore(es);
                    }
                }
            }
            if (self.equals("0")) {
                String type = "3";
                //获取所有评分人员
                List<PersonVo> personVo = dao.getGradePerson(id, type);
                //考评对象为科室
                if (obj.equals("1")) {
                    //获取所有考评科室
                    List<PersonVo> dept = dao.getDept(id, obj, "");
                    for (PersonVo p : personVo) {
                        for (PersonVo d : dept) {
                            //不能对自己所在的科室进行考评
                            if (!p.getDepId().equals(d.getDepId())) {
                                EvaluationScoreVo es = new EvaluationScoreVo();
                                es.preInsert();
                                es.setObj(type);
                                es.setSelf(self);
                                es.setUserId(d.getUserId());
                                es.setDeptId(d.getDepId());
                                es.setPlanId(e.getId());
                                es.setTypeId(t.getTypeId());
                                es.setOrgId(e.getOrgId());
                                es.setCreateDept(t.getCreateDept());
                                es.setTempletId(id);
                                es.setCreateBy("admin");
                                es.setGradeBy(p.getUserId());
                                es.setTotalScore(t.getScore());
                                dao.insertEvaluationScore(es);
                            }
                        }
                    }
                }
                //考评对象为人员
                if (obj.equals("2")) {
                    //获取所有考核人员
                    List<PersonVo> user = dao.getPerson(id, obj, "");
                    for (PersonVo p : personVo) {
                        for (PersonVo d : user) {
                            //不能对自己进行考评
                            if (!p.getUserId().equals(d.getUserId())) {
                                EvaluationScoreVo es = new EvaluationScoreVo();
                                es.preInsert();
                                es.setObj(type);
                                es.setSelf(self);
                                es.setUserId(d.getUserId());
                                es.setPlanId(e.getId());
                                es.setOrgId(e.getOrgId());
                                es.setCreateDept(t.getCreateDept());
                                es.setTempletId(id);
                                es.setTypeId(t.getTypeId());
                                es.setCreateBy("admin");
                                es.setGradeBy(p.getUserId());
                                es.setTotalScore(t.getScore());
                                dao.insertEvaluationScore(es);
                            }
                        }
                    }
                }
            }
        }
    }
}
