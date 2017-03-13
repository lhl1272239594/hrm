-- Create table
--    author:yangruidong
/*==============================================================*/
/* Table:INPUT_SETTING_MASTER                                     */
/*==============================================================*/

insert into INPUT_SETTING_MASTER (dict_name, dict_type, org_id, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('包内物品管理', 'v_exp_dict_price_list', '1', null, null, null, to_date('30-06-2016 14:03:21', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:03:21', 'dd-mm-yyyy hh24:mi:ss'), 'f0a3fc8d57254f7cb1a25d10513fb922');

insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('ORG_ID', '组织机构ID', '1', 0, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('21-07-2016 10:10:13', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('21-07-2016 10:10:13', 'dd-mm-yyyy hh24:mi:ss'), 'a0c7c6d590ef44d1af914758160a45df');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('UNITS', '单位', '1', 0, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '91a8928e162e4a95ad20b3d856ec2d86');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('SUPPLIER', '厂家', '1', 1, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '20ce513ebb904fdf96bc0727d192dc65');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('INPUT_CODE', '拼音码', '1', 2, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), 'd83ac9ac5bb24a1498a290ef830238a7');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('EXP_NAME', '项目名称', '1', 3, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), 'c8e08f26e7454bed801548b520bfc705');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('EXP_CODE', '项目代码', '1', 4, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '23cf011f1d9144a99327fbac4f02a0cf');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('TRADE_PRICE', '批发价', '1', 5, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), 'b434952de1464fa48c3dec0cdefeaf1a');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('RETAIL_PRICE', '零售价', '1', 6, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), 'a020de3550844855bb29e442b584e1d5');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('LABEL', '包单位', '1', 7, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), 'a665f81fef784967be30515fb7fc8b9e');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('EXP_SPEC', '包规格', '1', 8, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('30-06-2016 14:07:11', 'dd-mm-yyyy hh24:mi:ss'), '1c8f4f0aafe645fe812596c767200396');
insert into INPUT_SETTING_DETAIL (data_col, data_title, flag_show, show_sort, flag_isname, result_sort, show_width, input_setting_master_id, input_code, remarks, update_by, create_by, update_date, del_flag, create_date, id)
values ('ID', '厂家ID', '1', 0, 'Y', '0', 10, 'f0a3fc8d57254f7cb1a25d10513fb922', '00', null, null, null, to_date('04-07-2016 09:35:27', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('04-07-2016 09:35:27', 'dd-mm-yyyy hh24:mi:ss'), '343788d703524e21906e26129d6d7e84');







