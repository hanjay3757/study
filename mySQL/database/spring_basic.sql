use my_cat;

create table tbl_test (		
	no int,	
	str_data varchar(50)	
);		
insert into tbl_test values(1,'1');		
insert into tbl_test values(2,'2');		
insert into tbl_test values(3,'100');		
insert into tbl_test values(4,'HELLO WORLD');		
		create table tbl_guest(				
	bno int auto_increment primary key,			
	btext text			
);				
		
create table tbl_visitant_count (			
	count int		
);		
insert into tbl_guest (btext) values('개');				
insert into tbl_guest (btext) values('고양이');					
insert into tbl_visitant_count values(0);			
select * from tbl_visitant_count;			
select * from tbl_guest;		
SHOW VARIABLES LIKE 'general_log%';
show processlist; 
 SET GLOBAL general_log = 'ON';
 show variables where Variable_name in ('version', 'log', 'general_log');
 cd /usr/local/mysql/data
SET GLOBAL general_log_file='/var/lib/mysql/workbench/sql_actions_Local_instance_MySQL80.log.log';