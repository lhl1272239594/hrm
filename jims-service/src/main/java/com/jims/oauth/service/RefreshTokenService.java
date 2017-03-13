package com.jims.oauth.service;




import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.api.RefreshTokenApi;
import com.jims.oauth.bo.RefreshTokenServiceBo;
import com.jims.oauth.dao.RefreshTokenDao;
import com.jims.oauth.entity.RefreshToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * refresh_tokenService
 * @author luohk
 * @version 2016-05-30
 */
@Service(version = "1.0.0")
public class RefreshTokenService  implements RefreshTokenApi {

    @Autowired
    private RefreshTokenServiceBo refreshTokenServiceBo;

    public RefreshToken findUnique(String appKey, String userId){
        return refreshTokenServiceBo.findUnique(appKey,userId);
    }

    public String save(RefreshToken refreshToken){
        return refreshTokenServiceBo.save(refreshToken);
    }

    public String update(RefreshToken refreshToken){
        return refreshTokenServiceBo.update(refreshToken);
    }
	
}