package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.PageData;
import com.jims.common.data.StringData;
import com.jims.common.persistence.Page;
import com.jims.sys.api.AdministrationDictApi;
import com.jims.sys.api.DictServiceApi;
import com.jims.sys.entity.AdministrationDict;
import com.jims.sys.entity.Dict;
import com.jims.sys.vo.AdministrationDictVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by heren on 2016/4/5.
 */
@Component
@Produces("application/json")
@Path("AdministrationDict")
public class AdministrationDictRest {

    @Reference(version = "1.0.0")
    private AdministrationDictApi administrationDictApi;

    /**
     * 分页查询给药途径字典列表
     *
     * @return
     * @author yangruidong
     */
   /* @Path("listAll")
    @GET
    public PageData list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        Page<AdministrationDict> page = administrationDictApi.findPage(new Page<AdministrationDict>(request, response), new AdministrationDict());
        PageData pageData = new PageData();
        pageData.setRows(page.getList());
        pageData.setTotal(page.getCount());
        return pageData;
    }*/

    /**
     * 查询给药途径字典列表
     *
     * @return
     * @author yangruidong
     */
    @Path("listAll")
    @GET
    public List<AdministrationDict> listAll() {
        return administrationDictApi.findAllList();
    }

    /**
     * 查询给药途径字典表
     * @param inputCode 输入码
     * @return
     * @author fengyuguang
     */
    @Path("list")
    @GET
    public List<AdministrationDict> list(@QueryParam("q")String inputCode){
        return administrationDictApi.list(inputCode);
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     * @author yangruidong
     */
    @Path("get")
    @POST
    public AdministrationDict get(String id) {
        AdministrationDict administrationDict = administrationDictApi.get(id);
        return administrationDict;
    }

    /**
     * 保存修改方法
     *
     * @param administrationDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(AdministrationDict administrationDict) {
        String num = administrationDictApi.save(administrationDict);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
     * 保存  增删改
     *
     * @param administrationDictVo
     * @return
     * @author yangruidong
     */
    @Path("saveAll")
    @POST
    public StringData saveAll(AdministrationDictVo<AdministrationDict> administrationDictVo) {
        List<AdministrationDict> newUpdateDict = new ArrayList<AdministrationDict>();
        newUpdateDict = administrationDictApi.saveAll(administrationDictVo);

        StringData stringData = new StringData();
        stringData.setData("success");
        return stringData;

    }

    /**
     * 根据试用返回获取用药途径 ，传入是门诊则获取门诊+全部
     * 传入的是住院 = 住院+ 全部
     * 传入的全部 = 门诊 + 住院+全部
     *
     * @param inpOrOutpFlag 全部（综合）、门诊、住院
     * @return
     */
    @Path("listAdministrationByInpOrOutpFlag")
    @GET
    public List<AdministrationDict> listAdministrationByInpOrOutpFlag(@QueryParam("inpOrOutpFlag") String inpOrOutpFlag) {
        List<AdministrationDict> dict = administrationDictApi.listAdministrationByInpOrOutpFlag(inpOrOutpFlag);
        return dict;


    }
    /**
     * @param        orgId     传递参数
     * @return java.util.List<com.jims.sys.entity.AdministrationDict>    返回类型
     * @throws
     * @Title: mzViewList
     * @Description: (查询门诊用药途径)
     * @author CTQ
     * @date 2016/6/28
     */
    @Path("mzViewList")
    @GET
    public List<AdministrationDict> mzViewList(@QueryParam("orgId") String orgId){

        List<AdministrationDict> list = administrationDictApi.mzViewList(orgId);
        return list;
    }
}
