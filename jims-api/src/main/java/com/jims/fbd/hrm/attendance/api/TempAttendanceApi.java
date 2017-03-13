package com.jims.fbd.hrm.attendance.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.entity.TempAttendancePerson;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface TempAttendanceApi {

    /**
     * 业务处理：查询临时考勤
     *
     * @return
     */
    public Page<TempAttendancePerson> findList(Page<TempAttendancePerson> page,
                                               TempAttendancePerson tempAttendancePerson);
    /**
     * 业务处理:查询临时考勤人员
     *
     * @return
     */

    public List<TempAttendancePerson> findPerson(TempAttendancePerson tempAttendancePerson);



    /**
     * 业务处理：新增主表
     *
     * @return
     */
    public String insertPrimary(TempAttendancePerson tempAttendancePerson);

    /**
     * 业务处理：新增从表
     *
     * @return
     */
    public String insertForeign(TempAttendancePerson tempAttendancePerson);

    /**
     * 业务处理：修改主表
     *
     * @return
     */
    public String updatePrimary(TempAttendancePerson tempAttendancePerson);
    /**
     * 业务处理：删除主表信息
     *
     * @return
     */
    public String delPrimary(TempAttendancePerson tempAttendancePerson);
    /**
     * 业务处理：删除从表信息
     *
     * @return
     */
    public String delForeign(TempAttendancePerson tempAttendancePerson);
    /**
     * 业务处理：临时考勤重复判断
     *
     * @return
     */
    public List<TempAttendancePerson> findBoolean(TempAttendancePerson tempAttendancePerson);

}
