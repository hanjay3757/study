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
}
