package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.ApprovePersonUser;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface ApprovePersonApi {


    /**
     * 审批流程查询-无分页
     *
     * @return
     */
    public Page<ApprovePersonUser> findList(Page<ApprovePersonUser> page, ApprovePersonUser approvePersonUser);

    //新增主表信息
    public String insertPrimary(ApprovePersonUser approvePersonUser);

    //更新主表信息
    public String updatePrimary(ApprovePersonUser approvePersonUser);

    public String delPrimary(String dataId);

}
