-- Create table by zhuqi
-- 2016-8-9
create table INIT_PROCESS
(
  id        VARCHAR2(64) not null,
  org_id    VARCHAR2(64),
  init_flag CHAR(1),
  init_sort NUMBER,
  menu_name VARCHAR2(20),
  href      VARCHAR2(300),
  icon      VARCHAR2(100)
)
tablespace USERS
  pctfree 10
  initrans 1
  maxtrans 255;
-- Add comments to the table
comment on table INIT_PROCESS
  is '机构初始化进度表';
-- Add comments to the columns
comment on column INIT_PROCESS.org_id
  is '机构ID';
comment on column INIT_PROCESS.init_flag
  is '初始化标志，1 已初始化，0 未初始化';
comment on column INIT_PROCESS.init_sort
  is '初始化顺序';
comment on column INIT_PROCESS.menu_name
  is '初始化菜单名称';
comment on column INIT_PROCESS.href
  is '菜单路径';
comment on column INIT_PROCESS.icon
  is '图标';
-- Create/Recreate primary, unique and foreign key constraints
alter table INIT_PROCESS
  add constraint PK_INIT_PROCESS primary key (ID)
  using index
  tablespace USERS
  pctfree 10
  initrans 2
  maxtrans 255;
