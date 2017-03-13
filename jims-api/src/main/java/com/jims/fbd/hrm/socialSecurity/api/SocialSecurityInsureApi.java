package com.jims.fbd.hrm.socialSecurity.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsure;
import com.jims.fbd.hrm.socialSecurity.entity.SocialSecurityInsurePerson;

import java.util.List;

/**
 * Created by yangchen on 2016/8/23.
 */
public interface SocialSecurityInsureApi {


    /**
     * 业务处理：查询-分页
     *
     * @return
     */
    Page<SocialSecurityInsurePerson> findList(Page<SocialSecurityInsurePerson> page,
                                              SocialSecurityInsurePerson socialSecurityInsurePerson);
    /**
     * 业务处理：查询-无分页
     *
     * @return
     */
    List<SocialSecurityInsurePerson> findAllList(String orgId);
    /**
     * 业务处理：删除投保人员
     *
     * @return
     */
    String deletePrimary(SocialSecurityInsurePerson socialSecurityInsurePerson);
    /**
     * 业务处理：新增
     *
     * @return
     */
    String insertPrimary(SocialSecurityInsurePerson socialSecurityInsurePerson);

    /**
     * 业务处理：修改
     *
     * @return
     */
    String updatePrimary(SocialSecurityInsurePerson socialSecurityInsurePerson);

    String delPrimary(List<SocialSecurityInsure> plans);

}
