package com.jims.sys.api;

import com.jims.sys.entity.DrugTypeDict;

import java.util.List;

/**
 * Created by zq on 2016/5/14.
 */
public interface DrugTypeDictApi {

    /**
     * 查询全部
     *zhuQi
     * @return
     */
    public List<DrugTypeDict> findAllList();

}
