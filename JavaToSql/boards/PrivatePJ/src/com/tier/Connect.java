package com.tier;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connect {
	static private String DB_NAME = "my_cat";
	static private String DB_ID = "root";
	static private String DB_PW = "root";
	static private String tableName = "items";
	static public Connection con = null;
	static public Statement st = null;
	static public ResultSet result = null;
	private static final int LINE_LENGTH = 32;

	static public void dbInit() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void wn(String s) {
		System.out.print(s);
	}

	public static void wn() {
		System.out.println();
	}

	public static void w(String s) {
		System.out.print(s);
	}

	public static void w() {
		System.out.println();
	}

	public static void line() {
		for (int i = 0; i < LINE_LENGTH; i++) {
			w(" ");
		}
	}

	public static void space(int c) {
		for (int i = 0; i < c; i++) {
			w(" ");
		}
	}

	static public void dbExecuteUpdate(String query) {
		wn("전송할sql:" + query); // 로그찍기
		try {
			int resultCount = st.executeUpdate(query);
			wn("처리된 행 수:" + resultCount);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static public int getPostCount() {
		String count = "";
		try {
			Connect.result = Connect.st.executeQuery("select count(*)from " + tableName + "where b_reply_ori is null");
		} catch (Exception e) {
			e.printStackTrace();
		}
		int intCount = Integer.parseInt(count);
		return intCount;
	}

}
