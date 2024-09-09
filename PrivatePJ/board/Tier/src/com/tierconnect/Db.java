package com.tierconnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Db {
	static public Connection con = null;
	static public Statement st = null;
	static public ResultSet result = null;
	static private String DB_NAME = "my_cat";
	static private String DB_ID = "root";
	static private String DB_PW = "root";

	static public void dbInit() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + DB_NAME, DB_ID, DB_PW);
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static public void dbExcuteUpdate(String query) {
		Cw.wn("전송할 sql : " + query);
		try {
			int resultCount = st.executeUpdate(query);
			Cw.wn("처리된 행 수 : " + resultCount);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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

	static public void drops() {

		try {
			st.execute("DROP TABLE IF EXISTS items");
		} catch (SQLException e1) {
			// TODO 자동 생성된 catch 블록
			e1.printStackTrace();
		}

		try {
			st.execute("DROP TABLE IF EXISTS member");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static public void create() {
		String createTableSQL = "CREATE TABLE Items (" + "id INTEGER PRIMARY KEY AUTO_INCREMENT, "
				+ "tier TEXT NOT NULL, " + "name TEXT NOT NULL)";
		try {
			st.execute(createTableSQL);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void space(int c) {
		for (int i = 0; i < c; i++) {
			w(" ");
		}
	}
}