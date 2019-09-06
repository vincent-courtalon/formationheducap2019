package com.edugroupe.springsecurityjpaform.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/boutique")
public class BoutiqueController {
	
	@GetMapping(value="")
	@ResponseBody
	public String hello() {
		return "<h2> bienvenue dans la boutique </h2>";
	}

}
