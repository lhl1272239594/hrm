package com.jims.fbd.hrm.notice.dao;


import com.jims.common.persistence.CrudDao;
import com.jims.common.persistence.annotation.MyBatisHrmDao;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 公告管理DAO接口
 * @author 
 * @version 2016-09-22
 */
@MyBatisHrmDao
public interface NoticeDao extends CrudDao<Notice> {

    /**
     * 查询公告
     * @return
     * @author 
     * @version 2016-09-22
     */
    public List<Notice> noticeList(Notice notice);
    /**
     * 查询我的公告
     * @return
     * @author 
     * @version 2016-09-22
     */
    public List<Notice> mynoticeList(Notice notice);
    /**
     * 批量删除
     * @param notice
     * @return
     */
    public void deleteNotice(@Param("Notice") Notice notice);
    /**
     * 发布公告
     * @param notice
     * @return
     */
    public void publish_notice(@Param("Notice") Notice notice, @Param("userName") String userName);
    /**
     * 修改
     * @param notice
     * @return
     */
    public void updateNotice(@Param("Notice") Notice notice);
    /**
     * 新增
     * @param notice
     * @return
     */
    public void insertNotice(@Param("Notice") Notice notice);
    /**
     * 添加接收人
     * @return
     */
    public void insertPerson(@Param("PersonVo") PersonVo PersonVo);
    /**
     * 删除接收人
     * @param notice
     * @return
     */
    public void deletePerson(@Param("Notice") Notice notice);
    /**
     * 查看授权人员
     * @return
     */
    public List<PersonVo> getPersonById(String id);

}