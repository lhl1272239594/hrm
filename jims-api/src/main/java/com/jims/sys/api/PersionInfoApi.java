package com.jims.sys.api;

import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;

/**
 * Created by Administrator on 2016/4/12 0012.
 */
public interface PersionInfoApi {

    /**
     * 用户注册
     * @param persionInfo
     * @return
     */
    public String register(PersionInfo persionInfo, SysUser sysUser);



    /**
     * 校验身份证号是否正确
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getCard(PersionInfo persionInfo);

    /**
     * 校验用户名是否正确
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getNick(PersionInfo persionInfo);

    /**
     * 校验手机号是否正确
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getPhone(PersionInfo persionInfo);

    /**
     * 校验邮箱是否正确
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getEmail(PersionInfo persionInfo);

    /**
     * 根据身份证号查询相关的信息
     * @param cardNo
     * @return
     */
    public PersionInfo findInfoByCardNo(String cardNo);
}
