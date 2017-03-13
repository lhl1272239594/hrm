create table ORG_ROLE_VS_SERVICE
(
  id          VARCHAR2(64) not null,
  service_id  VARCHAR2(64) not null,
  role_id     VARCHAR2(64) not null,
  create_by  VARCHAR2(64),
  remark      VARCHAR2(2000),
  update_by   VARCHAR2(64),
  update_date DATE,
  del_flag    VARCHAR2(2),
  create_date DATE
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Add comments to the table
comment on table ORG_ROLE_VS_SERVICE
  is '机构角色对应服务表';
-- Add comments to the columns
comment on column ORG_ROLE_VS_SERVICE.service_id
  is '服务id';
comment on column ORG_ROLE_VS_SERVICE.role_id
  is '角色id';
-- Create/Recreate primary, unique and foreign key constraints
alter table ORG_ROLE_VS_SERVICE
  add constraint PK_ORG_ROLE_VS_SERVICE primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );