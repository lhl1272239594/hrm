-- Create table
create table PRICE_LIST_TEMPLATE
(
  item_class         VARCHAR2(20) not null,
  item_code          VARCHAR2(20) not null,
  item_name          VARCHAR2(100),
  item_spec          VARCHAR2(50) not null,
  units              VARCHAR2(10) not null,
  price              NUMBER(9,3),
  prefer_price       NUMBER(9,3),
  foreigner_price    NUMBER(9,3),
  performed_by       VARCHAR2(8),
  fee_type_mask      NUMBER(1) default 0,
  class_on_inp_rcpt  VARCHAR2(20),
  class_on_outp_rcpt VARCHAR2(20),
  class_on_reckoning VARCHAR2(20),
  subj_code          VARCHAR2(20),
  class_on_mr        VARCHAR2(4),
  memo               VARCHAR2(100),
  start_date         DATE not null,
  stop_date          DATE,
  operator           VARCHAR2(8),
  enter_date         DATE,
  high_price         NUMBER(10,4),
  material_code      VARCHAR2(100),
  score_1            NUMBER(10,2),
  score_2            NUMBER(10,2),
  price_name_code    VARCHAR2(20),
  control_flag       VARCHAR2(1),
  input_code         VARCHAR2(8),
  input_code_wb      VARCHAR2(8),
  std_code_1         VARCHAR2(20),
  changed_memo       VARCHAR2(40),
  class_on_insur_mr  VARCHAR2(24),
  cwtj_code          VARCHAR2(24),
  xm_wy              VARCHAR2(24),
  lb_wy              VARCHAR2(24),
  mzsj_wy            VARCHAR2(24),
  zysj_wy            VARCHAR2(24),
  group_flag         CHAR(1),
  stop_operator      VARCHAR2(20),
  id                 VARCHAR2(64) not null,
  master_id          VARCHAR2(64)
);

-- Add comments to the table
comment on table PRICE_LIST_TEMPLATE
  is '价表模板';

-- Add comments to the columns 
comment on column PRICE_LIST_TEMPLATE.item_class
  is '项目类别';
comment on column PRICE_LIST_TEMPLATE.item_code
  is '项目代码';
comment on column PRICE_LIST_TEMPLATE.item_name
  is '项目名称';
comment on column PRICE_LIST_TEMPLATE.item_spec
  is '规格';
comment on column PRICE_LIST_TEMPLATE.units
  is '单位';
comment on column PRICE_LIST_TEMPLATE.price
  is '基本价格';
comment on column PRICE_LIST_TEMPLATE.prefer_price
  is '优惠价格';
comment on column PRICE_LIST_TEMPLATE.foreigner_price
  is '外宾价格';
comment on column PRICE_LIST_TEMPLATE.performed_by
  is '执行科室';
comment on column PRICE_LIST_TEMPLATE.fee_type_mask
  is '是否自费';
comment on column PRICE_LIST_TEMPLATE.class_on_inp_rcpt
  is '住院收据分类';
comment on column PRICE_LIST_TEMPLATE.class_on_outp_rcpt
  is '门诊收据分类';
comment on column PRICE_LIST_TEMPLATE.class_on_reckoning
  is '核算科目';
comment on column PRICE_LIST_TEMPLATE.subj_code
  is '会计科目';
comment on column PRICE_LIST_TEMPLATE.class_on_mr
  is '病案首页分类';
comment on column PRICE_LIST_TEMPLATE.memo
  is '备注信息';
comment on column PRICE_LIST_TEMPLATE.start_date
  is '启用日期';
comment on column PRICE_LIST_TEMPLATE.stop_date
  is '停止日期';
comment on column PRICE_LIST_TEMPLATE.operator
  is '维护者';
comment on column PRICE_LIST_TEMPLATE.enter_date
  is '输入日期';
comment on column PRICE_LIST_TEMPLATE.high_price
  is '最高价格';
comment on column PRICE_LIST_TEMPLATE.material_code
  is '物价码';
comment on column PRICE_LIST_TEMPLATE.changed_memo
  is '价格变更原因包括调价和停用等都可以录入保存原因';
comment on column PRICE_LIST_TEMPLATE.xm_wy
  is '未央项目对照';
comment on column PRICE_LIST_TEMPLATE.lb_wy
  is '未央类别对照';
comment on column PRICE_LIST_TEMPLATE.mzsj_wy
  is '门诊收据对照';
comment on column PRICE_LIST_TEMPLATE.zysj_wy
  is '住院收据对照';
comment on column PRICE_LIST_TEMPLATE.id
  is '主键';
comment on column PRICE_LIST_TEMPLATE.master_id
  is '模板信息表ID';

-- Create/Recreate primary, unique and foreign key constraints 
alter table PRICE_LIST_TEMPLATE
  add constraint PK_PRICE_LIST_TEMPLATE primary key (ID);
alter table PRICE_LIST_TEMPLATE
  add constraint UK_PRICE_LIST_TEMPLATE unique (ITEM_CLASS, ITEM_CODE, ITEM_SPEC, UNITS, START_DATE,MASTER_ID)