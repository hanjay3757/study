package com.peisia.jsp.board.service;

import com.peisia.jsp.board.BoardListProcessor;
import com.peisia.jsp.board.dao.DaoBoard;
import com.peisia.jsp.board.dto.Dto;

//🐇서비스🐇
public class ServiceBoard {
	DaoBoard dao;
	public ServiceBoard() {
		dao = new DaoBoard();
	}
	public void del(String no) {
		dao.del(no);
	}
	public void write(Dto d) {
		dao.insert(d);
	}
	public Dto read(String no) {
		return dao.selectPost(no);
	}
	public BoardListProcessor list(String currentPage) {
		if(currentPage==null) {
			currentPage="1";
		}
		BoardListProcessor blp = new BoardListProcessor(dao,currentPage);
		return blp;
	}
	public void edit(Dto d,String no) {
		dao.update(d, no);
	}
}