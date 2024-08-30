show databases;
use my_cat;
create table board (
b_no int primary key auto_increment,	#글번호
b_title char(100),
b_id char(20) not null,
b_datetime datetime not null,
b_hit int, 
b_text text,
b_reply_count int,
b_reply_ori int,
b_reply_text text
);

drop table board;

insert into board(b_title,b_id,b_datetime,b_text,b_hit)
values(
'헬로'
,
'cat1'
,
now()
,
'글입니다. 글'
,
0
);

insert into board(b_id,b_datetime,b_reply_ori,b_text)
values(
'cat1'
,
now()
,
10
,
'댓댓댓'
);

delete from board;
select*from board;
select*from board where b_no=10;
select*from board where not b_title is null;
select*from board where b_reply_ori=10;