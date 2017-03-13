package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.register.dao.OrgSelfServiceVsMenuDao;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.dao.MenuDictDao;
import com.jims.sys.dao.RoleServiceMenuDao;
import com.jims.sys.entity.MenuDict;
import com.jims.sys.entity.RoleServiceMenu;
import com.jims.sys.vo.MenuDictVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/1.
 */
@Component
@Transactional(readOnly = true)
public class RoleServiceMenuService extends CrudImplService<RoleServiceMenuDao, RoleServiceMenu> {

    public void save(List<RoleServiceMenu> roleServiceMenus) {
        if(roleServiceMenus != null && roleServiceMenus.size() > 0){
            for(int i=0;i<roleServiceMenus.size();i++){
                RoleServiceMenu menu = roleServiceMenus.get(i);
                if(i == 0){
                    String roleServiceId = menu.getRoleServiceId();
                    dao.deleteService(roleServiceId);
                    continue;
                }
                menu.preInsert();
                dao.insert(menu);
            }
        }
    }

}
