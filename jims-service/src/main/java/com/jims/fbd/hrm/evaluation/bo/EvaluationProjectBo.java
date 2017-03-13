package com.jims.fbd.hrm.evaluation.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.dao.EvaluationProjectDao;
import com.jims.fbd.hrm.evaluation.vo.EvaluationType;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.exam.entity.*;
import com.jims.fbd.hrm.exam.vo.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class EvaluationProjectBo extends CrudImplService<EvaluationProjectDao,ProjectVo> {

    /**
     * 查询一级项目
     * @param page
     * @param projectVo
     * @return
     */
    public Page<ProjectVo> firstLevelList(Page<ProjectVo> page, ProjectVo projectVo) {
        projectVo.setPage(page);
        page.setList(dao.firstLevelList(projectVo));
        return page;
    }
    /**
     *  查询二级项目
     * @param page
     * @param projectVo
     * @return
     */
    public Page<ProjectVo> secondLevelList(Page<ProjectVo> page, ProjectVo projectVo) {
        projectVo.setPage(page);
        page.setList(dao.secondLevelList(projectVo));
        return page;
    }
    /**
     * 新增考评项目
     *
     * @param projectVo
     * @return
     */
    public String saveProject(ProjectVo projectVo) {
        String lx=projectVo.getLx();
        String orgId=projectVo.getOrgId();
        String parentId=projectVo.getParentId();
        String name=projectVo.getName();
        String id=projectVo.getId();
        if(lx.equals("1")){
            int nameNum=dao.getName(1,"",orgId,name,id);
            if(nameNum>0){
                return "hasName";
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
                dao.updateProject(projectVo);
            }
            else {
                projectVo.preInsert();
                dao.insertProject(projectVo);
            }
        }
        if(lx.equals("2")){
            int nameNum=dao.getName(2,parentId,orgId,name,id);
            if(nameNum>0){
                return "hasName";
            }
            if (org.apache.commons.lang3.StringUtils.isNotBlank(id)){
                dao.updateProject(projectVo);
            }
            else {
                projectVo.preInsert();
                dao.insertProject(projectVo);
            }
        }
        return "success";
    }
    /**
     * 修改考评项目状态
     *
     * @param projectVo
     * @return
     */
    public String editProject(ProjectVo projectVo) {
        String lx=projectVo.getLx();
        String id=projectVo.getId();
        String type=projectVo.getType();
        if(type.equals("del")){
            if(lx.equals("1")){
                String orgId=projectVo.getOrgId();
                dao.delAllProject(orgId,id);
            }
            if(lx.equals("2")){
                dao.delProject(id);
            }
        }
        else {
            if(lx.equals("1")){
                dao.editProject(projectVo);
                dao.editAllProject(projectVo);
            }
            dao.editProject(projectVo);
        }
        return "success";
    }
    /**
     * 查询考评类型
     *
     * @return
     */
    public Page<EvaluationType> evaluationType(Page<EvaluationType> page, EvaluationType evaluationType) {
        evaluationType.setPage(page);
        page.setList(dao.evaluationType(evaluationType));
        return page;
    }
    /**
     * 新增修改考评类型
     *
     * @param evaluationType
     * @return
     */
    public String typeMerge(EvaluationType evaluationType) {
        int num=dao.getTypeName(evaluationType);
        if(num>0){
            return "hasName";
        }
        if (!evaluationType.getId().equals("")){
            evaluationType.preUpdate();
            evaluationType.setUpdateBy(evaluationType.getCreateBy());
            dao.updateType(evaluationType);
            return "success";
        }
        else{

            evaluationType.preInsert();
            dao.insertType(evaluationType);
            return "success";
        }
    }
    /**
     * 启用停用考评类型
     * @param evaluationTypes
     * @return
     */
    public String typeStatus(List<EvaluationType> evaluationTypes) {
        for (EvaluationType e : evaluationTypes) {
            dao.typeStatus(e);
        }
        return "sucsess";
    }
    /**
     * 删除考评类型
     * @param evaluationTypes
     * @return
     */
    public String delType(List<EvaluationType> evaluationTypes) {
        for (EvaluationType e : evaluationTypes) {
            dao.delType(e);
        }
        return "sucsess";
    }
    /**
     * 查看考评类型是否被占用
     * @param evaluationTypes
     * @return
     */
    public String checkTypeIsUsed(List<EvaluationType> evaluationTypes) {
        for (EvaluationType q : evaluationTypes) {
            int num=dao.checkTypeIsUsed(q);
            if(num>0){
                return "isUsed";
            }
        }
        return "success";
    }
    /**
     * 查看考评项目是否被占用
     * @return
     */
    public String checkProjectIsUsed(String id, String lx) {
        if(lx.equals("1")){
            List<ProjectVo> project=dao.getProject(id,lx);
            for(ProjectVo p:project){
                int num=dao.checkProIsUsed(p.getId());
                if(num>0){
                    return "isUsed";
                }
            }
        }
        if(lx.equals("2")){
            int num=dao.checkProIsUsed(id);
            if(num>0){
                return "isUsed";
            }
        }
        return "success";
    }
    /**
     * 修改考评类型分类
     * @return
     */
    public String changeType(EvaluationType evaluationType) {
        dao.type(evaluationType);
        return "success";
    }
}
