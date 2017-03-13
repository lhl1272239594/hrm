package com.jims.fbd.hrm.socialSecurity.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityItem;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface SocialSecurityItemApi {


    /**
     * 业务处理：查询-分页
     *
     * @return
     */
    Page<SocialSecurityItem> findList(Page<SocialSecurityItem> page, SocialSecurityItem socialSecurityItem);
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    List<SocialSecurityItem> findAllList(String orgId);
    /**
     * 新增重复判断
     * @author
     * @version 2016-08-22
     * @return
     */
    List<SocialSecurityItem> findSame(String orgId, String editItemDes,String id);
    /**
     * 业务处理：新增
     *
     * @return
     */    String insertPrimary(SocialSecurityItem socialSecurityItem);

    /**
     * 业务处理：修改
     *
     * @return
     */
    String updatePrimary(SocialSecurityItem socialSecurityItem);

    /**
     * 删除占用判断
     * @author ZYG
     * @version 2016-08-22
     * @return
     */
    String findOccupy(SocialSecurityItem socialSecurityItem);
    /**
     * 业务处理：删除
     *
     * @return
     */
    String delPrimary(String dataId);

}
