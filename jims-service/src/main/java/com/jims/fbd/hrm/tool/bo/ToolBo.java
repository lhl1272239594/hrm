package com.jims.fbd.hrm.tool.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.tool.dao.ToolDao;
import com.jims.fbd.hrm.tool.entity.Tool;
import com.jims.register.entity.OrgSelfServiceVsMenu;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.entity.OrgStaff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Component
@Transactional(readOnly = false)
public class ToolBo extends CrudImplService<ToolDao,Tool> {


    @Autowired
    private ToolDao  toolDao;
    /**
     * 根据类型和输入的拼音码检索字典


     * @return
     * @author fengyuguang
     */
    public List<Tool> listByType(Tool tool) {

        return toolDao.listByType(tool);
    }

    /**
     * 查询人员角色信息
     *
     * @return
     */
    public List<OrgRole> getRole(String staffId, String orgId) {

        return dao.getRole(staffId,orgId);
    }

    /**
     * 根据roleServiceId查询数据列表
     *
     * @param serviceId 服务ID
     * @param staffId   员工ID
     * @param roleId 角色ID
     * @return role_service_menu和menu_dict两个表联查集合
     * @author fengyuguang
     */
    public List<OrgSelfServiceVsMenu> getMenu(String serviceId, String staffId, String roleId) {
        return dao.getMenu(serviceId, staffId,roleId);
    }



    public Tool findOrgName(Tool tool) {

        return dao.findOrgName(tool);
    }
    public List<Tool> findPersonList(Tool tool) {

        return dao.findPersonList(tool);
    }
    public List<Tool> findDeptList(Tool tool) {

        return dao.findDeptList(tool);
    }
    public List<Tool> findUserTree(Tool tool) {

        List<Tool> list = dao.findUserTree(tool);

        return list;
    }
    /**
     * 查询OrgStaff
     *
     * @return
     */
    public OrgStaff findStaffById(String staffId) {
        return dao.findStaffById(staffId);
    }
    /**
     * 获取StaffId
     *
     * @return
     */
    public String getStaffId(String persionId) {
        return dao.getStaffId(persionId);
    }
}
