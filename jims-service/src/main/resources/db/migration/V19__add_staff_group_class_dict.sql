-- Create table
create table STAFF_GROUP_CLASS_DICT
(
  GROUP_CLASS VARCHAR2(20),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE,
  ID          VARCHAR2(64) not null,
  ORG_ID      VARCHAR2(64)
);
-- Add comments to the table 
comment on table STAFF_GROUP_CLASS_DICT
  is '用户组分类类别';
-- Add comments to the columns 
comment on column STAFF_GROUP_CLASS_DICT.GROUP_CLASS
  is '组分类名称';
comment on column STAFF_GROUP_CLASS_DICT.ID
  is '主键';
comment on column STAFF_GROUP_CLASS_DICT.ORG_ID
  is '所属组织结构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table STAFF_GROUP_CLASS_DICT
  add constraint STAFF_GROUP_CLASS_DICT_PK primary key (ID);
alter table STAFF_GROUP_CLASS_DICT
  add constraint STAFF_GROUP_CLASS_DICT_UK unique (GROUP_CLASS, ORG_ID);
