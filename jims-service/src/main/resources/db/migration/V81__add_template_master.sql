-- Create table
create table TEMPLATE_MASTER
(
  id         VARCHAR2(64) not null,
  table_name VARCHAR2(64),
  area       VARCHAR2(10),
  area_name  VARCHAR2(100),
  details    VARCHAR2(2000),
  template_name VARCHAR2(500)
);
-- Add comments to the table 
comment on table TEMPLATE_MASTER
  is '模板信息表';
-- Add comments to the columns 
comment on column TEMPLATE_MASTER.table_name
  is '模板表名称';
comment on column TEMPLATE_MASTER.area
  is '模板区域';
comment on column TEMPLATE_MASTER.area_name
  is '模板区域名称';
comment on column TEMPLATE_MASTER.details
  is '描述';
comment on column TEMPLATE_MASTER.template_name
  is '模板名称';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TEMPLATE_MASTER
  add constraint PK_TEMPLATE_MASTER primary key (ID);