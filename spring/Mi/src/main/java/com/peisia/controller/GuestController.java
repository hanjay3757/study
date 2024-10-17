package com.peisia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	@GetMapping("/read")
	public void read(@RequestParam("bno") Long bno, Model model) {
		log.info("컨트롤러 ==== 글번호 ===============" + bno);
		// 나오는지 확인 용
		model.addAttribute("read", service.read(bno));
	}
////Model의 주요 기능:
//	컨트롤러에서 데이터를 뷰로 전달: 컨트롤러에서 비즈니스 로직을 처리한 후, 그 결과를 뷰로 전달하여 화면에 표시할 수 있도록 합니다.
//	데이터를 뷰 템플릿에 전달: 예를 들어, JSP나 Thymeleaf에서는 Model에 담긴 데이터를 가져와서 동적으로 HTML을 생성할 수 있습니다.
//	addAttribute() 메서드: 컨트롤러에서 뷰로 데이터를 전달할 때 사용되는 메서드로, 특정 이름의 속성(attribute)을 모델에 추가합니다.
	// @override 쪽꺼 실행
}