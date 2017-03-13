package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.common.utils.LoginInfoUtils;
import com.jims.common.utils.StringUtils;
import com.jims.common.vo.LoginInfo;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.api.DeptDictApi;
import com.jims.sys.api.DeptPropertyDictApi;
import com.jims.sys.api.InputSettingServiceApi;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.InputSettingDetail;
import com.jims.sys.entity.InputSettingMaster;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.vo.DeptDictVo;
import com.jims.sys.vo.InputInfoVo;
import com.jims.sys.vo.InputParamVo;
import com.jims.sys.vo.InputSettingVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.UriInfo;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by yangruidong on 2016/4/24 0024.
 */
@Component
@Produces("application/json")
@Path("input-setting")
public class InputSettingRest {
    @Reference(version = "1.0.0")
    private InputSettingServiceApi inputSettingServiceApi;

    /**
     * 根据组织机构id查询输入法的主记录
     *
     * @param orgId
     * @return
     */
    @Path("findAllListByOrgId")
    @GET
    public List<InputSettingMaster> findAllListByOrgId(@QueryParam("orgId") String orgId) {
        return inputSettingServiceApi.findAllListByOrgId(orgId);
    }

    /**
     * 保存  增删改
     *
     * @param inputSettingMasterVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(InputSettingVo<InputSettingMaster> inputSettingMasterVo) {
        List<InputSettingMaster> newUpdateDict = new ArrayList<InputSettingMaster>();
        newUpdateDict = inputSettingServiceApi.saveAll(inputSettingMasterVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 根据输入法主记录的id查询输入法字典明细表的信息
     *
     * @param id
     * @return
     * @author yangruidong
     */
    @Path("findListDetail")
    @GET
    public List<InputSettingDetail> findListDetail(@QueryParam("id") String id) {
        return inputSettingServiceApi.findListDetail(id);
    }


    /**
     * 根据表名称，查询表中有什么样的字段
     *
     * @param tableName
     * @return
     */
    @Path("listTableColByTableName")
    @GET
    public List<String> listTableColByTableName(@QueryParam("tableName") String tableName) {
        return inputSettingServiceApi.listTableColByTableName(tableName);
    }


    /**
     * 根据字典类型，查询字典设置，然后返回本字典的结果集
     *
     * @param dictType 字典类型
     * @param orgId    组织机构
     * @return
     * @Author ztq
     */
    @Path("list")
    @GET
    public List<BaseDto> list(@QueryParam("dictType") String dictType, @QueryParam("orgId") String orgId) {
        List<BaseDto> list = inputSettingServiceApi.listInputDataBy(dictType, orgId);
        return list;
    }


    /**
     * 保存  增删改      输入法字典明细表
     *
     * @param inputSettingDetailVo
     * @return
     * @author yangruidong
     */
    @Path("saveDetail")
    @POST
    public StringData saveDetail(InputSettingVo<InputSettingDetail> inputSettingDetailVo) {
        List<InputSettingDetail> newUpdateDict = new ArrayList<InputSettingDetail>();
        newUpdateDict = inputSettingServiceApi.saveDetail(inputSettingDetailVo);
        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 根据传入的信息查询并过滤相关内容
     *
     * @param inputInfoVo
     * @return
     */
    @Path("listParam")
    @POST
    public List<BaseDto> listParam(InputInfoVo inputInfoVo, @QueryParam("q") String q,@Context HttpServletRequest request) {
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        if(inputInfoVo.getIsOrgId()){
            inputInfoVo.setOrgId(loginInfo.getOrgId());
        }else{
            inputInfoVo.setOrgId("");
        }
        if (StringUtils.isNotEmpty(q)) {
            List<InputParamVo> inputParamVos = inputInfoVo.getInputParamVos();
            InputParamVo vo = new InputParamVo("input_code", q, "like");
            inputParamVos.add(vo);
        }
        List<BaseDto> list = inputSettingServiceApi.listInputDataByParam(inputInfoVo);
        return list;
    }


    /**
     * 根据传入的视图名，输入查询的字段查询数据
     * @param
     * @return
     * @zhuqi
     */
    @Path("listByName")
    @GET
    public List<BaseDto> listByName(@QueryParam("name") String name, @QueryParam("q") String q, @QueryParam("field") String field) {
        InputInfoVo inputInfoVo = new InputInfoVo();
        inputInfoVo.setDictType(name);
        if(StringUtils.isNotBlank(q)){
            inputInfoVo.setInputParamVos(new ArrayList<InputParamVo>());
            InputParamVo vo = new InputParamVo(field, q.toUpperCase(), "like");
            inputInfoVo.getInputParamVos().add(vo);
        }
        List<BaseDto>list=inputSettingServiceApi.listInputDataByParam(inputInfoVo);
        if(list.size()>20){
            list=list.subList(0,19);
        }
        return list;
    }


    @Path("listParamByGET")
    @GET
    public List<BaseDto> listParamByGET(@Context UriInfo info,@Context HttpServletRequest request) {
        InputInfoVo inputInfoVo = new InputInfoVo();
        LoginInfo loginInfo= LoginInfoUtils.getPersionInfo(request);
        if(inputInfoVo.getIsOrgId()){
            inputInfoVo.setOrgId(loginInfo.getOrgId());
        }else{
            inputInfoVo.setOrgId("");
        }
        inputInfoVo.setInputParamVos(new ArrayList<InputParamVo>());
        Class c = InputInfoVo.class;
        MultivaluedMap<String, String> params = info.getQueryParameters();
        Set<String> keys = params.keySet();
        int size = keys.size();
        try {
            for (String key : keys) {
                List<String> values = params.get(key);
                if ("q".equals(key)) {
                    InputParamVo vo = new InputParamVo("input_code", values.get(0).toUpperCase(), "like");
                    inputInfoVo.getInputParamVos().add(vo);
                    size--;
                } else if (!key.startsWith("inputParamVos")) {
                    if (values != null && values.size() > 0) {
                        PropertyDescriptor keyDes = new PropertyDescriptor(key, c);
                        Method m = keyDes.getWriteMethod();
                        m.invoke(inputInfoVo, values.get(0));
                        size--;
                    }
                }
            }
            for (int i = 0; i < size / 3; i++) {
                List<String> names = params.get("inputParamVos[" + i + "][colName]");
                List<String> values = params.get("inputParamVos[" + i + "][colValue]");
                List<String> operates = params.get("inputParamVos[" + i + "][operateMethod]");
                if (names != null && names.size() > 0 && values != null
                        && values.size() > 0 && operates != null && operates.size() > 0) {
                    InputParamVo vo = new InputParamVo(names.get(0), values.get(0), operates.get(0));
                    inputInfoVo.getInputParamVos().add(vo);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputSettingServiceApi.listInputDataByParam(inputInfoVo);
    }
}
