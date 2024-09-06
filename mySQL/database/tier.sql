show databases;
use my_cat;
drop table items;
DROP TABLE IF EXISTS member;

CREATE TABLE Items (
    id INTEGER PRIMARY KEY AUTO_INCREMENT,
    name TEXT NOT NULL,
    tier TEXT NOT NULL,
 b_reply_ori int,						#댓글의 원글 번호
    b_reply_text text
 
);
select*from items;
INSERT INTO Items  (name,tier) 
VALUES(
'서리한'
,
'2'
);
update  items set ;