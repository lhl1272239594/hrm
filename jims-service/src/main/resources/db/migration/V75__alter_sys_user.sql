--修改sys_user的login_name的长度为50（原为20）：

alter table sys_user modify login_name varchar2(50);