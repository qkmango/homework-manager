-- 某个用户已经提交的作业
SELECT hid,uid from commit_homework WHERE uid='aaa';

-- 某个用户未提交的作业
select * from homework
where id not in(SELECT hid from commit_homework WHERE uid='aaa');