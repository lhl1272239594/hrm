package com.jims.sys.api;

import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;

import java.util.List;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
public interface SysUserApi {

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    public SysUser login(SysUser sysUser);

    /**
     * 查询登录名是否正确
     *
     * @param loginName
     * @return
     */
    public SysUser selectLoginName(String loginName);

    /**
     * 查询登录密码是否正确
     *
     * @param loginName
     * @return
     */
    public SysUser selectPassword(String loginName);

    public SysCompany findNameByOwner(String loginName);

    /**
     *根据登录人的persionId查询四种方式的密码
     * @param sysUser
     * @return
     */
    public List<SysUser> findPasswordByPid(SysUser sysUser);

    /**
     * 根据persionId修改四种方式登录的密码
     *
     * @param sysUser
     * @return
     */
    public int updatePassword(SysUser sysUser);



}
