
/*==============================================================*/
/* Table:INPUT_SETTING_MASTER   收入法主记录                     */
/* Table:INPUT_SETTING_MASTER   收入法主记录                     */
/* CREATE_DATE: 2016-05-14                                      */
/* CREATE_BY :  ztq                                             */
/*==============================================================*/
-- Create table
create table INPUT_SETTING_MASTER
(
  DICT_NAME   VARCHAR2(100),
  DICT_TYPE   VARCHAR2(100),
  ORG_ID      VARCHAR2(64),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE,
  ID          VARCHAR2(64) not null
);
-- Add comments to the table
comment on table INPUT_SETTING_MASTER
  is '输入字典主记录';
-- Add comments to the columns
comment on column INPUT_SETTING_MASTER.DICT_NAME
  is '收入字典名称';
comment on column INPUT_SETTING_MASTER.DICT_TYPE
  is '收入字典的表或者视图';
comment on column INPUT_SETTING_MASTER.ORG_ID
  is '组织机构';
comment on column INPUT_SETTING_MASTER.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table INPUT_SETTING_MASTER
  add constraint PK_INPUT_SETTING_MASTER primary key (ID);
alter table INPUT_SETTING_MASTER
  add constraint UK_INPUT_SETTING_MASTER unique (DICT_TYPE, DICT_NAME, ORG_ID);



/*==============================================================*/
/* Table:INPUT_SETTING_DETAIL   收入法明细设置                    */
/* Table:INPUT_SETTING_DETAIL   收入法明细设置                   */
/* CREATE_DATE: 2016-05-14                                      */
/* CREATE_BY :  ztq                                             */
/*==============================================================*/
-- Create table
create table INPUT_SETTING_DETAIL
(
  DATA_COL                VARCHAR2(100) not null,
  DATA_TITLE              VARCHAR2(100) not null,
  FLAG_SHOW               VARCHAR2(2) default 'Y' not null,
  SHOW_SORT               NUMBER not null,
  FLAG_ISNAME             VARCHAR2(2) default 'N' not null,
  RESULT_SORT             VARCHAR2(2) default 0 not null,
  SHOW_WIDTH              NUMBER(4),
  INPUT_SETTING_MASTER_ID VARCHAR2(64),
  INPUT_CODE              VARCHAR2(10),
  REMARKS                 VARCHAR2(2000),
  UPDATE_BY               VARCHAR2(64),
  CREATE_BY               VARCHAR2(64),
  UPDATE_DATE             DATE,
  DEL_FLAG                VARCHAR2(100),
  CREATE_DATE             DATE,
  ID                      VARCHAR2(64) not null
);
-- Add comments to the table
comment on table INPUT_SETTING_DETAIL
  is '输入法明细设置';
-- Add comments to the columns
comment on column INPUT_SETTING_DETAIL.DATA_COL
  is '列名称';
comment on column INPUT_SETTING_DETAIL.DATA_TITLE
  is '显示表头';
comment on column INPUT_SETTING_DETAIL.FLAG_SHOW
  is '是否显示';
comment on column INPUT_SETTING_DETAIL.SHOW_SORT
  is '显示顺序';
comment on column INPUT_SETTING_DETAIL.FLAG_ISNAME
  is '是否字段名称';
comment on column INPUT_SETTING_DETAIL.RESULT_SORT
  is '结果顺序';
comment on column INPUT_SETTING_DETAIL.SHOW_WIDTH
  is '显示宽度';
comment on column INPUT_SETTING_DETAIL.INPUT_CODE
  is '输入码类型';
comment on column INPUT_SETTING_DETAIL.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table INPUT_SETTING_DETAIL
  add constraint PK_INPUT_SETTING_DETAIL primary key (ID);
alter table INPUT_SETTING_DETAIL
  add constraint UK_INPUT_SETTING_DETAIL unique (DATA_COL, INPUT_SETTING_MASTER_ID);
alter table INPUT_SETTING_DETAIL
  add constraint FK_INPUT_SETTING_DETAIL foreign key (INPUT_SETTING_MASTER_ID)
  references INPUT_SETTING_MASTER (ID);
