package com.jims.fbd.hrm.salary.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.salary.entity.SalaryPart;

import java.util.List;

/**
 * 工资组成api层
 * @author 
 * @version 2016-08-22
 */
public interface SalaryPartApi {


    /**
     * 根据参数查询新增数据是否重复
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryPart> findPartsame(String orgId, String partName, String partId);
    /**
     * 判断社保是否已存在
     * @author 
     * @version 2016-08-22
     * @return
     */
    List<SalaryPart> findSbsame(String orgId);
    /**
     * 保存或修改
     * @author 
     * @version 2016-08-31
     * @return
     */
    String merge(SalaryPart salaryPart, String userName, String createDept);
    /**
     * 启用
     * @param partId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changeup(String partId);
    /**
     * 停用
     * @param partId
     * @return
     * @author 
     * @version 2016-08-31
     */
    void changedown(String partId);
    /**
     * 启用停用
     * @param taxes
     * @return
     * @author 
     */
    String enableFlag(List<SalaryPart> taxes);
    /**
     * 恢复普通工资
     * @param taxes
     * @return
     * @author 
     */
    String back(List<SalaryPart> taxes, String userName);
    /**
     * 匹配社保基数
     * @param taxes
     * @return
     * @author 
     * @version 2016-08-31
     */
    String match_part(List<SalaryPart> taxes, String userName);
    /**
     * 删除
     * @param taxes
     * @return
     * @author 
     * @version 2016-08-31
     */
    String del_part(List<SalaryPart> taxes);
    /**
     * 删除占用判断
     * @author 
     * @version 2016-08-22
     * @return
     */
    String findOccupy(List<SalaryPart> parts);
    /**
     * 查询工资组成全部列表
     * @return
     * @author 
     * @version 2016-08-20
     */
    Page<SalaryPart> partList(Page<SalaryPart> page, SalaryPart salaryPart);


}
