package com.jims.fbd.hrm.comtract;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.contract.api.ContractProbationPeriodApi;
import com.jims.fbd.hrm.contract.entity.ContractProbationPeriod;
import com.jims.fbd.hrm.contract.vo.ContractProbationPeriodVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by yangchen on 2016/8/23 0.
 */
@Component
@Produces("application/json")
@Path("contractProbationPeriod")
public class ContractProbationPeriodRest {
    @Reference(version = "1.0.0")
    private ContractProbationPeriodApi contractProbationPeriodApi;

    /**
     * 合同试用期查询
     * @return
     * @author yangchen
     * @version 2017-03-07
     */
    @GET
    @Path("contract-probation-period-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        ContractProbationPeriod contractProbationPeriod = new ContractProbationPeriod();
        contractProbationPeriod.setCreateOrg(orgId);

        Page<ContractProbationPeriod> page = contractProbationPeriodApi.findList(new Page<ContractProbationPeriod>(request, response), contractProbationPeriod);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 合同试用期查询--不带分页
     * @return
     * @author yangchen
     * @version 2017-03-07
     */
    @GET
    @Path("contract-probation-period-all-list")
    public List<ContractProbationPeriod> findAllList(@QueryParam("orgId") String orgId,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        ContractProbationPeriod contractProbationPeriod = new ContractProbationPeriod();
        contractProbationPeriod.setCreateOrg(orgId);


        return contractProbationPeriodApi.findAllList(contractProbationPeriod);
    }
    /**
     * 合同试用期重复校验
     * @return
     * @author yangchen
     * @version 2017-03-07
     */
    @GET
    @Path("contract-probation-period-boolean")
    public List<ContractProbationPeriod> findBoolean(@QueryParam("orgId") String orgId,
                                      @QueryParam("probationPeriodId") String probationPeriodId,
                                         @QueryParam("probationPeriodTimes") String probationPeriodTimes) {

        ContractProbationPeriod contractProbationPeriod = new ContractProbationPeriod();

        contractProbationPeriod.setProbationPeriodId(probationPeriodId);
        contractProbationPeriod.setProbationPeriodTimes(probationPeriodTimes);
        contractProbationPeriod.setCreateOrg(orgId);
        List<ContractProbationPeriod> list= contractProbationPeriodApi.findBoolean(contractProbationPeriod);
        return list;
    }
    /**
     * 合同试用期新增保存方法
     * @return
     * @author yangchen
     * @version 2017-03-07
     */

    @POST
    @Path("merge")
    public StringData merge(ContractProbationPeriodVo<ContractProbationPeriod> contractProbationPeriodVo,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String deptId=loginInfo.getDeptId();
        String orgId=loginInfo.getOrgId();

        int num = 0;
        int count = 0;
        String id = contractProbationPeriodVo.getProbationPeriodId();
        ContractProbationPeriod contractProbationPeriod = new ContractProbationPeriod();

        if (id.equals("999")) {

            contractProbationPeriod.setProbationPeriodId(IdGen.uuid());
            contractProbationPeriod.setProbationPeriodTimes(contractProbationPeriodVo.getProbationPeriodTimes());
            contractProbationPeriod.setRemindTime(contractProbationPeriodVo.getRemindTime());
            contractProbationPeriod.setCreateOrg(orgId);
            contractProbationPeriod.setCreateDept(deptId);
            contractProbationPeriod.setCreateBy(userId);

            count = count + Integer.parseInt(contractProbationPeriodApi.insertPrimary(contractProbationPeriod));


        } else {

            contractProbationPeriod.setProbationPeriodId(id);
            contractProbationPeriod.setRemindTime(contractProbationPeriodVo.getRemindTime());
            contractProbationPeriod.setProbationPeriodTimes(contractProbationPeriodVo.getProbationPeriodTimes());
            contractProbationPeriod.setUpdateBy(userId);
            count = count + Integer.parseInt(contractProbationPeriodApi.updatePrimary(contractProbationPeriod));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 合同试用期删除方法
     * @return
     * @author yangchen
     * @version 2017-03-07
     */
    @Path("contract-probation-period-del")
    @POST
    public StringData dataDel(ContractProbationPeriodVo<ContractProbationPeriod> contractProbationPeriodVo) {

        String dataId=contractProbationPeriodVo.getProbationPeriodId();
        String num = contractProbationPeriodApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

