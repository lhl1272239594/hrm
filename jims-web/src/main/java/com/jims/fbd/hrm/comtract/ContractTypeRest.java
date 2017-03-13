package com.jims.fbd.hrm.comtract;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.IdGen;
import com.jims.common.vo.LoginInfo;
import com.jims.fbd.hrm.contract.api.ContractTypeApi;
import com.jims.fbd.hrm.contract.entity.ContractType;
import com.jims.fbd.hrm.contract.vo.ContractTypeVo;
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
@Path("contractType")
public class ContractTypeRest {
    @Reference(version = "1.0.0")
    private ContractTypeApi contractTypeApi;

    /**
     * 合同类型查询
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @GET
    @Path("contract-type-list")
    public PageData findList(@QueryParam("orgId") String orgId,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        ContractType contractType = new ContractType();
        contractType.setCreateOrg(orgId);

        Page<ContractType> page = contractTypeApi.findList(new Page<ContractType>(request, response), contractType);
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;

    }
    /**
     * 合同类型查询--不带分页
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @GET
    @Path("contract-type-all-list")
    public List<ContractType> findAllList(@QueryParam("orgId") String orgId,
                             @Context HttpServletRequest request,
                             @Context HttpServletResponse response) {

        ContractType contractType = new ContractType();
        contractType.setCreateOrg(orgId);


        return contractTypeApi.findAllList(contractType);
    }
    /**
     * 合同类型名称重复校验
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @GET
    @Path("contract-type-boolean")
    public List<ContractType> findBoolean(@QueryParam("orgId") String orgId,
                                      @QueryParam("contractTypeId") String contractTypeId,
                                         @QueryParam("contractTypeDes") String contractTypeDes) {

        ContractType contractType = new ContractType();

        contractType.setContractTypeId(contractTypeId);
        contractType.setContractTypeDes(contractTypeDes);
        contractType.setCreateOrg(orgId);
        List<ContractType> list= contractTypeApi.findBoolean(contractType);
        return list;
    }
    /**
     * 合同类型新增保存方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */

    @POST
    @Path("merge")
    public StringData merge(ContractTypeVo<ContractType> contractTypeVo,@Context HttpServletRequest request) {
        HttpSession session = request.getSession();
        LoginInfo loginInfo = (LoginInfo) session.getAttribute("LoginInfo");
        String userId=loginInfo.getPersionId();
        String deptId=loginInfo.getDeptId();
        String orgId=loginInfo.getOrgId();

        int num = 0;
        int count = 0;
        String id = contractTypeVo.getContractTypeId();
        ContractType contractType = new ContractType();

        if (id.equals("999")) {

            contractType.setContractTypeId(IdGen.uuid());
            contractType.setContractTypeDes(contractTypeVo.getContractTypeDes());
            contractType.setContractRemindTime(contractTypeVo.getContractRemindTime());
            contractType.setCreateOrg(orgId);
            contractType.setCreateDept(deptId);
            contractType.setCreateBy(userId);

            count = count + Integer.parseInt(contractTypeApi.insertPrimary(contractType));


        } else {

            contractType.setContractTypeId(id);
            contractType.setContractTypeDes(contractTypeVo.getContractTypeDes());
            contractType.setContractRemindTime(contractTypeVo.getContractRemindTime());
            contractType.setUpdateBy(userId);
            count = count + Integer.parseInt(contractTypeApi.updatePrimary(contractType));
        }

        StringData stringData = new StringData();
        if (count == 1) {
            stringData.setCode(String.valueOf(num));
            stringData.setData("success");
        }
        return stringData;
    }
    /**
     * 合同类型删除方法
     * @return
     * @author yangchen
     * @version 2016-08-23
     */
    @Path("contract-type-del")
    @POST
    public StringData dataDel(ContractTypeVo<ContractType> contractTypeVo) {

        String dataId=contractTypeVo.getContractTypeId();
        String num = contractTypeApi.delPrimary(dataId);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}

