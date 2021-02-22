
-- 查询未提交的作业
SELECT *
FROM homework h
LEFT JOIN commit_homework ch
ON h.id=ch.hid
WHERE h.id not in(select hid from commit_homework);

SELECT *
FROM homework h
         JOIN commit_homework ch
WHERE h.id not in(ch.hid)