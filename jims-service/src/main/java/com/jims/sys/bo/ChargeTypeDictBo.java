package com.jims.sys.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.sys.dao.ChargeTypeDictDao;
import com.jims.sys.entity.ChargeTypeDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 费别字典表bo事务处理层
 * Created by fyg on 2016/6/24.
 */
@Component
@Transactional(readOnly = false)
public class ChargeTypeDictBo extends CrudImplService<ChargeTypeDictDao,ChargeTypeDict>{

    @Autowired
    private ChargeTypeDictDao chargeTypeDictDao;

    /**
     * 查询某个组织机构的费别列表
     * @param q
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    public Page<ChargeTypeDict> findListByOrgId(String q,String orgId,Page<ChargeTypeDict> page,ChargeTypeDict chargeTypeDict){
        chargeTypeDict.setPage(page);
        page.setList(chargeTypeDictDao.findListByOrgId(q, orgId, chargeTypeDict));
        return page;
    }

    /**
     * 根据名称模糊查询数据
     * @param chargeTypeName 费别名称
     * @param orgId  组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> search(String chargeTypeName,String orgId){
        return chargeTypeDictDao.search(chargeTypeName,orgId);
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<ChargeTypeDict> beanChangeVo) {
        List<ChargeTypeDict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (ChargeTypeDict chargeTypeDict : insertedList) {
            chargeTypeDict.setInputCodeWb(PinYin2Abbreviation.cn2py(chargeTypeDict.getChargeTypeName()));
            chargeTypeDict.preInsert();
            inNum = chargeTypeDictDao.insert(chargeTypeDict);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<ChargeTypeDict> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (ChargeTypeDict chargeTypeDict : updatedList) {
            chargeTypeDict.setInputCodeWb(PinYin2Abbreviation.cn2py(chargeTypeDict.getChargeTypeName()));
            updNum = chargeTypeDictDao.update(chargeTypeDict);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<ChargeTypeDict> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (ChargeTypeDict chargeTypeDict : deletedList) {
            dltNum = chargeTypeDictDao.delete(chargeTypeDict);
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
     * 查询某个组织机构的费别列表
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<ChargeTypeDict> listAll(String orgId) {
        return chargeTypeDictDao.listAll(orgId);
    }

    public List<String> findTypeList() {
        return dao.findTypeList(new ChargeTypeDict());
    }

}
