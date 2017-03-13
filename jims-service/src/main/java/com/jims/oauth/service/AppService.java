package com.jims.oauth.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.oauth.api.AppApi;
import com.jims.oauth.bo.AppServiceBo;
import com.jims.oauth.dao.AppDao;
import com.jims.oauth.entity.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/5/30.
 */
@Service(version = "1.0.0")
public class AppService  implements AppApi{

    @Autowired
    private AppServiceBo appServiceBo;

    public App selectByPrimaryKey(String appKey){
        return appServiceBo.selectByPrimaryKey(appKey);
    }
}
