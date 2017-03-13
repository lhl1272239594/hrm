package com.jims.oauth.entity;

import com.jims.common.persistence.DataEntity;
import com.jims.sys.entity.User;

/**
 * authority Entity
 * @author luohk
 * @version 2016-05-30
 */
public class Authority extends DataEntity<Authority> {
	
	private static final long serialVersionUID = 1L;
	private String appKey;		// app_key
	private String userId;		// user_id
	
	public Authority() {
		super();
	}

	public Authority(String id){
		super(id);
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
}