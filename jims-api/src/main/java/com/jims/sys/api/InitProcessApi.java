package com.jims.sys.api;

import com.jims.sys.entity.InitProcess;

import java.util.List;

/**
 * 菜单操作顺序
 * Created by zhuqi on 2016/8/9.
 */
public interface InitProcessApi {

    /**
     * 根据orgId查询
     * @param orgId
     * @return
     * @author  zq
     */
    public List<InitProcess> findByOrgId (String orgId);

    /**
     * 保存多条数据
     * @param initProcesses
     * @return
     * @author  zq
     */
    public String saveList(List<InitProcess> initProcesses);

    /**
     * 根据菜单表数据生成initProcess表数据
     * @author zq
     */
    public List<InitProcess> findMenuList ();

    /**
     * 删除
     * @author zq
     */
    public String delete(String id);
    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public InitProcess get(String id);
    /**
     * 保存修改数据
     * @param initProcess
     * @return
     */
    public String save(InitProcess initProcess);

}
