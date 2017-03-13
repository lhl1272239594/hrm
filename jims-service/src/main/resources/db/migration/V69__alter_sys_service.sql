--created by chenxy
--修改系统服务表的服务描述字段类型为Blob
-- Add/modify columns

--删除原表
drop table sys_service;

-- Create table--创建新表（注意：新表名与原表名同，新表的service_description字段是blob类型，原表中是varchar2）
create table SYS_SERVICE
(
  id                  VARCHAR2(64) not null,
  service_name        VARCHAR2(100),
  service_type        VARCHAR2(2),
  service_class       VARCHAR2(2),
  service_image       VARCHAR2(2000),
  remarks             VARCHAR2(2000),
  update_by           VARCHAR2(64),
  create_by           VARCHAR2(64),
  update_date         DATE,
  del_flag            VARCHAR2(100),
  create_date         DATE,
  service_description BLOB
);

-- Add comments to the table
comment on table SYS_SERVICE
  is '系统服务';
-- Add comments to the columns
comment on column SYS_SERVICE.id
  is '系统服务';
comment on column SYS_SERVICE.service_name
  is '系统服务名称';
comment on column SYS_SERVICE.service_type
  is '1,有偿服务,0无偿服务';
comment on column SYS_SERVICE.service_class
  is '3,机构管理服务，2,所有服务，1,个人服务，0机构服务';
comment on column SYS_SERVICE.service_image
  is '服务图片';
-- Create/Recreate primary, unique and foreign key constraints

--下面是创建主键和附键
alter table SYS_SERVICE
  add constraint SYS_SERVICE_PK primary key (ID);
alter table SYS_SERVICE
  add constraint SYS_SERVICE_UK unique (SERVICE_NAME);