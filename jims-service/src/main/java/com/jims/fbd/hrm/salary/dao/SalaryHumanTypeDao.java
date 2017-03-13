package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工资级别DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryHumanTypeDao extends CrudDao<SalaryHumanType> {



    /**
     * 查询人员类别是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryHumanType> findHtsame(@Param("orgId") String orgId, @Param("typeName") String typeName, @Param("typeId") String typeId);
    /**
     * 查询人员类别（分页）

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryHumanType> htList(SalaryHumanType salaryHumanType);
    /**
     * 查询人员类别
     * @param orgId
     * @return
     */
    public List<SalaryHumanType> htList1(@Param("orgId") String orgId);
    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("typeId") String typeId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("typeId") String typeId);
    /**
     * 停用启用
     *
     * @param salaryHumanType
     * @return
     */
    public void enableFlag(@Param("SalaryHumanType") SalaryHumanType salaryHumanType);
    /**
     * 批量删除
     *
     * @param salaryHumanType
     * @return
     */
    public void deleteHt(@Param("SalaryHumanType") SalaryHumanType salaryHumanType);
    /**
     * 删除占用判断
     *
     * @param salaryHumanType
     * @return
     */
    public int findOccupy(@Param("SalaryHumanType") SalaryHumanType salaryHumanType);
    /**
     * 修改
     *
     * @param salaryHumanType
     * @return
     */
    public void updateHt(@Param("salaryHumanType") SalaryHumanType salaryHumanType);
    /**
     * 新增
     *
     * @param salaryHumanType
     * @return
     */
    public void insertHt(@Param("salaryHumanType") SalaryHumanType salaryHumanType);
    /**
     * 新增之前先删除全部类别父节点
     *
     * @param salaryHumanType
     * @return
     */
    public void deleteAlltype(@Param("salaryHumanType") SalaryHumanType salaryHumanType);
    /**
     * 删除后插入全部类别父节点
     *
     * @param salaryHumanType
     * @return
     */
    public void insertAlltype(@Param("salaryHumanType") SalaryHumanType salaryHumanType);

}