/*==============================================================*/
/* Table: org_role_vs_service    增加数据                           */
/* CREATE_DATE: 2016-06-24                                    */
/* CREATE_BY :  魏申                                             */
/*==============================================================*/
-- insert into

delete FROM  org_role_vs_service;
insert into org_role_vs_service (ID, SERVICE_ID, ROLE_ID, CREATE_BY, REMARK, UPDATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('27e59e2443d04de0b3ac55be5ae9377d', '4677898ebfd049f0a23fbc684c1d0704', '98733ef0410a4392a64d33ef3263a268', null, null, null, to_date('24-06-2016 11:10:37', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('24-06-2016 11:10:37', 'dd-mm-yyyy hh24:mi:ss'));

insert into org_role_vs_service (ID, SERVICE_ID, ROLE_ID, CREATE_BY, REMARK, UPDATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('f9111231dbfe46eaa6ea5b967820d868', '4134edf5551547cea00ca5f10a8cdd66', '98733ef0410a4392a64d33ef3263a268', null, null, null, to_date('17-06-2016 12:40:54', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('17-06-2016 12:40:54', 'dd-mm-yyyy hh24:mi:ss'));

insert into org_role_vs_service (ID, SERVICE_ID, ROLE_ID, CREATE_BY, REMARK, UPDATE_BY, UPDATE_DATE, DEL_FLAG, CREATE_DATE)
values ('d4fcb335cc2942c8b5199d2d625cbb13', '4677898ebfd049f0a23fbc684c1d0704', '6c5f401402bd48dca3f551bade1a9525', null, null, null, to_date('17-06-2016 12:43:01', 'dd-mm-yyyy hh24:mi:ss'), '0', to_date('17-06-2016 12:43:01', 'dd-mm-yyyy hh24:mi:ss'));


