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
#토트넘 테이블 생성 
(#토트넘 테이블 
drop table tottenham_squad;	
drop table tottenham_squad;		delete from tottenham_squad;		select * from tottenham_squad; select p_name from  tottenham_squad; 
create table tottenham_squad(                        	
	id int primary key auto_increment,
	
	p_number int,
	p_name char(50),
	p_birth date,
	p_position char(20),
	p_height int,
	p_weight int
);	


insert into tottenham_squad values(0,9,'도미닉 솔란케','1997-09-14','공격수',187,80);			
insert into tottenham_squad values(0,22,'브레넌 존슨','2001-05-23','공격수',179,73);			
insert into tottenham_squad values(0,7,'손흥민','1992-07-08','공격수',183,78);			
insert into tottenham_squad values(0,28,'윌슨 오도베르','2004-11-28','공격수',182,77);			
insert into tottenham_squad values(0,16,'티모 베르너','1996-03-06','공격수',180,75);			
insert into tottenham_squad values(0,9,'히살리송','1997-05-10','공격수',179,71);			
			
#미드필더			
insert into tottenham_squad values(0,21,'데얀클루셰프스키','2000-04-25','미드필더',186,75);			
insert into tottenham_squad values(0,19,'라이언세세뇽','2000-05-18','미드필더',178,70);			
insert into tottenham_squad values(0,30,'로드리고벤탄쿠르','1997-06-26','미드필더',187,77);			
insert into tottenham_squad values(0,27,'마노르 솔로몬','1999-07-24','미드필더',167,63);			
insert into tottenham_squad values(0,11,'브리안 힐 살바티에라','2001-02-11','미드필더',175,60);			
insert into tottenham_squad values(0,58,'야고 산티아고','2003-04-15','미드필더',180,80);			
insert into tottenham_squad values(0,4,'올리버 스킵','2000-09-16','미드필더',175,70);			
insert into tottenham_squad values(0,38,'이브스 비수마','1996-08-30','미드필더',182,72);			
insert into tottenham_squad values(0,10,'제임스 메디슨','1996-11-23','미드필더',175,73);			
insert into tottenham_squad values(0,18,'지오바니 로셀소','1996-04-09','미드필더',177,74);			
insert into tottenham_squad values(0,29,'파페 마타르 사르','2002-09-14','미드필더',184,70);			
insert into tottenham_squad values(0,5,'피에르 호이비에르','1995-08-05','미드필더',187,84);			
			
#수비수			
insert into tottenham_squad values(0,38,'데스티니 우도지','2002-11-28','수비수',186,80);			
insert into tottenham_squad values(0,37,'미키 판 더 펜','2001-04-19','수비수',193,81);			
insert into tottenham_squad values(0,33,'벤 데이비스','1993-04-24','수비수',181,76);			
insert into tottenham_squad values(0,65,'알피 도링턴','2005-04-20','수비수',192,86);			
insert into tottenham_squad values(0,35,'애슐리 필립스','2005-06-26','수비수',192,86);			
insert into tottenham_squad values(0,15,'에릭 다이어','1994-01-15','미드필더',188,90);			
insert into tottenham_squad values(0,12,'에메르송 로얄','1999-01-14','수비수',181,79);			
insert into tottenham_squad values(0,17,'크리스티안 로메로','1998-04-27','수비수',185,79);			
insert into tottenham_squad values(0,23,'페드로 포로','1999-09-13','수비수',173,71);			
			
#골키퍼			
insert into tottenham_squad values(0,21,'굴리엘모 비카리오','1996-10-07','골키퍼',188,83);			
insert into tottenham_squad values(0,40,'브랜든 오스틴','1999-01-08','골키퍼',188,80);			
insert into tottenham_squad values(0,41,'알피 화이트맨','1998-10-02','골키퍼',189,84);			
insert into tottenham_squad values(0,1,'위고 요리스','1986-12-26','골키퍼',188,82);			
insert into tottenham_squad values(0,20,'프레이저 포스터','1988-03-17','골키퍼',201,93);			
#문제 5번 토트넘 공격수만 선택
select * from tottenham_squad where p_position = '골키퍼';
select * from tottenham_squad ;				
				#7번 선수 제거
delete from tottenham_squad where p_number = 7;				
			# 토트넘 번호 30불러오기	
update tottenham_squad set p_name = '로드리고밴탄쿠르' where p_number=30;		
#9번 특정조건 선수(들) 정보를 불러오기
select * from tottenham_squad where p_name like '%제%' and p_number > 6;					
# 10번  주급 집어넣기 
ALTER TABLE tottenham_squad ADD weekly_pay int default 0;		
#12 주급 +추가하기	
update tottenham_squad set weekly_pay = 1000 where p_number=9;
select * from tottenham_squad order by weekly_pay desc;
#정렬 
select * from tottenham_squad order by p_number desc ;
select * from tottenham_squad order by p_number asc;
select * from tottenham_squad order by p_number desc;
select * from tottenham_squad order by p_height desc, p_weight;			
#한글화
select no,name,weeklywage as "주급" from tottenham_squad;		
select no,name,weeklywage as 주급 from tottenham_squad;		
select no,name,weeklywage 주급 from tottenham_squad;		
select no,name,weeklywage/7 일급 from tottenham_squad;		
#칼럼 추가
ALTER TABLE tottenham_squad add injury char(1);		
select * from tottenham_squad where injury is null;			
select * from tottenham_squad where injury is not null;		
select * from tottenham_squad where injury='y';		
#이름 검색
select * from tottenham_squad where name like '%손%'		
union		
select * from tottenham_squad where name like '%케%';			
);
            
            
            
            
            
            
  (          #문제 확인 1
   use my_cat;
            use my_cat;
(create table test();
create table visit_count(	#테이블 만들기. 칼럼(또는 필드 또는 열이름)은 딸랑 한개.
	count int
);
drop table visit_count;
show tables; 
  
select * from visit_count;
  
insert into visit_count values(0);	#데이터를 한 줄 넣기

update visit_count set count=count+1;

delete from visit_count;

# 회원가입 
drop table member;
select * from member;
create table member(
n int primary key auto_increment,
id char(50) unique not null,
# 중첩 불가 설정
name char(50) not null,
# 빈칸입력하면 적용 불가 
age int not null,
gender char(1) not null,
tel char(20) not null,
hobby char(50) null #null 이 default 값 안적어도 적용
);
insert into member (id,name,age,gender,tel,hobby) values('cat1','고양이',5,'여','010-1234-1234',null);
insert into member (id,name,age,gender,tel,hobby) values('cat2','개냥이',4,'남','010-1234-1234',null);
insert into member (id,name,age,gender,tel,hobby) values('cat3','호랑이',9,'남','010-1234-1234',null);
);
);
( #시간 생성
create table test();
create table test(
y year
);
create table test(
d date
);
create table test(
t time
);
create table test(
dt datetime
);
(#현재시간 출력
select now() from dual;
SELECT NOW() from dual;	
select curdate() from dual;			
select curtime() from dual;			
select DATE_FORMAT(now(), '%Y-%m-%d %H:%i:%s') from dual;			
	);
    
    
drop table test;
    );
    