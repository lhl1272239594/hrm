--修改charge_type_dict的CHARGE_TYPE_CODE的长度为2（原为1）：

alter table charge_type_dict modify CHARGE_TYPE_CODE varchar2(2);