package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.common.data.StringData;
import com.jims.sys.api.MenuDictApi;
import com.jims.sys.entity.MenuDict;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * 菜单
 *
 * @author tangxb
 * @version 2016-04-25
 */
@Component
@Produces("application/json")
@Path("menuDict")
public class MenuDictRest {

    @Reference(version = "1.0.0")
    private MenuDictApi menuDictApi;

    /**
     * 异步加载表格
     *
     * @param request
     * @param response
     * @return
     */
    @Path("list")
    @GET
    public List<MenuDict> list(@Context HttpServletRequest request, @Context HttpServletResponse response) {
        List<MenuDict> list = new ArrayList<MenuDict>();
        list = menuDictApi.findAll();
        return list;
    }

    /**
     * 输入菜单名查找菜单
     * @zhuqi
     */
    @Path("list-by-name")
    @GET
    public List<MenuDict> findByName(@QueryParam("q") String q) {
        List<MenuDict>list = menuDictApi.findByName(q);
        return list;
    }

    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    @Path("get")
    @POST
    public MenuDict get(String id) {
        MenuDict menuDict = menuDictApi.get(id);
        return menuDict;
    }

    /**
     * 保存修改方法
     *
     * @param menuDict
     * @return
     */
    @Path("save")
    @POST
    public StringData save(MenuDict menuDict) {
        String num = menuDictApi.save(menuDict);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
    /**
     * 保存方法(返回对象)
     *
     * @param menuDict
     * @return
     */
    @Path("insertReturnObject")
    @POST
    public MenuDict insertReturnObject(MenuDict menuDict) {
        menuDict = menuDictApi.get(menuDictApi.insertReturnObject(menuDict));
        return menuDict;
    }
    /**
     * 修改方法(返回对象)
     *
     * @param menuDict
     * @return
     */
    @Path("updateReturnObject")
    @POST
    public MenuDict updateReturnObject(MenuDict menuDict) {
        menuDict = menuDictApi.get(menuDictApi.updateReturnObject(menuDict));
        return menuDict;
    }

    /**
     * 批量id删除
     *
     * @param ids
     * @return
     */
    @Path("del")
    @POST
    public StringData del(String ids) {
        String num = menuDictApi.delete(ids);
        StringData stringData = new StringData();
        stringData.setCode(num);
        stringData.setData("success");
        return stringData;
    }
}
