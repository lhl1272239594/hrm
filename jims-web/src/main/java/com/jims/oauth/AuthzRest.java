/**
 *       Copyright 2010 Newcastle University
 *
 *          http://research.ncl.ac.uk/smart/
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jims.oauth;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.oauth.api.AppApi;
import com.jims.oauth.api.AuthorityApi;
import com.jims.oauth.entity.App;
import com.jims.oauth.entity.Authority;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.entity.SysUser;
import com.jims.oauth2.as.issuer.MD5Generator;
import com.jims.oauth2.as.issuer.OAuthIssuerImpl;
import com.jims.oauth2.as.request.OAuthAuthzRequest;
import com.jims.oauth2.as.response.OAuthASResponse;
import com.jims.oauth2.common.OAuth;
import com.jims.oauth2.common.error.ServerErrorType;
import com.jims.oauth2.common.exception.OAuthProblemException;
import com.jims.oauth2.common.exception.OAuthSystemException;
import com.jims.oauth2.common.message.OAuthResponse;
import com.jims.oauth2.common.message.types.ResponseType;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.oauth2.integration.utils.CacheManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;

/**
 * client request authorization
 */
@Component
@Path("/authz")
@Produces("application/json")
public class AuthzRest {

    @Reference(version = "1.0.0")
    private AppApi appApi;
    @Reference(version = "1.0.0")
    private AuthorityApi authorityApi;
    @Reference(version = "1.0.0")
    private SysUserApi sysUserApi;
    //登录页面
    private static String loginPage;

    //错误页面
    private static String errorPage;

    public static final String INVALID_CLIENT_DESCRIPTION = "Client authentication failed (e.g., unknown client, no client authentication included, or unsupported authentication method).";

    @GET
    public Response authorize(@Context HttpServletRequest request)
            throws URISyntaxException, OAuthSystemException {

        OAuthAuthzRequest oauthRequest = null;

        OAuthIssuerImpl oauthIssuerImpl = new OAuthIssuerImpl(
                new MD5Generator());

        try {
            oauthRequest = new OAuthAuthzRequest(request);
            System.out.print("clent_id=" + oauthRequest.getClientId());
            String username = request.getParameter("loginName");
            String password = request.getParameter("password");
            if ("".equals(username) || username == null && "".equals(password) || password == null) {
                // 用户没有登录就跳转到登录页面
                return Response.temporaryRedirect(new URI("http://localhost:8080/modules/oauth/login.html?client_id=" + oauthRequest.getClientId() + "&redirect_uri=" + oauthRequest.getRedirectURI() + "&response_type=" + oauthRequest.getResponseType() + "&scope=" + oauthRequest.getScopes() + "")).build();
            }
            SysUser sysUser = new SysUser();
            sysUser.setLoginName(username);
            sysUser.setPassword(password);
            sysUser = sysUserApi.login(sysUser);
            if(sysUser == null){
                return Response.temporaryRedirect(new URI("http://localhost:8080/service/oauth/result")).build();
            }
            App app = null;
            if (oauthRequest.getClientId() != null && !"".equals(oauthRequest.getClientId())) {
                app = appApi.selectByPrimaryKey(oauthRequest.getClientId());
                if(app == null){
                    return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.CLIENT_ID_IS_NULL)).build();
                }
            } else {
                return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.CLIENT_ID_IS_NULL)).build();
            }
            // 根据response_type创建response
            String responseType = oauthRequest
                    .getParam(OAuth.OAUTH_RESPONSE_TYPE);

            OAuthASResponse.OAuthAuthorizationResponseBuilder builder = OAuthASResponse
                    .authorizationResponse(request,
                            HttpServletResponse.SC_FOUND);
            String grantType = oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE);
            String scope = oauthRequest.getParam(OAuth.OAUTH_SCOPE);
            // 客户端跳转URI
            String redirectURI = oauthRequest
                    .getParam(OAuth.OAUTH_REDIRECT_URI);

            // 授权请求类型
            if (responseType.equals(ResponseType.CODE.toString())) {
                String code = oauthIssuerImpl.authorizationCode();
                builder.setCode(code);
                CacheManager.putCache("code", new Cache("code", code,
                        216000000, false));
                CacheManager.putCache("scope", new Cache("scope", scope,
                        216000000, false));
               Authority authority = authorityApi.findUnique(oauthRequest.getClientId(), username);
                if (authority == null) {
                    Authority authority1 = new Authority();
                    authority1.setUserId(username);
                    authority1.setAppKey(oauthRequest.getClientId());
                    authorityApi.save(authority1);
                }
                final OAuthResponse response = builder.location(redirectURI).setParam("code", code)
                        .buildQueryMessage();

                String test = response.getLocationUri();
                URI url = new URI(response.getLocationUri());
              /*  return Response.status(response.getResponseStatus()).location(url)
                        .build();*/
                return Response.temporaryRedirect(new URI("http://localhost:8080/service/oauth/code?url="+url+"")).build();
            }
            return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.BAD_RQUEST)).build();

        } catch (OAuthProblemException e) {
            return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.BAD_RQUEST)).build();
        }
    }

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }
}
