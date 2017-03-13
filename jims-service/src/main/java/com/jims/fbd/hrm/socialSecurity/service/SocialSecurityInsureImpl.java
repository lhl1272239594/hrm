package com.jims.fbd.hrm.socialSecurity.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.socialSecurity.api.SocialSecurityInsureApi;
import com.jims.fbd.hrm.socialSecurity.bo.SocialSecurityInsureBo;
import com.jims.fbd.hrm.socialSecurity.dao.SocialSecurityInsureDao;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsure;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsurePerson;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 *
 *
 * @author yangchen
 * @version 2016-08-23
 */
@Service(version = "1.0.0")
public class SocialSecurityInsureImpl implements SocialSecurityInsureApi {
    @Autowired
    private SocialSecurityInsureBo socialSecurityInsureBo;
    @Autowired
    private SocialSecurityInsureDao socialSecurityInsureDao;


    /**
     * 业务处理：查询-分页
     *
     * @return
     */
    @Override
    public Page<SocialSecurityInsurePerson> findList(Page<SocialSecurityInsurePerson> page,
                                                     SocialSecurityInsurePerson socialSecurityInsurePerson) {

        return socialSecurityInsureBo.findList(page,socialSecurityInsurePerson);
    }
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    @Override
    public List<SocialSecurityInsurePerson> findAllList(String orgId)
    {
        return socialSecurityInsureBo.findAllList(orgId);
    }
    /**
     * 业务处理：删除投保人员
     *
     * @return
     */
    @Override
    public String deletePrimary(SocialSecurityInsurePerson socialSecurityInsurePerson) {

        return socialSecurityInsureBo.deletePrimary(socialSecurityInsurePerson);
    }
    /**
     * 业务处理：新增
     *
     * @return
     */
    @Override
    public String insertPrimary(SocialSecurityInsurePerson socialSecurityInsurePerson) {

        return socialSecurityInsureBo.insertPrimary(socialSecurityInsurePerson);
    }
    /**
     * 业务处理：修改
     *
     * @return
     */
    @Override
    public String updatePrimary(SocialSecurityInsurePerson socialSecurityInsurePerson) {

        return socialSecurityInsureBo.updatePrimary(socialSecurityInsurePerson);
    }

    @Override
    public String delPrimary(List<SocialSecurityInsure> plans) {
        return socialSecurityInsureBo.delPrimary(plans);
    }

}


