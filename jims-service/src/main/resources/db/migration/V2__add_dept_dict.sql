-- Create table
create table DEPT_DICT
(
  id                  VARCHAR2(64) not null,
  parent_id           VARCHAR2(64),
  dept_name           VARCHAR2(100),
  dept_code           VARCHAR2(20),
  dept_propertity     VARCHAR2(100),
  org_id              VARCHAR2(64),
  input_code          VARCHAR2(64),
  remarks             VARCHAR2(2000),
  update_by           VARCHAR2(64),
  create_by           VARCHAR2(64),
  update_date         TIMESTAMP(6),
  del_flag            VARCHAR2(2),
  create_date         TIMESTAMP(6),
  clinic_attr         NUMBER(1),
  outp_or_inp         NUMBER(1),
  internal_or_sergery NUMBER(1)
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
comment on table DEPT_DICT
  is '部门字典表';
-- Add comments to the columns
comment on column DEPT_DICT.id
  is '主键';
comment on column DEPT_DICT.parent_id
  is '父部门';
comment on column DEPT_DICT.dept_name
  is '部门名称';
comment on column DEPT_DICT.dept_code
  is '部门代码';
comment on column DEPT_DICT.dept_propertity
  is '科室属性';
comment on column DEPT_DICT.org_id
  is '组织机构ID';
comment on column DEPT_DICT.input_code
  is '拼音码';
comment on column DEPT_DICT.clinic_attr
  is '临床科室属性';
comment on column DEPT_DICT.outp_or_inp
  is '门诊住院科室属性';
comment on column DEPT_DICT.internal_or_sergery
  is '内外科标志';
-- Create/Recreate primary, unique and foreign key constraints
alter table DEPT_DICT
  add constraint PK_DEPT_DICT primary key (ID);
