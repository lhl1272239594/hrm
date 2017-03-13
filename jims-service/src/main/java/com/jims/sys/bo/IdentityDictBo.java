package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.sys.dao.IdentityDictDao;
import com.jims.sys.entity.IdentityDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 身份字典表bo事务处理层
 * Created by fyg on 2016/6/21.
 */
@Component
@Transactional(readOnly = false)
public class IdentityDictBo extends CrudImplService<IdentityDictDao, IdentityDict> {

    @Autowired
    private IdentityDictDao identityDictDao;

    /**
     * 查询所有记录
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> findList(String orgId) {
        return identityDictDao.findList(orgId);
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<IdentityDict> beanChangeVo) {
        List<IdentityDict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (IdentityDict identityDict : insertedList) {
            identityDict.preInsert();
            identityDict.setInputCode(PinYin2Abbreviation.cn2py(identityDict.getIdentityName()));
            inNum = identityDictDao.insert(identityDict);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<IdentityDict> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (IdentityDict identityDict : updatedList) {
            identityDict.setInputCode(PinYin2Abbreviation.cn2py(identityDict.getIdentityName()));
            updNum = identityDictDao.update(identityDict);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<IdentityDict> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (IdentityDict identityDict : deletedList) {
            dltNum = identityDictDao.delete(identityDict);
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
     * 根据身份名称模糊查询记录
     * @param identityName 身份名称
     * @param orgId        所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<IdentityDict> search(String identityName, String orgId) {
        return identityDictDao.search(identityName, orgId);
    }
}
