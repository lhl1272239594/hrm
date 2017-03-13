-- Create table
create table ADMINISTRATION_DICT
(
  ID                  VARCHAR2(64) not null,
  ADMINISTRATION_CODE VARCHAR2(4),
  ADMINISTRATION_NAME VARCHAR2(16) not null,
  INPUT_CODE          VARCHAR2(8),
  INP_OUTP_FLAG       VARCHAR2(1),
  REMARKS             VARCHAR2(2000),
  UPDATE_BY           VARCHAR2(64),
  CREATE_BY           VARCHAR2(64),
  UPDATE_DATE         DATE,
  DEL_FLAG            VARCHAR2(100),
  CREATE_DATE         DATE
);
-- Add comments to the table
comment on table ADMINISTRATION_DICT
  is '给药途径';
-- Add comments to the columns
comment on column ADMINISTRATION_DICT.ID
  is '主键';
comment on column ADMINISTRATION_DICT.ADMINISTRATION_CODE
  is '给药途径代码';
comment on column ADMINISTRATION_DICT.ADMINISTRATION_NAME
  is '给药途径名称';
comment on column ADMINISTRATION_DICT.INPUT_CODE
  is '输入码';
comment on column ADMINISTRATION_DICT.INP_OUTP_FLAG
  is '门诊住院使用标示：0门诊、1住院、9综合';
-- Create/Recreate primary, unique and foreign key constraints
alter table ADMINISTRATION_DICT
  add constraint ADMINISTRATION_DICT_PK primary key (ID);
