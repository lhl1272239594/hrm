package com.jims.fbd.hrm.tool.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.fbd.hrm.tool.entity.Tool;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.OrgStaff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@MyBatisDao
public interface ToolDao extends CrudDao<Tool>{


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
    public List<OrgRole> getRole(@Param("staffId") String staffId,@Param("orgId") String orgId);
    /**
     * 根据roleServiceId查询数据列表
     * @param serviceId 服务ID
     * @param staffId 员工ID
     * @param roleId
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    public List<OrgSelfServiceVsMenu> getMenu(String serviceId, String staffId, String roleId);
    /**
     * 查询人员选择树
     *
     * @return
     */
    public List<Tool> findUserTree(Tool tool);
    /**
     * 查询人员姓名列表
     *
     * @return
     */


    //查询用户用户列表
    public List<Tool> findPersonList(Tool tool);

    public Tool findOrgName(Tool tool);
    /**
     * 查询部门组合列表
     *
     * @return
     */
    public List<Tool> findDeptList(Tool tool);
    /**
     * 查询OrgStaff
     *
     * @return
     */
    OrgStaff findStaffById(@Param("staffId") String staffId);
    /**
     * 获取StaffId
     *
     * @return
     */
    public String getStaffId(@Param("persionId") String persionId);
}
