/*==============================================================*/
/* Table: input_setting_master,input_setting_detail,menu_dict          */
/* CREATE_DATE: 2016-05-20                              */
/* CREATE_BY: yangruidong						                                    */
/*==============================================================*/

insert into INPUT_SETTING_MASTER (dict_name, dict_type, org_id, remarks, update_by, create_by, update_date, del_flag, create_date, id)values ('输入法明细表', 'input_setting_detail', '1', null, null, null, to_date('20-05-2016 10:18:21', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('20-05-2016 10:18:21', 'dd-mm-yyyy hh24:mi:ss'), '19fba1e47bd6483ca225f1569589cd34');
insert into INPUT_SETTING_MASTER (dict_name, dict_type, org_id, remarks, update_by, create_by, update_date, del_flag, create_date, id)values ('菜单', 'menu_dict', '1', null, null, null, to_date('19-05-2016 22:35:02', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('19-05-2016 22:35:02', 'dd-mm-yyyy hh24:mi:ss'), 'ab48bb43f3e1498595129f89f475d3ad');

insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)values ('MENU_NAME', '菜单名称', '1', 1, 'Y', '1', 1, 'ab48bb43f3e1498595129f89f475d3ad', '00', null, null, null, to_date('20-05-2016 09:55:47', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('19-05-2016 22:37:15', 'dd-mm-yyyy hh24:mi:ss'), '987eacfc1e984cd9ae79f6e7149a9dd9');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)values ('SORT', '排序', '1', 2, 'Y', '0', 1, 'ab48bb43f3e1498595129f89f475d3ad', '01', null, null, null, to_date('20-05-2016 09:44:17', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('20-05-2016 09:44:17', 'dd-mm-yyyy hh24:mi:ss'), 'ae52a9b5d1d84016a5407715ba6ba20f');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)values ('CREATE_DATE', '日期', '1', 3, 'Y', '1', 1, 'ab48bb43f3e1498595129f89f475d3ad', '00', null, null, null, to_date('20-05-2016 09:44:57', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('20-05-2016 09:44:57', 'dd-mm-yyyy hh24:mi:ss'), 'd4b4e802521c423b862ab7a98a07177e');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)values ('INPUT_CODE', '代码', '1', 0, 'Y', '0', 11, '19fba1e47bd6483ca225f1569589cd34', '00', null, null, null, to_date('20-05-2016 10:18:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('20-05-2016 10:18:45', 'dd-mm-yyyy hh24:mi:ss'), '30b61148a44549d0b69d91b437bc4887');





insert into MENU_DICT (id, menu_name, href, icon, sort, target, remarks, update_by, create_by, update_date, del_flag, create_date, pid, menu_level)values ('06509BF1BEA1440FB4299E27F65290C3', '输入法字典维护', '/modules/sys/input-setting.html', null, 2, '1', null, null, null, to_timestamp('20-05-2016 10:31:27.008000', 'dd-mm-yyyy hh24:mi:ss.ff'), '0', to_timestamp('20-05-2016 10:31:00.576000', 'dd-mm-yyyy hh24:mi:ss.ff'), '9ED7DB110B4F41F7AED1340F9B26CF1C', '1');