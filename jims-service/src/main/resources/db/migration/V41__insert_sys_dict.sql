
insert into sys_dict
  (id, label, value, type, description, sort)
values
  (sys_guid(), '长期', '1', 'REPEAT_INDICATOR_DICT', '医嘱标志', 1);
insert into sys_dict
  (id, label, value, type, description, sort)
values
  (sys_guid(), '临时', '0', 'REPEAT_INDICATOR_DICT', '医嘱标志', 2);


insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '新开', '1', 'ORDER_STATUS_DICT', '医嘱状态字典',1, 'XK');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '执行', '2', 'ORDER_STATUS_DICT', '医嘱状态字典',2, 'ZX');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '停止', '3', 'ORDER_STATUS_DICT', '医嘱状态字典',3, 'TZ');
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '作废', '4', 'ORDER_STATUS_DICT', '医嘱状态字典',4, 'ZF');
  
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '医生保存', '5', 'ORDER_STATUS_DICT', '医嘱状态字典',5, 'YSBC');
  
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '医生提交', '6', 'ORDER_STATUS_DICT', '医嘱状态字典',6, 'YSTJ');
  
  insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '医生停止', '7', 'ORDER_STATUS_DICT', '医嘱状态字典',7, 'YSTZ');
   insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '医生作废', '8', 'ORDER_STATUS_DICT', '医嘱状态字典',8, 'YSZF');
  
   insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '正常计价', '0', 'BILLING_ATTR_DICT', '计价属性',1);
    insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '自带药', '1', 'BILLING_ATTR_DICT', '计价属性',2);
    insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '需手工计价', '2', 'BILLING_ATTR_DICT', '计价属性',3);
  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '不计价', '3', 'BILLING_ATTR_DICT', '计价属性',4); 
  
 insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '在编', '0', 'BED_APPROVED_TYPE_DICT', '床位编制类型字典',1,'ZB'); 
   insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '非编', '1', 'BED_APPROVED_TYPE_DICT', '床位编制类型字典',2,'FB'); 
   insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '加床', '2', 'BED_APPROVED_TYPE_DICT', '床位编制类型字典',3,'JC'); 
   insert into sys_dict
  (id, label, value, type, description, sort,input_code)
  values
  (sys_guid(), '童床', '3', 'BED_APPROVED_TYPE_DICT', '床位编制类型字典',4,'TC'); 
  
/*  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '', '0', 'SKIN_FLAG_DICT', '皮试字典',0); */
   insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '皮试', '1', 'SKIN_FLAG_DICT', '皮试字典',1); 
   insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '续注', '2', 'SKIN_FLAG_DICT', '皮试字典',2); 
  
  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '阴', '0', 'SKIN_RESULT_DICT', '皮试结果字典',1); 
  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '阳', '1', 'SKIN_RESULT_DICT', '皮试结果字典',2); 
  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '无皮试', '2', 'SKIN_RESULT_DICT', '皮试结果字典',3); 

  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '非限制级', '0', 'LIMIT_CLASS_DICT', '药品限制等级',1);
  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '限制级', '1', 'LIMIT_CLASS_DICT', '药品限制等级',2);
  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '特殊级', '2', 'LIMIT_CLASS_DICT', '药品限制等级',3);
  insert into sys_dict
  (id, label, value, type, description, sort)
  values
  (sys_guid(), '其他', '3', 'LIMIT_CLASS_DICT', '药品限制等级',4);
