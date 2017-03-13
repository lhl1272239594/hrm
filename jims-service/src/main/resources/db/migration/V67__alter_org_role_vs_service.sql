/*=============================================================*/
/* Table: ORG_ROLE_VS_SERVICE    机构角色对应服务表            */
/* CREATE_DATE: 2016-07-04                                     */
/* CREATE_BY :  fengyuguang                                    */
/*  添加字段                                                   */
/*=============================================================*/
-- alter table
 ALTER TABLE ORG_ROLE_VS_SERVICE ADD(MENU_ID VARCHAR2(64));
comment on column ORG_ROLE_VS_SERVICE.MENU_ID
  is '菜单ID';

  ALTER TABLE ORG_ROLE_VS_SERVICE ADD(MENU_OPERATE VARCHAR2(10));
comment on column ORG_ROLE_VS_SERVICE.MENU_OPERATE
  is '菜单操作（0，view，1，edit）';

  DROP TABLE ROLE_SERVICE_MENU;
