package com.peisia.c.mysqltest;

public class boardSQL {
	public static final String CREATE_TABLE = 
		"create table board ("
		+ "b_no int primary key auto_increment,"
		+ "b_title varchar(100),"
		+ "b_id varchar(20) not null,"
		+ "b_datetime datetime not null,"
		+ "b_hit int,"
		+ "b_text text,"
		+ "b_reply_count int,"
		+ "b_reply_ori int,"
		+ "b_reply_text text"
		+ ");";
		
	public static final String DROP_TABLE = "drop table board;";

	public static final String INSERT_POST = 
		"insert into board (b_title,b_id,b_datetime,b_text,b_hit) "
		+ "values ('헬로','cat1',now(),'글입니다. 글글.....글....',0);";

	public static final String INSERT_REPLY = 
		"insert into board (b_id,b_datetime,b_reply_ori,b_reply_text) "
		+ "values ('cat1',now(),10,'댓글임......욕욕욕...');";
		
	public static final String DELETE_ALL = "delete from board;";
	public static final String SELECT_ALL = "select * from board;";
	public static final String SELECT_BY_NO = "select * from board where b_no=10;";
	public static final String SELECT_NOT_NULL_TITLE = "select * from board where not b_title is null;";
	public static final String SELECT_REPLIES = "select * from board where b_reply_ori=10;";
}
