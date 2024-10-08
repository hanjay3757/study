package com.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Dao extends Da {
	public void reg(String id, String pw) {
		super.connect();
		String sql = String.format("insert id from member where id  ='%s' and pw = '%s'", id, pw);
		super.update(sql);
		super.close();
	}

	public String login(String id, String pw) {
		super.connect();
		String sql = String.format("select id from member where id ='%s' and pw='%s'", id, pw);
		System.out.println("======sql로그" + sql);
		String loginId = null;
		try {
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				loginId = rs.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("s");
		super.close();
		return loginId;
	}

	public void del(String no) {
		super.connect();
		String sql = String.format("delete from %s where b_no= %s", Db.TABLE_PS_BOARD_FREE);
		super.update(sql);
		super.close();
	}

	public void write(Dto d) {
		super.connect();
		String sql = String.format("insert into %s(b_title,b_id,b_text)values('%s','%s','%s')", Db.TABLE_PS_BOARD_FREE,
				d.title, d.id, d.text);
		super.update(sql);
		super.close();
	}

	public Dto read(String no) {
		super.connect();
		Dto post = null;
		try {
			String sql = String.format("select * from %s where b_no=%s", Db.TABLE_PS_BOARD_FREE, no);
			System.out.println("sql" + sql);
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			post = new Dto(rs.getString("B_NO"), rs.getString("B_TITLE"), rs.getString("B_ID"),
					rs.getString("B_DATETIME"), rs.getString("B_HIT"), rs.getString("B_TEXT"),
					rs.getString("B_REPLY_COUNT"), rs.getString("B_REPLY_ORI"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return post;
	}

//{} 체크
	public ArrayList<Dto> list(String page) {
		super.connect();
		ArrayList<Dto> posts = new ArrayList<>();
		try {
			int startIndex = ((Integer.parseInt(page)) - 1) * Board.LIST_AMOUNT;
			String sql = String.format("select * from %s limit %s,%s", Db.TABLE_PS_BOARD_FREE, startIndex,
					Board.LIST_AMOUNT);
			System.out.println("sql:" + sql);
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				posts.add(new Dto(rs.getString("B_NO"), rs.getString("B_TITLE"), rs.getString("B_ID"),
						rs.getString("B_DATETIME"), rs.getString("B_HIT"), rs.getString("B_TEXT"),
						rs.getString("B_REPLY_COUNT"), rs.getString("B_REPLY_ORI")));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return posts;
	}

	public void edit(Dto d, String no) {
		super.connect();
		String sql = String.format("update %s set b_title = '%s',b_text='%s' where b_no=%s", Db.TABLE_PS_BOARD_FREE,
				d.title, d.text, no);
		super.update(sql);
		super.close();
	}

	public int getPostCount() {
		int count = 0;
		super.connect();
		try {
			String sql = String.format("select count(*) from %s", Db.TABLE_PS_BOARD_FREE);
			System.out.println("count(*)");
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			count = rs.getInt("count(*)");
		} catch (Exception e) {
			e.printStackTrace();
		}
		super.close();
		return count;
	}

	public int getSearchPostCount(String word) {
		int count = 0;
		super.connect();
		try {
			String sql = String.format("select count(*) from %s where b_title like '%%%s%%'",Db.TABLE_PS_BOARD_FREE,word);
		}
	}
}
