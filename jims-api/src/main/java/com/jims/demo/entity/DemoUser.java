package com.jims.demo.entity;

import java.io.Serializable;
import com.jims.common.persistence.DataEntity;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by JIMS on 2016/4/5.
 */

@XmlRootElement
public class DemoUser extends DataEntity<DemoUser>  {

    private String userName ;
    private String password ;

    public DemoUser() {
        super();
    }

    public DemoUser(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
