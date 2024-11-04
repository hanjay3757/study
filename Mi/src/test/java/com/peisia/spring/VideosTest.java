package com.peisia.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.peisia.dto.VideoVO;
import com.peisia.service.ViService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class VideosTest {

	@Setter(onMethod_ = @Autowired)
	private ViService service;

	@Test
	public void testService() {
		log.info("========== 서비스 테스트 ===========");
		log.info(service);
	}

	@Test
	public void testList() {
		log.info("========== 비디오 목록 가져오기 테스트 ===========");
	}

	@Test
	public void testRead() {
		log.info("========== 단일 비디오 가져오기 테스트 ===========");
	}

	@Test
	public void testRegister() {
		log.info("========== 비디오 저장 테스트 ===========");
		VideoVO video = new VideoVO();
		video.setTitle("테스트 비디오");
		video.setContent("테스트 내용");
		video.setWriter("테스터");
	}

	@Test
	public void testRemove() {
		log.info("========== 비디오 삭제 테스트 ===========");
	}

	@Test
	public void testModify() {
		log.info("========== 비디오 수정 테스트 ===========");
	}
}
