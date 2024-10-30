package com.peisia.service;

import java.util.ArrayList;

import com.peisia.dto.GuestDto;

public interface GuestService {
	public ArrayList<GuestDto> getList();

	public GuestDto read(long bno);

	public void del(long bno);

	public void write(GuestDto dto);

	public void modify(GuestDto dto);
}
