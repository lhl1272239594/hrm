package com.jims.demo.dao;

import com.jims.demo.entity.DemoUser;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.common.persistence.CrudDao;
/**
 * Created by heren on 2016/4/5.
 */
@MyBatisDao
public interface SayHelloDao extends  CrudDao<DemoUser>{

    /**
     *
     * @param user
     * @return
     */
    public void sayHello(DemoUser user) ;


}
