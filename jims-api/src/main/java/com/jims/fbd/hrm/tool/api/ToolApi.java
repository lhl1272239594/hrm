package com.jims.fbd.hrm.tool.api;

import com.jims.fbd.hrm.tool.entity.Tool;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.OrgStaff;

import java.util.List;

public interface ToolApi {
    /**
     * 根据类型和输入的拼音码检索字典
     *
     * @return
     * @author fengyuguang
     */
    public List<Tool> listByType(Tool tool);
    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<OrgRole> getRole(String staffId, String orgId);
    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @param roleId 角色ID
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    public List<OrgSelfServiceVsMenu> getMenu(String serviceId, String staffId,String roleId);

    public List<Tool> findUserTree(Tool tool);
    public List<Tool> findPersonList(Tool tool);
    public Tool findOrgName(Tool tool);
    public List<Tool> findDeptList(Tool tool);
    /**
     * 查询OrgStaff
     *
     * @return
     */
    public OrgStaff findStaffById(String staffId);
    /**
     * 获取StaffId
     *
     * @return
     */
    public String getStaffId(String persionId);
}