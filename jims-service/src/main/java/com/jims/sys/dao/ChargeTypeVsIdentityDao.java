package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.ChargeTypeVsIdentity;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@MyBatisDao
public interface ChargeTypeVsIdentityDao extends CrudDao<ChargeTypeVsIdentity>{
    public List<String> findTypeList(ChargeTypeVsIdentity chargeTypeVsIdentity);
}
