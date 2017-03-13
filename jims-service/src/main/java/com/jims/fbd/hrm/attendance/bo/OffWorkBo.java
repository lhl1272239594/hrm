
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.OffWorkDao;

import com.jims.fbd.hrm.attendance.entity.OffWork;
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
public class OffWorkBo extends CrudImplService<OffWorkDao, OffWork>{
    /**
     * 业务处理：查询带分页
     *
     * @return
     */
   public Page<OffWork> findList(Page<OffWork> page, OffWork offWork ) {
       offWork.setPage(page);
       page.setList(dao.findList(offWork));
       return page;
   }
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    public List<OffWork> findAllList(OffWork offWork){

        return dao.findList(offWork);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(OffWork offWork) {

        return dao.insertPrimary(offWork)+"";
    }

    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(OffWork offWork) {

        return dao.updatePrimary(offWork)+"";
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    public void delPrimary(String dataId) {

        dao.delPrimary(dataId);
    }
    /**
     * 业务处理：“我的请假”查询
     *
     * @return
     */    public Page<OffWork> myvacation(Page<OffWork> page, OffWork offWork) {
        offWork.setPage(page);
        page.setList(dao.myvacation(offWork));
        return page;
    }
}