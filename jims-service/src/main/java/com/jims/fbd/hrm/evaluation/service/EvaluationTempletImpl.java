package com.jims.fbd.hrm.evaluation.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.evaluation.api.EvaluationTempletApi;
import com.jims.fbd.hrm.evaluation.bo.EvaluationTempletBo;
import com.jims.fbd.hrm.evaluation.dao.EvaluationTempletDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service(version = "1.0.0")

public class EvaluationTempletImpl extends CrudImplService<EvaluationTempletDao, TempletVo> implements EvaluationTempletApi {

    @Autowired
    private EvaluationTempletBo evaluationTempletBo;
    /**
     * 查询考评类型
     *
     * @return
     * @param orgId
     */
    @Override
    public List<EvaluationType> evaluationType(String orgId) {
        return evaluationTempletBo.evaluationType(orgId);
    }
    /**
     * 查询考评模板
     *
     * @return
     */
    @Override
    public Page<TempletVo> templetList(Page<TempletVo> page, TempletVo templetVo) {
        return evaluationTempletBo.templetList(page,templetVo);
    }
    /**
     * 查询考评项目
     *
     * @param projectVo
     * @return
     */
    @Override
    public List<ProjectVo> projectList(ProjectVo projectVo) {
        return evaluationTempletBo.projectList(projectVo);
    }
    /**
     * 新增修改模板
     *
     * @param templetVo
     * @return
     */
    @Override
    public String templetMerge(TempletVo templetVo) {
        return evaluationTempletBo.templetMerge(templetVo);
    }
    /**
     * 根据项目查询标准
     *
     * @return
     */
    @Override
    public List<StandardVo> standardByProject(StandardVo standardVo) {
        return evaluationTempletBo.standardByProject(standardVo);
    }
    /**
     * 修改考评模板状态
     *
     * @param templetVo
     * @return
     */
    @Override
    public String templetState(TempletVo templetVo) {
        return evaluationTempletBo.templetState(templetVo);
    }
    /**
     * 保存考评标准
     * @param templetStandardVo
     * @return
     */
    @Override
    public String saveStandard(TempletStandardVo templetStandardVo) {
        return evaluationTempletBo.saveStandard(templetStandardVo);
    }
    /**
     * 查看已选考评标准
     *
     * @return
     */
    @Override
    public List<StandardVo> templetStandard(String orgId, String templetId) {
        return evaluationTempletBo.templetStandard(orgId,templetId);
    }
/*    *//**
     * 删除考评标准
     *
     * @param templetStandardVo
     * @return
     *//*
    @Override
    public String delStandard(TempletStandardVo templetStandardVo) {
        return evaluationTempletBo.delStandard(templetStandardVo);
    }*/
    /**
     * 查看授权人员
     *
     * @return
     */
    @Override
    public List<PersonVo> getPersonById(String templetId, String type) {
        return evaluationTempletBo.getPersonById(templetId,type);
    }
    /**
     * 保存考评对象
     *
     * @return
     */
    @Override
    public String savePerson(TempletVo templetVo) {
        return evaluationTempletBo.savePerson(templetVo);
    }
    /**
     * 删除考评对象
     *
     * @return
     */
    @Override
    public String removePerson(String id) {
        return evaluationTempletBo.removePerson(id);
    }
    /**
     * 启动考评
     *
     * @return
     */
    @Override
    public String templetPublish(TempletVo templetVo,String personId) {
        return evaluationTempletBo.templetPublish(templetVo,personId);
    }
    /**
     * 考评模板总分清零
     *
     * @return
     */
    @Override
    public void clearScore(String num) {
         evaluationTempletBo.clearScore(num);
    }
    /**
     * 查看模板授权
     *
     * @param templet
     * @return
     */
    @Override
    public String checkAuthorize(TempletVo templet) {
        return evaluationTempletBo.checkAuthorize(templet);
    }
    /**
     * 我的考评标准
     *
     * @param persionId
     * @param name
     * @return
     */
    @Override
    public List<StandardVo> myStandard(String persionId, String name) {
        return evaluationTempletBo.myStandard(persionId,name);
    }
}
