package com.db;

public class Dao extends Da {
	public void reg(String id, String pw) {
		super.connect();
		String sql = String.format("insert id from member where id  ='%s' and pw = '%s'", id, pw);
		super.update(sql);
		super.close();
	}
}
