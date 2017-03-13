package com.jims.sys.api;



import com.jims.sys.entity.ServiceVsMenu;
import com.jims.sys.entity.SysService;
import com.jims.sys.entity.SysServicePrice;
import com.jims.sys.vo.StaffGroupVo;

import java.util.List;

/**
 * 系统服务api层
 *
 * @author txb
 * @version 2016-05-31
 */
public interface SysServiceApi {


    /**
     * 保存或修改
     * @param sysService
     * @author txb
     * @version 2016-05-31
     * @return
     */
    public  String save(SysService sysService);

    /**
     * 修改保存服务明细
     * @param priceBeanVo
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public String saveDetail(StaffGroupVo priceBeanVo);
    /**
     * 修改保存服务菜单
     * @param serviceVsMenus
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public String saveServiceVsMenu(List<ServiceVsMenu> serviceVsMenus);

    /**
     * 删除
     * @param ids
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public String delete(String ids);
    /**
     * 查询服务全部列表
     * @return
     * @author txb
     * @version 2016-05-31
     */
    public List<SysService> findList();
    /**
     * 查询服务明细全部列表
     * @return
     * @author txb
     * @version 2016-06-01
     */
    public List<SysServicePrice> findDetailList(String serviceId);
    /**
     * 通过服务类别类型查询服务列表
     * @param serviceType 服务类型
     * @param serviceClass 服务类别
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<SysService> serviceListByTC( String serviceType,String serviceClass);
    /**
     * 查询服务全部菜单
     * @param serviceId 服务id
     * @return
     * @author txb
     * @version 2016-06-02
     */
    public List<ServiceVsMenu> serviceVsMenuList(String serviceId);

    /**
     * 检索不同人群的服务
     * @param serviceClass 服务人群 1,个人服务，0机构服务
     * @param serviceType  服务类型
     * @param persionId  用户persionId(若persionId不为空，查询的是可以或者不可以定制的个人服务，具体决定是可以或是不可以是根据state决定的)
     * @param state  若state=0，表示查询个人已经定制的个人服务；若state=1，查询的是个人还可以定制的其他个人服务
//     * @param id  sys_service.id
     * @return
     */
    public List<SysService> findServiceWithPrice(String serviceClass,String serviceType ,String persionId, String state ,String id);

    /**
     * 根据id查询服务
     * @param id 服务ID
     * @return 服务
     * @author fengyuguang
     */
    public SysService get(String id);
}
