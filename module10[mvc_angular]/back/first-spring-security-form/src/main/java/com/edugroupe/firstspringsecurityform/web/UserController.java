package com.edugroupe.firstspringsecurityform.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping(value = "")
	@ResponseBody
	public String hello() {
		return "<h2>bienvenue user</h2>";
	}
}
