package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.AttendanceData;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 节日设置DAO接口
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface AttendanceDataDao extends CrudDao<AttendanceData>{

  /**
   * 打卡数据查询
   *
   * @return
   */  public List<AttendanceData> findAttList(AttendanceData attendanceData);
  /**
   *考勤综合查询--日数据
   *
   * @return
   */
  public List<AttendanceData> findDayReport(AttendanceData attendanceData);
  /**
   * 打卡数据综合查询-月汇总
   *
   * @return
   */
  public List<AttendanceData> findMonthReport(AttendanceData attendanceData);

  //删除数据
  public void del(@Param("AttendanceData" ) AttendanceData attendanceData);
  //判断占用
  public int findOccupy(@Param("AttendanceData" ) AttendanceData attDate);

}