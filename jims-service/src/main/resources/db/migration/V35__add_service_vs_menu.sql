/*==============================================================*/
/* Table: service_vs_menu  服务价格明细表                       */
/*CREATE_BY: txb	                                              */
/*==============================================================*/
create table SERVICE_VS_MENU
(
   ID                VARCHAR2(64)         not null,
   SERVICE_ID         VARCHAR2(64),
   MENU_ID          VARCHAR2(64),
   MENU_SORT        VARCHAR2(10),
   CREATE_BY           VARCHAR2(64),
   REMARKS               VARCHAR2(2000),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          DATE,
   DEL_FLAG             VARCHAR2(2),
   CREATE_DATE          DATE,
   constraint PK_SERVICE_VS_MENU primary key (ID)
);

comment on table SERVICE_VS_MENU is
'服务对应菜单表';

comment on column SERVICE_VS_MENU.SERVICE_ID is
'服务id';

comment on column SERVICE_VS_MENU.MENU_ID is
'菜单id';

comment on column SERVICE_VS_MENU.MENU_SORT is
'菜单排序';