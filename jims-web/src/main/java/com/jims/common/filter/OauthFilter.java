package com.jims.common.filter;

import com.jims.common.vo.LoginInfo;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.sys.entity.MenuDict;
import com.jims.util.JedisUtils;
import com.jims.oauth2.integration.utils.CacheManager;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Administrator on 2016/5/30.
 */
public class OauthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpSession session = httpServletRequest.getSession();
        String orgId = httpServletRequest.getParameter("orgId");
        StringBuffer requestURL = req.getRequestURL();
        String requestUri =req.getRequestURI();
        if (!(requestURL.indexOf("/login") > 0 || requestURL.indexOf("modules/sys/register.html") > 0 || requestURL.indexOf("modules/sys/login.html") > 0 || requestURL.indexOf(".js") > 0 || requestURL.indexOf(".pdf") > 0  || requestURL.indexOf(".css") > 0
                || requestURL.indexOf("/service/register/add-info") > 0 || requestURL.indexOf("/service/register/getCard") > 0 || requestURL.indexOf("/service/register/getNick") > 0 || requestURL.indexOf("/service/register/getEmail") > 0
                || requestURL.indexOf("/service/register/getPhone") > 0 || requestURL.indexOf("/servlet/validateCodeServlet") > 0 || requestURL.indexOf("/service/login/list") > 0 || requestURL.indexOf("/service/skip/exit") > 0 || requestURL.substring(requestURL.length()-1,requestURL.length()).equals("/")
        )) {
            Cache cache = CacheManager.getCacheInfo(session.getId());
            if (cache == null) {
                res.setContentType("text/html;charset=UTF-8");
                // Ajax请求, 前段根据此header进行处理
                res.setHeader("sessionTimeout", "Session time out, you need relogin !");
                // 返回未认证的状态码(401)
                res.setStatus(HttpStatus.UNAUTHORIZED.value());
                return;
            }
            else {
                res.setContentType("text/html;charset=UTF-8");
                // Ajax请求, 前段根据此header进行处理
                res.setHeader("sessionIsAlive", "Session time out, you need relogin !");
            }
        }
        chain.doFilter(request, response);

    }

    @Override
    public void destroy() {

    }
}
