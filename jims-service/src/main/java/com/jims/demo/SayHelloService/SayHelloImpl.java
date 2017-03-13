package com.jims.demo.SayHelloService;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.JedisUtils;
import com.jims.demo.api.SayHelloApi;
import com.jims.demo.entity.DemoUser;
import org.springframework.beans.factory.annotation.Autowired;
import com.jims.demo.dao.SayHelloDao;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Service(version = "1.0.0")
public class SayHelloImpl extends CrudImplService<SayHelloDao,DemoUser> implements SayHelloApi{

    @Autowired
    private SayHelloDao sayHelloDao;

    @Override

    public String sayHello(DemoUser user) {
        sayHelloDao.sayHello(user);
        return "hello "+user.getUserName() + ", your password is :"+user.getPassword();
    }

    @Override
    public List<DemoUser> getDemo() {
        JedisUtils.set("jims","rrrr",60*30);
        //List<DemoUser> list= sayHelloDao.findAllList(new DemoUser());
        return new ArrayList<DemoUser>();
    }



}
