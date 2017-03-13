package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.PayWayDictApi;
import com.jims.sys.bo.PayWayDictBo;
import com.jims.sys.entity.PayWayDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.Version;

import java.util.List;

/**
 * 支付方式字典表api接口实现类
 * Created by fyg on 2016/6/23.
 */
@Service(version = "1.0.0")

public class PayWayDictImpl implements PayWayDictApi {

    @Autowired
    private PayWayDictBo payWayDictBo;

    /**
     * 根据组织机构ID和住院病人适用标志查询数据
     * @param orgId  组织机构ID
     * @param inpIndicator 住院病人适用标志
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> getByInpIndicator(String orgId, Integer inpIndicator){
        return payWayDictBo.getByInpIndicator(orgId,inpIndicator);
    }

    /**
     * 查询所有支付方式
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> findList(String orgId) {
        return payWayDictBo.findList(orgId);
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    public String merge(BeanChangeVo<PayWayDict> beanChangeVo) {
        return payWayDictBo.merge(beanChangeVo);
    }

    /**
     * 根据支付方式名称模糊查询记录
     * @param payWayName 支付方式名称
     * @param orgId  所属组织机构
     * @return
     * @author fengyuguang
     */
    public List<PayWayDict> search(String payWayName, String orgId) {
        return payWayDictBo.search(payWayName, orgId);
    }
}
