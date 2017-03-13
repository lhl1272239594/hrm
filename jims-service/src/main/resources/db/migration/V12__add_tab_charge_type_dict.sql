-- Create table
create table CHARGE_TYPE_DICT
(
  ID                     VARCHAR2(64) not null,
  CHARGE_TYPE_CODE       VARCHAR2(1),
  CHARGE_TYPE_NAME       VARCHAR2(8) not null,
  CHARGE_PRICE_INDICATOR NUMBER(1),
  INPUT_CODE_WB          VARCHAR2(8),
  IS_INSUR               CHAR(1),
  GROUP_NO               VARCHAR2(2),
  GROUP_NAME             VARCHAR2(20),
  INSURANCE_TYPE_INQ     CHAR(1),
  ORG_ID                 VARCHAR2(64),
  REMARKS                VARCHAR2(2000),
  UPDATE_BY              VARCHAR2(64),
  CREATE_BY              VARCHAR2(64),
  UPDATE_DATE            TIMESTAMP(6),
  DEL_FLAG               VARCHAR2(2),
  CREATE_DATE            TIMESTAMP(6)
);
-- Add comments to the table 
comment on table CHARGE_TYPE_DICT
  is '不同组织结构的费别字典表';
-- Add comments to the columns 
comment on column CHARGE_TYPE_DICT.ID
  is '主键';
comment on column CHARGE_TYPE_DICT.CHARGE_TYPE_CODE
  is '费别代码';
comment on column CHARGE_TYPE_DICT.CHARGE_TYPE_NAME
  is '费别名称';
comment on column CHARGE_TYPE_DICT.CHARGE_PRICE_INDICATOR
  is '享受优惠价格标志';
comment on column CHARGE_TYPE_DICT.IS_INSUR
  is '是否是医保费别 0－非医保 1－医保';
comment on column CHARGE_TYPE_DICT.GROUP_NO
  is '费别分组号  其中需要强制定义的有:01自费  02医保 03合作医疗  其他可根据需要定义';
comment on column CHARGE_TYPE_DICT.GROUP_NAME
  is '费别分组名称';
comment on column CHARGE_TYPE_DICT.INSURANCE_TYPE_INQ
  is '院长查询用的医保类别  0:自费 1:医保  2:合作医疗 合作医疗的相关统计从这个字段判断';
comment on column CHARGE_TYPE_DICT.ORG_ID
  is '所属结构';
-- Create/Recreate primary, unique and foreign key constraints 
alter table CHARGE_TYPE_DICT
  add constraint CHAGE_TYPE_DICT_PK primary key (ID);

  --不同医疗机构所在的区域不同，就可能有不同的费别
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'x','世园会员','1','SYHY','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'y','联合社区','1','LHSO','0','01','联合体','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'z','联合企业','1','LHQY','0','01','联合体','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'w','健康讲堂','1','JKJT','0','01','联合体','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'j','铁路医保','1','TLYB','1','01','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'v','石泉尘肺','1','SQCF','1','','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'o','本院职工','1','BYZZ','','','','');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'9','TJ9.0','0','TJJ','','01','','');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'8','TJ8.0','0','TJB','','01','','');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'7','TJ7.0','0','TJQ','','01','','');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'6','TJ6.0','0','TJL','','01','','');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'3','TJ5.0','0','TJW','','01','','');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'Z','自费','1','ZF','','','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'M','会员a','1','HYA','','','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'O','会员c','1','HYC','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'B','急诊抢救','0','JZQJ','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'Q','其它','1','QT','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'J','集团内部','0','JTNB','','01','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'N','会员b','1','HYB','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'E','会员e','0','HYE','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'1','合同单位','1','HTDW','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'P','会员d','1','HYD','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'S','挂号优惠','0','GHYH','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'T','商业保险','1','SYBX','','01','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'U','新农合','0','XNH','','01','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'F','离休保障','0','LXBZ','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'D','职工医保','0','ZGYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'C','居民医保','1','JMYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'X','新生儿','0','XSE','','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'W','外地医保','0','WDYB','','01','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'2','长庆医保','0','CQYB','','03','','');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'p','市医保','1','SYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'u','健康首次','1','JKSC','0','01','','0');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'L','铜川医保','0','TCYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'A','省直医保','0','SZYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'R','咸阳医保','0','XYYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'V','西航离休','0','XHLX','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'H','西职医保','0','XZYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'I','西安工伤','0','XAGS','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'K','高新医保','0','GXYB','1','02','','1');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'d','长安合疗','0','CAHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'c','阎良合疗','0','YLHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'Y','未央合疗','0','WYHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'a','雁塔合疗','0','YTHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'b','灞桥合疗','0','BQHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'g','临潼合疗','0','LTHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'e','户县合疗','0','HXHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'f','高陵合疗','0','GLHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'g','蓝田合疗','0','LTHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'h','周至合疗','0','ZZHL','','03','','2');
insert into charge_type_dict (id,charge_type_code,charge_type_name,charge_price_indicator,input_code_wb,is_insur,group_no,group_name,insurance_type_inq) values (sys_guid(),'i','省合疗','0','SHL','','03','','2');