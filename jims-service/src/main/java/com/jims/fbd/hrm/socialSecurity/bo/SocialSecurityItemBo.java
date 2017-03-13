
package com.jims.fbd.hrm.socialSecurity.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.socialSecurity.dao.SocialSecurityItemDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityItem;
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
public class SocialSecurityItemBo extends CrudImplService<SocialSecurityItemDao, SocialSecurityItem>{
    /**
     * 业务处理：查询-分页
     *
     * @return
     */
   public Page<SocialSecurityItem> findList(Page<SocialSecurityItem> page, SocialSecurityItem socialSecurityItem ) {
       socialSecurityItem.setPage(page);
       page.setList(dao.findList(socialSecurityItem));
       return page;
   }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    public List<SocialSecurityItem> findAllList(String orgId){

        return dao.findAllList(orgId);
    }
    /**
     *新增重复判断
     * @return
     * @author
     */
    public List<SocialSecurityItem> findSame(String orgId,String editItemDes,String id){
        return dao.findSame(orgId,editItemDes,id);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    public String insertPrimary(SocialSecurityItem socialSecurityItem) {

        return dao.insertPrimary(socialSecurityItem)+"";
    }


    /**
     * 业务处理：修改
     *
     * @return
     */
    public String updatePrimary(SocialSecurityItem socialSecurityItem) {

        return dao.updatePrimary(socialSecurityItem)+"";
    }

    /**
     * 删除占用判断
     * @return
     * @author ZYG
     * @version 2016-08-23
     */
    public String findOccupy(SocialSecurityItem socialSecurityItem) {
        String result = "no";
            int sum = dao.findOccupy(socialSecurityItem);
            if (sum>1||sum==1){
                result = "yes";
        }
        return result;
    }

    public void delPrimary(String dataId) {

        dao.delPrimary(dataId);
    }
}