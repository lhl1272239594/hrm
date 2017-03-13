package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.fbd.hrm.salary.entity.SalaryProject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工资组成部分DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryPartDao extends CrudDao<SalaryPart>{

    /**
     * 查询是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryPart> findPartsame(@Param("orgId") String orgId, @Param("partName") String partName,@Param("partId") String partId);
    /**
     * 查询社保基数是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryPart> findSbsame(@Param("orgId") String orgId);
    /**
     * 查询工资项目

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryPart> partList(SalaryPart salaryPart);
    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("partId") String partId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("partId") String partId);
    /**
     * 停用启用
     *
     * @param salaryPart
     * @return
     */
    public void enableFlag(@Param("SalaryPart" ) SalaryPart salaryPart);
    /**
     * 恢复普通工资
     *
     * @param salaryPart
     * @return
     */
    public void back(@Param("SalaryPart" ) SalaryPart salaryPart);
    /**
     * 同时将工资计算公式中的对应组成部分flag置为0
     *
     * @param salaryPart
     * @return
     */
    public void back_cal(@Param("SalaryPart" ) SalaryPart salaryPart);
    /**
     * 匹配社保（选中置1）

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void match_part(@Param("SalaryPart" ) SalaryPart salaryPart);
    /**
     * 匹配社保（选中置1）

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void match_part_cal(@Param("SalaryPart" ) SalaryPart salaryPart);
    /**
     * 匹配社保（未选中置0）

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void match_part_other(@Param("SalaryPart" ) SalaryPart salaryPart);
    /**
     * 修改
     *
     * @param salaryPart
     * @return
     */
    public void updatePart(@Param("salaryPart") SalaryPart salaryPart);
    /**
     * 新增
     *
     * @param salaryPart
     * @return
     */
    public void insertPart(@Param("salaryPart") SalaryPart salaryPart);
    /**
     * 批量删除
     *
     * @param salaryPart
     * @return
     */
    public void deletePart(@Param("SalaryPart" ) SalaryPart salaryPart);
    /**
     * 删除占用判断
     *
     * @param salaryPart
     * @return
     */
    public int findOccupy(@Param("SalaryPart" ) SalaryPart salaryPart);


}