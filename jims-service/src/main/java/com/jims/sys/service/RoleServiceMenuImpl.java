package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.sys.api.RoleServiceMenuApi;
import com.jims.sys.bo.RoleServiceMenuService;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.MenuDictVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
@Service(version = "1.0.0")
public class RoleServiceMenuImpl implements RoleServiceMenuApi{

    @Autowired
    private RoleServiceMenuService roleServiceMenuService;

    public String save(List<RoleServiceMenu> roleServiceMenus){
        String result = "1";
        try{
            roleServiceMenuService.save(roleServiceMenus);
            result = "0";
        } catch (RuntimeException e){
        }
        return result;
    }
}
