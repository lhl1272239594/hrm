package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.bo.AttendanceDataBo;
import com.jims.fbd.hrm.attendance.entity.AttendanceData;
import com.jims.fbd.hrm.attendance.api.AttendanceDataApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 考勤记录查询设置imp层
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class AttendanceDataImpl implements AttendanceDataApi {
    @Autowired
    private AttendanceDataBo attendanceDataBo;



    /**
     * 打卡数据查询
     *
     * @return
     */    @Override
    public Page<AttendanceData> findAttList(Page<AttendanceData> page, AttendanceData attendanceData) {
        return attendanceDataBo.findAttList(page,attendanceData);
    }
    /**
     *考勤综合查询--日数据
     *
     * @return
     */
    @Override
    public Page<AttendanceData> findDayReport(Page<AttendanceData> page, AttendanceData attendanceData) {
        return attendanceDataBo.findDayReport(page,attendanceData);
    }
    /**
     *考勤综合查询--日数据-汇总
     *
     * @return
     */
    @Override
    public List<AttendanceData> findDayReportAll(AttendanceData attendanceData)
    {
        return attendanceDataBo.findDayReportAll(attendanceData);
    }
    /**
     * 打卡数据综合查询-月汇总
     *
     * @return
     */
    @Override
    public Page<AttendanceData> findMonthReport(Page<AttendanceData> page, AttendanceData attendanceData) {
        return attendanceDataBo.findMonthReport(page,attendanceData);
    }
    //删除数据
    @Override
    public String del(List<AttendanceData> attendanceData) {

        return attendanceDataBo.del(attendanceData);
    }

    /**
     *新增打卡数据
     *
     * @return
     */    @Override
    public String insertPrimary(AttendanceData attendanceData) {

        return attendanceDataBo.insertPrimary(attendanceData);
    }
    @Override
    public String findOccupy(AttendanceData attDate) {
        return attendanceDataBo.findOccupy(attDate);
    }
}
