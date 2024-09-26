package com.peisia.jsp.board;

import java.util.ArrayList;

import com.peisia.jsp.board.dao.DaoBoard;
import com.peisia.jsp.board.dto.Dto;

public class BoardListProcessor {
	private DaoBoard dao;
	public String category;
	public ArrayList<Dto> posts;
	public int totalPage = 0;	//전체 페이지 수.	🐇페이징🐇
	public int currentPage = 0;	//현재 페이지 번호
	public String word;			//검색어(있으면)
	
	//🐿️🐿️🐿️{블럭 처리 - 1/9}.블럭 총 갯수 구하기🐿️🐿️🐿️//
	int totalBlock = 0;	//블럭 총 갯수
	//🐿️🐿️🐿️{블럭 처리 - 2/9}.현재 블럭 번호 구하기🐿️🐿️🐿️//
	int currentBlockNo = 0;
	//🐿️🐿️🐿️{블럭 처리 - 3/9}.블럭 시작 페이지 번호 구하기🐿️🐿️🐿️//
	int	blockStartNo = 0;
	//🐿️🐿️🐿️{블럭 처리 - 4/9}.블럭 페이지 끝 번호 구하기🐿️🐿️🐿️//
	int blockEndNo = 0;	
	//🐿️🐿️🐿️{블럭 처리 - 5/9}.(이전/다음) 관련 초기화 처리🐿️🐿️🐿️ 
	//🐿️🐿️🐿️: 현재 블럭에서 이전/다음을 눌렀을 때 현재 페이지 값을 변경할 때 넣을 값을 일단 초기화하기🐿️🐿️🐿️//
	//이전 다음이란? ex.    <이전 [1][2][3][4][5] 다음> <<요런거	
	int prevPage = 0;
	int nextPage = 0;
	//🐿️🐿️🐿️{블럭 처리 - 6/9}.(이전/다음) 관련 계산 등 처리🐿️🐿️🐿️
	boolean hasPrev = true;	//이전 블럭 가기 가능 여부 저장값 초기화.
	boolean hasNext = true;	//다음 블럭 가기 가능 여부 저장값 초기화.
	
	public BoardListProcessor(DaoBoard dao, String category, String currentPage, String word) {
		super();
		this.dao = dao;
		this.category = category;
		this.currentPage = Integer.parseInt(currentPage);
		this.word = word;
		if(word==null) {
			this.totalPage = getPageCount();
			getList();	//현재 페이지 번호와 전체 페이지 수를 기반으로 리스트 데이터 얻어오기
		} else {
			this.totalPage = getPageCount(word);
			getList(word);	// <검색> 현재 페이지 번호와 전체 페이지 수를 기반으로 리스트 데이터 얻어오기
		}
		
		//🐿️🐿️🐿️{블럭 처리 - 1/9}.블럭 총 갯수 구하기🐿️🐿️🐿️//
		//블럭이란? ex. [1][2][3][4][5] ... [6][7][8][9][10] << 이렇게 게시판 하단에 페이지 리스트들이 묶인 단위(1~5:1블럭, 6~10:2블럭)
		//블럭 총 갯수 = 페이지 수 / 블럭 당 페이지 수 << 결과에 올림 처리
		//참고: Math. 함수들 : 반올림(round), 올림(ceil), 버림(floor) 		
		totalBlock = (int)Math.ceil((double) totalPage / Board.PAGE_LINK_AMOUNT);
		//🐿️🐿️🐿️{블럭 처리 - 2/9}.현재 블럭 번호 구하기🐿️🐿️🐿️//
		//현재 블럭 번호 = 현재 페이지 번호 / 블럭 당 페이지 수 << 결과에 올림 처리		
		currentBlockNo = (int)Math.ceil((double)this.currentPage / Board.PAGE_LINK_AMOUNT);
		//🐿️🐿️🐿️{블럭 처리 - 3/9}.블럭 시작 페이지 번호 구하기🐿️🐿️🐿️//
		//블럭 시작 페이지 번호 = (현재 블럭 번호 - 1) * 블럭 당 페이지 수 + 1		
		blockStartNo = (currentBlockNo - 1) * Board.PAGE_LINK_AMOUNT + 1;
		//🐿️🐿️🐿️{블럭 처리 - 4/9}.블럭 페이지 끝 번호 구하기🐿️🐿️🐿️//
		//블럭 페이지 끝 번호 = 현재 블럭 번호 * 블럭 당 페이지 수		
		blockEndNo = currentBlockNo * Board.PAGE_LINK_AMOUNT;		
		if(blockEndNo > totalPage) {	//만약 블럭 마지막 페이지 번호가 전체 페이지 마지막 번호보다 큰경우에는.. 
			blockEndNo = totalPage;		//..블럭 마지막 페이지번호를 페이지 마지막 번호로 저장하는 예외 처리 하기
		}	
		
		//🐿️🐿️🐿️{블럭 처리 - 6/9}.(이전/다음) 관련 계산 등 처리🐿️🐿️🐿️ 
		//🐿️🐿️🐿️: 현재 블럭에서 이전/다음이 가능한지 계산하고 가능 여부를 저장해두기🐿️🐿️🐿️//
		if(currentBlockNo == 1){	//현재 블럭이 1번 블럭이면
			hasPrev = false;		//이전 블럭 가기 불가능
		} else {					//현재 블럭이 1번 블럭이 아니면
			hasPrev = true;			//이전 블럭 가기 가능
			//🐿️🐿️🐿️: 이전 블럭 이동 시 몇 페이지로 이동할지 정하기🐿️🐿️🐿️//
			//이전 블럭의 마지막 페이지로 이동하게 하면 됨(보통 이렇게 처리하니까)
			//공식 : (현재 블럭 번호 - 1) * 블럭 당 페이지 수
			prevPage = (currentBlockNo - 1 ) * Board.PAGE_LINK_AMOUNT;
		}
		if(currentBlockNo < totalBlock ){	//현재 블럭이 마지막 블럭보다 작으면
			hasNext = true;					//다음 블럭 가기 가능
			//🐿️🐿️🐿️: 다음 블럭 이동 시 몇 페이지로 이동할지 정하기🐿️🐿️🐿️//
			//다음 블럭의 첫 페이지로 이동하게 하면 됨(보통 이렇게 처리하니까)
			//공식 : 현재 블럭 번호 * 블럭 당 페이지 수 + 1
			nextPage = currentBlockNo * Board.PAGE_LINK_AMOUNT + 1;		
		} else {							//현재 블럭이 마지막 블럭보다 같거나 크면(큰값이 오면 안되겠지만)
			hasNext = false;				//다음 블럭 가기 불가능
		}		
	}
	public void getList() {
		int startIndex = (currentPage-1)*Board.LIST_AMOUNT;	//시작 인덱스 계산해서 넘기기
		posts = dao.selectList(category, startIndex);
	}
	public void getList(String word) {
		int startIndex = (currentPage-1)*Board.LIST_AMOUNT;	//시작 인덱스 계산해서 넘기기
		posts = dao.selectList(category, startIndex,word);
	}
	/* 총 페이지 수 구하기 */
	public int getPageCount() {
		int totalPageCount = 0;
		int count = dao.selectPostCount(category);
		if(count % Board.LIST_AMOUNT == 0){		//case1. 나머지가 없이 딱 떨어지는 경우
			totalPageCount = count / Board.LIST_AMOUNT;
		}else{					//case2. 나머지가 있어서 짜투리 페이지가 필요한 경우
			totalPageCount = count / Board.LIST_AMOUNT + 1;
		}
		return totalPageCount;
	}	
	/* <검색> 총 페이지 수 구하기 */
	public int getPageCount(String word) {
		int totalPageCount = 0;
		int count = dao.selectSearchPostCount(category, word);
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
	/* 페이지 리스트들을 출력하기 위한 html을 리턴 */
	public String getHtmlPageList() {
		String html="";

		//🐿️🐿️🐿️{블럭 처리 - 7/9}.(이전/다음)의 (이전) 처리🐿️🐿️🐿️
		//🐿️🐿️🐿️: 이전 블럭 이동이 가능하면 미리 계산한 이전 블록 이동 시 이동 할 페이지번호를 랑크에 전달하기🐿️🐿️🐿️//
		if(hasPrev){
			if(word==null) {
				html=html+String.format("<a href='/board/list?category=%s&page=%d'>🐿️이전블럭가기🐿️</a>",category,prevPage);
			}else {
				html=html+String.format("<a href='/board/list?category=%s&page=%d&word=%s'>🐿️이전블럭가기🐿️</a>",category,prevPage,word);
			}
		}		
		
		//🐿️🐿️🐿️{블럭 처리 - 8/9}.기존의 제한 없던 페이지리스트 나열을 ex.[1][2][3].....[n] 블럭 적용하기🐿️🐿️🐿️
		//🐿️🐿️🐿️현재 블럭의 페이지 시작번호와 끝번호를 이용하여 반복문의 시작값 끝값으로 하고 이 값을 페이지 번호로 출력하기🐿️🐿️🐿️		
		for(int i=blockStartNo;i<=blockEndNo;i++){	// 	<< 이렇게 바꿈
			if(word==null) {
				html = html + String.format("<a href='/board/list?category=%s&page=%d'>%d</a>&nbsp;&nbsp;",category,i,i);
			}else {
				html = html + String.format("<a href='/board/list?category=%s&page=%d&word=%s'>%d</a>&nbsp;&nbsp;",category,i,word,i);
			}
		}
		
		//🐿️🐿️🐿️{블럭 처리 - 9/9}.(이전/다음)의 (다음) 처리🐿️🐿️🐿️
		//🐿️🐿️🐿️: 다음 블럭 이동이 가능하면 미리 계산한 이전 블록 이동 시 이동 할 페이지번호를 랑크에 전달하기🐿️🐿️🐿️//
		if(hasNext){
			if(word==null) {
				html=html+String.format("<a href='/board/list?category=%s&page=%d'>🐿️다음블럭가기🐿️</a>",category,nextPage);
			}else {
				html=html+String.format("<a href='/board/list?category=%s&page=%d&word=%s'>🐿️다음블럭가기🐿️</a>",category,nextPage,word);
			}			
			
		}		
		return html;
	}
}