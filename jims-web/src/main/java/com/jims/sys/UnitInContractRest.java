package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.utils.PinYin2Abbreviation;
import com.jims.common.vo.LoginInfo;
import com.jims.sys.vo.StaffGroupVo;
import com.jims.sys.api.UnitInContractApi;
import com.jims.sys.entity.UnitInContract;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.List;

/**
 * Created by Administrator on 2016/4/20.
 */

@Component
@Produces("application/json")
@Path("UnitInContract")
public class UnitInContractRest {

    @Reference(version = "1.0.0")
    private UnitInContractApi unitInContractApi;

    @Path("list")
    @GET
    public PageData list(@Context HttpServletRequest request,@Context HttpServletResponse response){
        Page<UnitInContract> page=unitInContractApi.findPage(new Page<UnitInContract>(request,response),new UnitInContract());
        PageData<UnitInContract> pageData=new PageData<UnitInContract>();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public UnitInContract get(String id) {
        UnitInContract unitInContract = unitInContractApi.get(id);
        return unitInContract;
    }

    /**
     * 保存修改方法
     * @param unitInContract
     * @return
     */
    @Path("save")
    @POST
    public StringData save(UnitInContract unitInContract){
        String num=unitInContractApi.save(unitInContract);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     *删除
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids){
        String num=unitInContractApi.delete(ids);
        StringData stringData=new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 根据拼音码查询
     * @param
     * @param inputCode
     * @return
     */
    @GET
    @Path("find-by-input-code")
    public List<UnitInContract> getInputCode(@Context HttpServletRequest request,@QueryParam("inputCode")String inputCode){
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        List<UnitInContract> unitInContracts = unitInContractApi.getInputCode(loginInfo.getOrgId(), inputCode);
        return unitInContracts;
    }

    /**
     * 查询页面信息
     * @param orgId
     * @param request
     * @param response
     * @return
     */
    @Path("list-all")
    @GET
    public PageData list(@QueryParam("orgId")String orgId,@Context HttpServletRequest request,@Context HttpServletResponse response) {
        Page<UnitInContract> page= unitInContractApi.findAllPage(orgId, new Page<UnitInContract>(request, response), new UnitInContract());
        PageData pageData=new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }

    /**
     * 保存和修改
     * @param examRptPatternVo
     * @return
     * @author wei
     */
    @POST
    @Path("merge")
    public StringData save(StaffGroupVo<UnitInContract> examRptPatternVo) {
        int num = 0;
        int count = 0;
        for (int i = 0; i < examRptPatternVo.getInserted().size(); i++) {
            UnitInContract unitInContract = new UnitInContract();
            unitInContract = (UnitInContract) examRptPatternVo.getInserted().get(i);
            unitInContract.preInsert();
            unitInContract.setUnitName(unitInContract.getUnitName());
            unitInContract.setInputCode(PinYin2Abbreviation.cn2py(unitInContract.getUnitName()));
            unitInContract.setAddress(unitInContract.getAddress());
            unitInContract.setUnitType(unitInContract.getUnitType());
            unitInContract.setSubordinateTo(unitInContract.getSubordinateTo());
            unitInContract.setDistanceToHospital(unitInContract.getDistanceToHospital());
            unitInContract.setRegularNum(unitInContract.getRegularNum());
            unitInContract.setTempNum(unitInContract.getTempNum());
            unitInContract.setRetiredNum(unitInContract.getRetiredNum());
            unitInContract.setInputCodeWb(unitInContract.getInputCodeWb());
            unitInContract.setOrgId(examRptPatternVo.getOrgId());
            count = count + Integer.parseInt(unitInContractApi.save(unitInContract));
        }
        for (int i = 0; i < examRptPatternVo.getUpdated().size(); i++) {
            UnitInContract unitInContract = new UnitInContract();
            unitInContract = (UnitInContract) examRptPatternVo.getUpdated().get(i);
            unitInContract.setUnitName(unitInContract.getUnitName());
            unitInContract.setInputCode(PinYin2Abbreviation.cn2py(unitInContract.getUnitName()));
            unitInContract.setAddress(unitInContract.getAddress());
            unitInContract.setUnitType(unitInContract.getUnitType());
            unitInContract.setSubordinateTo(unitInContract.getSubordinateTo());
            unitInContract.setDistanceToHospital(unitInContract.getDistanceToHospital());
            unitInContract.setRegularNum(unitInContract.getRegularNum());
            unitInContract.setTempNum(unitInContract.getTempNum());
            unitInContract.setRetiredNum(unitInContract.getRetiredNum());
            unitInContract.setInputCodeWb(unitInContract.getInputCodeWb());
            unitInContract.setOrgId(examRptPatternVo.getOrgId());
            count = count + Integer.parseInt(unitInContractApi.update(unitInContract));
        }
        StringData stringData = new StringData();
        if (count == (examRptPatternVo.getInserted().size() + examRptPatternVo.getUpdated().size())) {
            num = 1;
        }
        stringData.setCode(String.valueOf(num));
        stringData.setData("success");
        return stringData;
    }


}
