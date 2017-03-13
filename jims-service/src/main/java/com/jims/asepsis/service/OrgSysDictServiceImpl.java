package com.jims.asepsis.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.asepsis.entity.OrgSysDict;
import com.jims.asepsis.api.OrgSysDictApi;
import com.jims.asepsis.bo.OrgSysDictBo;
import com.jims.common.persistence.Page;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
* 包单位维护service
* @author yangruidong
* @version 2016-06-28
*/
@Service(version = "1.0.0")
public class OrgSysDictServiceImpl implements OrgSysDictApi{

    @Autowired
    private OrgSysDictBo bo;

    /**
     * 异步加载页面左侧表格:机构字典表的类型和描述列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> leftList(String orgId){
        return bo.leftList(orgId);
    }

    /**
     * 异步加载页面右侧表格:机构字典表的键值列表
     * @param type  类型
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> rightList(String type,String orgId){
        return bo.rightList(type,orgId);
    }

    /**
     * 保存多条增删改数据
     * @param beanChangeVo 多条增删改数据的集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<OrgSysDict> beanChangeVo) {
        return bo.merge(beanChangeVo);
    }

    /**
     * 根据类型或描述查询某个组织机构的字典数据
     * @param type 类型
     * @param description 描述
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> search(String type, String description, String orgId) {
        return bo.search(type,description,orgId);
    }

    /**
    * 根据ID检索
    * @param id
    * @return
    */
    public OrgSysDict get(String id) {
        return bo.get(id);
    }

    /**
    * 检索
    * @param entity
    * @return
    */
    public List<OrgSysDict> findList(OrgSysDict entity) {
        return bo.findList(entity);
    }

    /**
     * 根据类型查询包单位
     * @param entity
     * @return
     */
    public List<OrgSysDict>  findUnits(OrgSysDict entity){
        return bo.findUnits(entity);
    }

    /**
    * 分页检索
    * @param page 分页对象
    * @param entity
    * @return
    */
    public Page<OrgSysDict> findPage(Page<OrgSysDict> page, OrgSysDict entity) {
        return bo.findPage(page, entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    public String save(OrgSysDict entity) {
        try {
            bo.save(entity);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    public String save(List<OrgSysDict> list) {
        try {
            bo.save(list);
            return "1";
        } catch(RuntimeException e) {}
        return "0";
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    public String delete(String ids) {
        try {
            return bo.delete(ids);
        } catch(RuntimeException e) {}
        return "0";
    }
}