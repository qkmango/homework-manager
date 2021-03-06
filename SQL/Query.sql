-- 查询作业提交情况
SELECT h.id,IFNULL(c.count,0) count
from homework h
LEFT JOIN v_commit_count c
on h.id=c.hid;


-- 查询各科最近一次作业提交人数
SELECT h.id,c.name course,IFNULL(vcc.count,0) count
FROM (SELECT id,title,course FROM (SELECT id,title,course FROM homework ORDER BY id DESC) h GROUP BY course) h
LEFT JOIN v_commit_count vcc
ON h.id=vcc.hid
LEFT JOIN course c
ON h.course=c.id;

-- 获取最近提交动态
SELECT u.realname,ch.datetime,c.name course,h.title
FROM commit_homework ch
LEFT JOIN homework h
   ON ch.hid=h.id
LEFT JOIN user u
   ON ch.uid=u.id
LEFT JOIN course c
   ON h.course=c.id
ORDER BY ch.datetime desc
LIMIT 0,20;


-- 按小时分组，查询每个小时内提交作业的人数
SELECT
    count(*) count,
    unix_timestamp(CONCAT(date(datetime)," ",hour(datetime),":00:00"))*1000 timestamp
FROM commit_homework
WHERE datetime > '2021-03-07 12:22:21'
GROUP BY hour(datetime)