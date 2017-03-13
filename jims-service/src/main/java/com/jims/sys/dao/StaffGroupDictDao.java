package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by yangruidong on 2016/5/23.
 */
@MyBatisDao
public interface StaffGroupDictDao extends CrudDao<StaffGroupDict>{
    /**
     * 根据工作组类的id查询工作组的信息
     * @param id
     * @return
     * @author yangruidong
     */
    public List<StaffGroupDict> findListGroupDict(@Param("id")String id,@Param("q")String q) ;
}
