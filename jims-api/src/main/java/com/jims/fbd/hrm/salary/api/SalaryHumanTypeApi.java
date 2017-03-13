package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryHumanType;

import java.util.List;

/**
 * 工资级别api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryHumanTypeApi {


    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryHumanType> findHtsame(String orgId, String typeName, String typeId);
    /**
     * 保存或修改
     * @author 
     * @version 2016-08-31
     * @return
     */
    String merge(SalaryHumanType salaryHumanType, String userName, String createDept);
    /**
     * 启用
     * @param typeId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changeup(String typeId);
    /**
     * 停用
     * @param typeId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changedown(String typeId);
    /**
     * 启用停用
     * @param humanTypes
     * @return
     * @author 
     */
    String enableFlag(List<SalaryHumanType> humanTypes);
    /**
     * 删除
     * @param humanTypes
     * @return
     * @author 
     * @version 2016-08-31
     */
    String del_ht(List<SalaryHumanType> humanTypes);
    /**
     * 删除占用判断
     * @author 
     * @version 2016-08-22
     * @return
     */
    String findOccupy(List<SalaryHumanType> humanTypes);
    /**
     * 查询工资级别全部列表（分页）
     * @return
     * @author 
     * @version 2016-08-20
     */
    Page<SalaryHumanType> htList(Page<SalaryHumanType> page, SalaryHumanType salaryHumanType);
    /**
     * 工资级别下拉框查询
     * @return
     * @author 
     * @version 2016-08-20
     */
    List<SalaryHumanType> htList1(String orgId);


}
