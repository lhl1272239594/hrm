create table ORG_STAFF
(
   ID                   VARCHAR(64)          not null,
   DEPT_ID              VARCHAR(64),
   ORG_ID               VARCHAR(64),
   TITLE                VARCHAR(20),
   PERSION_ID           VARCHAR (64),
   REMARKS              VARCHAR2(2000),
   UPDATE_BY            VARCHAR(64),
   CREATE_BY           VARCHAR2(64),
   UPDATE_DATE          TIMESTAMP,
   DEL_FLAG             VARCHAR(2),
   CREATE_DATE          TIMESTAMP,
   constraint PK_ORG_STAFF primary key (ID)
);

comment on table ORG_STAFF is
'组织员工信息表';

comment on column ORG_STAFF.ID is
'主键';

comment on column ORG_STAFF.DEPT_ID is
'所属科室';

comment on column ORG_STAFF.ORG_ID is
'所属组织结构';

comment on column ORG_STAFF.TITLE is
'职称';

comment on column ORG_STAFF.PERSION_ID is
'人员ID';
