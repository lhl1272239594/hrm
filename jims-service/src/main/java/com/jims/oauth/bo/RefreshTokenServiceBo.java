package com.jims.oauth.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.dao.RefreshTokenDao;
import com.jims.oauth.entity.RefreshToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class RefreshTokenServiceBo extends CrudImplService<RefreshTokenDao, RefreshToken> {

    public RefreshToken findUnique(String appKey, String userId) {
        return dao.findUnique(appKey, userId);
    }

    public String save(RefreshToken refreshToken) {
        String data = String.valueOf(dao.insert(refreshToken));
        return data;
    }

    public String update(RefreshToken refreshToken) {
        String data = String.valueOf(dao.update(refreshToken));
        return data;
    }
}
