
alter table init_process add menu_id varchar(32);

insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('1','','0','1','自定义服务','/modules/sys/org-self-service.html','','6651d30ef59e4b3589b00f238577f572');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('2','','0','2','组织机构科室管理','/modules/sys/deptManager.html','','CBA9B6C774464B7A95FC24F50401F620');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('3','','0','3','角色维护','/modules/sys/org-role.html','','69357a97e49f4f8397f05d858384b817');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('4','','0','4','权限分配','/modules/sys/org-service-menu.html','','a96676df95914212828865d13f26f83a');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('5','','0','5','组织机构人员管理','/modules/sys/orgStaff.html','','AA68F77546214C038A1F1833365F9123');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('6','','0','6','科室分组维护','/modules/sys/staff-group.html','','AADA20D179AB4C3595449DC63FCA5F5C');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('7','','0','7','科室组人员维护','/modules/sys/staff-vs-group.html','','BF1520FFD2F742999E279136A65B3EBB');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('8','','0','8','费别字典管理','/modules/sys/chargeTypeDict.html','','610DAD4EF43D47D38D6603D85385C107');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('9','','0','9','身份字典表管理','/modules/sys/identity-dict.html','','5364008C1B3A49B18B3262320372DEA0');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('10','','0','10','合同单位管理','/modules/sys/unitInContract.html','','7C73CFCFF9534B618EAE7C30651789E2');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('11','','0','11','支付方式管理','/modules/sys/pay-way-dict.html','','F6270CC6891C44F4BF9388AD92A93788');

insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('51','010a2ad0b0384599bc2e9dfeddc2a985','0','1','自定义服务','/modules/sys/org-self-service.html','','6651d30ef59e4b3589b00f238577f572');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('52','010a2ad0b0384599bc2e9dfeddc2a985','0','2','组织机构科室管理','/modules/sys/deptManager.html','','CBA9B6C774464B7A95FC24F50401F620');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('53','010a2ad0b0384599bc2e9dfeddc2a985','0','3','角色维护','/modules/sys/org-role.html','','69357a97e49f4f8397f05d858384b817');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('54','010a2ad0b0384599bc2e9dfeddc2a985','0','4','权限分配','/modules/sys/org-service-menu.html','','a96676df95914212828865d13f26f83a');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('55','010a2ad0b0384599bc2e9dfeddc2a985','0','5','组织机构人员管理','/modules/sys/orgStaff.html','','AA68F77546214C038A1F1833365F9123');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('56','010a2ad0b0384599bc2e9dfeddc2a985','0','6','科室分组维护','/modules/sys/staff-group.html','','AADA20D179AB4C3595449DC63FCA5F5C');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('57','010a2ad0b0384599bc2e9dfeddc2a985','0','7','科室组人员维护','/modules/sys/staff-vs-group.html','','BF1520FFD2F742999E279136A65B3EBB');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('58','010a2ad0b0384599bc2e9dfeddc2a985','0','8','费别字典管理','/modules/sys/chargeTypeDict.html','','610DAD4EF43D47D38D6603D85385C107');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('59','010a2ad0b0384599bc2e9dfeddc2a985','0','9','身份字典表管理','/modules/sys/identity-dict.html','','5364008C1B3A49B18B3262320372DEA0');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('60','010a2ad0b0384599bc2e9dfeddc2a985','0','10','合同单位管理','/modules/sys/unitInContract.html','','7C73CFCFF9534B618EAE7C30651789E2');
insert into init_process(id,org_Id,init_flag,init_sort,menu_name,href,icon,menu_id) values('61','010a2ad0b0384599bc2e9dfeddc2a985','0','11','支付方式管理','/modules/sys/pay-way-dict.html','','F6270CC6891C44F4BF9388AD92A93788');

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '46b8a914fef240f48b6dba7d1775ba82', '1', '血库', 'BLOOD_FROM_TYPE', '血液来源', 1, null, to_date('2016-08-26 10:02:24','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-26 10:02:24','yyyy-MM-dd HH24:mi:ss'), '', '0', '1' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '3a6e5103178746cb9f52a58c03e5106d', 'U', '单位', 'BLOOD_UNIT_DICT', '血液单位', 1, null, to_date('2016-08-26 10:10:05','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-26 10:10:05','yyyy-MM-dd HH24:mi:ss'), '', '0', 'dw' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '72e9e65316e94ecc862da12a066cd4e3', 'ML', '毫升', 'BLOOD_UNIT_DICT', '血液单位', 2, null, to_date('2016-08-26 10:10:05','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-26 10:10:05','yyyy-MM-dd HH24:mi:ss'), '', '0', 'hs' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'ef77bfc169174dc988b413c5ee0f705f', 'P', '人/份', 'BLOOD_UNIT_DICT', '血液单位', 3, null, to_date('2016-08-26 10:10:05','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-26 10:10:05','yyyy-MM-dd HH24:mi:ss'), '', '0', 'r/f' ) ;

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'da82f5b623c24bb2be001b554d801bf2', '1', '发放出库', 'BLOOD_EXPORT_CLASS', '血液出库方式', 1, null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), '', '0', '1' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'ad3701ff5f954a0ea1523c206bd7f947', '2', '报损出库', 'BLOOD_EXPORT_CLASS', '血液出库方式', 2, null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), '', '0', '2' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'cb49be1dc44047aeaf737bcd84dac13e', '3', '退回血站', 'BLOOD_EXPORT_CLASS', '血液出库方式', 3, null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), '', '0', '3' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '5d9ea84040574a659b8b03bd05c0700f', '4', '调整出库', 'BLOOD_EXPORT_CLASS', '血液出库方式', 4, null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-29 08:48:54','yyyy-MM-dd HH24:mi:ss'), '', '0', '4' ) ;

INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'd91fcfcb33ba4eecba3a6b19b046f867', '1', '特殊介质交叉配血法', 'BLOOD_WAY_DICT', '配血方法', 1, null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), '', '0', 'tsjzjcpxf' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '5b2848e4891c4f1f9f1b24191ad6430f', '2', '盐水介质交叉配血法', 'BLOOD_WAY_DICT', '配血方法', 2, null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), '', '0', 'ysjzjcpxf' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( 'f1b9a72e45ea4441bc192c8354bca2ad', '3', '同型血浆', 'BLOOD_WAY_DICT', '配血方法', 3, null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), '', '0', 'txxj' ) ;
INSERT INTO sys_dict( id, value, label, type, description, sort, create_by, create_date, update_by, update_date, remarks, del_flag, input_code ) VALUES ( '53adde9c598143b49820efe0e0abdb08', '4', '同型输注', 'BLOOD_WAY_DICT', '配血方法', 4, null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), null, to_date('2016-08-30 11:51:46','yyyy-MM-dd HH24:mi:ss'), '', '0', 'txsz' ) ;

--手术类型字典
insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('93124a4d1c594806ae125c027d9a0244', '一般手术', '1', 'OPERATION_TYPE', '手术类型字典', 1, null, '14-9月 -16 09.32.44.1380000 上午', null, '14-9月 -16 09.34.55.0240000 上午', null, '0', 'ybss');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('cb53ded9d6df42d7b546d6db42c55d4d', '急抢救手术', '2', 'OPERATION_TYPE', '手术类型字典', 2, null, '14-9月 -16 09.33.07.6270000 上午', null, '14-9月 -16 09.34.55.0310000 上午', null, '0', 'jqjss');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('9b781e14afbc47378597f677e82157fb', '术中急抢救', '3', 'OPERATION_TYPE', '手术类型字典', 3, null, '14-9月 -16 09.33.45.2210000 上午', null, '14-9月 -16 09.34.55.0340000 上午', null, '0', 'szjqj');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('12daf85da9cf445080f6dbb4aa3fed48', '死亡', '4', 'OPERATION_TYPE', '手术类型字典', 4, null, '14-9月 -16 09.34.02.4900000 上午', null, '14-9月 -16 09.34.55.0360000 上午', null, '0', 'sw');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('4c8e3f0e63224da68f787ec56e1e5903', '急诊', '5', 'OPERATION_TYPE', '手术类型字典', 5, null, '14-9月 -16 09.34.13.4410000 上午', null, '14-9月 -16 09.34.55.0380000 上午', null, '0', 'jz');

--麻醉满意度字典
insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('6bc726d4029d4cf783f22f7ab2ae24c8', '满意', '1', 'ANESTHESIA_CSI', '麻醉满意度', 1, null, '14-9月 -16 09.42.37.5460000 上午', null, '14-9月 -16 09.42.37.5460000 上午', null, '0', 'my');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('a3b31d9535b94f2f908a8ba9140e817e', '基本满意', '2', 'ANESTHESIA_CSI', '麻醉满意度', 2, null, '14-9月 -16 09.44.18.1070000 上午', null, '14-9月 -16 09.44.18.1070000 上午', null, '0', 'jbmy');

insert into sys_dict (ID, LABEL, VALUE, TYPE, DESCRIPTION, SORT, CREATE_BY, CREATE_DATE, UPDATE_BY, UPDATE_DATE, REMARKS, DEL_FLAG, INPUT_CODE)
values ('d1256771cf23496687b0c4b785f8c36c', '不满意', '3', 'ANESTHESIA_CSI', '麻醉满意度', 3, null, '14-9月 -16 09.44.38.5240000 上午', null, '14-9月 -16 09.44.38.5240000 上午', null, '0', 'bmy');