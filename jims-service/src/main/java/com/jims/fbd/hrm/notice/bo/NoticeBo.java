
package com.jims.fbd.hrm.notice.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.fbd.hrm.notice.dao.NoticeDao;
import com.jims.fbd.hrm.notice.entity.Notice;
import com.jims.fbd.hrm.tool.vo.PersonVo;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公告管理BAO层
 * @author 
 * @version 2016-09-22
 */
@Service
@Component
@Transactional(readOnly = false)
public class NoticeBo extends CrudImplService<NoticeDao, Notice> {
    /**
     * 查询
     * @return
     * @author 
     * @version 2016-09-22
     */
    public Page<Notice> noticeList(Page<Notice> page, Notice notice) {
        notice.setPage(page);
        page.setList(dao.noticeList(notice));
        return page;
    }
    /**
     * 查询我的公告信息
     * @return
     * @author 
     * @version 2016-09-22
     */
    public Page<Notice> mynoticeList(Page<Notice> page, Notice notice) {
        notice.setPage(page);
        page.setList(dao.mynoticeList(notice));
        return page;
    }
    /**
     *新增修改保存
     * @return
     * @author 
     */
    public String merge(Notice notice, String userName, String createDept) {
        //判断主键id是否为空，不为空则修改
        if (org.apache.commons.lang3.StringUtils.isNotBlank(notice.getNoticeId())){
            notice.preUpdate();
            notice.setUpdateBy(userName);
            notice.setCreateDept(createDept);
            dao.updateNotice(notice);
            dao.deletePerson(notice);//修改接收人，先删除后插入
            List<PersonVo> personVos=notice.getPersonVos();
            for(PersonVo o:personVos){
                o.preInsert();
                o.setDataId(notice.getNoticeId());
                o.setOrgId(notice.getOrgId());
                dao.insertPerson(o);
            }
            return "edit";
        }
        else{
            notice.preInsert();
            notice.setCreateBy(userName);
            notice.setNoticeId(notice.getId());
            notice.setCreateDept(createDept);
            dao.insertNotice(notice);
            List<PersonVo> personVos=notice.getPersonVos();
            for(PersonVo o:personVos){
                o.preInsert();
                o.setDataId(notice.getNoticeId());
                o.setOrgId(notice.getOrgId());
                dao.insertPerson(o);
            }
            return "add";
        }
    }
    /**
     * 删除数据
     * @param notices
     * @return
     * @author 
     */
    public String delete(List<Notice> notices) {
        for (Notice q : notices) {
            q.preUpdate();
            dao.deleteNotice(q);
        }
        return "sucsess";
    }
    /**
     * 发布公告
     * @param notices
     * @return
     * @author 
     */
    public String publish_notice(List<Notice> notices, String userName) {
        for (Notice q : notices) {
            q.preUpdate();
            dao.publish_notice(q,userName);
        }
        return "sucsess";
    }
    /**
     * 查看授权人员
     * @return
     */
    public List<PersonVo> getPersonById(String id) {
        return dao.getPersonById(id);
    }
}