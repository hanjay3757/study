package com.peisia.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.peisia.dto.GuestDto;
import com.peisia.mapper.GuestMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Service
//@AllArgsConstructor
public class GuestServiceImpl implements GuestService {

	@Setter(onMethod_ = @Autowired)
	// setter를 입력했을때 자동으로 lombok을 따라가는다는 거
	private GuestMapper mapper;

	// 주입
	//
	@Override
	public ArrayList<GuestDto> getList() {
		log.info("비지니스 계층===========");
		return mapper.getList();
	}

}
