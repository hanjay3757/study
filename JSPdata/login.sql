#멤버-회원테이블
create table PS_MEMBER(
PS_ID char(50) primary key,
PS_PW char(50) not null
);
insert into PS_MEMBER values('cat','1234');
select * from ps_member;
select count(*) from ps_member where ps_id='cat' and ps_pw='1234';