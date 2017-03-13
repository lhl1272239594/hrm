package com.jims.fbd.hrm.salary.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.salary.entity.SalaryCalculate;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 工资计算公式DAO接口
 * @author 
 * @version 2016-08-22
 */
@MyBatisHrmDao
public interface SalaryCalculateDao extends CrudDao<SalaryCalculate> {



    /**
     * 查询公式是否已存在

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryCalculate> findCalsame(@Param("orgId") String orgId, @Param("content") String content, @Param("typeName") String typeName, @Param("partName") String partName);
    /**
     * 工资项目下拉框带回

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryProjectMoney> projectList(@Param("orgId") String orgId, @Param("typeId") String typeId);
    /**
     * 工资级别下拉框带回

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryHumanType> htList(@Param("orgId") String orgId);
    /**
     * 工资组成部分下拉框带回

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryPart> partList(@Param("orgId") String orgId);
    /**
     * 绩效考评下拉框带回

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryCalculate> performanceList(@Param("orgId") String orgId);

    /**
     * 查询公式

     * @return
     * @author 
     * @version 2016-08-22
     */
    public List<SalaryCalculate> calList(SalaryCalculate salaryCalculate);
    /**
     * 启用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changeup(@Param("contentId") String contentId);
    /**
     * 停用

     * @return
     * @author 
     * @version 2016-08-22
     */
    public void changedown(@Param("contentId") String contentId);
    /**
     * 停用启用
     *
     * @param salaryCalculate
     * @return
     */
    public void enableFlag(@Param("SalaryCalculate") SalaryCalculate salaryCalculate);
    /**
     * 批量删除
     *
     * @param salaryCalculate
     * @return
     */
    public void deleteCal(@Param("SalaryCalculate") SalaryCalculate salaryCalculate);
    /**
     * 修改
     *
     * @param salaryCalculate
     * @return
     */
    public void updateCal(@Param("salaryCalculate") SalaryCalculate salaryCalculate);
    /**
     * 新增
     *
     * @param salaryCalculate
     * @return
     */
    public void insertCal(@Param("salaryCalculate") SalaryCalculate salaryCalculate);

}