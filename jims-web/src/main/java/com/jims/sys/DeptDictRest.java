package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.utils.StringUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.entity.SysCompany;
//import com.jims.sys.vo.DeptDictVo;
import com.jims.sys.vo.DeptDictVo;
import org.springframework.stereotype.Component;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yangruidong on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("dept-dict")
public class DeptDictRest {
    @Reference(version = "1.0.0")
    private DeptDictApi deptDictApi;

    @Reference(version = "1.0.0")
    private DeptPropertyDictApi deptPropertyDictApi;

    /**
     * 查询所有的科室信息
     *
     * @return
     */
    @Path("list")
    @GET
    public List<DeptDict> list(@QueryParam("orgId") String orgId) {

        //查询出所有的科室信息
        List<DeptDict> list = deptDictApi.findAllList(orgId);
        return list;
    }


    /**
     * 查询多个科室属性信息
     *
     * @return
     */
    @Path("listByOrgId")
    @GET
    public List<DeptDict> getByOrgId(@QueryParam("orgId") String orgId) {

        //查询某个科室信息
        List<DeptDict>  list = deptDictApi.getByOrgId(orgId);
        return list;
    }

    /**
     * 查询所有的上级科室
     *
     * @return
     */
    @Path("selectParent")
    @POST
    public List<DeptDict> findParent() {

        List<DeptDict> list = deptDictApi.findParent();
        return list;
    }

    /**
     * 查询某个机构上级科室
     *
     * @return
     */
    @Path("selectParentByOrgId")
    @POST
    public List<DeptDict> findParent(@QueryParam("orgId") String orgId, @QueryParam("q") String q, @QueryParam("deptCode") String deptCode) {

        List<DeptDict> list = deptDictApi.findListParent(orgId, q, deptCode);
        return list;
    }

    /**
     * 保存修改方法
     *
     * @param
     * @return
     */
    @Path("add")
    @POST
    public StringData saveDept(DeptDictVo deptDictVo) {


        DeptDict deptDict = new DeptDict();
        deptDict.setParentId(deptDictVo.getParentId());

        deptDict.setId(deptDictVo.getId());

        deptDict.setDeptCode(deptDictVo.getDeptCode());
        deptDict.setDeptName(deptDictVo.getDeptName());
        deptDict.setOrgId(deptDictVo.getOrgId());
        deptDict.setInputCode(deptDictVo.getInputCode());
        deptDict.setClinicAttr(deptDictVo.getClinicAttr());
        deptDict.setOutpOrInp(deptDictVo.getOutpOrInp());
        deptDict.setInternalOrSergery(deptDictVo.getInternalOrSergery());

        StringBuilder sb = new StringBuilder();
        String deptPropertity[] = deptDictVo.getArray();

        if(deptPropertity.length==0){
            sb.append(";;");
        }
        if (deptPropertity.length == 1) {
            sb.append(deptPropertity[0]+";;");
        } else {
            for (int i = 0; i < deptPropertity.length; i++) {

                sb.append(deptPropertity[i] + ";");
            }
        }


        /*if (deptPropertity.length > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }*/

        deptDict.setDeptPropertity(sb.toString());


        String num = deptDictApi.save(deptDict);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;
    }


    /**
     * 删除科室信息
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = deptDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 查询所有的下级科室
     *
     * @return
     */
    @Path("findListByCode")
    @POST
    public List<DeptDict> findListByCode(String code) {
        int index = code.indexOf("=");
        code = code.substring(index + 1);
        List<DeptDict> list = deptDictApi.findListByCode(code);
        return list;
    }

    /**
     * 查询所有的科室信息
     *
     * @return
     */
    @Path("findListWithFilter")
    @GET
    public List<DeptDict> findListWithFilter(@QueryParam("orgId") String orgId, @QueryParam("q") String q) {

        DeptDict dept = new DeptDict();
        dept.setOrgId(orgId);
        dept.setQ(q);
        List<DeptDict> list = deptDictApi.findList(dept);
        return list;
    }

    /**
     * 查询检验科室
     *
     * @return
     */
    @Path("getList")
    @POST
    public List<DeptDict> getList() {
        return deptDictApi.getList();
    }

    /**
     * 手术科室
     *
     * @return
     */
    @Path("getOperation")
    @POST
    public List<DeptDict> getOperation(@Context HttpServletRequest request) {
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        List<DeptDict> operationList = deptDictApi.getOperation(loginInfo.getOrgId());
        return operationList;
    }

    /**
     * 获取医生所在科室
     *
     * @return
     */
    @Path("getDoctorDept")
    @GET
    public List<DeptDict> getDoctorDept(@QueryParam("doctorGroup") String doctorGroup, @Context HttpServletRequest request) {
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        List<DeptDict> operationList = deptDictApi.getDoctorDept(loginInfo.getOrgId(), loginInfo.getPersionId(), doctorGroup);
        return operationList;
    }
    /**
     * 获取科室底下的权限医生
     *
     * @return
     */
    @Path("getDoctor")
    @GET
    public List<BaseDto> getDoctor(@QueryParam("doctorGroup") String doctorGroup,@QueryParam("deptId") String deptId, @Context HttpServletRequest request) {
        LoginInfo loginInfo = LoginInfoUtils.getPersionInfo(request);
        List<BaseDto> operationList = deptDictApi.getDoctor(loginInfo.getOrgId(),doctorGroup,deptId);
        return operationList;
    }


    /**
     * 查询病房
     * @param orgId
     * @param q
     * @return
     */
    @Path("find-list-of-inpatients")
    @GET
    public List<DeptDict> findListOfInpatients(@QueryParam("orgId")String orgId,@QueryParam("q")String q){
        return deptDictApi.findListOfInpatients(orgId, q);
    }

    /**
     * 查询当前登陆人所属科室
     * @param orgId
     * @param personId
     * @return
     */
    @Path("find-by-personId")
    @GET
    public  List<DeptDict> findByPersonId(@QueryParam("orgId")String orgId,@QueryParam("personId")String personId){
        return deptDictApi.findByPersonId(orgId,personId);
    }

    /**
     *根据id查询科室编码
     * @param
     * @param
     * @return
     */
    @Path("findDeptCode")
    @GET
    public DeptDict findDeptCode(@QueryParam("id")String id){

        return deptDictApi.findDeptCode(id);
    }
}
