package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.sys.dao.PayWayDictDao;
import com.jims.sys.entity.PayWayDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 支付方式字典表bo事务处理层
 * Created by fyg on 2016/6/23.
 */
@Component
@Transactional(readOnly = false)
public class PayWayDictBo extends CrudImplService<PayWayDictDao,PayWayDict> {

    @Autowired
    private PayWayDictDao payWayDictDao;

    /**
     * 根据组织机构ID和住院病人适用标志查询数据
     * @param orgId  组织机构ID
     * @param inpIndicator 住院病人适用标志
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> getByInpIndicator(String orgId, Integer inpIndicator) {
        return payWayDictDao.getByInpIndicator(orgId,inpIndicator);
    }

    /**
     * 查询所有支付方式
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> findList(String orgId) {
        List<PayWayDict> list1 = payWayDictDao.findList(orgId);
        List<PayWayDict> list2 = payWayDictDao.listByNullChargeType(orgId);
        list1.addAll(list2);
        return list1;
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<PayWayDict> beanChangeVo) {
        List<PayWayDict> insertedList = beanChangeVo.getInserted();
        int inNum = 0;
        for (PayWayDict payWayDict : insertedList) {
            payWayDict.preInsert();
//            payWayDict.setChargeType(payWayDict.getChargeTypeName());
            payWayDict.setInputCode(PinYin2Abbreviation.cn2py(payWayDict.getPayWayName()));
            inNum = payWayDictDao.insert(payWayDict);
            inNum++;
        }
        String insertedNum = inNum + "";

        List<PayWayDict> updatedList = beanChangeVo.getUpdated();
        int updNum = 0;
        for (PayWayDict payWayDict : updatedList) {
//            payWayDict.setChargeType(payWayDict.getChargeTypeName());
            payWayDict.setInputCode(PinYin2Abbreviation.cn2py(payWayDict.getPayWayName()));
            updNum = payWayDictDao.update(payWayDict);
            updNum++;
        }
        String updatedNum = updNum + "";

        List<PayWayDict> deletedList = beanChangeVo.getDeleted();
        int dltNum = 0;
        for (PayWayDict payWayDict : deletedList) {
            dltNum = payWayDictDao.delete(payWayDict);
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
     * 根据支付方式名称模糊查询记录
     * @param payWayName 支付方式名称
     * @param orgId  所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> search(String payWayName, String orgId) {
        return payWayDictDao.search(payWayName, orgId);
    }
}
