/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.common.utils.StringUtils;
import com.jims.sys.api.AdministrationDictApi;
import com.jims.sys.dao.AdministrationDictDao;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.vo.AdministrationDictVo;


import java.util.ArrayList;
import java.util.List;


/**
 * 字典Service
 *
 * @author zhangyao
 * @version 2014-05-18
 */
@Service(version = "1.0.0")

public class AdministrationDictImpl extends CrudImplService<AdministrationDictDao, AdministrationDict> implements AdministrationDictApi {
    /**
     * @param        orgId     传递参数
     * @return java.util.List<com.jims.sys.entity.AdministrationDict>    返回类型
     * @throws
     * @Title: mzViewList
     * @Description: (查询视图中门诊用药途径)
     * @author CTQ
     * @date 2016/6/28
     */
    @Override
    public List<AdministrationDict> mzViewList(String orgId) {
        return dao.mzViewList(orgId);
    }

    /**
     * 查询给药途径字典表
     * @param inputCode 输入码
     * @return
     * @author fengyuguang
     */
    public List<AdministrationDict> list(String inputCode){
        return dao.list(inputCode);
    }

    /**
     * 查询给药途径字典列表,并使用文字回显门诊住院标识
     *
     * @return
     * @author yangruidong
     */
    @Override
    public List<AdministrationDict> findAllList() {
        List<AdministrationDict> list= dao.findAllList();
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.equalsIgnoreCase(list.get(i).getInpOutpFlag(), "9")) {
                list.get(i).setInpOutpFlag("综合");
            }
            if (StringUtils.equalsIgnoreCase(list.get(i).getInpOutpFlag(), "1")) {
                list.get(i).setInpOutpFlag("住院");
            }
            if (StringUtils.equalsIgnoreCase(list.get(i).getInpOutpFlag(), "0")) {
                list.get(i).setInpOutpFlag("门诊");
            }
        }
        return list;
    }

    /**
     * 保存  增删改
     *
     * @param administrationDictVo
     * @return
     * @author yangruidong
     */
    @Override

    public List<AdministrationDict> saveAll(AdministrationDictVo<AdministrationDict> administrationDictVo) {
        List<AdministrationDict> newUpdateDict = new ArrayList<AdministrationDict>();
        List<AdministrationDict> inserted = administrationDictVo.getInserted();
        List<AdministrationDict> updated = administrationDictVo.getUpdated();
        List<AdministrationDict> deleted = administrationDictVo.getDeleted();
        //插入
        for (AdministrationDict administrationDict : inserted) {
            if(StringUtils.equalsIgnoreCase(administrationDict.getInpOutpFlag(),"门诊"))
            {
                administrationDict.setInpOutpFlag("0");
            }
            if(StringUtils.equalsIgnoreCase(administrationDict.getInpOutpFlag(),"住院"))
            {
                administrationDict.setInpOutpFlag("1");
            }
            if(StringUtils.equalsIgnoreCase(administrationDict.getInpOutpFlag(),"全部"))
            {
                administrationDict.setInpOutpFlag("9");
            }
            administrationDict.preInsert();
            int num = dao.insert(administrationDict);
            //newUpdateDict.add(administrationDict);
        }
        //更新
        for (AdministrationDict administrationDict : updated) {
            if(StringUtils.equalsIgnoreCase(administrationDict.getInpOutpFlag(),"门诊"))
            {
                administrationDict.setInpOutpFlag("0");
            }
            if(StringUtils.equalsIgnoreCase(administrationDict.getInpOutpFlag(),"住院"))
            {
                administrationDict.setInpOutpFlag("1");
            }
            if(StringUtils.equalsIgnoreCase(administrationDict.getInpOutpFlag(),"全部"))
            {
                administrationDict.setInpOutpFlag("9");
            }
            administrationDict.preUpdate();
            int num = dao.update(administrationDict);
        }
        //删除
        List<String> ids = new ArrayList<String>();

        for (AdministrationDict administrationDict : deleted) {
            ids.add(administrationDict.getId());
        }
        for (String id : ids) {
            dao.delete(id);
        }
        return newUpdateDict;
    }

    /**
     * 根据试用返回获取用药途径 ，传入是门诊则获取门诊+全部
     * 传入的是住院 = 住院+ 全部
     * 传入的全部 = 门诊 + 住院+全部
     *
     * @param inpOrOutpFlag 全部（综合）、门诊、住院
     * @return
     * @author yangruidong
     */
    @Override
    public List<AdministrationDict> listAdministrationByInpOrOutpFlag(String inpOrOutpFlag)
    {
        if(StringUtils.equalsIgnoreCase(inpOrOutpFlag,"门诊"))
        {
            inpOrOutpFlag= "0";
        }
        if(StringUtils.equalsIgnoreCase(inpOrOutpFlag,"住院"))
        {
            inpOrOutpFlag= "1";
        }
        if(StringUtils.equalsIgnoreCase(inpOrOutpFlag,"全部"))
        {
            inpOrOutpFlag= "9";
        }
        List<AdministrationDict> list=dao.listAdministrationByInpOrOutpFlag(inpOrOutpFlag);
        for (int i = 0; i < list.size(); i++) {
            if (StringUtils.equalsIgnoreCase(list.get(i).getInpOutpFlag(), "9")) {
                list.get(i).setInpOutpFlag("综合");
            }
            if (StringUtils.equalsIgnoreCase(list.get(i).getInpOutpFlag(), "1")) {
                list.get(i).setInpOutpFlag("住院");
            }
            if (StringUtils.equalsIgnoreCase(list.get(i).getInpOutpFlag(), "0")) {
                list.get(i).setInpOutpFlag("门诊");
            }
        }
        return list;
    }
}
