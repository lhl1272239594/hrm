/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.data.StringData;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.dao.OrgRoleDao;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.SysCompany;
import com.jims.sys.vo.BeanChangeVo;
import com.jims.template.dao.OrgRoleTemplateDao;
import com.jims.template.entity.OrgRoleTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 检查预约记录BAO层
 *
 * @author zhangyao
 * @version 2016-04-25
 */
@Service
@Component
@Transactional(readOnly = false)
public class OrgRoleBo extends CrudImplService<OrgRoleDao, OrgRole> {

    @Autowired
    private OrgRoleDao orgRoleDao;

    @Autowired
    private OrgRoleTemplateDao orgRoleTemplateDao;

    /**
     * 根据orgId获取所有的角色
     *
     * @return
     */
    public List<OrgRole> findAllList(String orgId) {
        return orgRoleDao.findAllList(orgId);
    }
    public List<OrgRole> findAllList1(String orgId) {
        return orgRoleDao.findAllList1(orgId);
    }
    /**
     * 根据角色名称模糊查询角色信息
     *
     * @param roleName 角色名称
     * @param orgId    组织机构ID
     * @return 角色信息列表
     * @author fengyuguang
     */
    public List<OrgRole> findByRoleName(String roleName, String orgId) {
        return orgRoleDao.findByRoleName(roleName, orgId);
    }

    /**
     * 保存角色增删改
     *
     * @param beanChangeVo 角色增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<OrgRole> beanChangeVo) {
        List<OrgRole> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (OrgRole orgRole : insertedList) {
            orgRole.preInsert();
            inNum = orgRoleDao.insert(orgRole);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<OrgRole> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (OrgRole orgRole : updatedList) {
            updNum = orgRoleDao.update(orgRole);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<OrgRole> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (OrgRole orgRole : deletedList) {
            dltNum = orgRoleDao.delete(orgRole);
            dltNum++;
        }
        String deletedNum = dltNum + "";
        if (insertedNum == "0" && updatedNum == "0" && deletedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 导入模板数据
     *
     * @param orgRole
     * @return
     */
    public String  insertByTemplate(OrgRole orgRole, String masterId) {

        //查询某个地区的数据并导入到表中
        OrgRoleTemplate orgRoleTemplate = new OrgRoleTemplate();
        orgRoleTemplate.setMasterId(masterId);
        List<OrgRoleTemplate> list = orgRoleTemplateDao.findAllList(orgRoleTemplate);
        if (list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                OrgRole orgRole1 = new OrgRole();
                orgRole1.preInsert();
                orgRole1.setRoleName(list.get(i).getRoleName());
                orgRole1.setOrgId(orgRole.getOrgId());
                orgRole1.setDelFlag("0");
                orgRoleDao.insert(orgRole1);
            }
            return "1";
        }
        return null;
    }

}