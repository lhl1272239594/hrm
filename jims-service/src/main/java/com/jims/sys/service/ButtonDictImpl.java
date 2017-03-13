package com.jims.sys.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.service.impl.CrudImplService;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.api.ButtonDictApi;
import com.jims.sys.dao.ButtonDictDao;
import com.jims.sys.entity.ButtonDict;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.MENU_DICT_BUTTON;
import org.springframework.beans.factory.annotation.Autowired;
import com.jims.sys.bo.ButtonDictBo;
import java.util.List;

/**
 * 菜单
 * @author yangchen
 * @version 2016-08-17
 */

@Service(version = "1.0.0")

public class ButtonDictImpl extends CrudImplService<ButtonDictDao, ButtonDict> implements ButtonDictApi{
    @Autowired
    private ButtonDictBo bo;
    /**
     * 保存方法（返回id）
     * @param buttonDict
     */
    @Override
    public String insertReturnObject(ButtonDict buttonDict) {
        buttonDict.preInsert();//添加日期
        int i = dao.insertReturnObject(buttonDict);
        String id = buttonDict.getId();
        return  id;
    }

    /**
     * 修改方法（返回id）
     * @param buttonDict
     * @return
     */
    @Override
    public String updateReturnObject(ButtonDict buttonDict) {
        buttonDict.preUpdate();//添加日期
        int i = dao.updateReturnObject(buttonDict);
        String id = buttonDict.getId();
        return  id;
    }

    /**
     * 获取全部列表
     * @return
     */
    @Override
    public List<ButtonDict> findAll() {

        return dao.findAllList(new ButtonDict());
    }
    /**
     *
     * @param serviceId
     * @param roleId
     * @param isTree
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceMenu(String serviceId, String roleId, boolean isTree,String orgid) {
        return bo.findSelfServiceMenu(serviceId, roleId, isTree,orgid);
    }
    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAllList(String orgId, String roleid, String serid, String mid) {
        return bo.findAllList(orgId,roleid,serid,mid);

    }
    /**
     *
     */
    public List<MENU_DICT_BUTTON> getALLbut(String mid)
    {
        List<MENU_DICT_BUTTON>  list= dao.getALLbut(mid);
        return list;
    }
    /**
     *
     */
    public void delbt(String ids){
        String id [] =ids.split(",");
        for(int i=0;i<id.length;i++)
        {
            dao.delbt(id[i]);
        }
    }
    /**
     * 更新数据权限
     */
    public void upmdata(ButtonDict buttonDict)
    {
        bo.upmdata(buttonDict);
    }
    /**
     *
     */
    public void upbtnrole(List<ButtonDict> buttonDict)
    {
        bo.upbtnrole(buttonDict);
    }
    /**
     *
     * @param serviceId
     * @param roleId
     * @param isTree
     * @return
     */
    public List<OrgSelfServiceVsMenu> findSelfServiceVsMenuBtn(String serviceId, String roleId, boolean isTree, String orgid,String meid) {
        return bo.findSelfServiceVsMenuBtn(serviceId, roleId, isTree,orgid,meid);
    }
}
