package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.TripWork;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface TripWorkApi {

    /**
     * 业务处理：查询带分页
     *
     * @return
     */

    public Page<TripWork> findList(Page<TripWork> page, TripWork tripWork);
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    public List<TripWork> findAllList(TripWork tripWork);

    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(TripWork tripWork);

    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(TripWork tripWork);
    /**
     * 业务处理：删除
     *
     * @return
     */
    public String delPrimary(String dataId);
    /**
     * 业务处理：“我的公出”查询
     *
     * @return
     */
    public Page<TripWork> mytrip(Page<TripWork> tripWorkPage, TripWork tripWork);
}
