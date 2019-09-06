package com.edugroupe.springsecurityjpaform.web;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/boutique")
public class BoutiqueController {
	
	@GetMapping(value="/hello")
	@ResponseBody
	@PreAuthorize(value = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public String hello() {
		return "<h2> bienvenue dans la boutique </h2>";
	}

	@GetMapping(value="/Platinumhello")
	@ResponseBody
	@PreAuthorize(value = "hasRole('ROLE_ADMIN')")
	public String platinumhello() {
		return "<h2> bienvenue dans la boutique, cher client adoré </h2>";
	}

}
