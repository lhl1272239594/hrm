-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:DRUG_PRICE_LIST      DRUG_DICT         drug_supplier_catalog                */
/*==============================================================*/


insert into INPUT_SETTING_MASTER (dict_name, dict_type, org_id, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('录入申请', 'v_drug_provide_application', '1', null, null, null, to_date('04-07-2016 12:59:53', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 12:59:53', 'dd-mm-yyyy hh24:mi:ss'), '34e5ce4a43fa49918848c5a8d6ab4488');


insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('SUPPLIER_ID', '厂家别名', '1', 0, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 16:16:43', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 16:16:43', 'dd-mm-yyyy hh24:mi:ss'), '68bb28056c1d4aa4a379f6ca5fd9a8fb');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('LABEL', '单位名', '1', 1, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 16:16:43', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 16:16:43', 'dd-mm-yyyy hh24:mi:ss'), 'b2b43a7c3a9a4eac9112c3f7880605e8');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('UNITS', '单位', '1', 0, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '8b1ba706df934f56b22580f403b56166');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('INPUT_CODE', '拼音码', '1', 1, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '897c177130ec4f78aaba856dea2a942b');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('TRADE_PRICE', '批发价', '1', 3, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '73f66b5c0be74baab5ed3f0656eaac25');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('DRUG_SPEC', '规格', '1', 4, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '4d1c6d76c147472389e31f7de6e71358');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('FIRM_ID', '厂家ID', '1', 5, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), 'c26f677c641c4b1d8b4a7e3317ea3dac');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('RETAIL_PRICE', '零售价', '1', 6, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '60435e01664140589d985216a0b01f29');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('DRUG_NAME', '名称', '1', 7, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '98b65651b132424db5bdca04eb75e63d');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('DRUG_CODE', '代码', '1', 8, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 13:02:45', 'dd-mm-yyyy hh24:mi:ss'), '4215ab72b14c45b3953e8b2aa8db1397');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('ORG_ID', '组织机构ID', '1', 0, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('21-07-2016 09:00:42', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('21-07-2016 09:00:42', 'dd-mm-yyyy hh24:mi:ss'), '2f5a881af6564357ba2057cef6d396b7');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('MIN_SPEC', '最小规格', '1', 0, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('25-07-2016 11:15:00', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('25-07-2016 11:15:00', 'dd-mm-yyyy hh24:mi:ss'), 'd27848d3cc1e47259fd4e01b9859c787');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('MIN_UNITS', '最小单位', '1', 0, 'Y', '0', 10, '34e5ce4a43fa49918848c5a8d6ab4488', '00', null, null, null, to_date('25-07-2016 14:49:37', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('25-07-2016 14:49:37', 'dd-mm-yyyy hh24:mi:ss'), '7b6c34ec7d8141ce82e7ccd0df7b16d3');
