-- Create table 合同单位字典表
create table UNIT_IN_CONTRACT
(
  id            VARCHAR2(64) not null,
  UNIT_NAME            VARCHAR2(40),
  INPUT_CODE           VARCHAR2(8),
  ADDRESS              VARCHAR2(40),
  UNIT_TYPE            VARCHAR2(1),
  SUBORDINATE_TO       VARCHAR2(11),
  DISTANCE_TO_HOSPITAL NUMBER(4,1),
  REGULAR_NUM          NUMBER(4),
  TEMP_NUM             NUMBER(4),
  RETIRED_NUM          NUMBER(4),
  INPUT_CODE_WB        VARCHAR2(8),
  org_id                varchar2(64)
);
-- Add comments to the table
comment on table UNIT_IN_CONTRACT
  is '合同单位记录';
-- Add comments to the columns
comment on column UNIT_IN_CONTRACT.id
  is '合同单位代码';
comment on column UNIT_IN_CONTRACT.UNIT_NAME
  is '合同单位名称';
comment on column UNIT_IN_CONTRACT.INPUT_CODE
  is '输入码';
comment on column UNIT_IN_CONTRACT.ADDRESS
  is '单位地址';
comment on column UNIT_IN_CONTRACT.UNIT_TYPE
  is '单位性质';
comment on column UNIT_IN_CONTRACT.SUBORDINATE_TO
  is '隶属单位';
comment on column UNIT_IN_CONTRACT.DISTANCE_TO_HOSPITAL
  is '就医距离';
comment on column UNIT_IN_CONTRACT.REGULAR_NUM
  is '在编人数';
comment on column UNIT_IN_CONTRACT.TEMP_NUM
  is '非编人数';
comment on column UNIT_IN_CONTRACT.RETIRED_NUM
  is '离退休人数';
comment on column UNIT_IN_CONTRACT.INPUT_CODE_WB
  is '五笔码';
-- Create/Recreate primary, unique and foreign key constraints
alter table UNIT_IN_CONTRACT
  add constraint PK_UNIT_IN_CONTRACT primary key (id);
