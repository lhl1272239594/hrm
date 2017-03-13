/*==============================================================*/
/* Table: menu_dict    字典表插入数据                             */
/* CREATE_DATE: 2016-05-12                                      */
/* CREATE_BY :  冯宇光                                              */
/*==============================================================*/
-- insert into

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('4afe4f27a181403998ca93aeddc4df83', '药库', '0', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 0, 1);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('2433a1c90d26436eabe15f033ae1f0f3', '门诊药局', '1', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 1, 2);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('803fc4b087c04f5099ebb6cb5cd90073', '住院药局', '2', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 2, 2);

insert into SYS_DICT (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, remarks)values ('e0da71d3797843ceb45cab57d7f28264', '其他药局', '3', 'DRUG_STOCK_TYPE_DICT', '库存类型字典表', 3, 2);


INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '22464f0c0ded4b0c83ae35a3630d770c', '1', '上级', 'DRUG_TRANSFER_DIR', '药品入出库方向', 0, null, to_date('2016-07-14 14:29:26','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:29:26','yyyy-MM-dd HH24:mi:ss'), '', '0', 'sj' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '99833dc1d75c41b28fbf4cc462f05de9', '2', '平级', 'DRUG_TRANSFER_DIR', '药品入出库方向', 1, null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), '', '0', 'pj' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'fdaa160fb77847fb97629b5992713cfc', '3', '下级', 'DRUG_TRANSFER_DIR', '药品入出库方向', 2, null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:31:05','yyyy-MM-dd HH24:mi:ss'), '', '0', 'xj' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '7cb930e4cc4047cabc34faa357ff4fc1', '4', '供应商', 'DRUG_TRANSFER_DIR', '药品入出库方向', 3, null, to_date('2016-07-14 14:31:31','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-07-14 14:31:31','yyyy-MM-dd HH24:mi:ss'), '', '0', 'gys' ) ;


delete from sys_dict where type = 'dose_unit';

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('D7DCC75301664F0FA55200F8EA0E8426', 'L', 'L', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'L');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('420433153E5C43B4935CD2D1F77D4940', 'g', 'g', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'G');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('49E99BD67BF44222B8C67EAF78237494', 'kg', 'kg', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'KG');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('240E8212712047F099E39072DB3E55A4', 'km', 'km', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'KM');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('18896A26882C4C438A59994D1DEBB74B', 'ml', 'ml', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'ML');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('D5B117CC46014534B45F7503C32C83A1', '把', '把', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'B');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('640F7B5938114A49B94BBAB7121945BB', '包', '包', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'B');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('0C1A14A8A61846C8A4AE85AF7C7D18DE', '本', '本', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'B');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('5D6D63F9A28A40358A9545E4FCCF2E0E', '床', '床', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'C');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('E3705980FA1B43B1A5AE903C546BD421', '袋', '袋', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'D');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('83F537DC87CC4E069C2D506C2B6AF040', '对', '对', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'D');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('E1CF56F7DE0C427FACA4A916A2813993', '份', '份', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'F');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('774BB9E59342482BBC68A0BF84BEEE94', '副', '副', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'F');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('42A22516130D4105A4D4EC9A492560B0', '付', '付', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'F');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('E1F42B7C1FDE4B4584378A2F0FCC2B7E', '个', '个', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'G');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('F9FDD635E34C45D799BD0A5295D9EAF8', '根', '根', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'G');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('138BAAB183D644A29EAD5D2870CD923C', '公斤', '公斤', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'GJ');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('8E337BC96CC944548C372D32BAAC1E7A', '罐', '罐', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'G');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('9D8C93D69F62424F83B40C4F39D5EC95', '毫居', '毫居', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'HJ');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('8202D6C3C30442F9A6993E1B20DBE5F6', '盒', '盒', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'H');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('9BC3B431705B4D8292CEDDC707C3802D', '件', '件', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'J');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('B47ACB10ABC84F7A896870217DF9454B', '斤', '斤', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'J');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('046B9F7A5D1B471AB02CEDC28FD63BB0', '卷', '卷', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'J');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('B569017304894E9D9A6E07BC24D1AFCE', '颗', '颗', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'K');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('D2D96A3016744645899A6CD41B3FFDD3', '克', '克', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'K');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('91CD9FBBCE884A3C95A1C31458DF4FC0', '块', '块', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'K');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('D26AC85A6B65410CA3607BA880717B12', '捆', '捆', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'K');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('D921F6128F4349B3AB90C803DFFE86B0', '粒', '粒', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'L');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('C6745AB349C143CCB7DEA24F58DDF009', '辆', '辆', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'L');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('A9D1FCAD8EB24CE59C28D2E53CBE929D', '列', '列', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'L');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('CD12D37B1BCE481B9469D81E3FCA8ED1', '枚', '枚', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'M');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('B0EEDF18FDC2495DA9583E40342BFA0B', '米', '米', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'M');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('A30A88618A7F4DA8854923066CB872D9', '排', '排', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'P');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('B2A0B2ECA4AE4BA1AA9926EEF2AFB61A', '盘', '盘', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'P');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('9562BEF214DD4D98AB527306CF646181', '批', '批', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'P');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('449F2DC9D9734508B4A0170024CC646F', '匹', '匹', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'P');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('112BBFE1628D4D2EA6374EA13F5B0102', '片', '片', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'P');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('1F2AF97D4E90444199D8578A7C0DA6ED', '平方', '平方', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'PF');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('10BDDFA91F83424F817F7FA989979A2C', '瓶', '瓶', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'P');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('10DED424697D4D609538E5DDEB07ED5E', '束', '束', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'S');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('637ABB74C70E4C92927528C15DD2A074', '双', '双', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'S');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('55BDE03B5E9042B7B10E9FA3E376C691', '台', '台', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'T');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('47C969CF3A0D48BEA0FA03CA4B86527B', '套', '套', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'T');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('67AFB154EF0C455195FB850BCAD2BF25', '条', '条', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'T');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('718AACA1432D408A8C5D9F6B7B7BFDB8', '帖', '帖', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'T');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('CE04A79BA4D34054AF59E0DC64233F16', '桶', '桶', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'T');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('EF46B3BD0A8E4FA68F40B1466AF5B7F8', '万支', '万支', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'WZ');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('B829B1876B60493F9B78119548C3F20D', '箱', '箱', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'X');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('26346A4650D54B7E84474E1D362DACAD', '盏', '盏', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'Z');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('58F7F64F9B414821BF9E4B13D2679F6B', '张', '张', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'Z');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('A21EABD4398A44BC9606638DC8790C21', '支', '支', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'Z');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('80D7ADE5263749C98C7A6712D0F1FCA7', '只', '只', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'Z');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('E78A0AF445514956B9E492DBAE5BAE4F', '轴', '轴', 'dose_unit', '计量单位', 10, null, null, null, null, null, '0', 'Z');