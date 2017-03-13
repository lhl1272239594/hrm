package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.AttendanceData;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface AttendanceDataApi {

    /**
     * 打卡数据查询
     *
     * @return
     */
    public Page<AttendanceData> findAttList(Page<AttendanceData> page, AttendanceData attendanceData);
    /**
     *考勤综合查询--日数据
     *
     * @return
     */
    public Page<AttendanceData> findDayReport(Page<AttendanceData> page, AttendanceData attendanceData);
    /**
     *考勤综合查询--日数据-汇总
     *
     * @return
     */
    public List<AttendanceData> findDayReportAll(AttendanceData attendanceData);
    /**
     * 打卡数据综合查询-月汇总
     *
     * @return
     */
    public Page<AttendanceData> findMonthReport(Page<AttendanceData> page, AttendanceData attendanceData);

    /**
     *打卡记录删除
     *
     * @return
     */
    public String del(List<AttendanceData> attendanceData);


    /**
     *新增打卡数据
     *
     * @return
     */
    public String insertPrimary(AttendanceData attendanceData);
    /**
     *打卡数据重复校验
     *
     * @return
     */
    public String findOccupy(AttendanceData attDate);


}
