package com.peisia.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/weather")
@Controller

public class WeatherController {
	@RequestMapping("/w")
	public void w() {
		//// 우리나라 공공 api ////
		// 인코딩 인증키
		String API_KEY = "Mt91qMdx8W4yKPihsvFhagDx1Fz5E68okUvCdbu4Lze3%2BeCj63Lr%2BPTVvABNixLRvHCN%2Fcl5djCraF%2BD8engAg%3D%3D";
		String API_URL = "http://apis.data.go.kr/1360000/AsosDalyInfoService/getWthrDataList?numOfRows=10&pageNo=1&dateCd=DAY&startDt=20230220&endDt=20230220&stnIds=108&dataCd=ASOS&dataType=JSON&serviceKey="
				+ API_KEY;
		// * 주의 * https 아님 http 임. https 는 인증관련 복잡한 처리를 해야함.
		RestTemplate restTemplate = new RestTemplate();
		URI uri = null; // java.net.URI 임포트 하셈
		String s = restTemplate.getForObject(uri, String.class); //
		log.info("====== 우리나라 날씨 잘 나오나? " + s);
		//// **** 중요 **** uri
		try {
			uri = new URI(API_URL); // URI 클래스는 URL에 대한 유효성 검사, 구성요소 추출, 보안(특수문자, 공백 처리 등)을 도와줌
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

	}
}
