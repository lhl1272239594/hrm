-- 机构服务自定义表
CREATE TABLE ORG_SELF_SERVICE_LIST
(
   ID                 VARCHAR2(64)         NOT NULL,
   SERVICE_NAME       VARCHAR2(200),
   ORG_ID             VARCHAR2(64),
   CREATE_BY           VARCHAR2(64),
   MENU_POSITION       VARCHAR2(64),
   MENU_STYLE          VARCHAR2(64),
   REMARKS               VARCHAR2(2000),
   UPDATE_BY            VARCHAR2(64),
   UPDATE_DATE          DATE,
   DEL_FLAG             VARCHAR2(2),
   CREATE_DATE          DATE,
   CONSTRAINT PK_ORG_SELF_SERVICE_LIST PRIMARY KEY (ID)
);

COMMENT ON TABLE ORG_SELF_SERVICE_LIST IS
'机构私人服务列表';

COMMENT ON COLUMN ORG_SELF_SERVICE_LIST.SERVICE_NAME IS
'自定义服务名字';

COMMENT ON COLUMN ORG_SELF_SERVICE_LIST.MENU_POSITION IS
'自定义服务中菜单界面显示位置';

COMMENT ON COLUMN ORG_SELF_SERVICE_LIST.MENU_STYLE IS
'自定义服务中菜单显示样式';

COMMENT ON COLUMN ORG_SELF_SERVICE_LIST.ORG_ID IS
'机构ID';