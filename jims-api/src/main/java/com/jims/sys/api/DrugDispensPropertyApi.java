package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.DrugDispensProperty;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface DrugDispensPropertyApi {
    /**
     * 查询字段列表
     * @param page
     * @param drugDispensProperty
     * @return
     */
    public Page<DrugDispensProperty> findPage(Page<DrugDispensProperty> page, DrugDispensProperty drugDispensProperty);

    /**
     * 保存修改数据
     * @param drugDispensProperty
     * @return
     */
    public String save(DrugDispensProperty drugDispensProperty);

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
    public DrugDispensProperty get(String id);
}
