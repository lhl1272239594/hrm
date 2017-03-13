package com.jims.sys.api;

import com.jims.common.persistence.Page;
import com.jims.sys.entity.StaffGroupClassDict;
import com.jims.sys.entity.StaffGroupDict;
import com.jims.sys.vo.StaffGroupVo;

import java.util.List;

/**
 * Created by Administrator on 2016/4/18.
 */
public interface StaffGroupApi {
    /**
     * 保存  增删改   工作组类
     * @param staffGroupClassDictVo
     * @return
     *  @author  yangruidong
     */
    public List<StaffGroupClassDict> saveGroupClass(StaffGroupVo<StaffGroupClassDict> staffGroupClassDictVo);

    /**
     * 保存  增删改   工作组
     * @param staffGroupDictVo
     * @return
     *  @author  yangruidong
     */
    public List<StaffGroupDict> saveGroup(StaffGroupVo<StaffGroupDict> staffGroupDictVo);

    /**
     * 根据组织机构id查询工作组类的全部信息
     * @param orgId 组织机构id
     * @return
     * @author yangruidong
     */
    public List<StaffGroupClassDict> findAllListByOrgId(String orgId,String q);

    /**
     * 根据工作组类的id查询工作组的信息
     * @param id
     * @return
     * @author yangruidong
     */
    public List<StaffGroupDict> findListGroupDict(String id,String q);


}
