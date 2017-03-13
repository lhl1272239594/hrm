package com.jims.demo.api;


import com.jims.common.service.CrudService;
import com.jims.demo.entity.DemoUser;

import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
public interface SayHelloApi{

    /**
     *
     * @param user
     * @return
     */
    public String sayHello(DemoUser user);

    public List<DemoUser> getDemo() ;


}
