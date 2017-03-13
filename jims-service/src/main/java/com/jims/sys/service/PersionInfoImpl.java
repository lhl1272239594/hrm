package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.PersionInfoApi;
import com.jims.sys.bo.PersonInfoBo;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by yangruidong on 2016/4/13 0013.
 */
@Service(version = "1.0.0")
public class PersionInfoImpl implements PersionInfoApi {

    @Autowired
    private PersonInfoBo personInfoBo;


    /**
     * 用户注册
     * @param persionInfo
     * @return
     */
    @Override
    public String register(PersionInfo persionInfo, SysUser sysUser) {
        return personInfoBo.register(persionInfo,sysUser);

    }

    /**
     * 校验身份证号是否存在
     * @param persionInfo
     * @return
     */
    public PersionInfo getCard(PersionInfo persionInfo)
    {

        return personInfoBo.getCard(persionInfo);
    }

    /**
     * 校验用户名是否存在
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getNick(PersionInfo persionInfo) {

        return personInfoBo.getNick(persionInfo);
    }

    /**
     * 校验手机号是否存在
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getPhone(PersionInfo persionInfo) {
        return personInfoBo.getPhone(persionInfo);
    }

    /**
     * 校验邮箱是否存在
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getEmail(PersionInfo persionInfo) {
        return personInfoBo.getEmail(persionInfo);
    }

    /**
     * 根据身份证号查询相关的信息
     * @param cardNo
     * @return
     */
    @Override
    public PersionInfo findInfoByCardNo(String cardNo) {
        return personInfoBo.findInfoByCardNo(cardNo);
    }


}
