-- Create table
create table ORG_SERVICE_LIST
(
  ID                 VARCHAR2(64) not null,
  SERVICE_ID         VARCHAR2(64),
  SERVICE_START_DATE DATE,
  SERVICE_END_DATE   DATE,
  ORG_ID             VARCHAR2(64),
  REMARKS            VARCHAR2(2000),
  UPDATE_BY          VARCHAR2(64),
  CREATE_BY          VARCHAR2(64),
  UPDATE_DATE        DATE,
  DEL_FLAG           VARCHAR2(100),
  CREATE_DATE        DATE
);
-- Add comments to the table
comment on table ORG_SERVICE_LIST
  is '机构服务列表';
-- Add comments to the columns
comment on column ORG_SERVICE_LIST.ID
  is '主键';
comment on column ORG_SERVICE_LIST.SERVICE_ID
  is '所购买的服务';
comment on column ORG_SERVICE_LIST.SERVICE_START_DATE
  is '服务启用日期';
comment on column ORG_SERVICE_LIST.SERVICE_END_DATE
  is '服务停用日期';
comment on column ORG_SERVICE_LIST.ORG_ID
  is '购买服务单位';
-- Create/Recreate primary, unique and foreign key constraints
alter table ORG_SERVICE_LIST
  add constraint ORG_SERVICE_LIST_PK primary key (ID);
alter table ORG_SERVICE_LIST
  add constraint ORG_SERVICE_LIST_UK unique (SERVICE_ID, ORG_ID);
