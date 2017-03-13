package com.jims.template.bo;

import com.jims.template.entity.TemplateMaster;
import com.jims.template.dao.TemplateMasterDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.template.vo.OrgRoleTemplateVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 模板信息bo
 *
 * @author lgx
 * @version 2016-08-09
 */
@Service
@Transactional(readOnly = false)
public class TemplateMasterBo extends CrudImplService<TemplateMasterDao, TemplateMaster> {

    /**
     * 批量保存（插入或更新）
     *
     * @param list
     */
    public void save(List<TemplateMaster> list) {
        if (list != null && list.size() > 0) {
            for (TemplateMaster entity : list) {
                save(entity);
            }
        }
    }

    /**
     * 根据区域查询此区域在表中是否唯一
     *
     * @param entity
     * @return
     */
    public List<TemplateMaster> getArea(TemplateMaster entity) {
        return dao.getArea(entity);
    }


    /**
     * 保存  增删改
     *
     * @param templateVo
     * @return
     * @author yangruidong
     */
    public List<TemplateMaster> saveAll(OrgRoleTemplateVo<TemplateMaster> templateVo) {
        List<TemplateMaster> newUpdateDict = new ArrayList<TemplateMaster>();
        List<TemplateMaster> inserted = templateVo.getInserted();
        List<TemplateMaster> updated = templateVo.getUpdated();
        List<TemplateMaster> deleted = templateVo.getDeleted();
        //插入
        for (TemplateMaster templateMaster : inserted) {
            templateMaster.preInsert();
            int num = dao.insert(templateMaster);
        }
        //更新
        for (TemplateMaster templateMaster : updated) {
            templateMaster.preUpdate();
            int num = dao.update(templateMaster);
        }
        //删除
        List<String> ids = new ArrayList<String>();
        for (TemplateMaster templateMaster : deleted) {
            ids.add(templateMaster.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

}