package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.ProjectVo;
import com.jims.fbd.hrm.evaluation.vo.StandardPersonVo;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface EvaluationStandardDao extends CrudDao<ProjectVo>{

    /**
     * 查询考评项目
     * @param projectVo
     * @return
     */

    public List<ProjectVo> projectList(ProjectVo projectVo);
    /**
     * 根据项目查询标准
     * @param standardVo
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo);
    /**
     * 查看标准评分是否超过项目评分
     * @param score
     * @param pcode
     * @return
     */
    public int checkScore(@Param("score" ) String score,@Param("pcode" ) String pcode,@Param("orgId" ) String orgId);
    /**
     * 查询标准编码是否重复
     * @return
     */
    public int getCode(@Param("code" ) String code,@Param("pcode" ) String pcode,@Param("orgId" ) String orgId);
    /**
     * 查询标准名称是否重复
     * @return
     */
    public int getName(@Param("pcode") String pcode, @Param("orgId") String orgId, @Param("name") String name,@Param("id") String id);
    /**
     * 新增标准
     */
    public void insertStandard(@Param("StandardVo" ) StandardVo standardVo);
    /**
     * 保存标准执行人
     */
    public void insertStandardPerson(@Param("StandardPersonVo" ) StandardPersonVo StandardPersonVo);
    /**
     * 修改考评标准状态
     */
    public void editStandard(@Param("id" ) String id,@Param("type" ) String type);
    /**
     * 删除考评标准
     */
    public void delStandard(@Param("id" ) String id);
    /**
     * 查看授权人员
     *
     * @param id
     * @return
     */
    public List<StandardPersonVo> getPersonById(@Param("id" ) String id);
    /**
     * 增加考评项目分值
     *
     * @param standardVo
     * @return
     */
    public void updateScore(@Param("StandardVo" ) StandardVo standardVo);
    /**
     * 减少考评项目分值
     *
     * @param standardVo
     * @return
     */
    public void subScore(@Param("StandardVo" ) StandardVo standardVo);
    /**
     * 查看考评标准是否被占用
     * @param q
     * @return
     */
    public int checkStandardIsUsed(StandardVo q);
    /**
     * 获取标准分值
     * @param id
     * @return
     */
    public String getScore(@Param("id" ) String id);
    /**
     * 修改标准
     */
    public void updateStandard(@Param("StandardVo" ) StandardVo standardVo);
    /**
     * 移除标准授权人员
     */
    public void removeStandardPerson(@Param("id" ) String id);

    String getPcode(@Param("pname" ) String pname);
    String getScode(@Param("sname") String sname,@Param("pcode") String pcode);
    StandardPersonVo getUser(@Param("userName" ) String userName);
}
