package com.peisia.mapper;

import java.util.List;

import com.peisia.dto.VideoVO;

public interface VideoMapper {
	public List<VideoVO> getList();

	public VideoVO read(Long vno);

	public void insert(VideoVO video);

	public void delete(Long vno);

	public void update(VideoVO video);
}
