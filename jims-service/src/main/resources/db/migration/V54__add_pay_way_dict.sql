-- Create table
create table PAY_WAY_DICT
(
  id                varchar(64),
  PAY_WAY_CODE      VARCHAR2(2) not null,
  PAY_WAY_NAME      VARCHAR2(8),
  ACCTING_INDICATOR NUMBER(1),
  OUTP_INDICATOR    NUMBER(1),
  INP_INDICATOR     NUMBER(1),
  INPUT_CODE        VARCHAR2(8),
  REGIST_INDICATOR  NUMBER(1),
  CHARGE_TYPE       VARCHAR2(20),
  org_id            varchar2(64)
);
-- Add comments to the table
comment on table PAY_WAY_DICT
  is '支付方式字典';
-- Add comments to the columns
comment on column PAY_WAY_DICT.id
  is '主键';
comment on column PAY_WAY_DICT.PAY_WAY_CODE
  is '支付方式代码';
comment on column PAY_WAY_DICT.PAY_WAY_NAME
  is '支付方式名称';
comment on column PAY_WAY_DICT.ACCTING_INDICATOR
  is '记账标志，0，不记账，1记账';
comment on column PAY_WAY_DICT.OUTP_INDICATOR
  is '门诊病人适用标志，0，否，1是';
comment on column PAY_WAY_DICT.INP_INDICATOR
  is '住院病人适用标志，0，否，1是';
comment on column PAY_WAY_DICT.INPUT_CODE
  is '输入码';
comment on column PAY_WAY_DICT.CHARGE_TYPE
  is '医保类别';
comment on column pay_way_dict.org_id is '所属组织机构';
-- Create/Recreate primary, unique and foreign key constraints
alter table PAY_WAY_DICT
  add constraint PK_PAY_WAY_DICT primary key (ID);
