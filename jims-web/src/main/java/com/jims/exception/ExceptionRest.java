package com.jims.exception;

import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.vo.LoginInfo;
 import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

@Component
@Produces("application/json")
@Path("exceptions")
public class ExceptionRest  {


    @Path("getCookie")
    @GET
    public String getExamSubclass(@QueryParam("key")String key,@Context HttpServletRequest request ){
        String returnVal="";
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies){
            if(key.equals(cookie.getName())){
                try {
                    returnVal= URLDecoder.decode(cookie.getValue(),"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        }
        return returnVal;
    }
}
