package com.jims.fbd.hrm.comtract;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.contract.api.ContractApi;
import com.jims.fbd.hrm.contract.entity.Contract;
import com.jims.fbd.hrm.contract.vo.ContractVo;
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
@Path("contract")
public class ContractRest {
    @Reference(version = "1.0.0")
    private ContractApi contractApi;
    /**
     * 业务处理：查询
     *
     * @return
     */
    @GET
    @Path("contract-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @QueryParam("contractCode") String contractCode,
                             @QueryParam("contractName") String contractName,
                             @QueryParam("contractType") String contractType,
                             @QueryParam("statusType") String statusType,
                             @QueryParam("endStartDate") String endStartDate,
                             @QueryParam("endEndDate") String endEndDate,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        Contract contract = new Contract();
        contract.setOrgId(orgId);
        contract.setContractName(contractName);
        contract.setContractCode(contractCode);
        contract.setContractType(contractType);
        contract.setEndStartDate(endStartDate);
        contract.setEndEndDate(endEndDate);
        contract.setStatusType(statusType);

        Page<Contract> page = contractApi.findList(new Page<Contract>(request, response), contract);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 业务处理：合同名称重复校验
     *
     * @return
     */    @GET
    @Path("contract-boolean")
    public List<Contract> findContractBoolean(@QueryParam("orgId") String orgId,
                                         @QueryParam("contractName") String contractName) {

        return contractApi.findContractBoolean(orgId,contractName);
    }
    /**
     * 合同编号重复判断
     *
     * @return
     */
    @GET
    @Path("if-exist")
    public List<Contract> findPartsame(@QueryParam("orgId") String orgId,
                                       @QueryParam("contractCode") String contractCode,
                                       @QueryParam("contractId") String contractId) {
        return contractApi.findPartsame(orgId,contractCode,contractId);
    }
    /**
     * 合同签订次数
     *
     * @return
     */
    @GET
    @Path("sign-num")
    public List<Contract> findSignNum(@QueryParam("orgId") String orgId,
                                       @QueryParam("userId") String userId) {
        return contractApi.findSignNum(orgId,userId);
    }
    /**
     * 业务处理：新增、修改
     *
     * @return
     */
    @POST
    @Path("merge")
    public StringData merge(ContractVo<Contract> contractVo,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String deptId=loginInfo.getDeptId();
        String orgId=contractVo.getOrgId();

        int num = 0;
        int count = 0;
        String id = contractVo.getContractId();
        Contract contract = new Contract();

        if (id.equals("999")) {

            contract.setContractId(IdGen.uuid());
            contract.setContractCode(contractVo.getContractCode());
            contract.setContractName(contractVo.getContractName());
            contract.setContractType(contractVo.getContractType());
            contract.setStartDate(contractVo.getStartDate());
            contract.setEndDate(contractVo.getEndDate());
            contract.setUserId(contractVo.getUserId());
            contract.setDeptId(contractVo.getDeptId());
            contract.setOrgId(contractVo.getOrgId());
            contract.setUrl(contractVo.getUrl());
            contract.setCreateOrg(orgId);
            contract.setCreateDept(deptId);
            contract.setCreateBy(userId);
            contract.setProbationPeriodId(contractVo.getProbationPeriodId());
            contract.setCreateDept(loginInfo.getDeptId());

            count = count + Integer.parseInt(contractApi.insertPrimary(contract));


        } else {

            contract.setContractId(id);
            contract.setContractCode(contractVo.getContractCode());
            contract.setContractName(contractVo.getContractName());
            contract.setContractType(contractVo.getContractType());
            contract.setContractCode(contractVo.getContractCode());
            contract.setContractName(contractVo.getContractName());
            contract.setProbationPeriodId(contractVo.getProbationPeriodId());
            contract.setStartDate(contractVo.getStartDate());
            contract.setEndDate(contractVo.getEndDate());
            contract.setUrl(contractVo.getUrl());
            contract.setUpdateBy(userId);
            contract.setCreateDept(loginInfo.getDeptId());
            count = count + Integer.parseInt(contractApi.updatePrimary(contract));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 业务处理：删除
     *
     * @return
     */
    @Path("contract-del")
    @POST
    public StringData dataDel(ContractVo<Contract> contractVo) {

        String dataId=contractVo.getContractId();
        String num = contractApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

