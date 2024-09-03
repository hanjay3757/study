package com.practice_board_SQL;

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
	Scanner sc = new Scanner(System.in);

	void run() {
		Display.showTitle();
		dbInit();
		loop: while (true) {
			dbPostCount();
			Display.showMainMenu();
			System.out.println("명령어 입력:");
			String cmd = sc.next();
			switch (cmd) {
			case "1":
				System.out.println("=====================================");
				System.out.println("================List=================");
				System.out.println("글 번호 /글 제목 /작성자 id /작성시간");

				try {
					result = st.executeQuery("select*from board");
					while (result.next()) {
						String no = result.getString("b_no");
						String Title = result.getString("b_title");
						String id = result.getString("b_id");
						String dateTime = result.getString("b_dateTime");
						System.out.println(no + " " + Title + " " + id + " " + dateTime);
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "2":
				System.out.println("READ??");
				String readNo = sc.next();
				try {
					result = st.executeQuery("select*from board where b_no" + readNo);
					result.next();
					String title = result.getString("b_title");
					String content = result.getString("b_text");
					System.out.println("글 제목: " + title);
					System.out.println("글 내용: " + content);

				} catch (SQLException e) {
					e.printStackTrace();
				}
				break;
			case "3":
				sc.nextLine();
				System.out.println("title");
				String title = sc.nextLine();
			case "4":
			}
		}

	}

	private void dbInit() {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:xx/name", "ID", "PASSWORD");
			st = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void dbExecuteUpdate(String query) {
		try {
			int resultCount = st.executeUpdate(query);
			System.out.println("처리된 행 수: " + resultCount);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	private void dbPostCount() {
		try {
			result = st.executeQuery("select count(*) from board");
			result.next();
			String count = result.getString("count(*)");
			System.out.println("글 수: " + count);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
}
