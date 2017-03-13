package com.jims.oauth.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.api.AuthorityApi;
import com.jims.oauth.bo.AuthorityServiceBo;
import com.jims.oauth.dao.AuthorityDao;
import com.jims.oauth.entity.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * authorityService
 * @author luohk
 * @version 2016-05-30
 */
@Service(version = "1.0.0")
public class AuthorityService  implements AuthorityApi{

    @Autowired
    private AuthorityServiceBo authorityServiceBo;

    public Authority findUnique(String appKey, String userId){
         return authorityServiceBo.findUnique(appKey,userId);
    }

    public String save(Authority authority){
        return authorityServiceBo.save(authority);
    }

    public Authority findAppKey(String appKey){
        return authorityServiceBo.findAppKey(appKey);
    }
	
}