create table MENU_DICT
(
   ID                   VARCHAR(64)          not null,
   MENU_NAME            VARCHAR(20),
   HREF                 VARCHAR(300),
   ICON                 VARCHAR(100),
   SORT                 NUMBER,
   TARGET               VARCHAR(100),
   REMARKS              VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   CREATE_BY           VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   CREATE_DATE          TIMESTAMP,
   constraint PK_MENU_DICT primary key (ID)
);

comment on table MENU_DICT is
'系统菜单表';

comment on column MENU_DICT.ID is
'主键';

comment on column MENU_DICT.MENU_NAME is
'菜单名称';

comment on column MENU_DICT.HREF is
'链接地址';

comment on column MENU_DICT.ICON is
'图标';

comment on column MENU_DICT.SORT is
'排序';

comment on column MENU_DICT.TARGET is
'打开方式';
