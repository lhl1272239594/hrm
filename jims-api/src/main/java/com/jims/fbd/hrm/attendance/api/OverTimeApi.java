package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.OverTime;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface OverTimeApi {


    /**
     * 业务处理：查询带分页
     *
     * @return
     */
    public Page<OverTime> findList(Page<OverTime> page, OverTime overTime);
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    public List<OverTime> findAllList(OverTime overTime);

    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(OverTime overTime);

    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(OverTime overTime);
    /**
     * 业务处理：删除
     *
     * @return
     */
    public String delPrimary(String dataId);
    /**
     * 业务处理：“我的加班”查询
     *
     * @return
     */    public Page<OverTime> myovertime(Page<OverTime> overTimePage, OverTime overTime);
}
