package com.jims.fbd.hrm.evaluation.api;

import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.evaluation.vo.*;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;

public interface EvaluationTempletApi {
    /**
     * 查询考评类型
     *
     * @return
     * @param orgId
     */
    public List<EvaluationType> evaluationType(String orgId);
    /**
     * 查询考评模板
     *
     * @return
     */
    public Page<TempletVo> templetList(Page<TempletVo> templetVoPage, TempletVo templetVo);
    /**
     * 查询考评项目
     *
     * @param projectVo
     * @return
     */
    public List<ProjectVo> projectList(ProjectVo projectVo);
    /**
     * 新增修改模板
     *
     * @param templetVo
     * @return
     */
    public String templetMerge(TempletVo templetVo);
    /**
     * 根据项目查询标准
     *
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo);
    /**
     * 修改考评模板状态
     *
     * @param templetVo
     * @return
     */
    public String templetState(TempletVo templetVo);
    /**
     * 保存考评标准
     * @param standardVos
     * @return
     */
    public String saveStandard(TempletStandardVo standardVos);
    /**
     * 查看已选考评标准
     *
     * @return
     */
    public List<StandardVo> templetStandard(String orgId, String templetId);
    /**
     * 查看授权人员
     *
     * @return
     */
    public List<PersonVo> getPersonById(String templetId, String type);
    /**
     * 保存考评对象
     *
     * @return
     */
    public String savePerson(TempletVo templetVo);
    /**
     * 删除考评对象
     *
     * @return
     */
    public String removePerson(String id);
    /**
     * 启动考评
     *
     * @return
     */
    public String templetPublish(TempletVo templetVo,String personId);
    /**
     * 考评模板总分清零
     *
     * @return
     */
    public void clearScore(String num);
    /**
     * 查看模板授权
     *
     * @param templet
     * @return
     */
    public String checkAuthorize(TempletVo templet);
    /**
     * 我的考评标准
     *
     * @param persionId
     * @param name
     * @return
     */
    public List<StandardVo> myStandard(String persionId, String name);
}
