
package com.jims.fbd.hrm.attendance.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.attendance.dao.ApproveDao;
import com.jims.fbd.hrm.attendance.entity.Approve;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * AO层
 *
 * @author txb
 * @version 2016-08-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class ApproveBo extends CrudImplService<ApproveDao, Approve>{


    @Autowired
    private ApproveDao approveDao;

   public Page<Approve> findList(Page<Approve> page, Approve approve ) {
       approve.setPage(page);
       page.setList(dao.findList(approve));
       return page;
   }
    public String approve(Approve approve) {

        return approveDao.approve(approve)+"";
    }



}