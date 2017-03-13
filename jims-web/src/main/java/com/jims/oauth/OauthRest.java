package com.jims.oauth;

import com.jims.common.data.StringData;
import com.jims.util.JedisUtils;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 * Created by Administrator on 2016/5/30.
 */
@Component
@Path("oauth")
@Produces("application/json")
public class OauthRest {


    @Path("result")
    @GET
    public StringData result(){
        StringData stringData = new StringData();
        stringData.setCode("false");
        stringData.setData("success");
        return stringData;
    }

    @Path("code")
    @GET
    public StringData code(@QueryParam("url")String url){
        StringData stringData = new StringData();
        stringData.setCode(url);
        stringData.setData("success");
        return stringData;
    }

    @Path("redis")
    @GET
    public StringData redis(){
        String code=JedisUtils.set("GnslTZRF","user",0);
        StringData stringData = new StringData();
        stringData.setCode(code);
        stringData.setData("success");
        return stringData;
    }
}
