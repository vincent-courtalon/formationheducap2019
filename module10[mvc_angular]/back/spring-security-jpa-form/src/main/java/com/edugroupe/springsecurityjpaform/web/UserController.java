package com.edugroupe.springsecurityjpaform.web;

import java.security.Principal;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/user")
@Controller
public class UserController {

	@GetMapping(value = "/hello")
	@ResponseBody
	public String hello(Principal principal) {		
		return "<h2>bienvenue "+ principal.getName() +"</h2>";
	}
	
	@GetMapping(value = "/bonjour")
	@ResponseBody
	public String bonjour(Authentication authentication) {
		//UserDetails ud = (UserDetails)authentication.getDetails();
		System.out.println("nous avons " + authentication.getAuthorities().size() +" roles");
		return "<h2>bienvenue "+ authentication.getName() +"</h2>";
	}
}
