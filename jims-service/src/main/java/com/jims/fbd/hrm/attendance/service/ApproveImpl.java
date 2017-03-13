package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.ApproveApi;
import com.jims.fbd.hrm.attendance.bo.ApproveBo;
import com.jims.fbd.hrm.attendance.dao.ApproveDao;
import com.jims.fbd.hrm.attendance.entity.Approve;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class ApproveImpl implements ApproveApi {
    @Autowired
    private ApproveBo approveBo;
    @Autowired
    private ApproveDao approveDao;



    @Override
    public Page<Approve> findList(Page<Approve> page, Approve approve) {

        return approveBo.findList(page,approve);
    }

    @Override
    public String approve(Approve approve) {

        return approveBo.approve(approve);
    }



}


