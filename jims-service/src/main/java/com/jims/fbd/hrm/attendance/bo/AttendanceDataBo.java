
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.entity.AttendanceData;
import com.jims.fbd.hrm.attendance.dao.AttendanceDataDao;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * AO层
 *
 * @author txb
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class AttendanceDataBo extends CrudImplService<AttendanceDataDao, AttendanceData>{



    /**
     * 打卡数据查询
     *
     * @return
     */
    public Page<AttendanceData> findAttList(Page<AttendanceData> page, AttendanceData attendanceData ) {
       attendanceData.setPage(page);
       page.setList(dao.findAttList(attendanceData));
       return page;
   }
    /**
     *考勤综合查询--日数据
     *
     * @return
     */    public Page<AttendanceData> findDayReport(Page<AttendanceData> page, AttendanceData attendanceData ) {
        attendanceData.setPage(page);
        page.setList(dao.findDayReport(attendanceData));
        return page;
    }
    /**
     *考勤综合查询--日数据-汇总
     *
     * @return
     */
    public List<AttendanceData> findDayReportAll(AttendanceData attendanceData){

        return dao.findDayReport(attendanceData);
    }
    /**
     * 打卡数据综合查询-月汇总
     *
     * @return
     */    public Page<AttendanceData> findMonthReport(Page<AttendanceData> page, AttendanceData attendanceData ) {
        attendanceData.setPage(page);
        page.setList(dao.findMonthReport(attendanceData));
        return page;
    }

    /**
     *新增打卡数据
     *
     * @return
     */    public String insertPrimary(AttendanceData attendanceData) {

        return dao.insertPrimary(attendanceData)+"";
    }
    //删除数据
    public String del(List<AttendanceData> attendanceData) {
        for (AttendanceData q : attendanceData) {
            dao.del(q);
        }
        return "sucsess";
    }

    public String findOccupy(AttendanceData attDate) {
        String result = "no";
        int sum = dao.findOccupy(attDate);
        if (sum>1||sum==1){
            result = "yes";
        }
        return result;
    }
}