package com.jims.sys.api;

import com.jims.sys.entity.SpecimanDict;

import java.util.List;

/**
 * Created by Administrator on 2016/5/4.
 * 标本字典Api接口
 * @author xueyx
 * @version 2016-05-04
 */
public interface SpecimanDictServiceApi {

    /**
     * 查询科室代码下的检验标本
     * @param检验科室编码 deptCode
     * @return
     */
    public List<SpecimanDict> findListByDeptCode(String deptCode);
}
