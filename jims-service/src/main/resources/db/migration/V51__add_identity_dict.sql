
--身份字典表
-- Create table
create table IDENTITY_DICT
(
  id          varchar(64),
  IDENTITY_CODE      VARCHAR2(1),
  IDENTITY_NAME      VARCHAR2(10) not null,
  INPUT_CODE         VARCHAR2(8),
  PRIORITY_INDICATOR NUMBER(1),
  MILITARY_INDICATOR NUMBER(1),
  INPUT_CODE_WB      VARCHAR2(8),
  org_id             varchar2(64)
);
-- Add comments to the table
comment on table IDENTITY_DICT
  is '身份字典';
-- Add comments to the columns
comment on column IDENTITY_DICT.id
  is '序号';
comment on column IDENTITY_DICT.IDENTITY_CODE
  is '身份代码';
comment on column IDENTITY_DICT.IDENTITY_NAME
  is '身份名称';
comment on column IDENTITY_DICT.INPUT_CODE
  is '输入码';
comment on column IDENTITY_DICT.PRIORITY_INDICATOR
  is '优先标志(1优先，0不优先)';
comment on column IDENTITY_DICT.MILITARY_INDICATOR
  is '军人标志(1是，0否)';
comment on column IDENTITY_DICT.INPUT_CODE_WB
  is '五笔码';
-- Create/Recreate primary, unique and foreign key constraints
alter table IDENTITY_DICT
  add constraint PK_IDENTITY_DICT primary key (id);

