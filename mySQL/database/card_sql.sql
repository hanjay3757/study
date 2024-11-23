use my_cat;

show tables;

drop table card_my;
create table card_my(
	no int primary key auto_increment,
    job char(20) not null default '전사',
    grade char(20) not null default 'N',
    id char(20) not null default 'cat'
    ,deployment int not null default 0
);
select * from card_my;
insert into card_my value();
update card_my set deployment = 1 where id = 'cat' and no = 1;


create table card_my_party(
	no int primary key auto_increment,
    job char(20) not null default '전사',
    grade char(20) not null default 'N',
    id char(20) not null default 'cat',
    deployment car(20) not null default '1'
);
select * from card_my;
insert into card_my_party value();
delete from card_my_party;

truncate card_my_party;



##내 재산
drop table my_wealth;
create table my_wealth(
    gold int UNSIGNED not null default 0,
    dice int UNSIGNED not null default 0,
    id char(20) not null default 'cat'
);

select * from my_wealth;
insert into my_wealth value();
update my_wealth set gold = -1 where id = 'cat';
update my_wealth set gold = 1 where id = 'cat';
update my_wealth set dice = -1 where id = 'cat';
update my_wealth set dice = 1 where id = 'cat';
update my_wealth set dice = 12 where id = 'cat';
truncate my_wealth;

#pj
truncate pj;
create table pj(
	no	int primary key auto_increment		#번호
	,name char(40) not null default '무제'	#이름
	,content char(255) not null default '설명없음'		#설명
	,level	int not null default 1			#난이도
	,gold	bigint not null default 0	#보상(골드)    
);
drop table pj;
insert into pj values();
insert into pj (name) values('약초캐기');
insert into pj (name) values('토끼사냥');
insert into pj (name) values('담배심부름');
select * from pj;
CREATE TABLE pj (
    no INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    level INT DEFAULT 1,
    gold INT DEFAULT 1000,
    content TEXT
);
select * from card_my;