
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.TempAttendanceDao;
import com.jims.fbd.hrm.attendance.entity.TempAttendancePerson;
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
public class TempAttendanceBo extends CrudImplService<TempAttendanceDao, TempAttendancePerson>{



    /**
     * 业务处理：查询临时考勤
     *
     * @return
     */
   public Page<TempAttendancePerson> findList(Page<TempAttendancePerson> page, TempAttendancePerson tempAttendancePerson ) {
       tempAttendancePerson.setPage(page);
       page.setList(dao.findList(tempAttendancePerson));
       return page;
   }
    /**
     * 业务处理:查询临时考勤人员
     *
     * @return
     */

    public List<TempAttendancePerson> findPerson(TempAttendancePerson tempAttendancePerson){

        return dao.findPerson(tempAttendancePerson);
    }

    /**
     * 业务处理：新增主表
     *
     * @return
     */
    public String insertPrimary(TempAttendancePerson tempAttendancePerson) {

        return dao.insertPrimary(tempAttendancePerson)+"";
    }
    /**
     * 业务处理：修改主表
     *
     * @return
     */
    public String updatePrimary(TempAttendancePerson tempAttendancePerson) {

        return dao.updatePrimary(tempAttendancePerson)+"";
    }


    /**
     * 业务处理：新增从表
     *
     * @return
     */
    public String insertForeign(TempAttendancePerson tempAttendancePerson) {

        return dao.insertForeign(tempAttendancePerson)+"";
    }

    public String updateForeign(TempAttendancePerson tempAttendancePerson) {

        return dao.updateForeign(tempAttendancePerson)+"";
    }

    /**
     * 业务处理：删除主表信息
     *
     * @return
     */
    public void delPrimary(TempAttendancePerson tempAttendancePerson) {


        dao.delPrimary(tempAttendancePerson);

    }
    /**
     * 业务处理：删除从表信息
     *
     * @return
     */
    public void delForeign(TempAttendancePerson tempAttendancePerson) {

        dao.delForeign(tempAttendancePerson);
    }
    /**
     * 业务处理：临时考勤重复判断
     *
     * @return
     */
    public List<TempAttendancePerson> findBoolean(TempAttendancePerson tempAttendancePerson) {

        return dao.findBoolean(tempAttendancePerson);
    }
}