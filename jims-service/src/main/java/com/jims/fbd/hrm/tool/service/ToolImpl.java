package com.jims.fbd.hrm.tool.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.fbd.hrm.tool.api.ToolApi;
import com.jims.fbd.hrm.tool.bo.ToolBo;
import com.jims.fbd.hrm.tool.entity.Tool;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.Dict;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.OrgStaff;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by Administrator on 2016/4/24 0024.
 */
@Service(version = "1.0.0")

public class ToolImpl implements ToolApi {

    @Autowired
    private ToolBo toolBo;
    /**
     * 根据类型和输入的拼音码检索字典

     * @return
     * @author fengyuguang
     */
    @Override
    public List<Tool> listByType(Tool tool){

        return toolBo.listByType(tool);
    }
    /**
     * 查询人员角色信息
     *
     * @return
     */
    @Override
    public List<OrgRole> getRole(String staffId, String orgId){
        return toolBo.getRole(staffId,orgId);
    }
    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    @Override
    public List<OrgSelfServiceVsMenu> getMenu(String serviceId, String staffId, String roleId) {
        return toolBo.getMenu(serviceId,staffId,roleId);
    }



    @Override
    public Tool findOrgName(Tool tool){

        return toolBo.findOrgName(tool);
    }
    @Override
    public List<Tool> findPersonList(Tool tool){

        return toolBo.findPersonList(tool);
    }

    @Override
    public List<Tool> findDeptList(Tool tool){

        return toolBo.findDeptList(tool);
    }
    /**
     * 查询OrgStaff
     *
     * @return
     */
    @Override
    public OrgStaff findStaffById(String staffId) {
        return toolBo.findStaffById(staffId);
    }
    /**
     * 获取StaffId
     *
     * @return
     */
    @Override
    public String getStaffId(String persionId) {
        return toolBo.getStaffId(persionId);
    }

    @Override
    public List<Tool> findUserTree(Tool tool) {
        return toolBo.findUserTree(tool);

    }

}
