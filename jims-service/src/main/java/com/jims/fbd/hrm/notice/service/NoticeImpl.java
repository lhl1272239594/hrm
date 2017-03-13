package com.jims.fbd.hrm.notice.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.notice.api.NoticeApi;
import com.jims.fbd.hrm.notice.bo.NoticeBo;
import com.jims.fbd.hrm.notice.dao.NoticeDao;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 公告管理imp层
 * @author
 * @version 2016-9-27
 */
@Service(version = "1.0.0")
public class NoticeImpl extends CrudImplService<NoticeDao,Notice> implements NoticeApi {
    @Autowired
    private NoticeBo noticeBo;

    /**
     * 保存修改
     * @return
     * @author 
     * @version 2016-09-27
     */
    @Override
    public String merge(Notice notice, String userName, String createDept) {
        return noticeBo.merge(notice,userName,createDept);
    }
   /**
     * 删除
     * @param notices
     * @return
     * @author 
     * @version 2016-09-27
     */
    @Override
    public String del_notice(List<Notice> notices) {
        return noticeBo.delete(notices);
    }
    /**
     * 发布
     * @param notices
     * @return
     * @author 
     * @version 2016-08-22
     */
    @Override
    public String publish_notice(List<Notice> notices, String userName) {
        return noticeBo.publish_notice(notices,userName);
    }

    /**
     * 查询公告信息
     * @return
     * @author 
     */
    @Override
    public Page<Notice> noticeList(Page<Notice> page, Notice notice) {
        return noticeBo.noticeList(page,notice);
    }
    /**
     * 查询我的公告信息
     * @return
     * @author 
     */
    @Override
    public Page<Notice> mynoticeList(Page<Notice> page, Notice notice) {
        return noticeBo.mynoticeList(page,notice);
    }
    /**
     * 查看授权人员
     * @return
     */
    @Override
    public List<PersonVo> getPersonById(String id) {
        return noticeBo.getPersonById(id);
    }


}
