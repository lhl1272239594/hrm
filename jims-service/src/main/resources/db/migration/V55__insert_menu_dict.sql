/*==============================================================*/
/* Table: menu_dict    增加菜单按钮                             */
/* CREATE_DATE: 2016-06-22                                     */
/* CREATE_BY :  魏申                                             */
/*==============================================================*/
-- insert into

DELETE  FROM  menu_dict;

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('81A6B79F536F4EB18C957D09783BECE0', '住院管理', '/modules/clinic/centerRegionHospital.html', '1', 2, '1', null, null, null, '17-5月 -16 02.02.44.213000 下午', '0', '17-5月 -16 02.02.06.090000 下午', 'c41e3b43214a404ba50115c7d545a62d', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('35E111DB41F9420B9B19B200A41488CB', '菜单管理', '/modules/sys/menuDict.html', '1', 1, '1', null, null, null, '28-4月 -16 09.37.43.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');


insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('55CB0105F69B490DA48F7D1F1029370A', '系统管理', null, '1', 9, '2', null, null, null, '28-4月 -16 09.30.34.000000 上午', '0', '28-4月 -16 09.30.34.000000 上午', null, '0');


insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('CBA9B6C774464B7A95FC24F50401F620', '组织机构科室管理', '/modules/sys/deptManager.html', null, null, '1', null, null, null, '05-5月 -16 03.39.30.443000 下午', '0', '05-5月 -16 03.39.23.878000 下午', '55CB0105F69B490DA48F7D1F1029370A', '1');


insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('AA68F77546214C038A1F1833365F9123', '组织机构人员管理', '/modules/sys/orgStaff.html', null, 1, '1', null, null, null, '12-5月 -16 09.41.48.960000 上午', '0', '12-5月 -16 09.41.48.960000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');


insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('8A3097F4A8DE489E9B9509E444ACB735', '字典表维护', '/modules/sys/public-dict.html', null, null, null, null, null, null, '12-5月 -16 01.45.42.300000 下午', '1', '12-5月 -16 01.45.42.300000 下午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('5364008C1B3A49B18B3262320372DEA0', '身份字典表管理', '/modules/sys/identity-dict.html', null, null, null, null, null, null, '22-6月 -16 03.42.18.018000 下午', '0', '22-6月 -16 03.42.18.018000 下午', '55CB0105F69B490DA48F7D1F1029370A', '1');


insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('06509BF1BEA1440FB4299E27F65290C3', '输入法字典维护', '/modules/sys/input-setting.html', null, 2, '1', null, null, null, '20-5月 -16 10.31.27.008000 上午', '0', '20-5月 -16 10.31.00.576000 上午', '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('BF1520FFD2F742999E279136A65B3EBB', '科室组人员维护', '/modules/sys/staff-vs-group.html', '1', 4, '1', null, null, null, '28-5月 -16 10.43.12.858000 上午', '0', '28-5月 -16 10.42.17.221000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('AADA20D179AB4C3595449DC63FCA5F5C', '科室分组维护', '/modules/sys/staff-group.html', '1', 5, '1', null, null, null, '28-5月 -16 10.42.45.651000 上午', '0', '28-5月 -16 10.42.45.651000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');
insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('6651d30ef59e4b3589b00f238577f572', '自定义服务', ' /modules/sys/org-self-service.html', '1', 1, '1', null, null, null, '23-6月 -16 11.32.54.000000 上午', '0', '23-6月 -16 11.32.54.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('69357a97e49f4f8397f05d858384b817', '角色维护', '/modules/sys/org-role.html', '1', 1, '1', null, null, null, '03-6月 -16 09.16.09.473000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('a96676df95914212828865d13f26f83a', '权限分配', '/modules/sys/org-service-menu.html', '1', 1, '1', null, null, null, '03-6月 -16 09.16.09.473000 上午', '0', '28-4月 -16 09.30.34.000000 上午', '55CB0105F69B490DA48F7D1F1029370A', '1');

insert into menu_dict (ID, MENU_NAME, HREF, ICON, SORT, TARGET, REMARKS, UPDATE_BY, CREATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE, PID, MENU_LEVEL)
values ('7C73CFCFF9534B618EAE7C30651789E2', '合同单位管理', '/modules/sys/unitInContract.html', '1', 1, '1', null, null, null, '22-6月 -16 04.42.41.995000 下午', '0', '22-6月 -16 04.41.40.052000 下午', '55CB0105F69B490DA48F7D1F1029370A', '1');
