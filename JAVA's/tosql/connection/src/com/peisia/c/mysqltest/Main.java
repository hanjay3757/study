package com.peisia.c.mysqltest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;

		Scanner sc = new Scanner(System.in);
		System.out.println("글제목을 입력해주세요:");
		String title = sc.next();
		System.out.println("글내용을 입력해주세요:");
		String content = sc.next();
		System.out.println("작성자 id를 입력해주세요:");
		String id = sc.next();

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			st = con.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			String sql = String.format(
					"insert into board (b_title,b_id,b_datetime,b_text,b_hit) values ('%s','%s',now(),'%s',0);",
					title, id, content);

			System.out.println("sql 로그:" + sql);
			st.executeUpdate(sql);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}