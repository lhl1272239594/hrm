package com.jims.fbd.hrm.socialSecurity.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.socialSecurity.api.SocialSecurityItemApi;
import com.jims.fbd.hrm.socialSecurity.bo.SocialSecurityItemBo;
import com.jims.fbd.hrm.socialSecurity.dao.SocialSecurityItemDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class SocialSecurityItemImpl implements SocialSecurityItemApi {
    @Autowired
    private SocialSecurityItemBo socialSecurityItemBo;
    @Autowired
    private SocialSecurityItemDao socialSecurityItemDao;


    /**
     * 业务处理：查询-分页
     *
     * @return
     */
    @Override
    public Page<SocialSecurityItem> findList(Page<SocialSecurityItem> page, SocialSecurityItem socialSecurityItem) {

        return socialSecurityItemBo.findList(page,socialSecurityItem);
    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    @Override
    public List<SocialSecurityItem> findAllList(String orgId) {
        return socialSecurityItemBo.findAllList(orgId);
    }
    /**
     * 查询是否重复
     * @return
     * @author
     * @version 2016-08-23
     */
    @Override
    public List<SocialSecurityItem> findSame(String orgId,String editItemDes,String id) {
        return socialSecurityItemBo.findSame(orgId,editItemDes,id);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(SocialSecurityItem socialSecurityItem) {

        return socialSecurityItemBo.insertPrimary(socialSecurityItem);
    }
    /**
     * 业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(SocialSecurityItem socialSecurityItem) {

        return socialSecurityItemBo.updatePrimary(socialSecurityItem);
    }

    /**
     * 删除占用判断
     * @return
     * @author ZYG
     * @version 2016-08-23
     */
    @Override
    public String findOccupy(SocialSecurityItem socialSecurityItem) {
        return socialSecurityItemBo.findOccupy(socialSecurityItem);
    }

    @Override
    public String delPrimary(String dataId) {
        try {
            socialSecurityItemBo.delPrimary(dataId);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

}


