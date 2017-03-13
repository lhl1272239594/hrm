-- Create table
create table ORG_ROLE
(
  ID          VARCHAR2(64) not null,
  ORG_ID      VARCHAR2(64),
  ROLE_NAME   VARCHAR2(100),
  REMARKS     VARCHAR2(2000),
  UPDATE_BY   VARCHAR2(64),
  CREATE_BY   VARCHAR2(64),
  UPDATE_DATE DATE,
  DEL_FLAG    VARCHAR2(100),
  CREATE_DATE DATE
);
-- Add comments to the table
comment on table ORG_ROLE
  is '组织机构角色';
-- Add comments to the columns
comment on column ORG_ROLE.ID
  is '主键';
comment on column ORG_ROLE.ORG_ID
  is '所属组织机构';
comment on column ORG_ROLE.ROLE_NAME
  is '角色名称';
-- Create/Recreate primary, unique and foreign key constraints
alter table ORG_ROLE
  add constraint ORG_ROLE_PK primary key (ID);
alter table ORG_ROLE
  add constraint ORG_ROLE_UK unique (ORG_ID, ROLE_NAME);
