create or replace view v_staff_dict as
select a.org_id,
       a.title,
       b.name,
       b.card_no,
       b.phone_num,
       b.id,
       b.input_code,
       a.dept_id,
       c.dept_name ,
       a.id as staff_id,
       c.outp_or_inp,
       b.nick_name nickname
  from org_staff a, persion_info b, dept_dict c
 where a.persion_id = b.id
   and a.dept_id = c.id;