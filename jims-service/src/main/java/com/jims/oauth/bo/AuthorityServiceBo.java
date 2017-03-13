package com.jims.oauth.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.dao.AuthorityDao;
import com.jims.oauth.entity.Authority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class AuthorityServiceBo extends CrudImplService<AuthorityDao, Authority> {

    public Authority findUnique(String appKey, String userId) {
        return dao.findUnique(appKey, userId);
    }

    public String save(Authority authority) {
        String data = String.valueOf(dao.insert(authority));
        return data;
    }

    public Authority findAppKey(String appKey) {
        return dao.findAppKey(appKey);
    }
}
