package com.jims.sys.api;

import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.PersionInfo;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.entity.SysUser;
import com.jims.sys.vo.BeanChangeVo;
import com.jims.sys.vo.OrgStaffVo;

import java.util.List;

/**
 * Created by Administrator on 2016/5/31
 */
public interface OrgRoleApi {

    /**
     * 根据角色名称模糊查询角色信息
     * @param roleName 角色名称
     * @param orgId  组织机构ID
     * @return 角色信息列表
     * @author fengyuguang
     */
    public List<OrgRole> findByRoleName(String roleName,String orgId);

    /**
     * 根据orgId获取所有的角色
     * @return
     */
    public List<OrgRole> findAllList(String orgId);
    public List<OrgRole> findAllList1(String orgId);
    /**
     * 保存或修改
     * @param orgRole
     * @return
     */
    public  String save(OrgRole orgRole);

    /**
     * 保存角色增删改
     * @param beanChangeVo 角色增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<OrgRole> beanChangeVo);

    /**
     * 删除
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 导入模板数据
     * @param orgRole
     * @return
     */
    public String insertByTemplate(OrgRole orgRole,String masterId);

}
