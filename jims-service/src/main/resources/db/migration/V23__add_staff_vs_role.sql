-- Create table
create table STAFF_VS_ROLE
(
  ID          VARCHAR2(64) not null,
  ROLE_ID     VARCHAR2(64),
  STAFF_ID    VARCHAR2(64),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE
);
-- Add comments to the table
comment on table STAFF_VS_ROLE
  is '员工与角色';
-- Add comments to the columns
comment on column STAFF_VS_ROLE.ID
  is '主键';
comment on column STAFF_VS_ROLE.ROLE_ID
  is '角色';
comment on column STAFF_VS_ROLE.STAFF_ID
  is '所属用户';
-- Create/Recreate primary, unique and foreign key constraints
alter table STAFF_VS_ROLE
  add constraint STAFF_VS_ROLE_PK primary key (ID);
