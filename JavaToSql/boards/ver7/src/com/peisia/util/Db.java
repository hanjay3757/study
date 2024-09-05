package com.peisia.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	static private String DB_NAME = "my_cat";
	static private String DB_ID = "root";
	static private String DB_PW = "root";
	static public Connection con = null;
	static public Statement st = null;
	static public ResultSet result = null;
	static public void dbInit() {
		try {
			Db.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+DB_NAME, DB_ID, DB_PW);
			Db.st = Db.con.createStatement();
		} catch (Exception e) {
		}
	}	
	static public void dbExecuteUpdate(String query) {
		Cw.wn("전송할sql"+query);	//로그찍기
		try {
			int resultCount = st.executeUpdate(query);
			Cw.wn("처리된 행 수:"+resultCount);
		} catch (Exception e) {
		}
	}
	static public void dbPostCount() {	
		try {
			Db.result = Db.st.executeQuery("select count(*) from board where b_reply_ori is null");
			Db.result.next();
			String count = Db.result.getString("count(*)");
			Cw.wn("글 수:"+count);
		} catch (Exception e) {
		}
	}
	static public int getPostCount() {
		String count = "";
		try {
			Db.result = Db.st.executeQuery("select count(*) from board where b_reply_ori is null");
			Db.result.next();
			count = Db.result.getString("count(*)");
		} catch (Exception e) {
		}
		int intCount = Integer.parseInt(count);
		return intCount;
	}
	static public int getPostCountSearch(String searchWord) {
		String count = "";
		try {
			Db.result = Db.st.executeQuery(
					"select count(*) from board where b_reply_ori is null"
					+
					" and b_title like '%"+searchWord+"%'"
			);
			Db.result.next();
			count = Db.result.getString("count(*)");
			Cw.wn("글 수:"+count);
		} catch (Exception e) {
		}
		int intCount = Integer.parseInt(count);
		return intCount;
	}
}