package com.jims.sys.entity;
import com.jims.common.persistence.DataEntity;

/**
 * 功能按钮菜单
 * @author yangchen
 * @version 2016-08-17
 */
public class MENU_DICT_BUTTON extends DataEntity<MENU_DICT_BUTTON> {
    private static final long serialVersionUID = 1L;
    private String zid;
    private String mdid;		// 菜单名称
    private String db;		// 链接地址
    private String bn;		// 图标

    public String getZid() {
        return zid;
    }

    public void setZid(String zid) {
        this.zid = zid;
    }

    public MENU_DICT_BUTTON(String mdid, String db, String bn, String zid) {

        this.mdid=mdid;
        this.db=db;
        this.bn=bn;
        this.zid=zid;

    }

    public String getMdid() {
        return mdid;
    }

    public void setMdid(String mdid) {
        this.mdid = mdid;
    }

    public String getDb() {
        return db;
    }

    public void setDb(String db) {
        this.db = db;
    }

    public String getBn() {
        return bn;
    }

    public void setBn(String bn) {
        this.bn = bn;
    }

    public MENU_DICT_BUTTON() {
        super();
    }

    public MENU_DICT_BUTTON(String id){
        super(id);
    }

}
