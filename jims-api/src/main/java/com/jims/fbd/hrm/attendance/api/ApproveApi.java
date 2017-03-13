package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.Approve;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface ApproveApi {



    public Page<Approve> findList(Page<Approve> page, Approve approve);



    public String approve(Approve approve);

}
