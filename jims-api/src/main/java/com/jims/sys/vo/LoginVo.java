package com.jims.sys.vo;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
@XmlRootElement
public class LoginVo implements Serializable {
    private String loginName;
    private String password;
    private String validateNode;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getValidateNode() {
        return validateNode;
    }

    public void setValidateNode(String validateNode) {
        this.validateNode = validateNode;
    }
}
