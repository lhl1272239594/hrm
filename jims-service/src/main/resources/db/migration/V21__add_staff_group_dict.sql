-- Create table
-- Create table
create table STAFF_GROUP_DICT
(
  group_class_id VARCHAR2(64) not null,
  group_code     VARCHAR2(8) not null,
  group_name     VARCHAR2(20),
  input_code     VARCHAR2(8),
  dept_name      VARCHAR2(20),
  dept_code      VARCHAR2(8),
  group_manager  VARCHAR2(20),
  remarks        VARCHAR2(2000),
  update_by      VARCHAR2(64),
  create_by      VARCHAR2(64),
  update_date    DATE,
  del_flag       VARCHAR2(100),
  create_date    DATE,
  id             VARCHAR2(64) not null,
  dept_id        VARCHAR2(64) not null
);
-- Add comments to the table
comment on table STAFF_GROUP_DICT
  is '用户分组';
-- Add comments to the columns
comment on column STAFF_GROUP_DICT.group_class_id
  is '所属组类';
comment on column STAFF_GROUP_DICT.group_code
  is '组代码，默认为组织机构';
comment on column STAFF_GROUP_DICT.group_name
  is '组名';
comment on column STAFF_GROUP_DICT.input_code
  is '输入码';
comment on column STAFF_GROUP_DICT.id
  is '主键';
comment on column STAFF_GROUP_DICT.dept_id
  is '科室ID';
-- Create/Recreate primary, unique and foreign key constraints
alter table STAFF_GROUP_DICT
  add constraint STAFF_GROUP_DICT_PK primary key (ID)
  using index
  ;
alter table STAFF_GROUP_DICT
  add constraint STAFF_GROUP_DICT_UK unique (GROUP_CLASS_ID, GROUP_CODE)
  using index
 ;
