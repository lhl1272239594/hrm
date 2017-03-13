package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryCalculate;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;
import com.jims.fbd.hrm.salary.entity.SalaryPart;
import com.jims.fbd.hrm.salary.entity.SalaryProjectMoney;

import java.util.List;

/**
 * 工资计算公式api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryCalculateApi {


    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryCalculate> findCalsame(String orgId, String content, String typeName, String partName);
    /**
     * 工资项目下拉框带回
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryProjectMoney> projectList(String orgId, String typeId);

    /**
     * 工资级别下拉框带回
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryHumanType> htList(String orgId);
    /**
     * 工资组成部分下拉框带回
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryPart> partList(String orgId);
    /**
     * 绩效部分下拉框带回
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryCalculate> performanceList(String orgId);

    /**
     * 保存或修改
     * @param salaryCalculate
     * @author 
     * @version 2016-08-31
     * @return
     */
    String merge(SalaryCalculate salaryCalculate, String userName, String createDept);

    /**
     * 启用
     * @param contentId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changeup(String contentId);
    /**
     * 停用
     * @param contentId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changedown(String contentId);
    /**
     * 启用停用
     * @param calculates
     * @return
     * @author 
     */
    String enableFlag(List<SalaryCalculate> calculates);
    /**
     * 删除
     * @param calculates
     * @return
     * @author 
     * @version 2016-08-31
     */
    String del_cal(List<SalaryCalculate> calculates);
    /**
     * 查询公式全部列表
     * @return
     * @author 
     * @version 2016-08-20
     */
    Page<SalaryCalculate> calList(Page<SalaryCalculate> page, SalaryCalculate salaryCalculate);


}
