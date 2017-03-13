/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.jims.sys.bo;

import com.jims.common.service.impl.CrudImplService;
import com.jims.common.utils.StringUtils;
import com.jims.common.web.impl.BaseDto;
import com.jims.sys.dao.DeptDictDao;
import com.jims.sys.dao.OrgDeptPropertyDictDao;
import com.jims.sys.dao.OrgRoleDao;
import com.jims.sys.entity.DeptDict;
import com.jims.sys.entity.OrgDeptPropertyDict;
import com.jims.sys.entity.OrgRole;
import com.jims.sys.vo.BeanChangeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *
 * @author   yangruidong
 * @version 2016-06-16
 */
@Service
@Component
@Transactional(readOnly = false)
public class DeptDictBo extends CrudImplService<DeptDictDao, DeptDict> {

    @Autowired
    private DeptDictDao  deptDictDao;

    @Autowired
    private OrgDeptPropertyDictDao orgDeptPropertyDictDao;

    /**
     * 查询所有的科室信息
     * @return
     */
    public List<DeptDict> findAllList(String orgId) {

        //查询出所有的科室信息
        List<DeptDict> list = deptDictDao.findAll(orgId);

        //查询出所有的科室属性的类型
        List<OrgDeptPropertyDict> listProperty = orgDeptPropertyDictDao.findProperty(orgId);
        if (listProperty.size() > 0) {
            //遍历所有的科室信息
            for (int i = 0; i < list.size(); i++) {
                StringBuilder sb = new StringBuilder();
                if(list.get(i).getDeptPropertity()!=null){ //得到每一个对象的科室属性，以；进行切割
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
                    list.get(i).setDeptPropertityName(sb.toString());}

            }
        }
        return list;
    }

    /**
     *查询某个科室
     * @param orgId
     * @return
     */
    public List<DeptDict>  getByOrgId(String orgId){
        return deptDictDao.getByOrgId(orgId);
    }

    /**
     * 查询所有的科室属性的类型
     * @return
     */
    public List<DeptDict> findProperty() {
        return deptDictDao.findProperty();
    }

    /**
     * 查询所有的上级科室
     * @return
     */
    public List<DeptDict> findParent() {
        return deptDictDao.findParent();
    }

    /**
     * 查询某个机构的上级科室
     * @return
     */
    public List<DeptDict> findListParent(String orgId,String q,String deptCode){
        return deptDictDao.findListParent(orgId,q,deptCode) ;
    }

    /**
     * 查询科室代码下的所以科室
     * @return
     */
    public List<DeptDict> findListByCode(String code){
        return deptDictDao.findListByCode(code);
    }

    /**
     * 查询所有科室
     * @return
     */
    public List<DeptDict> getList(){
        return deptDictDao.getList();
    }

    /**
     * 手术科室
     * @return
     */
    public List<DeptDict> getOperation(String orgId){
        return deptDictDao.getOperation(orgId);
    }

    /**
     * 获取医生科室
     * @param orgId
     * @param persionId
     * @return
     */
    public List<DeptDict> getDoctorDept(String orgId,String persionId,String doctorGroup) {
        return deptDictDao.getDoctorDept(orgId,persionId,doctorGroup);
    }

    /**
     * 获取科室医生
     * @param orgId
     * @param doctorGroup
     * @param deptId
     * @return
     */
    public List<BaseDto> getDoctor(String orgId, String doctorGroup, String deptId) {
        return deptDictDao.getDoctor(orgId,doctorGroup,deptId);
    }
    /**
     * 根据部门名称和组织机构ID查询指定组织机构的部门信息
     * @param deptName 部门名称
     * @param orgId 组织机构ID
     * @return
     * @author fengyuguang
     */
    public List<DeptDict> getByNameOrgId(String deptName,String orgId){
        return deptDictDao.getByNameOrgId(deptName,orgId);
    }



    /**
     * 查询病房
     * @param orgId
     * @param q
     * @return
     */
    public List<DeptDict> findListOfInpatients(String orgId,String q){
        return  deptDictDao.findListOfInpatients(orgId,q);
    };

    /**
     * 查询当前登陆人所属科室
     * @param orgId
     * @param personId
     * @return
     */
    public List<DeptDict> findByPersonId(String orgId, String personId) {
        return deptDictDao.findByPersonId(orgId,personId);
    }

    /**
     *根据id查询科室编码
     * @param
     * @param
     * @return
     */
    public DeptDict findDeptCode(String id){
        return deptDictDao.get(id);
    }
}