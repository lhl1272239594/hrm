package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.DeptConfig;
import com.jims.fbd.hrm.evaluation.vo.Mould;
import com.jims.fbd.hrm.evaluation.vo.StandardVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface DeptStandardDao extends CrudDao<DeptConfig> {
    /**
     * 查询模板分类
     * @return
     */
    public List<Mould> getMouldType();
    /**
     * 查询模板名称
     * @return
     */
    public List<Mould> getMouldName(@Param("id") String id);
    /**
     * 导入模板
     */
    public void importMould(@Param("id")String id,@Param("deptId") String deptId);
    /**
     * 查询标准
     * @return
     */
    public List<StandardVo> findListByid(@Param("id")String id);
    /**
     * 删除已有标准
     * @return
     */
    public void delStandard(@Param("id") String id, @Param("deptId") String deptId);
    /**
     * 根据科室查询标准
     * @return
     */
    public List<StandardVo> standardByProject(StandardVo standardVo);
    /**
     * 保存考评标准
     */
    public void saveStandard(@Param("StandardVo" ) StandardVo s);
    /**
     * 修改标准
     */
    public void editStandard(@Param("StandardVo" ) StandardVo standardVo);
}
