package com.jims.fbd.hrm.notice.api;


import com.jims.common.persistence.Page;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.fbd.hrm.tool.vo.PersonVo;

import java.util.List;

/**
 * 公告管理api层
 * @author 
 * @version 2016-09-22
 */
public interface NoticeApi {

    /**
     * 保存或修改
     * @author 
     * @version 2016-09-27
     * @return
     */
    String merge(Notice notice, String userName, String createDept);
    /**
     * 删除
     * @param notices
     * @return
     * @author 
     * @version 2016-09-27
     */
    String del_notice(List<Notice> notices);
    /**
     * 发布
     * @param userName
     * @return
     * @author 
     * @version 2016-08-31
     */
    String publish_notice(List<Notice> notices, String userName);
    /**
     * 查询
     * @return
     * @author 
     * @version 2016-09-27
     */
    Page<Notice> noticeList(Page<Notice> page, Notice notice);
    /**
     * 查询我的公告信息
     * @return
     * @author 
     * @version 2016-09-27
     */
    Page<Notice> mynoticeList(Page<Notice> page, Notice notice);
    /**
     * 查看授权人员
     * @return
     */
    List<PersonVo> getPersonById(String id);

}
