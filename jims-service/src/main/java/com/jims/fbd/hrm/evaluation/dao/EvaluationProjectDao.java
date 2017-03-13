package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.EvaluationType;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface EvaluationProjectDao extends CrudDao<ProjectVo>{



    /**
     * 查询一级项目
     * @param projectVo
     * @return
     */
    public  List<ProjectVo> firstLevelList(ProjectVo projectVo);
    /**
     * 查询二级项目
     * @param projectVo
     * @return
     */
    public  List<ProjectVo> secondLevelList(ProjectVo projectVo);
    /**
     * 查询项目名称是否重复
     * @return
     */
    public int getName(@Param("lx") int lx, @Param("parentId") String parentId, @Param("orgId") String orgId, @Param("name") String name,@Param("id") String id);
    /**
     * 新增项目
     */
    public void insertProject(@Param("ProjectVo" ) ProjectVo ProjectVo);
    /**
     * 更新一级项目考评分值
     */
    public void updateScore(@Param("ProjectVo" ) ProjectVo ProjectVo);
    /**
     * 修改考评项目状态
     */
    public void editProject(ProjectVo projectVo);
    /**
     * 启用停用二级考评项目
     * @param projectVo
     * @return
     */
    public void editAllProject(ProjectVo projectVo);
    /**
     * 删除二级考评项目
     */
    public void delProject(@Param("id" ) String id);
    /**
     * 删除一级考评项目
     */
    public void delAllProject(@Param("orgId" )String orgId,@Param("id" ) String id);
    /**
     * 查询考评类型
     *
     * @return
     */
    public List<EvaluationType> evaluationType(EvaluationType evaluationType);
    /**
     * 查询考评类型名称是否重复
     *
     * @return
     */
    public int getTypeName(EvaluationType evaluationType);
    /**
     * 新增考评类型名称
     *
     * @return
     */
    public void insertType(EvaluationType evaluationType);
    /**
     * 更新考评类型名称
     *
     * @return
     */
    public void updateType(EvaluationType evaluationType);
    /**
     * 启用停用考评类型
     * @param evaluationType
     * @return
     */
    public void typeStatus(EvaluationType evaluationType);
    /**
     * 删除考评类型
     * @param evaluationTypes
     * @return
     */
    public void delType(EvaluationType evaluationTypes);
    /**
     * 查看考评类型是否被占用
     * @param q
     * @return
     */
    public int checkTypeIsUsed(EvaluationType q);
    /**
     * 获取一级项目
     * @return
     */
    public List<ProjectVo> getProject(@Param("id" )String id,@Param("lx" ) String lx);
    /**
     * 查看考评项目是否被占用
     * @return
     */
    public int checkProIsUsed(@Param("id" )String id);
    /**
     * 查看考评类型分类是否存在
     * @return
     */
    public int getTypeIsExist(EvaluationType evaluationType);
    /**
     * 修改考评类型分类
     * @return
     */
    public void type(EvaluationType evaluationType);
    /**
     * 修改考评类型
     * @return
     */
    public void updateProject(ProjectVo projectVo);
}
