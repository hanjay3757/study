CREATE DATABASE my_cat;
USE my_cat;

CREATE TABLE PS_BOARD_FREE (
    B_NO INT PRIMARY KEY AUTO_INCREMENT,           
    B_TITLE CHAR(100) NOT NULL DEFAULT "",         
    B_ID CHAR(50) NOT NULL,                         
    B_DATETIME DATETIME NOT NULL DEFAULT NOW(),     
    B_HIT INT NOT NULL DEFAULT 0,                   
    B_TEXT TEXT NOT NULL,                           
    B_REPLY_COUNT INT NOT NULL DEFAULT 0,           
    B_REPLY_ORI INT NOT NULL DEFAULT -1             
);

INSERT INTO PS_BOARD_FREE (B_TITLE, B_ID, B_TEXT) VALUES ('야옹', 'cat', 'aaa');

CREATE TABLE PS_MEMBER (
    PS_ID CHAR(50) PRIMARY KEY,
    PS_PW CHAR(50) NOT NULL
);

INSERT INTO PS_MEMBER VALUES ('cat', '1234');
show tables;
SHOW DATABASES LIKE 'my_cat';
SELECT COUNT(*) FROM PS_MEMBER WHERE PS_ID = 'cat' AND PS_PW = '1234';



drop table member;
create table member(
	id char(20),
    pw char(20)
);
desc member;
select * from member;
insert into member values('tester','abcd');