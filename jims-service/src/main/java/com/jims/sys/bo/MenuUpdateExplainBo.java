package com.jims.sys.bo;

import com.jims.sys.entity.MenuUpdateExplain;
import com.jims.sys.dao.MenuUpdateExplainDao;
import com.jims.common.service.impl.CrudImplService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* 服务菜单更新说明bo
* @author lgx
* @version 2016-07-19
*/
@Service
@Transactional(readOnly = false)
public class MenuUpdateExplainBo extends CrudImplService<MenuUpdateExplainDao, MenuUpdateExplain>{

    /**
    * 批量保存（插入或更新）
    * @param list
    */
    public void save(List<MenuUpdateExplain> list) {
        if(list != null && list.size() > 0) {
            for(MenuUpdateExplain entity : list) {
                save(entity);
            }
        }
    }
}