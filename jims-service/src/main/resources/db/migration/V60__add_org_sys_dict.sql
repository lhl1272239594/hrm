-- 机构系统字典表
create table ORG_SYS_DICT
(
  id          VARCHAR2(64),
  label       VARCHAR2(100) not null,
  value       VARCHAR2(20) not null,
  type        VARCHAR2(100) not null,
  description VARCHAR2(100),
  input_code  VARCHAR2(8),
  org_id      VARCHAR2(64)
);
-- Add comments to the table
comment on table ORG_SYS_DICT
  is '机构系统字典表';
-- Add comments to the columns 
comment on column ORG_SYS_DICT.label
  is '标签名';
comment on column ORG_SYS_DICT.value
  is '数据值';
comment on column ORG_SYS_DICT.type
  is '类型';
comment on column ORG_SYS_DICT.description
  is '描述';
comment on column ORG_SYS_DICT.org_id
  is '所属机构ID';

alter table ORG_SYS_DICT
  add constraint PK_ORG_SYS_DICT primary key (ID);

