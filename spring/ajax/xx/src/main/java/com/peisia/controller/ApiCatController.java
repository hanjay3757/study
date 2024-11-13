package com.peisia.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.peisia.spring.dto.Cat;

@RequestMapping("/api/*")
@RestController
public class ApiCatController {
	@GetMapping("/catOne")
	public Cat getCatOne() {
		Cat cat = new Cat();
		cat.setName("야옹이");
		cat.setAge(5);

		// 이렇게 객체를 리턴하면 json 이 객체 구조로 해서 자동으로 출력됨.
		// 즉, 이 주소로 api 요청을 하면
		// 고양이 이름, 나이 json을 받아갈 수 있게 되는 것.
		return cat;
	}

	@GetMapping("/catTwo")
	public Cat getCatTwo() {
		Cat cat = new Cat();
		cat.setName("떼껄룩");
		cat.setAge(10);
		return cat;
	}
}