create database my_cats;
use my_cats;


create table cat_board(
	num int primary key auto_increment,title char(255),content text,id char(30)
);

insert into cat_board values(0,'테스트제목','테스트내용 내용.......','cat4');
insert into cat_board values(0,'테스트제목2','테스트내용2 내용.......2','dog3');

select * from cat_board;

drop table cat_board;
