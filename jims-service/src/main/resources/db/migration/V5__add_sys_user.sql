
create table SYS_USER
(
   ID                   VARCHAR(64)          not null,
   LOGIN_NAME           VARCHAR(20),
   PASSWORD             VARCHAR(100),
   LAST_LOGIN_TIME      TIMESTAMP,
   PERSION_ID           VARCHAR(64),
   REMARKS              VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   CREATE_BY           VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   CREATE_DATE          TIMESTAMP,
   constraint PK_SYS_USER primary key (ID)
);

comment on table SYS_USER is
'登陆信息表';

comment on column SYS_USER.ID is
'主键';

comment on column SYS_USER.LOGIN_NAME is
'登录名';

comment on column SYS_USER.PASSWORD is
'密码';

comment on column SYS_USER.LAST_LOGIN_TIME is
'最后登陆时间';

comment on column SYS_USER.PERSION_ID is
'人员';