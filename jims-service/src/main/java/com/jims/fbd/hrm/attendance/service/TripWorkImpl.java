package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.TripWorkApi;
import com.jims.fbd.hrm.attendance.bo.TripWorkBo;
import com.jims.fbd.hrm.attendance.dao.TripWorkDao;
import com.jims.fbd.hrm.attendance.entity.TripWork;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class TripWorkImpl implements TripWorkApi {
    @Autowired
    private TripWorkBo tripWorkBo;
    @Autowired
    private TripWorkDao tripWorkDao;

    /**
     * 业务处理：查询带分页
     *
     * @return
     */

    @Override
    public Page<TripWork> findList(Page<TripWork> page, TripWork tripWork) {

        return tripWorkBo.findList(page,tripWork);
    }
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    @Override
    public List<TripWork> findAllList(TripWork tripWork) {

        return tripWorkBo.findAllList(tripWork);
    }

    /**
     * 业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(TripWork tripWork) {

        return tripWorkBo.insertPrimary(tripWork);
    }
    /**
     * 业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(TripWork tripWork) {

        return tripWorkBo.updatePrimary(tripWork);
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Override
    public String delPrimary(String dataId) {
        try {
            tripWorkBo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 业务处理：“我的公出”查询
     *
     * @return
     */    @Override
    public Page<TripWork> mytrip(Page<TripWork> page, TripWork tripWork) {
        return tripWorkBo.mytrip(page,tripWork);
    }

}


