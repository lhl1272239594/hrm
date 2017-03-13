package com.jims.demo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.demo.api.SayHelloApi;
import com.jims.demo.entity.DemoUser;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("hello")
public class HelloRest {

    @Reference(version = "1.0.0")
    private SayHelloApi sayHelloApi;

    @GET
    @Path("{userName}/{password}")
    public String sayHello(@PathParam("userName") String userName, @PathParam("password") String password) {
        DemoUser user = new DemoUser(userName, password);
        return sayHelloApi.sayHello(user);
    }

    @GET
    @Path("get")
    public List<DemoUser> getDemo() throws ServletException {
        throw  new ServletException("19 指定的端口没有连接。\n" +
                "620 无法确定端点。\n" +
                "621 系统无法打开电话簿。\n" +
                "622 系统无法加载电话簿。\n" +
                "623 系统无法找到此连接的电话簿项。\n" +
                "624 系统无法更新电话簿文件。\n" +
                "625 系统在电话簿中找到无效信息。\n" +
                "626~650\n" +
                "626 无法加载字符串。\n" +
                "627 无法找到关键字。\n" +
                "628 连接被关闭。\n" +
                "629 连接被远程计算机关闭。\n" +
                "630 由于硬件故障，调制解调器断开连接。\n" +
                "631 用户断开了调制解调器连接。\n" +
                "632 检测到不正确的结构大小。") ;
    }


}
