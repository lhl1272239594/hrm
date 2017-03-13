package com.jims.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.UUID;

/**
 * Created by admin on 2016/9/21.
 */
@Component
public class ExceptionInterceptor implements HandlerInterceptor {

     private int openingTime;
     private int closingTime;
     public void setOpeningTime(int openingTime) {
               this.openingTime = openingTime;
     }

       public void setClosingTime(int closingTime) {
        this.closingTime = closingTime;
     }
    /**
     * 该方法在action执行前执行，可以实现对数据的预处理，比如：编码、安全控制等。
     如果方法返回true，则继续执行action。
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        System.out.println("preHandle");
        return true;
    }

    /**
     * 该方法在action执行后，生成视图前执行。在这里，我们有机会修改视图层数据。
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    /**
     * 最后执行，通常用于释放资源，处理异常。我们可以根据ex是否为空，来进行相关的异常处理。因为我们在平时处理异常时，都是从底层向上抛出异常，最后到了spring框架从而到了这个方法中。
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion--------------------------"+ex.getMessage());
        String path = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
        if(ex!=null&&ex.getMessage()!=null){
            String key= UUID.randomUUID().toString();
            Cookie cookie= null;
            try {
                cookie = new Cookie(key, URLEncoder.encode(ex.getMessage(), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            response.setCharacterEncoding("UTF-8");
            cookie.setMaxAge(60*60*24);
            cookie.setPath("/");
            response.addCookie(cookie);
            response.sendRedirect(path + "/500.html?key="+key);
        }
     }
}
