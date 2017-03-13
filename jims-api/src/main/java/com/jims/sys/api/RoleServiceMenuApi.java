package com.jims.sys.api;

import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.MenuDictVo;

import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
public interface RoleServiceMenuApi {

    public String save(List<RoleServiceMenu> roleServiceMenus);

}
