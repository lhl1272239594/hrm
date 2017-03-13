
--自定义服务参数设置
-- Create table
create table ORG_SERVICE_PARAM
(
  ID           VARCHAR2(64) not null,
  PARAM_NAME   VARCHAR2(100),
  PARAM_VALUE  VARCHAR2(100),
  SERVICE_ID   VARCHAR2(100),
  VALUE_RANGE  VARCHAR2(2000),
  PARM_DESP    VARCHAR2(1000),
  SERVICE_TYPE VARCHAR2(10),
  ORG_ID       varchar2(64)
);
-- Add comments to the table
comment on table ORG_SERVICE_PARAM
  is '系统服务参数设置';
-- Add comments to the columns
comment on column ORG_SERVICE_PARAM.ID
  is '主键';
comment on column ORG_SERVICE_PARAM.PARAM_NAME
  is '参数名称';
comment on column ORG_SERVICE_PARAM.PARAM_VALUE
  is '参数值';
comment on column ORG_SERVICE_PARAM.SERVICE_ID
  is '服务ID';
comment on column ORG_SERVICE_PARAM.VALUE_RANGE
  is '值域';
comment on column ORG_SERVICE_PARAM.PARM_DESP
  is '参数描述';
comment on column ORG_SERVICE_PARAM.SERVICE_TYPE
  is '0系统服务，其他服务';
-- Create/Recreate primary, unique and foreign key constraints
alter table ORG_SERVICE_PARAM
  add constraint ORG_SERVICE_PARAM_PK primary key (ID);
