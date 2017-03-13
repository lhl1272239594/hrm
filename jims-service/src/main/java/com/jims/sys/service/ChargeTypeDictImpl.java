package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.sys.api.ChargeTypeDictApi;
import com.jims.sys.bo.ChargeTypeDictBo;
import com.jims.sys.dao.ChargeTypeDictDao;
import com.jims.sys.entity.ChargeTypeDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@Service(version = "1.0.0")

public class ChargeTypeDictImpl implements ChargeTypeDictApi{

    @Autowired
    private ChargeTypeDictBo chargeTypeDictBo;

    /**
     * 查询某个组织机构的费别列表
     * @param orgId  组织机构ID
     * @param page  分页
     * @param chargeTypeDict
     * @return
     * @author fengyuguang
     */
    public Page<ChargeTypeDict> findPage(String q,String orgId, Page<ChargeTypeDict> page, ChargeTypeDict chargeTypeDict){
        return chargeTypeDictBo.findListByOrgId(q, orgId, page, chargeTypeDict);
    }

    /**
     * 根据名称模糊查询数据
     * @param chargeTypeName 费别名称
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> search(String chargeTypeName, String orgId){
        return chargeTypeDictBo.search(chargeTypeName,orgId);
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<ChargeTypeDict> beanChangeVo){
        return chargeTypeDictBo.merge(beanChangeVo);
    }

    /**
     * 查询某个组织机构的费别列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> listAll(String orgId){
        return chargeTypeDictBo.listAll(orgId);
    }

    @Override
    public Page<ChargeTypeDict> findPage(Page<ChargeTypeDict> page, ChargeTypeDict chargeTypeDict) {
        return chargeTypeDictBo.findPage(page,chargeTypeDict);
    }

    @Override
    public String save(ChargeTypeDict chargeTypeDict) {
        return chargeTypeDictBo.save(chargeTypeDict);
    }

    @Override
    public String delete(String ids) {
        return chargeTypeDictBo.delete(ids);
    }

    @Override
    public ChargeTypeDict get(String id) {
        return chargeTypeDictBo.get(id);
    }

    @Override
    public List<String> findTypeList() {
        return chargeTypeDictBo.findTypeList();
    }

}
