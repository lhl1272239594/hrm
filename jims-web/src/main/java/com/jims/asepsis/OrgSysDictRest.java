package com.jims.asepsis;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.asepsis.entity.OrgSysDict;
import com.jims.asepsis.api.OrgSysDictApi;
import com.jims.common.data.StringData;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
* 包单位维护rest
* @author yangruidong
* @version 2016-06-28
*/
@Component
@Produces(MediaType.APPLICATION_JSON)
@Path("orgSysDict")
public class OrgSysDictRest {

    @Reference(version = "1.0.0")
    private OrgSysDictApi api;

    /**
     * 异步加载页面左侧表格:机构字典表的类型和描述列表
     * @param orgId 组织机构ID
     * @param request
     * @param response
     * @return 机构字典表type和description两个字段的list集合
     * @author fengyuguang
     */
    @GET
    @Path("type-description-list")
    public List<OrgSysDict> leftList(@QueryParam("orgId")String orgId,@Context HttpServletRequest request, @Context HttpServletResponse
            response) {
        List<OrgSysDict> list = api.leftList(orgId);
        return list;
    }

    /**
     * 异步加载页面右侧表格:机构字典表的键值列表
     * @param type 类型
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    @GET
    @Path("label-value-list")
    public List<OrgSysDict> rightList(@QueryParam("type") String type,@QueryParam("orgId")String orgId) {
        return api.rightList(type,orgId);
    }

    /**
     * 保存多条增删改数据
     * @param beanChangeVo 多条增删改数据的集合
     * @return
     * @author fengyuguang
     */
    @POST
    @Path("merge")
    public StringData merge(BeanChangeVo<OrgSysDict> beanChangeVo) {
        String num = api.merge(beanChangeVo);
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
     * 根据类型或描述查询某个组织机构的字典数据
     * @param type 类型
     * @param description 描述
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    @Path("search")
    @GET
    public List<OrgSysDict> search(@QueryParam("type")String type,@QueryParam("description")String description,@QueryParam("orgId")String
            orgId){
        return api.search(type,description,orgId);
    }

    /**
     * 批量删除
     * @param ids ID集合
     * @return
     * @author fengyuguang
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }

    /**
    * 检索
    * @param orgId
    * @return
    */
    @GET
    @Path("findList")
    public List<OrgSysDict> findList(@QueryParam("orgId")String orgId) {
        OrgSysDict entity = new OrgSysDict();
        entity.setOrgId(orgId);
        return api.findList(entity);
    }

    /**
     * 根据类型查询包单位
     * @param type
     * @return
     */
    @GET
    @Path("findUnits")
    public List<OrgSysDict> findUnits(@QueryParam("type")String type,@QueryParam("orgId")String orgId) {
        OrgSysDict entity = new OrgSysDict();
        entity.setType(type);
        entity.setOrgId(orgId);
        return api.findUnits(entity);
    }

    /**
    * 保存（插入或更新）
    * @param entity
    * @return 0 失败，1成功
    */
    @POST
    @Path("save")
    public String save(OrgSysDict entity) {
        return api.save(entity);
    }

    /**
    * 批量保存（插入或更新）
    * @param list
    * @return 0 失败，1成功
    */
    @POST
    @Path("saveList")
    public String saveList(List<OrgSysDict> list) {
        return api.save(list);
    }

    /**
    * 删除数据
    * @param ids,多个id以逗号（,）隔开
    * @return 0 失败，1成功
    */
    @GET
    @Path("delete")
    public String delete(String ids) {
        return api.delete(ids);
    }
}