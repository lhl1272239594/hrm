package com.jims.template.bo;

import com.jims.sys.dao.OrgRoleDao;
import com.jims.sys.entity.OrgRole;
import com.jims.template.dao.TemplateMasterDao;
import com.jims.template.entity.OrgRoleTemplate;
import com.jims.template.dao.OrgRoleTemplateDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.template.entity.TemplateMaster;
import com.jims.template.vo.OrgRoleTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色模板bo
 *
 * @author lgx
 * @version 2016-08-09
 */
@Service
@Transactional(readOnly = false)
public class OrgRoleTemplateBo extends CrudImplService<OrgRoleTemplateDao, OrgRoleTemplate> {

    @Autowired
    private TemplateMasterDao templateMasterDao;

    @Autowired
    private OrgRoleDao orgRoleDao;

    /**
     * 批量保存（插入或更新）
     *
     * @param list
     */
    public void save(List<OrgRoleTemplate> list) {
        if (list != null && list.size() > 0) {
            for (OrgRoleTemplate entity : list) {
                save(entity);
            }
        }
    }


    /**
     * 检索
     *
     * @param entity
     * @return
     */
    public List<OrgRoleTemplate> findAllList(OrgRoleTemplate entity) {
        return dao.findAllList(entity);
    }

    /**
     * 保存  增删改
     *
     * @param orgRoleTemplateVo
     * @return
     * @author yangruidong
     */
    public List<OrgRoleTemplate> saveAll(OrgRoleTemplateVo<OrgRoleTemplate> orgRoleTemplateVo) {
        List<OrgRoleTemplate> newUpdateDict = new ArrayList<OrgRoleTemplate>();
        List<OrgRoleTemplate> inserted = orgRoleTemplateVo.getInserted();
        List<OrgRoleTemplate> updated = orgRoleTemplateVo.getUpdated();
        List<OrgRoleTemplate> deleted = orgRoleTemplateVo.getDeleted();
        //插入

        for (OrgRoleTemplate orgRoleTemplate : inserted) {
            orgRoleTemplate.preInsert();
            int num = dao.insert(orgRoleTemplate);
        }
        //更新
        for (OrgRoleTemplate orgRoleTemplate : updated) {
            orgRoleTemplate.preUpdate();
            int num = dao.update(orgRoleTemplate);
        }
        //删除
        List<String> ids = new ArrayList<String>();
        for (OrgRoleTemplate orgRoleTemplate : deleted) {
            ids.add(orgRoleTemplate.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }


     /*
     *使用模板数据
     * @param orgId
     * @param area
     * @return
     * */

    public String saveTemplate(String orgId, String area, String areaName) {
        List<OrgRole> orgRoles = orgRoleDao.findAllList(orgId);
        TemplateMaster templateMaster = new TemplateMaster();
        templateMaster.setArea(area);
        templateMaster.setTableName("ORG_ROLE_TEMPLATE");
        List<TemplateMaster> list = templateMasterDao.getArea(templateMaster);
        if (orgRoles.size() > 0) {
            if (list.size() > 0) {
                for (int i = 0; i < orgRoles.size(); i++) {
                    OrgRoleTemplate orgRoleTemplate = new OrgRoleTemplate();
                    orgRoleTemplate.preInsert();
                    orgRoleTemplate.setRoleName(orgRoles.get(i).getRoleName());
                    orgRoleTemplate.setMasterId(list.get(0).getId());
                    dao.insert(orgRoleTemplate);
                }
            } else {
                TemplateMaster templateMaster1 = new TemplateMaster();
                templateMaster1.setArea(area);
                templateMaster1.setAreaName(areaName);
                templateMaster1.setTableName("ORG_ROLE_TEMPLATE");
                templateMaster1.preInsert();
                templateMasterDao.insertById(templateMaster1);
                String id = templateMaster1.getId();
                for (int i = 0; i < orgRoles.size(); i++) {
                    OrgRoleTemplate orgRoleTemplate = new OrgRoleTemplate();
                    orgRoleTemplate.preInsert();
                    orgRoleTemplate.setRoleName(orgRoles.get(i).getRoleName());
                    orgRoleTemplate.setMasterId(id);
                    dao.insert(orgRoleTemplate);
                }
            }
            return "1111";
        }
        return null;
    }

}