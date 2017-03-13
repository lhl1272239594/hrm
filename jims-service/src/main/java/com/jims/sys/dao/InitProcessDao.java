package com.jims.sys.dao;

/**
 * Created by zhu on 2016/8/1.
 */

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.InitProcess;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 菜单顺序
 * @author zq
 * @version 2016-8-9
 */
@MyBatisDao
public interface InitProcessDao extends CrudDao<InitProcess> {

    /**
     * 根据orgId查询
     * @param orgId
     * @return
     * @author  zq
     */
    public List<InitProcess> findByOrgId (@Param("orgId") String orgId);
    /**
     * 根据orgId查询可以插入的向导数据
     * @param orgId
     * @return
     * @author  zq
     */
    public List<InitProcess> findInitProcessByOrgId (@Param("orgId") String orgId);

    /**
     * 根据菜单表数据生成initProcess表数据
     * @author zq
     */
    public List<InitProcess> findMenuList ();

}
