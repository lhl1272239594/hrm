/*==============================================================*/
/* Table: SYS_SERVICE_PRICE  服务价格明细表                     */
/*CREATE_BY: txb	                                              */
/*==============================================================*/
create table SYS_SERVICE_PRICE
(
   ID                 VARCHAR2(64)         not null,
   SERVICE_ID        VARCHAR2(64),
   SERVICE_PRICE      NUMBER(10,2),
   SERVICE_TIME_LIMIT  VARCHAR2(10),
   CREATE_BY           VARCHAR2(64),
   REMARKS               VARCHAR2(2000),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          DATE,
   DEL_FLAG             VARCHAR2(2),
   CREATE_DATE          DATE,
   constraint PK_SYS_SERVICE_PRICE primary key (ID)
);

comment on table SYS_SERVICE_PRICE is
'服务价格明细表';

comment on column SYS_SERVICE_PRICE.SERVICE_ID is
'服务ID';

comment on column SYS_SERVICE_PRICE.SERVICE_PRICE is
'服务价格';

comment on column SYS_SERVICE_PRICE.SERVICE_TIME_LIMIT is
'服务时间限制';
