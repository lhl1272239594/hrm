package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.OffWork;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface OffWorkApi {


    /**
     * 业务处理：查询带分页
     *
     * @return
     */
    public Page<OffWork> findList(Page<OffWork> page, OffWork offWork);
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    public List<OffWork> findAllList(OffWork offWork);
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(OffWork offWork);

    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(OffWork offWork);
    /**
     * 业务处理：删除
     *
     * @return
     */
    public String delPrimary(String dataId);
    /**
     * 业务处理：“我的请假”查询
     *
     * @return
     */    public Page<OffWork> myvacation(Page<OffWork> offWorkPage, OffWork offWork);
}
