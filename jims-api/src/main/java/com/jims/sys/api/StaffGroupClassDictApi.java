package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.StaffGroupClassDict;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface StaffGroupClassDictApi {
    /**
     * 查询字段列表
     * @param page
     * @param staffGroupClassDict
     * @return
     */
    public Page<StaffGroupClassDict> findPage(Page<StaffGroupClassDict> page, StaffGroupClassDict staffGroupClassDict);

    /**
     * 保存修改数据
     * @param staffGroupClassDict
     * @return
     */
    public String save(StaffGroupClassDict staffGroupClassDict);

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
    public StaffGroupClassDict get(String id);
}
