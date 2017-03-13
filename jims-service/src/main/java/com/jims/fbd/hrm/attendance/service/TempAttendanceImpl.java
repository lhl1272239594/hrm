package com.jims.fbd.hrm.attendance.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.attendance.api.TempAttendanceApi;
import com.jims.fbd.hrm.attendance.bo.TempAttendanceBo;
import com.jims.fbd.hrm.attendance.entity.TempAttendancePerson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 临时考勤查询设置imp层
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class TempAttendanceImpl implements TempAttendanceApi {
    @Autowired
    private TempAttendanceBo tempAttendanceBo;



    /**
     * 业务处理：查询临时考勤
     *
     * @return
     */
    @Override
    public Page<TempAttendancePerson> findList(Page<TempAttendancePerson> page, TempAttendancePerson tempAttendancePerson) {
        return tempAttendanceBo.findList(page,tempAttendancePerson);
    }
    /**
     * 业务处理:查询临时考勤人员
     *
     * @return
     */

    @Override
    public List<TempAttendancePerson> findPerson(TempAttendancePerson tempAttendancePerson)
    {
        return tempAttendanceBo.findPerson(tempAttendancePerson);
    }
    /**
     * 业务处理：删除主表信息
     *
     * @return
     */
    @Override
    public String delPrimary(TempAttendancePerson tempAttendancePerson) {
        try {
            tempAttendanceBo.delPrimary(tempAttendancePerson);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }
    /**
     * 业务处理：删除从表信息
     *
     * @return
     */
    @Override
    public String delForeign(TempAttendancePerson tempAttendancePerson) {
        try {
            tempAttendanceBo.delForeign(tempAttendancePerson);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
     * 业务处理：新增主表
     *
     * @return
     */    @Override
    public String insertPrimary(TempAttendancePerson tempAttendancePerson) {

        return tempAttendanceBo.insertPrimary(tempAttendancePerson);
    }

    /**
     * 业务处理：新增从表
     *
     * @return
     */
    public String insertForeign(TempAttendancePerson tempAttendancePerson) {

        return tempAttendanceBo.insertForeign(tempAttendancePerson)+"";
    }
    /**
     * 业务处理：修改主表
     *
     * @return
     */
    public String updatePrimary(TempAttendancePerson tempAttendancePerson) {

        return tempAttendanceBo.updatePrimary(tempAttendancePerson)+"";
    }
    /**
     * 业务处理：临时考勤重复判断
     *
     * @return
     */
    @Override
    public List<TempAttendancePerson> findBoolean(TempAttendancePerson tempAttendancePerson) {

        return tempAttendanceBo.findBoolean(tempAttendancePerson);
    }

}
