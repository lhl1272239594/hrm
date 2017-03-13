
create table SYS_COMPANY
(
   ID                   VARCHAR2(64)         not null,
   PARENT_ID            VARCHAR2(64),
   ORG_NAME             VARCHAR2(50),
   ORG_CODE             VARCHAR2(20),
   ZIP_CODE             VARCHAR2(20),
   LINK_MAN             VARCHAR2(64),
   LINK_PHONE_NUM       VARCHAR2(11),
   APPLY_STATUS         VARCHAR2(2 CHAR),
   ADDRESS              VARCHAR2(500 CHAR),
   EMAIL                VARCHAR2(50),
   CREATE_BY           VARCHAR2(64),
   UPDATE_BY            VARCHAR(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   OWNER                VARCHAR(64),
   REMARKS              VARCHAR2(2000),
   CREATE_DATE          TIMESTAMP,
   constraint PK_SYS_COMPANY primary key (ID)
);

comment on table SYS_COMPANY is
'组织结构';

comment on column SYS_COMPANY.PARENT_ID is
'父机构ID';

comment on column SYS_COMPANY.ORG_NAME is
'组织结构名称';

comment on column SYS_COMPANY.ORG_CODE is
'组织结构代码';

comment on column SYS_COMPANY.LINK_MAN is
'联系人
';

comment on column SYS_COMPANY.LINK_PHONE_NUM is
'联系电话';

comment on column SYS_COMPANY.APPLY_STATUS is
'申请状态：0，暂存；1，提交待审核，2，通过 ，-1 审核失败';

comment on column SYS_COMPANY.ADDRESS is
'组织结构地址';

comment on column SYS_COMPANY.EMAIL is
'EMAIL地址';

comment on column SYS_COMPANY.OWNER is
'超级管理员';