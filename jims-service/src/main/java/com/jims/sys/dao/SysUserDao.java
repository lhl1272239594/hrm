package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/4/14 0014.
 */
@MyBatisDao
public interface SysUserDao extends CrudDao<SysUser> {

    /**
     * 用户登录
     * @param sysUser
     * @return
     */
    public SysUser login(SysUser sysUser);

    /**
     * 查询登录名是否正确
     * @param loginName
     * @return
     */

    public SysUser selectLoginName(@Param("loginName")String loginName);

    /**
     * 查询登录密码是否正确
     * @param loginName
     * @return
     */
    public SysUser selectPasswrod(@Param("loginName") String loginName );

    /**
     * 根据名称修改用户信息
     * @param sysUser
     * @return
     */
    public int updateById(SysUser sysUser);

    /**
     * 查询用户的信息
     * @param loginName
     * @param persionId
     * @return
     */
    public SysUser findByLoginName(String loginName,String persionId);

    /**
     * 通过persionId 删除
     *
     * @param id
     * @return
     */
    public Integer deleteByPid(String id);

    /**
     * 通过persionId查询密码，用于回显页面
     * @param persionId
     * @return
     */
    public SysUser findPasswordByPersionId(String persionId);

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
