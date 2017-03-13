package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.OffWorkApi;
import com.jims.fbd.hrm.attendance.bo.OffWorkBo;
import com.jims.fbd.hrm.attendance.dao.OffWorkDao;
import com.jims.fbd.hrm.attendance.entity.OffWork;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class OffWorkImpl implements OffWorkApi {
    @Autowired
    private OffWorkBo offWorkBo;
    @Autowired
    private OffWorkDao offWorkDao;

    /**
     * 业务处理：查询带分页
     *
     * @return
     */

    @Override
    public Page<OffWork> findList(Page<OffWork> page, OffWork offWork) {

        return offWorkBo.findList(page,offWork);
    }
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    @Override
    public List<OffWork> findAllList(OffWork offWork) {

        return offWorkBo.findAllList(offWork);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(OffWork offWork) {

        return offWorkBo.insertPrimary(offWork);
    }
    /**
     * 业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(OffWork offWork) {

        return offWorkBo.updatePrimary(offWork);
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Override
    public String delPrimary(String dataId) {
        try {
            offWorkBo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 业务处理：“我的请假”查询
     *
     * @return
     */
    @Override
    public Page<OffWork> myvacation(Page<OffWork> page, OffWork offWork) {
        return offWorkBo.myvacation(page,offWork);
    }

}


