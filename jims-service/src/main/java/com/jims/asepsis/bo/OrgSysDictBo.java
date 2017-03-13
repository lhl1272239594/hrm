package com.jims.asepsis.bo;

import com.jims.asepsis.entity.OrgSysDict;
import com.jims.asepsis.dao.OrgSysDictDao;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 包单位维护bo
* @author yangruidong
* @version 2016-06-28
*/
@Service
@Transactional(readOnly = false)
public class OrgSysDictBo extends CrudImplService<OrgSysDictDao, OrgSysDict>{
    @Autowired
    private OrgSysDictDao orgSysDictDao;

    /**
     * 异步加载页面左侧表格:机构字典表的类型和描述列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> leftList(String orgId){
        return orgSysDictDao.leftList(orgId);
    }

    /**
     * 异步加载页面右侧表格:机构字典表的键值列表
     * @param type  类型
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> rightList(String type,String orgId){
        return orgSysDictDao.rightList(type,orgId);
    }

    /**
     * 保存多条增删改数据
     * @param beanChangeVo 多条增删改数据的集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<OrgSysDict> beanChangeVo){
        List<OrgSysDict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (OrgSysDict dict : insertedList) {
            dict.preInsert();
            String label = dict.getLabel();
            dict.setInputCode(PinYin2Abbreviation.cn2py(label));
            inNum = orgSysDictDao.insert(dict);
            inNum++;
        }
        String insertedNum = inNum + "";
        List<OrgSysDict> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (OrgSysDict dict : updatedList) {
            dict.setInputCode(PinYin2Abbreviation.cn2py(dict.getLabel()));
            updNum = dao.update(dict);
            updNum++;
        }
        String updatedNum = updNum + "";
        if (insertedNum == "0" && updatedNum == "0") {
            return "0";
        } else {
            return "1";
        }
    }

    /**
     * 根据类型或描述查询某个组织机构的字典数据
     * @param type 类型
     * @param description 描述
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<OrgSysDict> search(String type,String description,String orgId){
        return orgSysDictDao.search(type,description,orgId);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<OrgSysDict> list) {
        if(list != null && list.size() > 0) {
            for(OrgSysDict entity : list) {
                save(entity);
            }
        }
    }


    /**
     * 根据类型查询包单位
     * @param entity
     * @return
     */
    public List<OrgSysDict>  findUnits(OrgSysDict entity){
        return dao.findUnits(entity);
    }
}