package com.peisia.c.mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ProcBoard {
	Connection con = null;
	Statement st = null;
	ResultSet result = null;

	void run() {
		dbInit();
//		dbExecuteQuery("select * from tottenham_squad where p_number=7");

		Scanner sc = new Scanner(System.in);
		System.out.println("글제목을 입력해주세요:");
		String title = sc.next();

//		dbExecuteUpdate("insert into board (b_title,b_id,b_datetime,b_text,b_hit) values ('"+ title +"','kitty',now(),'콘솔프로그램에서 작성한 글입니다. 글글.....글....',0);");

//		String x = "insert into board (b_title,b_id,b_datetime,b_text,b_hit) values ('"+ title +"','kitty',now(),'콘솔프로그램에서 작성한 글입니다. 글글.....글....',0);";
		String x = String.format("insert into board (b_title,b_id,b_datetime,b_text,b_hit) "
				+ "values ('%s','kitty',now(),'콘솔프로그램에서 작성한 글입니다. 글글.....글....',0);", title);
		System.out.println(x);
		// sql log
		dbExecuteUpdate(x);
	}

	private void dbInit() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			st = con.createStatement(); // Statement는 정적 SQL문을 실행하고 결과를 반환받기 위한 객체다. Statement하나당 한개의 ResultSet 객체만을 열
										// 수있다.
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
		}
	}

	private void dbExecuteQuery(String query) {
		try {
			result = st.executeQuery(query);
			while (result.next()) { // 결과를 하나씩 빼기. 더 이상 없으면(행 수가 끝나면) false 리턴됨.
				String name = result.getString("p_name"); // p_name 필드(열) 의 데이터 꺼내기(1개 꺼낸거에서)
				System.out.println(name);
			}
		} catch (SQLException e) {
			System.out.println("SQLException: " + e.getMessage());
			System.out.println("SQLState: " + e.getSQLState());
		}
	}

	private void dbExecuteUpdate(String query) {
		try {
			int resultCount = st.executeUpdate(query);
			System.out.println("처리된 행 수:" + resultCount);
		} catch (SQLException e) {
			e.printStackTrace();
//			System.out.println("SQLException: " + e.getMessage());
//			System.out.println("SQLState: " + e.getSQLState());
		}
	}
}
