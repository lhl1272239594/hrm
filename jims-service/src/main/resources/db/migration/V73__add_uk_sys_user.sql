/*=============================================================*/
/* update sys_user添加表约束                                                  */
/*=============================================================*/


alter table SYS_USER
  add constraint UK_SYS_USER unique (LOGIN_NAME);