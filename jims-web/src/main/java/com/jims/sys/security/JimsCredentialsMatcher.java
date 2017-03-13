package com.jims.sys.security;

import com.jims.common.utils.Entrypt;
import com.jims.common.utils.JedisUtils;
import com.jims.common.utils.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.springframework.stereotype.Service;

/**
 * shiro密码验证类
 * 重写doCredentialsMatch方法，返回true验证通过
 * 返回false 验证不通过
 * Created by heren on 2015/10/22.
 */
@Service
public class JimsCredentialsMatcher extends SimpleCredentialsMatcher {


    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String mobileCode = usernamePasswordToken.getMobileCode();
        if (StringUtils.isNoneBlank(mobileCode)) {
            return mobileCode.equals(JedisUtils.getObject("smsCode_" + username));
        } else {
            HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Entrypt.HASH_ALGORITHM);
            matcher.setHashIterations(Entrypt.HASH_INTERATIONS);
            return matcher.doCredentialsMatch(token, info);
        }
    }



}
