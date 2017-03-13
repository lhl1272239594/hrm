package com.jims.oauth.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.dao.AppDao;
import com.jims.oauth.entity.App;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by luohk on 2016/6/16.
 */
@Component
@Transactional(readOnly = true)
public class AppServiceBo extends CrudImplService<AppDao, App> {
    public App selectByPrimaryKey(String appKey) {
        return dao.selectByPrimaryKey(appKey);
    }
}
