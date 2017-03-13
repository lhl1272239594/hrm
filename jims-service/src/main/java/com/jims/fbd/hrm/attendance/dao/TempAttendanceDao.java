package com.jims.fbd.hrm.attendance.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.attendance.entity.TempAttendancePerson;

import java.util.List;

/**
 * 节日设置DAO接口
 * @author yangchen
 * @version 2016-08-23
 */
@MyBatisHrmDao
public interface TempAttendanceDao extends CrudDao<TempAttendancePerson>{

  /**
   * 业务处理：查询临时考勤
   *
   * @return
   */
  public List<TempAttendancePerson> findList(TempAttendancePerson tempAttendancePerson);
  /**
   * 业务处理:查询临时考勤人员
   *
   * @return
   */

  public List<TempAttendancePerson> findPerson(TempAttendancePerson tempAttendancePerson);
  /**
   * 业务处理：删除主表信息
   *
   * @return
   */
  public void delPrimary(TempAttendancePerson tempAttendancePerson) ;
  /**
   * 业务处理：删除从表信息
   *
   * @return
   */
  public void delForeign(TempAttendancePerson tempAttendancePerson) ;
  /**
   * 业务处理：临时考勤重复判断
   *
   * @return
   */
  public List<TempAttendancePerson> findBoolean(TempAttendancePerson tempAttendancePerson);

}