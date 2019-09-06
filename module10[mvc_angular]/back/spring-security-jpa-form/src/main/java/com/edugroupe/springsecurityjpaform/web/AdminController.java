package com.edugroupe.springsecurityjpaform.web;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edugroupe.springsecurityjpaform.metier.User;
import com.edugroupe.springsecurityjpaform.repositories.RoleRespository;
import com.edugroupe.springsecurityjpaform.repositories.UserRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRespository roleRespository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	@GetMapping(value = "/hello")
	@ResponseBody
	public String hello() {
		return "<h2> bienveneue dans la matrice</h2>";
	}
	
	@GetMapping(value="/createuser", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<User> createuser(@RequestParam("username")String username,
							@RequestParam("password") String password) {
		User u = userRepository.findByUsername(username);
		if (u != null)
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		u = new User(0, username, passwordEncoder.encode(password), true);
		u.setRoles(new HashSet<>());
		u.getRoles().add(roleRespository.findByRoleName("ROLE_USER"));
		u = userRepository.save(u);
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}
}
