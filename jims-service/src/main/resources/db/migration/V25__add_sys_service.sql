-- Create table
create table SYS_SERVICE
(
  ID                  VARCHAR2(64) not null,
  SERVICE_NAME        VARCHAR2(100),
  SERVICE_DESCRIPTION VARCHAR2(2048),
  SERVICE_TYPE        VARCHAR2(2),
  SERVICE_CLASS        VARCHAR2(2),
  SERVICE_IMAGE       VARCHAR2(2000),
  REMARKS             VARCHAR2(2000),
  UPDATE_BY           VARCHAR2(64),
  CREATE_BY           VARCHAR2(64),
  UPDATE_DATE         DATE,
  DEL_FLAG            VARCHAR2(100),
  CREATE_DATE         DATE
);
-- Add comments to the table
comment on table SYS_SERVICE
  is '系统服务';
-- Add comments to the columns
comment on column SYS_SERVICE.ID
  is '系统服务';
comment on column SYS_SERVICE.SERVICE_NAME
  is '系统服务名称';
comment on column SYS_SERVICE.SERVICE_DESCRIPTION
  is '服务描述';
comment on column SYS_SERVICE.SERVICE_CLASS
  is '3,机构管理服务，2,所有服务，1,个人服务，0机构服务';
comment on column SYS_SERVICE.SERVICE_TYPE
  is '1,有偿服务,0无偿服务';
comment on column SYS_SERVICE.SERVICE_IMAGE
  is '服务图片';
-- Create/Recreate primary, unique and foreign key constraints
alter table SYS_SERVICE
  add constraint SYS_SERVICE_PK primary key (ID);
alter table SYS_SERVICE
  add constraint SYS_SERVICE_UK unique (SERVICE_NAME);
