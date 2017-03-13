package com.jims.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by heren on 2016/9/18.
 */
@Provider
public class ExceptionSupport implements ExceptionMapper<Exception> {

//    public static ThreadLocal<String> MSG = new ThreadLocal<String>();
    @Context
    private HttpServletRequest request;

    @Context
    private HttpServletResponse response;




  /*  @Override
    public Response toResponse(Exception e) {
         String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
         try {
           response.sendRedirect(path + "/500.html?e="+java.net.URLEncoder.encode(e.getMessage().toString(),"utf-8"));
         } catch (IOException e1) {
            e1.printStackTrace();
         }
        return  null;
    }*/


    @Override
    public Response toResponse(Exception exception) {

        try {
            PrintWriter writer = response.getWriter();
            response.setCharacterEncoding("UTF-8");
            writer.write(exception.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build() ;
    }

}
