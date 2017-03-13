
package com.jims.fbd.hrm.salary.bo;

import com.jims.common.persistence.Page;
import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.fbd.hrm.salary.dao.SalaryDataDao;
import com.jims.fbd.hrm.salary.entity.SalaryData;
import com.jims.fbd.hrm.salary.vo.SalaryDataPersonVo;
import com.jims.sys.dao.OrgDeptPropertyDictDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 薪资档案BO层
 * @author
 * @version 2016-08-31
 */
@Service
@Component
@Transactional(readOnly = false)
public class SalaryDataBo extends CrudImplService<SalaryDataDao, SalaryData> {

    @Autowired
    private SalaryDataDao salaryDataDao;
    @Autowired
    private OrgDeptPropertyDictDao orgDeptPropertyDictDao;

    /**
     * 人员树形列表查询
     * @param page
     * @param salaryData
     * @return
     */
    public Page<SalaryData> findPageByVo(Page<SalaryData> page, SalaryData salaryData) {
        salaryData.setPage(page);
        page.setList(dao.findListByVo(salaryData));
        return page;
    }
    /**
     * 查询薪资档案数据
     * @return
     * @author
     * @version 2016-08-22
     */
    public Page<SalaryData> dataList(Page<SalaryData> page, SalaryData salaryData) {
        salaryData.setPage(page);
        page.setList(dao.dataList(salaryData));
        return page;
    }
    /**
     * 查询职业信息数据
     * @return
     * @author
     * @version 2016-09-29
     */
    public Page<SalaryData> careerList(Page<SalaryData> page, SalaryData salaryData) {
        salaryData.setPage(page);
        page.setList(dao.careerList(salaryData));
        return page;
    }
    /**
     * 查询职业变动报表
     * @return
     * @author
     * @version 2016-08-22
     */
    public Page<SalaryData> careerSearch(Page<SalaryData> page, SalaryData salaryData) {
        salaryData.setPage(page);
        page.setList(dao.careerSearch(salaryData));
        return page;
    }
    /**
     * 查询职业历史信息数据
     * @return
     * @author
     * @version 2016-09-29
     */
    public Page<SalaryData> careerListAll(Page<SalaryData> page, SalaryData salaryData) {
        salaryData.setPage(page);
        page.setList(dao.careerListAll(salaryData));
        return page;
    }
    /**
     * 新增薪资档案
     * @param loop
     * @return
     */
    public String merge(List<SalaryDataPersonVo> loop) {
        for(SalaryDataPersonVo o:loop){
            //o.preInsert();
            dao.deleteLoop(o);
        }
        for(SalaryDataPersonVo o:loop){
            o.preInsert();
            dao.insertLoop(o);
        }
        return "success";
    }
    /**
     *职业信息新增修改保存
     * @return
     * @author
     */
    public String careerMerge(SalaryData salaryData, String userName) {
        if (org.apache.commons.lang3.StringUtils.isNotBlank(salaryData.getCareerId())){
            salaryData.preUpdate();
            salaryData.setUpdateBy(userName);
            dao.updateCareer(salaryData);
            return "edit";
        }
        else{
            salaryData.preInsert();
            salaryData.setCareerId(salaryData.getCareerId());
            salaryData.setCreateBy(userName);
            salaryData.setUpdateBy(userName);
            dao.insertCareer(salaryData);
            return "add";
        }
    }
    /**
     * 起薪停薪
     * @param  datas
     * @return
     */
    public String enableFlag(List<SalaryData> datas) {
        for (SalaryData q : datas) {
            q.preUpdate();
            dao.enableFlag(q);
        }
        return "sucsess";
    }
    /**
     * 变更类别
     * @param  datas
     * @return
     */
    public String change(List<SalaryData> datas, String personId, String createDept) {

       for (SalaryData q : datas) {
            dao.deleteChange(q);//变更之前先删除
        }
        for (SalaryData q : datas) {
            q.setUpdateBy(personId);
            q.setCreateDept(createDept);
            q.preUpdate();
            dao.change(q);//插入数据
            dao.updateLevel(q);//同步更新person_info表中人员工资级别信息
        }
        return "sucsess";
    }

    /**
     * 删除职业信息
     * @param salaryDatas
     * @return
     * @author
     */
    public String deleteCareer(List<SalaryData> salaryDatas) {
        for (SalaryData q : salaryDatas) {
            q.preUpdate();
            dao.deleteCareer(q);
        }
        return "sucsess";
    }
    /**
     * 查询科室信息
     * @return
     */
    public List<DeptDict> findAllList(String orgId, String deptIds) {
        //查询出所有的科室信息
        List<DeptDict> list = dao.findAll(orgId,deptIds);
        //查询出所有的科室属性的类型
        List<OrgDeptPropertyDict> listProperty = orgDeptPropertyDictDao.findProperty(orgId);
        if (listProperty.size() > 0) {
            //遍历所有的科室信息
            for (int i = 0; i < list.size(); i++) {
                StringBuilder sb = new StringBuilder();
                //得到每一个对象的科室属性，以；进行切割
                String[] str = list.get(i).getDeptPropertity().split(";");
                //遍历获得的数组
                for (int y = 0; y < str.length; y++) {
                    //得到每一个切割后的科室属性值
                    if (StringUtils.isNotBlank(str[y])) {
                        //拿科室属性值和科室的类型去数据库中查询科室属性名称
                        OrgDeptPropertyDict orgDeptPropertyDict=new OrgDeptPropertyDict();
                        orgDeptPropertyDict.setPropertyType(listProperty.get(y).getPropertyType());
                        orgDeptPropertyDict.setOrgId(orgId);
                        orgDeptPropertyDict.setPropertyValue(str[y]);
                        List<OrgDeptPropertyDict> listName = orgDeptPropertyDictDao.findByCondition(orgDeptPropertyDict);
                        if (listName.get(0) == null) {
                            sb.append(" ");
                        } else {
                            sb.append(listName.get(0).getPropertyName() + " ");
                        }
                    }
                }
                list.get(i).setDeptPropertityName(sb.toString());
            }
        }
        return list;
    }
}