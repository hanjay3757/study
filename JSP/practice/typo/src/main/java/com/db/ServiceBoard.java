package com.db;

public class ServiceBoard {
	DaoBoard dao;

	public ServiceBoard() {
		dao = new DaoBoard();
	}

	public void del(String no) {
		dao.del(no);
	}
}
