package com.peisia.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peisia.dto.GuestDto;
import com.peisia.mapper.GuestMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// 로그 출력을 위한 Log4j 어노테이션
@Log4j
// 이 클래스는 서비스 계층에 해당하는 클래스임을 나타내는 어노테이션
@Service
public class GuestServiceImpl implements GuestService {
//클래스가 추상

	// GuestMapper를 자동으로 주입하기 위한 어노테이션
	@Setter(onMethod_ = @Autowired)
	private GuestMapper mapper; // 데이터베이스와의 상호작용을 담당하는 Mapper

	// 방명록 목록을 조회하는 메서드
	@Override
	public ArrayList<GuestDto> getList() {
		log.info("비지니스 계층==========="); // 로깅: 서비스 계층에서 호출됨을 알림
		return mapper.getList(); // GuestMapper의 getList 메서드를 호출하여 방명록 목록을 가져옴
	}

	// 특정 게시글을 조회하는 메서드
	@Override
	public GuestDto read(long bno) {
		return mapper.read(bno); // GuestMapper의 read 메서드를 호출하여 특정 게시글을 가져옴
	}

	// 특정 게시글을 삭제하는 메서드
	@Override
	public void del(long bno) {
		mapper.del(bno); // GuestMapper의 del 메서드를 호출하여 게시글을 삭제
//		mapper.del(Integer.parseInt(bno)); // GuestMapper의 del 메서드를 호출하여 게시글을 삭제
	}

	// 새 게시글을 작성하는 메서드
	@Override
	public void write(GuestDto dto) {
		mapper.write(dto); // GuestMapper의 write 메서드를 호출하여 새로운 방명록을 DB에 추가
	}

	// 게시글을 수정하는 메서드
	@Override
	public void modify(GuestDto dto) {
		mapper.modify(dto); // GuestMapper의 modify 메서드를 호출하여 기존 게시글을 수정
	}
}
