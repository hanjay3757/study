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
		
		System.out.println("게시글 작성 프로그램");
		System.out.println("==================");
		
		System.out.println("제목을 입력하세요:");
		String title = sc.nextLine();
		
		System.out.println("내용을 입력하세요:");
		String content = sc.nextLine();
		
		System.out.println("작성자 ID를 입력하세요:");
		String id = sc.nextLine();

		try {
			// MySQL 데이터베이스 연결
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/my_cat", "root", "root");
			st = con.createStatement();
			
			// SQL 쿼리 생성 및 실행
			String sql = String.format(
					"insert into board (b_title,b_id,b_datetime,b_text,b_hit) values ('%s','%s',now(),'%s',0);",
					title, id, content);

			System.out.println("실행될 SQL 쿼리: " + sql);
			
			int result = st.executeUpdate(sql);
			if(result > 0) {
				System.out.println("게시글이 성공적으로 등록되었습니다.");
			}

		} catch (Exception e) {
			System.out.println("오류가 발생했습니다:");
			e.printStackTrace();
		} finally {
			try {
				if(st != null) st.close();
				if(con != null) con.close();
				if(sc != null) sc.close();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}