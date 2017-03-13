package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.EvaluationTempletDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class EvaluationTempletBo extends CrudImplService<EvaluationTempletDao, TempletVo> {

    /**
     * 查询考评类型
     *
     * @return
     */
    public List<EvaluationType> evaluationType(String orgId) {

        return dao.evaluationType(orgId);
    }

    /**
     * 查询考评模板
     *
     * @return
     */
    public Page<TempletVo> templetList(Page<TempletVo> page, TempletVo templetVo) {
        templetVo.setPage(page);
        page.setList(dao.templetList(templetVo));
        return page;
    }

    /**
     * 查询考评项目
     *
     * @param projectVo
     * @return
     */
    public List<ProjectVo> projectList(ProjectVo projectVo) {
        return dao.projectList(projectVo);
    }

    /**
     * 新增修改模板
     *
     * @param templetVo
     * @return
     */
    public String templetMerge(TempletVo templetVo) {
        int num = dao.getTempletName(templetVo);
        if (num > 0) {
            return "hasName";
        }
        if (org.apache.commons.lang3.StringUtils.isNotBlank(templetVo.getId())) {
            templetVo.setUpdateBy(templetVo.getCreateBy());
            dao.updateTemplet(templetVo);
            return templetVo.getId();
        } else {
            templetVo.preInsert();
            dao.insertTemplet(templetVo);
            return templetVo.getId();
        }
    }

    /**
     * 根据项目查询标准
     *
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo) {
        return dao.standardByProject(standardVo);
    }

    /**
     * 修改考评模板状态
     *
     * @param templetVo
     * @return
     */
    public String templetState(TempletVo templetVo) {
        String type = templetVo.getType();
        if (type.equals("del")) {
            dao.delTemplet(templetVo.getId());
            dao.delAuthorize(templetVo.getId());
            dao.delStandardById(templetVo.getId());
        } else {
            dao.editTemplet(templetVo.getId(), type);
        }
        return "success";
    }

    /**
     * 保存考评标准
     *
     * @param templetStandardVo
     * @return
     */
    public String saveStandard(TempletStandardVo templetStandardVo) {
        String templetId = templetStandardVo.getId();
        dao.delStandard(templetId);
        dao.clearStandard(templetId);
        for (StandardVo s : templetStandardVo.getStandardVos()) {
            s.setCode(s.getId());
            s.setTempletId(templetId);
            s.preInsert();
            dao.saveStandard(s);
            dao.addTempletScore(s);
        }
        return "success";
    }

    /**
     * 查看已选考评标准
     *
     * @return
     */
    public List<StandardVo> templetStandard(String orgId, String templetId) {
        return dao.templetStandard(orgId, templetId);
    }

    /**
     * 查看授权人员
     *
     * @return
     */
    public List<PersonVo> getPersonById(String templetId, String type) {
        if (type.equals("1")) {
            return dao.getDeptById(type, templetId);
        } else {
            return dao.getPersonById(type, templetId);
        }
    }

    /**
     * 保存考评对象
     *
     * @return
     */
    public String savePerson(TempletVo templetVo) {
        String templet = templetVo.getId();
        String type = templetVo.getType();
        String self = templetVo.getSelf();
        PersonVo p = new PersonVo();
        p.setType(type);
        p.setDataId(templet);
        for (PersonVo s : templetVo.getPersonVos()) {
            s.setDataId(templet);
            s.preInsert();
            if (s.getType().equals("1")) {
                if (self.equals("1")) {
                    dao.saveDeptPerson(s);
                } else {
                    dao.saveDept(s);
                }
            } else if (s.getType().equals("2")) {
                dao.savePerson(s);
            } else {
                dao.savePerson(s);
            }
        }
        return "success";
    }

    /**
     * 删除考评对象
     *
     * @return
     */
    public String removePerson(String id) {
        dao.removePerson(id);
        return "success";
    }

    /**
     * 启动考评
     *
     * @return
     */
    public String templetPublish(TempletVo templetVo, String personId) {
        String templetId = templetVo.getId();
        String lx = templetVo.getType();
        int num = dao.getPlanName(templetVo.getName(), templetVo.getOrgId());
        if (num > 0) {
            return "hasName";
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = templetVo.getLastStartDate();
        String startDate = sdf1.format(date1);
        int day = dao.checkDay(startDate);
        if (day < 0) {
            return "overDay";
        }
        String state = null;
        if (day == 0) {
            state = "1";
        }
        if (day > 0) {
            state = "0";
        }
        String self = templetVo.getSelf();
        String obj = templetVo.getObj();
        String orgId = templetVo.getOrgId();
        //新增考评计划
        EvaluationPlan e = new EvaluationPlan();
        e.preInsert();
        e.setCreateBy(personId);
        e.setTempletId(templetId);
        e.setName(templetVo.getName());
        e.setTypeId(templetVo.getTypeId());
        e.setScore(templetVo.getScore());
        e.setState(state);
        e.setSelf(self);
        e.setCreateDept(templetVo.getCreateDept());
        e.setStartDate(templetVo.getLastStartDate());
        e.setExpiryDate(templetVo.getExpiryDate());
        e.setObj(obj);
        e.setOrgId(templetVo.getOrgId());
        Date date = null;
        Calendar cl = Calendar.getInstance();
        cl.setTime(templetVo.getLastStartDate());
        if(lx.equals("1")){
            cl.set(Calendar.DATE, cl.getActualMaximum(Calendar.DATE));
        }
        else {
            cl.add(Calendar.DAY_OF_YEAR, Integer.valueOf(templetVo.getExpiryDate()));
        }
        date = cl.getTime();
        e.setEndDate(date);
        dao.insertEvaluationPlan(e);
        //如果考评计划类型为普通考评，添加考评标准
        if (lx.equals("3")) {
            dao.insertPlanStandard(templetId, e.getId());
        }
        //更新启动时间
        dao.lastPublish(templetId);
        //考评计划类型为科室自评(特殊业务)
        if (lx.equals("1")) {
            //自评
            if (self.equals("1")) {
                if (obj.equals("1")) {
                    //考评对象为科室，按照选择的人员所有科室生成考评头表
                    dao.insertEvaluationScoreByDept(e);
                }
            }
        }
        //考评计划类型为考评科室(特殊业务)
        if (lx.equals("2")) {
            //考评
            if (self.equals("0")) {
                if (obj.equals("1")) {
                    //从已选科室中查询出上月参加自评的科室生成考评头表
                    dao.insertEvaluationScoreByHasGrade(e);
                    //从已选科室中查询出上月未参加自评的科室生成考评头表
                    dao.insertEvaluationScoreByHasNotGrade(e);
                }
            }
        }
        //考评计划类型为普通考评
        if (lx.equals("3")) {
            if (obj.equals("1")) {
                //获取所有考评科室生成考评主表
                dao.insertEvaluationScoreDept(e);

                //获取所有考评科室
                List<PersonVo> dept = dao.getDept(templetId, "1", "");

                if (self.equals("1")) {
                    for (PersonVo d : dept) {
                        //如果为考评科室，获取所有考评人员，插入考评成绩主表
                        e.setState("3");
                        e.setDeptId(d.getDepId());
                        dao.insertEvaluationScoreByPerson(e);
                    }
                }
            }
            //考评对象为人员
            if (obj.equals("2")) {
                //人员自评，获取所有人员，生成考评主表
                if (self.equals("1")) {
                    e.setObj(obj);
                    e.setState(obj);
                    dao.insertEvaluationScoreByPerson(e);
                }
                //人员互评，获取所有人员，生成考评主表
                else {
                    e.setObj(obj);
                    dao.insertEvaluationScore(e);
                }
            }
            if (self.equals("0")) {
                String type = "3";
                //获取所有评分人员
                List<PersonVo> personVo = dao.getGradePerson(templetId, type);
                //考评对象为科室
                if (obj.equals("1")) {
                    //获取所有评分人员，生成每个评分人员针对所选科室的考评主表
                    for (PersonVo p : personVo) {
                        e.setState("3");
                        e.setUpdateBy(p.getUserId());
                        dao.insertEvaluationScoreGradeDept(e);
                    }
                }
                //考评对象为人员
                if (obj.equals("2")) {
                    //获取所有考核人员
                    List<PersonVo> user = dao.getPerson(templetId, obj, "");
                    for (PersonVo p : personVo) {
                        e.setState("3");
                        e.setUpdateBy(p.getUserId());
                        dao.insertEvaluationScoreGradePerson(e);
                        /*for (PersonVo d : user) {
                            //不能对自己进行考评
                            if (!p.getUserId().equals(d.getUserId())) {
                                EvaluationScoreVo es = new EvaluationScoreVo();
                                es.preInsert();
                                es.setObj(type);
                                es.setSelf(self);
                                es.setUserId(d.getUserId());
                                es.setPlanId(e.getId());
                                es.setOrgId(e.getOrgId());
                                es.setCreateDept(templetVo.getCreateDept());
                                es.setTempletId(templetId);
                                es.setTypeId(templetVo.getTypeId());
                                es.setCreateBy(personId);
                                es.setGradeBy(p.getUserId());
                                es.setTotalScore(templetVo.getScore());
                                dao.insertEvaluationScore(es);
                            }
                        }*/
                    }
                }
            }
        }
        return "success";
    }

    /**
     * 考评模板总分清零
     *
     * @return
     */
    public void clearScore(String id) {
        dao.clearScore(id);
    }

    /**
     * 查看模板授权
     *
     * @param templet
     * @return
     */
    public String checkAuthorize(TempletVo templet) {
        String id = templet.getId();
        String obj = templet.getObj();
        String self = templet.getSelf();
        int num = 0;
        if (obj.equals("1")) {
            num = dao.checkAuthorize(id, "1");
            if (num == 0) {
                return "dept";
            }
        }
        if (obj.equals("2")) {
            num = dao.checkAuthorize(id, "2");
            if (num == 0) {
                return "user";
            }
        }
        if (self.equals("0")) {
            num = dao.checkAuthorize(id, "3");
            if (num == 0) {
                return "grade";
            }
        }
        return "none";
    }

    /**
     * 我的考评标准
     *
     * @param persionId
     * @param name
     * @return
     */
    public List<StandardVo> myStandard(String persionId, String name) {
        return dao.myStandard(persionId,name);
    }
}
