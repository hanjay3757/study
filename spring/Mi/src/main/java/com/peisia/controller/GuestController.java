package com.peisia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peisia.service.GuestService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/guest/*") // 프로젝트 루트 경로 이하 /guest 상위폴더로 진입 시 여기로 진입하게 됨.
@AllArgsConstructor // 필드 값을 매개변수로 하는 생성자를 스프링이 알아서 만들어 줌. 그리고 그런 형태의 생성자를 추가하면 스프링이 알아서 객체관리
					// 해줌(@Auto.. 처럼)
@Controller
public class GuestController {

//	위에 @AllArgsConstructor 이걸 쓰면
//	롬복라이브러리가 아래 코드를 자동으로 삽입해줌

	//
//	public GuestController(GuestService service){
//		this.service = service;
//	}

	private GuestService service;

	@GetMapping("/getList") // 프로젝트 루트 경로 이하 /guest/getList url 진입 시 여기로 진입하게 됨.
	public void getList(Model model) { // 매개변수에 Model m 식으로 작성하게 되면, 스프링이 알아서 모델 객체를 만들어서 넘겨줌.
		model.addAttribute("list", service.getList());
	} // 위 /getList 와 동일한 jsp파일을 염. 상위 경로 포함(/guest). 즉 PJ루트/guest/getList.jsp 파일을 염.
		// 그리고 이 파일은
		// PJ\src\main\webapp\WEB-INF\views\guest\getList.jsp
		// 에 만들어 놓으면 됨.

}