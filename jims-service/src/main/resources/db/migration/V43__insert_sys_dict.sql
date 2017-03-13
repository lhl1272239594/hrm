  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '未收费', '0', 'CHARGE_INDICATOR_DICT', '收费标记字典',1);

  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '已收费', '1', 'CHARGE_INDICATOR_DICT', '收费标记字典',2);

  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '已退费', '2', 'CHARGE_INDICATOR_DICT', '收费标记字典',3);

  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '已经发药状态', '3', 'CHARGE_INDICATOR_DICT', '收费标记字典',4);

  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '申请退费状态', '4', 'CHARGE_INDICATOR_DICT', '收费标记字典',5);

  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '申请退方', '5', 'CHARGE_INDICATOR_DICT', '收费标记字典',6);