package com.edugroupe.springsecurityjpaform.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/public")
public class OpenController {
	
	@GetMapping(value="")
	@ResponseBody
	public String hello () {
		return "<h2>bonjour dans public</h2>";
	}
	
}
