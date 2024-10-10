package com.peisia.jsp.board;

import java.util.ArrayList;

import com.peisia.jsp.board.dao.DaoBoard;
import com.peisia.jsp.board.dto.Dto;

public class BoardListProcessor {
	private DaoBoard dao;
	public ArrayList<Dto> posts;
	public int totalPage = 0;	//전체 페이지 수.	🐇페이징🐇
	public int currentPage = 0;	//현재 페이지 번호
	public BoardListProcessor(DaoBoard dao, String currentPage) {
		super();
		this.dao = dao;
		this.currentPage = Integer.parseInt(currentPage);
		this.totalPage = getPageCount();
		//todo
		//현재 페이지 번호와 전체 페이지 수를 기반으로
		getList();	//리스트 데이터 얻어오기

	}
	public void getList() {
		//시작 인덱스 계산해서 넘기기
		int startIndex = (currentPage-1)*Board.LIST_AMOUNT;
		posts = dao.selectList(startIndex);
		//todo test안함
	}

	/* 총 페이지 수 구하기 */
	public int getPageCount() {
		int totalPageCount = 0;
		int count = dao.selectPostCount();
		
		if(count % Board.LIST_AMOUNT == 0){		//case1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / Board.LIST_AMOUNT;
		}else{					//case2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / Board.LIST_AMOUNT + 1;
		}
		return totalPageCount;
	}	
	
	
	//페이징 블럭(페이징 처리랑 다 같이)
	
	
	//검색

	/* 총 페이지 수 구하기<검색> */
	public int getSearchPageCount(String word) {
		int totalPageCount = 0;
		int count = dao.selectSearchPostCount(word);
		
		if(count % Board.LIST_AMOUNT == 0){		//case1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / Board.LIST_AMOUNT;
		}else{					//case2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / Board.LIST_AMOUNT + 1;
		}
		return totalPageCount;
	}
	
	/* 글 리스트 객체 얻는 함수 */
	public ArrayList<Dto> getPosts() {
		return posts;
	}
}
