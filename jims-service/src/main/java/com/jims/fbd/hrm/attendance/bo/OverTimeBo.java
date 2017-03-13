
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.OverTimeDao;
import com.jims.fbd.hrm.attendance.entity.OverTime;
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
public class OverTimeBo extends CrudImplService<OverTimeDao, OverTime>{

    /**
     * 业务处理：查询带分页
     *
     * @return
     */
   public Page<OverTime> findList(Page<OverTime> page, OverTime overTime ) {
       overTime.setPage(page);
       page.setList(dao.findList(overTime));
       return page;
   }
    /**
     * 业务处理：查询不带分页
     *
     * @return
     */
    public List<OverTime> findAllList(OverTime overTime){

        return dao.findList(overTime);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(OverTime overTime) {

        return dao.insertPrimary(overTime)+"";
    }


    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(OverTime overTime) {

        return dao.updatePrimary(overTime)+"";
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
     * 业务处理：“我的加班”查询
     *
     * @return
     */    public Page<OverTime> myovertime(Page<OverTime> page, OverTime overTime) {
        overTime.setPage(page);
        page.setList(dao.myovertime(overTime));
        return page;
    }
}