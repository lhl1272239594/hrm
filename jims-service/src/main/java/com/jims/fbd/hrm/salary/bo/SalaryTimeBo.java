
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.salary.dao.SalaryTimeDao;
import com.jims.fbd.hrm.salary.entity.SalaryTime;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 工资结算日期BO层
 *
 * @author
 * @version 2016-09-22
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryTimeBo extends CrudImplService<SalaryTimeDao, SalaryTime> {

    /**
     * 判断该机构是否已存在
     * @param orgId
     * @return
     */
    public List<SalaryTime> findTimesame(String orgId){

        return dao.findTimesame(orgId);
    }

    /**
     * 查询
     * @param salaryTime
     * @return
     */
    public List<SalaryTime> timeList(SalaryTime salaryTime) {

        return dao.timeList(salaryTime);

    }

    /**
     * 新增修改保存
     * @return
     * @author
     */
    public String merge(SalaryTime salaryTime, String userName, String createDept) {
        //判断主键Id是否为空，不为空则更新
        if (org.apache.commons.lang3.StringUtils.isNotBlank(salaryTime.getTimeId())){
            salaryTime.preUpdate();
            salaryTime.setUpdateBy(userName);
            dao.updateTime(salaryTime);
            return "edit";
        }
        else{
            salaryTime.preInsert();
            salaryTime.setTimeId(salaryTime.getTimeId());
            salaryTime.setCreateBy(userName);
            salaryTime.setUpdateBy(userName);
            salaryTime.setCreateDept(createDept);
            dao.insertTime(salaryTime);
            return "add";
        }
    }

}