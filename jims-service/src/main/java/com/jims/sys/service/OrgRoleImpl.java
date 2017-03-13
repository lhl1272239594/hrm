package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.OrgRoleApi;
import com.jims.sys.api.OrgStaffApi;
import com.jims.sys.bo.OrgRoleBo;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")

public class OrgRoleImpl implements OrgRoleApi {

    @Autowired
    private OrgRoleBo orgRoleBo;

    /**
     * 根据orgId获取所有的角色
     * @return
     */
    @Override
    public List<OrgRole> findAllList(String orgId) {
        return orgRoleBo.findAllList(orgId);
    }
    @Override
    public List<OrgRole> findAllList1(String orgId) {
        return orgRoleBo.findAllList1(orgId);
    }
    /**
     * 根据角色名称模糊查询角色信息
     * @param roleName 角色名称
     * @param orgId  组织机构ID
     * @return 角色信息列表
     * @author fengyuguang
     */
    public List<OrgRole> findByRoleName(String roleName,String orgId){
        return orgRoleBo.findByRoleName(roleName,orgId);
    }

    /**
     * 保存角色增删改
     * @param beanChangeVo 角色增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<OrgRole> beanChangeVo){
        return orgRoleBo.merge(beanChangeVo);
    }

    /**
     * 保存或修改
     * @param orgRole
     * @return
     */
    @Override
    public String save(OrgRole orgRole) {
        return null;
    }

    /**
     * 删除
     *
     * @param ids
     * @return
     */
    @Override
    public String delete(String ids) {
        return null;
    }

    /**
     * 导入模板数据
     * @param orgRole
     * @return
     */
    public String  insertByTemplate(OrgRole orgRole,String masterId){
        return orgRoleBo.insertByTemplate(orgRole,masterId);
    }
}
