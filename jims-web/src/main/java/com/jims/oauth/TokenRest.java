package com.jims.oauth;

import com.alibaba.dubbo.config.annotation.Reference;

import com.jims.oauth.api.AppApi;
import com.jims.oauth.api.AuthorityApi;
import com.jims.oauth.api.RefreshTokenApi;
import com.jims.oauth.entity.App;
import com.jims.oauth.entity.Authority;
import com.jims.oauth.entity.RefreshToken;
import com.jims.sys.api.SysUserApi;
import com.jims.sys.entity.SysUser;
import com.jims.util.JedisUtils;
import com.jims.oauth2.as.issuer.MD5Generator;
import com.jims.oauth2.as.issuer.OAuthIssuer;
import com.jims.oauth2.as.issuer.OAuthIssuerImpl;
import com.jims.oauth2.as.request.OAuthTokenRequest;
import com.jims.oauth2.as.response.OAuthASResponse;
import com.jims.oauth2.common.OAuth;
import com.jims.oauth2.common.error.OAuthError;
import com.jims.oauth2.common.error.ServerErrorType;
import com.jims.oauth2.common.exception.OAuthProblemException;
import com.jims.oauth2.common.exception.OAuthSystemException;
import com.jims.oauth2.common.message.OAuthResponse;
import com.jims.oauth2.common.message.types.GrantType;
import com.jims.oauth2.integration.utils.Cache;
import com.jims.oauth2.integration.utils.CacheManager;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * get access token
 */
@Component
@Path("/token")
public class TokenRest {
    @Reference(version = "1.0.0")
    private AppApi appApi;
    @Reference(version = "1.0.0")
    private AuthorityApi authorityApi;
    @Reference(version = "1.0.0")
    private RefreshTokenApi refreshTokenApi;
    @Reference(version = "1.0.0")
    private SysUserApi sysUserApi;


    // 登录页面
    private static String loginPage;

    // 错误页面
    private static String errorPage;


    public static final String INVALID_CLIENT_DESCRIPTION = "Client authentication failed (e.g., unknown client, no client authentication included, or unsupported authentication method).";

    @SuppressWarnings({"unchecked", "rawtypes"})
    @POST
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response authorize(@Context HttpServletRequest request)
            throws OAuthSystemException, URISyntaxException {

        OAuthTokenRequest oauthRequest = null;
        String scope = "";
        String userName = null;
        OAuthIssuer oauthIssuerImpl = new OAuthIssuerImpl(new MD5Generator());
        try {
            String a = request.getParameter("grant_type");
            oauthRequest = new OAuthTokenRequest(request);


            App app = null;
            if (oauthRequest.getClientId() != null && !"".equals(oauthRequest.getClientId())) {
                app = appApi.selectByPrimaryKey(oauthRequest.getClientId());
                if (app == null) {
                    return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.CLIENT_ID_IS_NULL)).build();
                }
            } else {
                return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.CLIENT_ID_IS_NULL)).build();
            }

            // 校验clientid
            if (app == null || !app.getAppKey().toString().equals(oauthRequest.getClientId())) {
                if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                    return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.UNKOWN_CLIENT_ID)).build();
                } else {
                    OAuthResponse response =
                            OAuthASResponse.errorResponse(HttpServletResponse.SC_OK)
                                    .setError(OAuthError.TokenResponse.INVALID_CLIENT).setErrorDescription(INVALID_CLIENT_DESCRIPTION)
                                    .buildJSONMessage();
                    return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
                }
            }

            // 校验client_secret
            if (!app.getSecretKey().equals(oauthRequest.getClientSecret())) {
                if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                    return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.UNKOWN_CLIENT_SECRET)).build();
                } else {
                    OAuthResponse response =
                            OAuthASResponse.errorResponse(HttpServletResponse.SC_OK)
                                    .setError(OAuthError.TokenResponse.INVALID_CLIENT).setErrorDescription(INVALID_CLIENT_DESCRIPTION)
                                    .buildJSONMessage();
                    return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
                }
            }

            // 校验不同类型的授权方式
            if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.AUTHORIZATION_CODE.toString())) {
                String cacheCode = null;
                if (CacheManager.getCacheInfo("code").getValue() != null) {
                    cacheCode = CacheManager.getCacheInfo("code")
                            .getValue().toString();
                    Authority authority = authorityApi.findAppKey(oauthRequest.getClientId());
                    userName = authority.getUserId();
                }

                if (!cacheCode.equals(oauthRequest.getParam(OAuth.OAUTH_CODE))) {
                    return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.INVALID_AUTHORIZATION_CODE)).build();
                }
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(GrantType.PASSWORD.toString())) {
                SysUser sysUser = new SysUser();
                sysUser.setLoginName(oauthRequest.getUsername());
                sysUser.setPassword(oauthRequest.getPassword());
                SysUser user = sysUserApi.login(sysUser);
                if (user == null) {
                    OAuthResponse response = OAuthASResponse
                            .errorResponse(HttpServletResponse.SC_OK)
                            .setError(OAuthError.TokenResponse.INVALID_CLIENT)
                            .setErrorDescription("Invalid username or password.")
                            .buildJSONMessage();
                    return Response.status(response.getResponseStatus()).entity(response.getBody()).build();
                }

                userName = user.getLoginName();
            } else if (oauthRequest.getParam(OAuth.OAUTH_GRANT_TYPE).equals(
                    GrantType.REFRESH_TOKEN.toString())) {
                // 刷新令牌未实现
            }

            String accessToken = oauthIssuerImpl.accessToken();
            String refreshToken = oauthIssuerImpl.refreshToken();
            CacheManager.putCache("accessToken", new Cache("accessToken", accessToken, 7200, false));
            CacheManager.putCache("refreshToken", new Cache("refreshToken", refreshToken, 7200, false));
            CacheManager.putCache(accessToken+"_userName", new Cache(accessToken + "_userName", userName, 7200, false));
            CacheManager.putCache(accessToken + "_clientId", new Cache(accessToken + "_clientId", oauthRequest.getClientId(), 7200, false));
            JedisUtils.set(oauthRequest.getClientId(),app.getScope(),0);
            // 构建响应
            OAuthResponse response = OAuthASResponse
                    .tokenResponse(HttpServletResponse.SC_OK)
                    .setAccessToken(accessToken).setRefreshToken(refreshToken)
                    .setExpiresIn("3600")
                    .buildJSONMessage();
            Authority  authority = authorityApi.findUnique(oauthRequest.getClientId(),userName);
            if (authority == null) {
                Authority authority1 = new Authority();
                authority1.setUserId(userName);
                authority1.setAppKey(oauthRequest.getClientId());
                authorityApi.save(authority1);
            }
            RefreshToken refreshTokens = refreshTokenApi.findUnique(oauthRequest.getClientId(), userName);
            if (refreshTokens != null) {
                refreshTokens.setAppKey(oauthRequest.getClientId());
                refreshTokens.setUserId(userName);
                refreshTokens.setAccessToken(accessToken);
                refreshTokens.setRefreshToken(refreshToken);
                refreshTokens.setCreateTime(new Date());
                refreshTokens.setScope(scope);
                refreshTokens.setAuthorizationTime(new Date());
                refreshTokenApi.update(refreshTokens);
            } else {
                RefreshToken refreshToken1 = new RefreshToken();
                refreshToken1.setAppKey(oauthRequest.getClientId());
                refreshToken1.setUserId(userName);
                refreshToken1.setAccessToken(accessToken);
                refreshToken1.setRefreshToken(refreshToken);
                refreshToken1.setCreateTime(new Date());
                refreshToken1.setScope(scope);
                refreshToken1.setAuthorizationTime(new Date());
                refreshTokenApi.save(refreshToken1);
            }
            return Response.status(response.getResponseStatus())
                    .entity(response.getBody()).build();

        } catch (OAuthProblemException e) {
            System.out.println(e.getDescription());
            return Response.temporaryRedirect(new URI(errorPage + "?error=" + ServerErrorType.BAD_RQUEST)).build();
        }
    }

    private String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(System.currentTimeMillis());
    }
}