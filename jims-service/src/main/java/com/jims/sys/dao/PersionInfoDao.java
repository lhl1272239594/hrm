package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016/4/13 0013.
 */
@MyBatisDao
public interface PersionInfoDao extends CrudDao<PersionInfo> {

    /**
     * 用户注册
     * @param persionInfo
     */
    public int register(PersionInfo persionInfo);

    /**
     * 注册时向登录表中插入数据
     * @param sysUser
     */
    public void registAddUser(SysUser sysUser);

    /**
     * 查询用户名
     * @param persionInfo
     * @return
     */
    public PersionInfo getNick(PersionInfo persionInfo);

    /**
     * 查询手机号
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getPhone(PersionInfo persionInfo);

    /**
     * 查询邮箱
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getEmail(PersionInfo persionInfo);

    /**
     * 查询身份证号
     *
     * @param persionInfo
     * @return
     */
    public PersionInfo getCard(PersionInfo persionInfo);

    /**
     * 根据身份证号查询相关的信息
     * @param cardNo
     * @return
     */
    public PersionInfo findInfoByCardNo(String cardNo);

    /**
     * 根据id修改persionInfo中的信息
     *
     * @param persionInfo
     * @return
     */
    public int updateById(PersionInfo persionInfo);

    /**
     * 同时向职业信息表插入数据
     * @param persionInfo
     * @return
     */
    public int insertCareer(PersionInfo persionInfo);
}
