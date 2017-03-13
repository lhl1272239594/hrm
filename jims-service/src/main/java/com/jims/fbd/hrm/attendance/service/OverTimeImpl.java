package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.OverTimeApi;
import com.jims.fbd.hrm.attendance.bo.OverTimeBo;
import com.jims.fbd.hrm.attendance.dao.OverTimeDao;
import com.jims.fbd.hrm.attendance.entity.OverTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class OverTimeImpl implements OverTimeApi {
    @Autowired
    private OverTimeBo overTimeBo;
    @Autowired
    private OverTimeDao overTimeDao;


    /**
     * 业务处理：查询带分页
     *
     * @return
     */
    @Override
    public Page<OverTime> findList(Page<OverTime> page, OverTime overTime) {

        return overTimeBo.findList(page,overTime);
    }
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    @Override
    public List<OverTime> findAllList(OverTime overTime) {

        return overTimeBo.findAllList(overTime);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(OverTime overTime) {

        return overTimeBo.insertPrimary(overTime);
    }

    /**
     * 业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(OverTime overTime) {

        return overTimeBo.updatePrimary(overTime);
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Override
    public String delPrimary(String dataId) {
        try {
            overTimeBo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 业务处理：“我的加班”查询
     *
     * @return
     */    @Override
    public Page<OverTime> myovertime(Page<OverTime> page, OverTime overTime) {
        return overTimeBo.myovertime(page,overTime);
    }

}


