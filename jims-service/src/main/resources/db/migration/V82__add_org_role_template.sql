-- Create table
create table ORG_ROLE_TEMPLATE
(
  id          VARCHAR2(64) not null,
  role_name   VARCHAR2(100),
  master_id   VARCHAR2(64)
);
-- Add comments to the table 
comment on table ORG_ROLE_TEMPLATE
  is '组织机构角色模板';
-- Add comments to the columns 
comment on column ORG_ROLE_TEMPLATE.id
  is '主键';
comment on column ORG_ROLE_TEMPLATE.role_name
  is '角色名称';
comment on column ORG_ROLE_TEMPLATE.master_id
  is '模板信息表ID';
-- Create/Recreate primary, unique and foreign key constraints 
alter table ORG_ROLE_TEMPLATE
  add constraint ORG_ROLE_TEMPLATE_PK primary key (ID);
alter table ORG_ROLE_TEMPLATE
  add constraint ORG_ROLE_TEMPLATE_UK unique ( MASTER_ID,ROLE_NAME);