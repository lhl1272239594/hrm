-- Create table
create table STAFF_VS_GROUP
(
  GROUP_CLASS VARCHAR2(64) not null,
  GROUP_CODE  VARCHAR2(8) not null,
  STAFF_ID    VARCHAR2(64) not null,
  GROUP_ID    VARCHAR2(64),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE,
  ID          VARCHAR2(64) not null
);
-- Add comments to the table
comment on table STAFF_VS_GROUP
  is '用户分组设置';
-- Add comments to the columns
comment on column STAFF_VS_GROUP.GROUP_CLASS
  is '组类';
comment on column STAFF_VS_GROUP.GROUP_CODE
  is '组代码';
comment on column STAFF_VS_GROUP.STAFF_ID
  is '人员';
comment on column STAFF_VS_GROUP.GROUP_ID
  is '用户分组';
comment on column STAFF_VS_GROUP.ID
  is '主键';
-- Create/Recreate primary, unique and foreign key constraints
alter table STAFF_VS_GROUP
  add constraint STAFF_VS_GROUP__PK primary key (ID);
alter table STAFF_VS_GROUP
  add constraint STAFF_VS_GROUP__UK unique (GROUP_CLASS, GROUP_CODE, STAFF_ID, GROUP_ID);
