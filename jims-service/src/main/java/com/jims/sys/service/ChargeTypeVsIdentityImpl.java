package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.ChargeTypeVsIdentityApi;
import com.jims.sys.dao.ChargeTypeVsIdentityDao;
import com.jims.sys.entity.ChargeTypeVsIdentity;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service(version = "1.0.0")

public class ChargeTypeVsIdentityImpl extends CrudImplService<ChargeTypeVsIdentityDao, ChargeTypeVsIdentity> implements ChargeTypeVsIdentityApi{

    @Override
    public List<String> findTypeList() {
        return dao.findTypeList(new ChargeTypeVsIdentity());
    }
}
