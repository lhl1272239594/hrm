
package com.jims.fbd.hrm.socialSecurity.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.socialSecurity.dao.SocialSecurityInsureDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsure;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsurePerson;
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
public class SocialSecurityInsureBo extends CrudImplService<SocialSecurityInsureDao, SocialSecurityInsurePerson>{

    /**
     * 业务处理：查询-分页
     *
     * @return
     */
   public Page<SocialSecurityInsurePerson> findList(Page<SocialSecurityInsurePerson> page,
                                                    SocialSecurityInsurePerson socialSecurityInsurePerson ) {
       socialSecurityInsurePerson.setPage(page);
       page.setList(dao.findList(socialSecurityInsurePerson));
       return page;
   }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    public List<SocialSecurityInsurePerson> findAllList(String orgId){

        return dao.findAllList(orgId);
    }
    /**
     * 业务处理：删除投保人员
     *
     * @return
     */
    public String deletePrimary(SocialSecurityInsurePerson socialSecurityInsurePerson) {

         dao.deletePrimary(socialSecurityInsurePerson);
        return "success";
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(SocialSecurityInsurePerson socialSecurityInsurePerson) {

        return dao.insertPrimary(socialSecurityInsurePerson)+"";
    }

    /**
     * 业务处理：修改
     *
     * @return
     */

    public String updatePrimary(SocialSecurityInsurePerson socialSecurityInsurePerson) {

        return dao.updatePrimary(socialSecurityInsurePerson)+"";
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    public String delPrimary(List<SocialSecurityInsure> plans) {
        for (SocialSecurityInsure q : plans) {
            q.preUpdate();
            dao.delPrimary(q);
        }
        return "sucsess";
    }

}