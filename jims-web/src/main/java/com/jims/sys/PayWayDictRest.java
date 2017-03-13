package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.PayWayDictApi;
import com.jims.sys.entity.IdentityDict;
import com.jims.sys.entity.PayWayDict;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * 支付方式字典表Rest
 * Created by fyg on 2016/6/23.
 */
@Component
@Produces("application/json")
@Path("pay-way-dict")
public class PayWayDictRest {

    @Reference(version = "1.0.0")
    private PayWayDictApi payWayDictApi;

    /**
     * 根据组织机构ID和住院病人适用标志查询数据
     * @param orgId  组织机构ID
     * @param inpIndicator  住院病人适用标志
     * @return
     * @author fengyuguang
     */
    @Path("list-by-inpIndicator")
    @POST
    public List<PayWayDict> getByInpIndicator(PayWayDict payWayDict){
        return payWayDictApi.getByInpIndicator(payWayDict.getOrgId(),payWayDict.getInpIndicator());
    }

    /**
     * 查询所有支付方式
     * @param orgId 所属组织机构
     * @return
     * @author fengyuguang
     */
    @GET
    @Path("list")
    public List<PayWayDict> findList(@QueryParam("orgId") String orgId) {
        return payWayDictApi.findList(orgId);
    }

    /**
     * 保存增删改
     * @param beanChangeVo 增删改集合
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<PayWayDict> beanChangeVo) {
        List<PayWayDict> inserted = beanChangeVo.getInserted();
        String num = payWayDictApi.merge(beanChangeVo);
        StringData stringData = new StringData();
        stringData.setCode(num);
        if (Integer.parseInt(num) > 0) {
            stringData.setData("success");
        } else {
            stringData.setData("error");
        }
        return stringData;
    }

    /**
     * 根据支付方式名称模糊查询记录
     * @param payWayName 支付方式名称
     * @param orgId  所属组织机构
     * @return
     * @author fengyuguang
     */
    @GET
    @Path("search")
    public List<PayWayDict> search(@QueryParam("payWayName") String payWayName, @QueryParam("orgId") String orgId) {
        return payWayDictApi.search(payWayName, orgId);
    }
}
