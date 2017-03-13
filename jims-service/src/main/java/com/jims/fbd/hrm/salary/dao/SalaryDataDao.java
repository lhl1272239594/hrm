package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryData;
import com.jims.fbd.hrm.salary.vo.SalaryDataPersonVo;
import com.jims.fbd.hrm.salary.vo.SalaryDataVo;
import com.jims.sys.entity.DeptDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 薪资档案DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryDataDao extends CrudDao<SalaryData> {

    /**
     * 多表查询返回orgStaffVo
     * @param salaryData
     * @return
     */
    List<SalaryData> findListByVo(SalaryData salaryData);
    /**
     * 查询薪资档案数据

     * @return
     * @author 
     * @version 2016-08-22
     */
    List<SalaryData> dataList(SalaryData salaryData);
    /**
     * 查询职业信息数据

     * @return
     * @author 
     * @version 2016-09-29
     */
    List<SalaryData> careerList(SalaryData salaryData);
    /**
     * 查询职业变动报表

     * @return
     * @author 
     * @version 2016-08-22
     */
    List<SalaryData> careerSearch(SalaryData salaryData);
    /**
     * 查询职业历史信息数据

     * @return
     * @author 
     * @version 2016-09-29
     */
    List<SalaryData> careerListAll(SalaryData salaryData);
    /**
     * 新增标准
     */
    void merge(@Param("SalaryDataVo") SalaryDataVo salaryDataVo);
    /**
     * 保存档案之前先删除所选人员的档案信息
     */
    void deleteLoop(@Param("SalaryDataPersonVo") SalaryDataPersonVo salaryDataPersonVo);
    /**
     * 保存档案
     */
    void insertLoop(@Param("SalaryDataPersonVo") SalaryDataPersonVo salaryDataPersonVo);
    /**
     * 修改职业信息
     *
     * @param salaryData
     * @return
     */
    void updateCareer(@Param("SalaryData") SalaryData salaryData);
    /**
     * 新增职业信息
     *
     * @param salaryData
     * @return
     */
    void insertCareer(@Param("SalaryData") SalaryData salaryData);
    /**
     * 起薪停薪
     * @param salaryData
     * @return
     */
    void enableFlag(@Param("SalaryData") SalaryData salaryData);
    /**
     * 变更之前先删除
     * @param salaryData
     * @return
     */
    void deleteChange(@Param("SalaryData") SalaryData salaryData);
    /**
     * 变更类别
     * @param salaryData
     * @return
     */
    void change(@Param("SalaryData") SalaryData salaryData);
    /**
     * 同步变更人员表中数据
     * @param salaryData
     * @return
     */
    void updateLevel(@Param("SalaryData") SalaryData salaryData);
    /**
     * 批量删除职业信息
     *
     * @param salaryData
     * @return
     */
    void deleteCareer(@Param("SalaryData") SalaryData salaryData);
    /**
     * 查询科室信息
     * @return
     */
    List<DeptDict> findAll(@Param("orgId") String orgId, @Param("deptIds") String deptIds);

}