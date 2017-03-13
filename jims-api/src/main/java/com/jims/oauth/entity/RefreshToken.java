package com.jims.oauth.entity;


import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.User;

import java.util.Date;

/**
 * refresh_tokenEntity
 * @author luohk
 * @version 2016-05-30
 */
public class RefreshToken extends DataEntity<RefreshToken> {
	
	private static final long serialVersionUID = 1L;
	private String accessToken;		// access_token
	private String refreshToken;		// refresh_token
	private Date createTime;		// create_time
	private String expire;		// expire
	private String appKey;		// app_key
	private String userId;		// user_id
	private String scope;		// scope
	private Date authorizationTime;		// authorization_time
	private String grantType;		// grant_type
	
	public RefreshToken() {
		super();
	}

	public RefreshToken(String id){
		super(id);
	}


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}


	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getExpire() {
		return expire;
	}

	public void setExpire(String expire) {
		this.expire = expire;
	}


	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}


	public Date getAuthorizationTime() {
		return authorizationTime;
	}

	public void setAuthorizationTime(Date authorizationTime) {
		this.authorizationTime = authorizationTime;
	}


	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

}