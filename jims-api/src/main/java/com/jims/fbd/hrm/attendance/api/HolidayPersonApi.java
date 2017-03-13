package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.HolidayPerson;
import com.jims.fbd.hrm.attendance.entity.HolidayPersonUser;
/**
 * Created by yangchen on 2016/8/23.
 */
public interface HolidayPersonApi {



    public Page<HolidayPersonUser> findList(Page<HolidayPersonUser> page, HolidayPersonUser holidayPersonUser);

    //新增主表信息
    public String insertPrimary(HolidayPersonUser holidayPersonUser);

    //更新主表信息
    public String updatePrimary(HolidayPersonUser holidayPersonUser);

    public String delPrimary(String dataId);

}
