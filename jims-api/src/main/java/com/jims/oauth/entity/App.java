package com.jims.oauth.entity;

import com.jims.common.persistence.DataEntity;

import java.util.Date;

/**
 * app Entity
 * @author luohk
 * @version 2016-05-30
 */
public class App extends DataEntity<App> {
	
	private static final long serialVersionUID = 1L;
	private String appKey;		// app_key
	private String secretKey;		// secret_key
	private String callbackUrl;		// callback_url
	private String description;		// description
	private String name;		// name
	private Long status;		// status
	private Date createTime;		// create_time
	private String owner;		// owner
	private String scope;		// scope
	private String approval;		// approval
	
	public App() {
		super();
	}

	public App(String id){
		super(id);
	}


	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}


	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}


	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}


	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}


	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}


	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}


	public String getApproval() {
		return approval;
	}

	public void setApproval(String approval) {
		this.approval = approval;
	}

}