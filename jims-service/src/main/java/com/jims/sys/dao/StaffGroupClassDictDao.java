package com.jims.sys.dao;

import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisDao;
import com.jims.sys.entity.StaffGroupClassDict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2016/4/21.
 */
@MyBatisDao
public interface StaffGroupClassDictDao extends CrudDao<StaffGroupClassDict>{
    public List<String> findTypeList(StaffGroupClassDict staffGroupClassDict);



    /**
     * 根据组织机构id查询工作组类的全部信息
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    public List<StaffGroupClassDict> findAllListByOrgId(@Param("orgId")String orgId,@Param("q")String q);
}
