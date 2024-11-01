
package com.peisia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.peisia.service.ViService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/video/*")
@Controller
public class MouseMoveController {
	@Setter(onMethod_ = @Autowired)
	private ViService vserv;

	@GetMapping("/getVideo")
	public void getVideo(Model model) {
		log.info("비디오 데이터 가져오기");
		model.addAttribute("videoList", vserv.getVideoList());
	}
}
