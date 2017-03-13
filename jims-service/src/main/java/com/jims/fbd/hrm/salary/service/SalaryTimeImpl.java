package com.jims.fbd.hrm.salary.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.fbd.hrm.salary.api.SalaryTimeApi;
import com.jims.fbd.hrm.salary.bo.SalaryTimeBo;
import com.jims.fbd.hrm.salary.entity.SalaryTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 工资结算日期imp层
 *
 * @author 
 * @version 2016-09-22
 */
@Service(version = "1.0.0")
public class SalaryTimeImpl implements SalaryTimeApi {
    @Autowired
    private SalaryTimeBo salaryTimeBo;

    /**
     * 查询是否存在
     * @return
     * @author 
     * @version 2016-09-23
     */
    @Override
    public List<SalaryTime> findTimesame(String orgId) {
        return salaryTimeBo.findTimesame(orgId);
    }

    /**
     * 保存修改
     * @return
     * @author 
     * @version 2016-09-22
     */
    @Override
    public String merge(SalaryTime salaryTime, String userName, String createDept) {
        return salaryTimeBo.merge(salaryTime,userName,createDept);
    }

    /**
     * 查询
     *
     * @return
     */
    @Override
    public List<SalaryTime> timeList(SalaryTime salaryTime) {

        return salaryTimeBo.timeList(salaryTime);
    }
}
