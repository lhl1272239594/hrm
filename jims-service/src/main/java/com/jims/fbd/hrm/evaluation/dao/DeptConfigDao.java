package com.jims.fbd.hrm.evaluation.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.evaluation.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisHrmDao
public interface DeptConfigDao extends CrudDao<DeptConfig> {
    /**
     * 查询科系
     *
     * @return
     */
    public List<DeptConfig> getParent();

    /**
     * 查询科系名称是否重复
     *
     * @return
     */
    public int getDeptName(DeptConfig deptConfig);

    /**
     * 新增科系名称
     *
     * @return
     */
    public void insertDept(DeptConfig deptConfig);

    /**
     * 更新科系名称
     *
     * @return
     */
    public void updateDept(DeptConfig deptConfig);

    /**
     * 删除科系名称
     *
     * @param deptConfig
     * @return
     */
    public void delDept(DeptConfig deptConfig);

    /**
     * 查询科室
     *
     * @return
     */
    public List<DeptConfig> findListByPid(@Param("id") String id);
    /**
     * 查询科室是否已存在
     *
     * @return
     */
    public int checkDept(@Param("id") String id);
    /**
     * 删除科室
     *
     * @param deptConfig
     * @return
     */
    public void remove(DeptConfig deptConfig);
    /**
     * 查询已选科室
     * @return
     */
    public List<DeptConfig> getDeptById(@Param("id") String id);
}
