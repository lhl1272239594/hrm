
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.TripWorkDao;
import com.jims.fbd.hrm.attendance.entity.TripWork;
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
public class TripWorkBo extends CrudImplService<TripWorkDao, TripWork>{

    /**
     * 业务处理：查询带分页
     *
     * @return
     */
   public Page<TripWork> findList(Page<TripWork> page, TripWork tripWork ) {
       tripWork.setPage(page);
       page.setList(dao.findList(tripWork));
       return page;
   }
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    public List<TripWork> findAllList(TripWork tripWork){

        return dao.findList(tripWork);
    }

    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(TripWork tripWork) {

        return dao.insertPrimary(tripWork)+"";
    }

    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(TripWork tripWork) {

        return dao.updatePrimary(tripWork)+"";
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
     * 业务处理：“我的公出”查询
     *
     * @return
     */    public Page<TripWork> mytrip(Page<TripWork> page, TripWork tripWork) {
        tripWork.setPage(page);
        page.setList(dao.mytrip(tripWork));
        return page;
    }
}