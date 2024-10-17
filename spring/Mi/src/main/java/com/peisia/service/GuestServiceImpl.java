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
	private GuestMapper mapper;

	@Override
	public ArrayList<GuestDto> getList() {
		log.info("비지니스 계층===========");
		return mapper.getList();
	}

	@Override
	public GuestDto read(long bno) {
		return mapper.read(bno);
	}

	@Override
	public void del(long bno) {
		mapper.del(bno);
	}

}
