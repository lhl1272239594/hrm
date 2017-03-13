package com.jims.fbd.hrm;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.filter.OauthFilter;
import com.jims.common.utils.StringUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.tool.api.ToolApi;
import com.jims.fbd.hrm.tool.entity.Tool;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.oauth2.integration.utils.CacheManager;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.OrgStaff;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import java.io.IOException;
import java.util.List;


/**
 * Created by wangzhiming on 2016/8/18 0024.
 */
@Component
@Produces("application/json")
@Path("skip")
public class SkipRest {

    @Reference(version = "1.0.0")
    private SysUserApi sysUserApi;
    @Reference(version = "1.0.0")
    private PersionInfoApi persionInfoApi;
    @Reference(version = "1.0.0")
    private ToolApi toolApi;
    @Reference(version = "1.0.0")
    private OrgStaffApi orgStaffApi;

    /**
     * 根据组织机构查询试题分类
     *
     * @return
     * @author wangzhiming
     */
    @Path("loginToHrm")
    @GET
    public StringData  loginToHrm(@QueryParam("loginName") String loginName, @QueryParam("password") String password,
                                  @Context HttpServletRequest request, @Context HttpServletResponse response)
    {

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
                PersionInfo persionInfo = new PersionInfo();
                LoginInfo loginInfo = new LoginInfo();
                if(!loginName.equals("admin")){
                    String staffId=toolApi.getStaffId(sysUser.getPersionId());
                    OrgStaff orgStaff = toolApi.findStaffById(staffId);
                    if(orgStaff==null){
                        //判断密码是否正确   向页面返回信息
                        StringData sd = new StringData();
                        sd.setData("nameNull");
                        return sd;
                    }
                    List<OrgRole> role = toolApi.getRole(staffId,orgStaff.getOrgId());
                    if(role.size()>0){
                        String roleId=role.get(0).getId();
                        String roleName=role.get(0).getRoleName();
                        loginInfo.setRoleId(roleId);
                        loginInfo.setRoleName(roleName);
                    }


                    persionInfo.setId(orgStaff.getPersionId());
                    PersionInfo p = persionInfoApi.getCard(persionInfo);

                    Tool tool = new Tool();
                    tool.setOrgId(orgStaff.getOrgId());
                    tool.setUserId(orgStaff.getPersionId());
                    Tool orgNameList=toolApi.findOrgName(tool);

                    loginInfo.setOrgId(orgStaff.getOrgId());
                    loginInfo.setStaffId(staffId);
                    loginInfo.setDeptId(orgStaff.getDeptId());
                    loginInfo.setUserName(p.getName());
                    session.setAttribute("OrgNameList",orgNameList);
                    session.setAttribute("PersionInfo",p);
                    session.setAttribute("LoginInfo",loginInfo);
                    session.setAttribute("OrgStaff",orgStaff);

                }
                loginInfo.setPersionId(persionInfo.getId());
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
    @Path("get-login-info")
    @GET
    public LoginInfo getLoginInfo(@Context HttpServletRequest request,@Context HttpServletResponse response,@Context UriInfo uriInfo) throws IOException {
        HttpSession session = request.getSession() ;
        String sessionId = session.getId() ;
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        return loginInfo;
    }



    @Path("exit")
    @GET
    public void exit(@Context HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession() ;
        String sessionId = session!=null?null:session.getId() ;
        CacheManager.clearOnly(sessionId);
        session.removeAttribute("LoginInfo");
        session.invalidate();
    }

}
