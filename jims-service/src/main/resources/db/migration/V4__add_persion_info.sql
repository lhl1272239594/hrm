create table PERSION_INFO
(
   ID                   VARCHAR(64)          not null,
   NAME                 VARCHAR(100),
   SEX                  VARCHAR(2),
   NATION              VARCHAR(64),
   CARD_NO              VARCHAR(20),
   INPUT_CODE           VARCHAR(20),
   PHONE_NUM            VARCHAR(11),
   EMAIL                VARCHAR(100),
   NICK_NAME            VARCHAR(100),
   REMARKS              VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   CREATE_BY           VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   CREATE_DATE          TIMESTAMP,
   constraint PK_PERSION_INFO primary key (ID)
);

comment on table PERSION_INFO is
'人员字典表';

comment on column PERSION_INFO.ID is
'主键';

comment on column PERSION_INFO.NAME is
'姓名';

comment on column PERSION_INFO.SEX is
'性别';

comment on column PERSION_INFO.NATION is
'民族';

comment on column PERSION_INFO.CARD_NO is
'身份证号';

comment on column PERSION_INFO.PHONE_NUM is
'联系电话';

comment on column PERSION_INFO.EMAIL is
'邮箱';

comment on column PERSION_INFO.NICK_NAME is
'昵称';
