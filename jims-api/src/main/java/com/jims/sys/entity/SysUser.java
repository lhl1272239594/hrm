/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.entity;

import com.jims.common.utils.CustomDateDeSerializer; import com.jims.common.utils.CustomDateSerializer; import org.codehaus.jackson.map.annotate.JsonDeserialize; import org.codehaus.jackson.map.annotate.JsonSerialize;
import com.jims.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * 登录信息Entity
 * @author yangruidong
 * @version 2016-04-13
 */
@XmlRootElement
public class SysUser extends DataEntity<SysUser> {
	
	private static final long serialVersionUID = 1L;
	private String loginName;		// 登录名
	private String password;		// 密码
	private Date lastLoginTime;		// 最后登陆时间
	private String persionId;		// 人员


    public SysUser() {
		super();
	}

	public SysUser(String id){
		super(id);
	}

	@Length(min=0, max=20, message="登录名长度必须介于 0 和 20 之间")
	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	@Length(min=0, max=100, message="密码长度必须介于 0 和 100 之间")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@JsonSerialize(using = CustomDateSerializer.class)
	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	
	@Length(min=0, max=64, message="人员长度必须介于 0 和 64 之间")
	public String getPersionId() {
		return persionId;
	}

	public void setPersionId(String perisonId) {
		this.persionId = perisonId;
	}
	
}