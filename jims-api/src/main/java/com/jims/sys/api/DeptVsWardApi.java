package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.DeptVsWard;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface DeptVsWardApi {
    /**
     * 查询字段列表
     * @param page
     * @param deptVsWard
     * @return
     */
    public Page<DeptVsWard> findPage(Page<DeptVsWard> page, DeptVsWard deptVsWard);

    /**
     * 保存修改数据
     * @param deptVsWard
     * @return
     */
    public String save(DeptVsWard deptVsWard);

    /**
     * 删除数据
     * @param ids
     * @return
     */
    public String delete(String ids);

    /**
     * 查询html类型列表
     * @return
     */
    public List<String> findTypeList();

    /**
     * 获取单条数据
     * @param id
     * @return
     */
    public DeptVsWard get(String id);
}
