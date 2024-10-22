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

@Log4j
@RequestMapping("/guests/*")
@AllArgsConstructor
@Controller
public class testing {
	private GuestService service;

	@GetMapping("/getList")
	public void getList(Model model) {
		model.addAttribute("list", service.getList());
	}

	@GetMapping({ "/read", "/modift" })
	public void read(@RequestParam("bno") Long bno, Model model) {
		log.info("controller " + bno);
		model.addAttribute("read", service.read(bno));
	}

	@GetMapping("/del")
	public String del(@RequestParam("bno") Long bno) {
		log.info("controller " + bno);
		service.del(bno);
		return "redirect:/guest/getList";
	}

	@GetMapping("/write")
	public void write() {
	}

	@PostMapping("/write")
	public String write(GuestDto dto) {
		service.write(dto);
		return "redirect:/guest/getList";
	}

	@PostMapping("/modify")
	public String modify(GuestDto dto) {
		service.modify(dto);
		return "redirect:/guest/getList";
	}
}
