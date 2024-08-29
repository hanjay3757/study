CREATE DATABASE my_cat default CHARACTER SET UTF8MB4; #1
use my_cat; #2

create table visit_count(	#테이블 만들기. 칼럼(또는 필드 또는 열이름)은 딸랑 한개. #3
	count int
);
drop table visit_count; #테이블 날리는 명령어
show tables; #4
  
select * from visit_count;# 6
  
insert into visit_count values(0);	#데이터를 한 줄 넣기 #5

update visit_count set count=count+1; 

delete from visit_count;
#행 삭제 명령어