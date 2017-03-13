package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.CookieUtils;
import com.jims.common.utils.StringUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.oauth2.integration.utils.CacheManager;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.LoginVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
@Component
@Produces("application/json")
@Path("login")
public class LoginRest {

    @Reference(version = "1.0.0")
    private SysUserApi sysUserApi;

    @Reference(version = "1.0.0")
    private PersionInfoApi persionInfoApi;
    SysUser user = null;

    @GET
    @Path("list")
    @Consumes({MediaType.APPLICATION_JSON})
    public StringData login(@QueryParam("loginName") String loginName, @QueryParam("password") String password, @Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        //判断用户名和密码是否不为空
        if (StringUtils.isNotBlank(loginName) && StringUtils.isNotBlank(password)) {

            //与数据库中的用户名进行比对，查看是否正确，并响应给用户
            SysUser sysUser = sysUserApi.selectLoginName(loginName);
            if (sysUser == null) {
                StringData stringData = new StringData();
                stringData.setData("nameNull");
                return stringData;
            }
            String pwd = sysUser.getPassword();
            //与数据库中的密码进行比对，查看是否正确，并响应给用户
            if (password.equals(pwd)) {
                StringData stringData = new StringData();
                stringData.setCode("success");
                stringData.setData(sysUser.getPersionId());
                LoginInfo loginInfo = new LoginInfo();
                loginInfo.setPersionId(sysUser.getPersionId());
                PersionInfo persionInfo = new PersionInfo();
                persionInfo.setId(sysUser.getPersionId());
                PersionInfo p = persionInfoApi.getCard(persionInfo);
                loginInfo.setUserName(p.getName());
                //将登陆信息方到缓存中
                Cache cache = new Cache(session.getId(), loginInfo, 7200, true);
                CacheManager.putCache(session.getId(), cache);

                return stringData;
            } else {
                //判断密码是否正确   向页面返回信息
                StringData stringData = new StringData();
                stringData.setData("passNull");
                return stringData;
            }
        }
        return null;
    }

    @POST
    @Path("findNameByOwner")
    public StringData findNameByOwner(String loginName) {
        SysCompany sysCompany = sysUserApi.findNameByOwner(loginName);
        StringData stringData = new StringData();
        stringData.setData(sysCompany.getOrgName());
        return stringData;
    }

    @Path("get-login-info")
    @GET
    public LoginInfo getLoginInfo(@Context HttpServletRequest request,@Context HttpServletResponse response,@Context UriInfo uriInfo) throws IOException {
        HttpSession session = request.getSession() ;
        String sessionId = session.getId() ;
        Cache cache = CacheManager.getCacheInfo(sessionId);
        LoginInfo loginInfo = cache==null?null:(LoginInfo)cache.getValue() ;
        return loginInfo;
    }


    /**
     * 根据登录人的persionId查询四种方式的密码
     *
     * @param persionId
     * @return
     */
    @GET
    @Path("findPasswordByPid")
    public StringData findPasswordByPid(@QueryParam("persionId") String persionId) {

        SysUser sysUser = new SysUser();
        sysUser.setPersionId(persionId);

        List<SysUser> list = sysUserApi.findPasswordByPid(sysUser);

        StringData stringData = new StringData();
        stringData.setData(list.get(0).getPassword());
        return stringData;
    }

    /**
     * 根据persionId修改四种方式登录的密码
     *
     * @param password
     * @return
     */
    @GET
    @Path("updatePassword")
    public StringData updatePassword(@QueryParam("password") String password,@QueryParam("persionId") String persionId) {

        SysUser sysUser = new SysUser();
        sysUser.setPassword(password);
        sysUser.setPersionId(persionId);
        sysUserApi.updatePassword(sysUser);

        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }


    @Path("exit")
    @GET
    public void exit(@Context HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession() ;
        String sessionId = session!=null?null:session.getId() ;
        CacheManager.clearOnly(sessionId);
    }

}

