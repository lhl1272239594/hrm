/*==============================================================*/
/* Table: V_SYS_DICT_AREA       省市县视图      */
/* CREATE_BY: CTQ         */
/*==============================================================*/
CREATE OR REPLACE VIEW V_SYS_DICT_AREA AS
  SELECT ID,LABEL,VALUE,TYPE,SORT,INPUT_CODE
    FROM SYS_DICT
   WHERE DEL_FLAG='0' AND TYPE='AREA_DICT'