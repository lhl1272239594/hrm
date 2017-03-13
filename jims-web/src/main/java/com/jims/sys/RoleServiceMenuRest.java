package com.jims.sys;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jims.sys.api.RoleServiceMenuApi;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.MenuDictVo;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
@Component
@Produces("application/json")
@Path("service-menu")
public class RoleServiceMenuRest {

    @Reference(version = "1.0.0")
    private RoleServiceMenuApi roleServiceMenuApi;

    @Path("save")
    @POST
    public String save(List<RoleServiceMenu> roleServiceMenus){
       return roleServiceMenuApi.save(roleServiceMenus);
    }

}
