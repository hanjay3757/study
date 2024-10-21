package com.peisia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.peisia.dto.GuestDto;
import com.peisia.service.GuestService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Log4j // 로그 출력을 위한 어노테이션
@RequestMapping("/guest/*") // /guest 하위 경로로 요청이 들어오면 이 컨트롤러로 매핑
@AllArgsConstructor // 클래스의 모든 필드를 매개변수로 하는 생성자를 자동으로 생성
@Controller // 이 클래스가 스프링의 컨트롤러 역할을 함을 정의
public class GuestController {

	private GuestService service; // GuestService 객체를 필드로 선언 (서비스 로직을 호출)

	// 'getList' 요청을 처리하는 메서드
	@GetMapping("/getList") // URL /guest/getList 로 GET 요청이 들어오면 실행
	public void getList(Model model) { // Model 객체를 사용하여 데이터를 뷰로 전달
		// GuestService의 getList() 메서드를 호출하여 방명록 목록을 가져오고,
		// 이를 "list"라는 이름으로 뷰에 전달
		model.addAttribute("list", service.getList());
	}
	// 이 메서드는 /guest/getList.jsp 파일로 데이터를 전달하고 렌더링한다.

	// 글 조회 및 수정 페이지를 처리하는 메서드 (공통 URL 사용)
	@GetMapping({ "/read", "/modify" }) // URL /guest/read 또는 /guest/modify로 GET 요청이 들어오면 실행
	public void read(@RequestParam("bno") Long bno, Model model) {
		// URL에서 전달받은 'bno'를 사용하여 해당 방명록을 조회
		log.info("컨트롤러 ==== 글번호 ===============" + bno);
		// 방명록 데이터를 'read'라는 이름으로 뷰에 전달
		model.addAttribute("read", service.read(bno));
		// 수정하러가는거
	}

	// 글 삭제 요청을 처리하는 메서드
	@GetMapping("/del") // URL /guest/del로 GET 요청이 들어오면 실행
	public String del(@RequestParam("btext") String bno) {
		// 'bno'를 사용하여 해당 방명록을 삭제
		log.info("컨트롤러 ==== 글번호 ===============" + bno);
		service.del(bno);
		service.del(bno);
		// 삭제 후 /guest/getList로 리다이렉트하여 목록 페이지로 이동
		return "redirect:/guest/getList";
	}

	// 글쓰기 요청을 처리하는 메서드 (POST 방식)
	@PostMapping("/write") // URL /guest/write로 POST 요청이 들어오면 실행
	public String write(GuestDto dto) {
		// 폼에서 전달된 GuestDto 객체를 사용하여 글을 작성
		service.write(dto);
		// 작성 후 /guest/getList로 리다이렉트하여 목록 페이지로 이동
		return "redirect:/guest/getList";
	}

	// 글쓰기 화면을 보여주는 메서드 (GET 방식)
	@GetMapping("/write") // URL /guest/write로 GET 요청이 들어오면 실행
	public void write() {
		// 이 메서드는 특별히 처리할 로직이 없고, 단순히 글쓰기 화면을 보여주는 역할을 한다.
		// /guest/write.jsp 페이지가 뷰로 렌더링된다.
	}

	// 글 수정 요청을 처리하는 메서드 (POST 방식)
	@PostMapping("/modify") // URL /guest/modify로 POST 요청이 들어오면 실행
	public String modify(GuestDto dto) {
		// 수정할 데이터를 담고 있는 GuestDto 객체를 받아서 수정 처리
		service.modify(dto);
		// 수정 후 /guest/getList로 리다이렉트하여 목록 페이지로 이동
		return "redirect:/guest/getList";
		// 처리 get의 경우 url에 등재가 되기 때문에 보안상 이슈가 있기에 post로만 사용가능 근데 공부중이면 get으로 하는게 더 나을지도
	}

}
